package  org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.TempUserDAO;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.dao.UserDAOimpl;
import org.teamwe.carrent.entity.TempUser;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.RegisterService;

import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.hash.BCryptMd5Hash;
import org.teamwe.carrent.utils.hash.Hash;
import org.teamwe.carrent.utils.serviceUtil.EmailTest;
import org.teamwe.carrent.utils.serviceUtil.ResisTest;

import java.security.NoSuchAlgorithmException;


/*
 3  type        int     非空  0 普通用户 1 技师 2 管理员
 4  status         int      非空  0 记录逻辑存在 1 记录逻辑删除
 5  isvalidated    int      非空  0用户未验证   1 用户已经验证
    12 isauthorized int                    1技师验证成功 0技师未验证
     13 balance   bigint  允许空值          账户余额
 */

@Service
public class RegisterServiceImpl implements RegisterService {
    private  static  int credit = 1000;
    private  static  int isvalidated = 0;
    private  static  int status = 0;
    private  static  int points = 1000;

    @Value("${project.controller.allowed.origins}")
    public String host;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private Hash hashh;

//    {
//        try {
//            hashh = new BCryptMd5Hash();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    //@Autowired
    private TempUserDAO tempUserDAO;
    @Autowired
    private ResisTest resisTest;

    public RegisterServiceImpl() throws NoSuchAlgorithmException {
    }

    /**
     * @param name     注册用户名
     * @param email    注册邮箱
     * @param password 密码, 未哈希过
     * @param license  驾照条码
     * @param head     头像哈希值
     * @param type     用户类型
     * @param phone    电话， 可选
     * @return 空或者null表示注册成功， 否则注册失败
     *
     */
    @Override
    public String register(String name, String email, String password, String license, String head, int type, String phone) {

        User user_temp = userDao.Get_userByEmial(email);

        if(user_temp != null&&user_temp.getStatus()==1){//表示该用户物理存在但是逻辑不存在，再注册时直接改变为逻辑存在
            user_temp.setStatus(0);
            userDao.Update_user(user_temp);

            System.out.println("表示该用户物理存在但是逻辑不存在，再注册时直接改变为逻辑存在");
            return null;
        }
        if(user_temp != null&&user_temp.getStatus()==0){//用户逻辑存在

            System.out.println("用户逻辑存在");
            return "该邮箱已经注册！";
        }

        if(resisTest.isExist(email)){
            System.out.println("该邮箱已经申请注册，请在规定时间内完成激活！");
            return "该邮箱已经申请注册，请在规定时间内完成激活！";
        }

        String password_hash = hashh.hashPassword(password);


       User user = new User( email,password_hash, name,license, head, phone,type,credit,isvalidated,status,points,0,0);
        System.out.println(user.toString());
        int result1 = resisTest.insertUser(user," ");//将用户注册信息插入Redis

        String random_string = hashh.genRandomChar(20); //随机生成hash值用来激活用户
        System.out.println("随机字符："+random_string);

        String context =
                "<html lang='zh-CN'><head ><meta charset='utf-8'>"
                        + "</head><body>欢迎注册敝舆汽车租赁网站"
                        + "<a href='"+host+"/active.html?id="+random_string+"'>【点击链接完成注册激活】</a></body></html>"+random_string;

        String hash_random_string = hashh.hashNormal(random_string);
       if(!EmailTest.sendMail(email, "激活账号", context)){
           System.out.println("邮件发送失败");
           return "邮件发送失败";
       }


        System.out.println("随机字符的hash："+hash_random_string);


        resisTest.insertUser(user,hash_random_string);//将用户信息插入redis，key为随机字符的hash
        resisTest.insertString(email," ");//将用户email插入redis，用来表明用户已经在注册过程中

        System.out.println("插入到redis成功");

        return null;


    }

    /**
     * @param hash 激活所用哈希
     * @return 空或者null表示注册成功， 否则注册失败
     */
    @Override
    public String active(String hash) {
        String hash_random = hashh.hashNormal(hash);

        if(!resisTest.isExist(hash_random)){
            System.out.println("随机字符的hash："+hash_random);
            System.out.println("未在规定时间内激活，请重新注册！");
            return "未在规定时间内激活，请重新注册！";
        }

        User user = resisTest.getUser(hash_random);//用随机字符的hash值取出user信息

        System.out.println("从redis中取user信息："+user.toString());

        if(userDao.Add_usr(user) < 0){
            System.out.println("数据库异常，无法插入数据!");
            return "数据库异常，无法插入数据!";
        }

        resisTest.delete(user.getEmail());
        resisTest.delete(hash_random);

        System.out.println("激活成功");


        return null;
    }

}
