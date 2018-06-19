package  org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.TempUserDAO;
import org.teamwe.carrent.dao.UserDAO;
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
 */

//@Service
public class RegisterServiceImpl implements RegisterService {
    private  static  int credit = 1000;
    private  static  int isvalidated = 0;
    private  static  int status = 0;
    private  static  int points = 1000;

    //@Autowired
    private UserDAO userDao;

    //@Autowired
    private Hash hashh;

    {
        try {
            hashh = new BCryptMd5Hash();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //@Autowired
    private TempUserDAO tempUserDAO;

    private ResisTest resisTest = new ResisTest();

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


        if(userDao.Get_userByEmial(email) != null){
            return "该邮箱已被注册！";
        }

        String password_hash = hashh.hashPassword(password);



       User user = new User( email,password_hash, name,license, head, phone,type,credit,isvalidated,status,points,0,100);
        System.out.println(user.toString());
        int result1 = resisTest.insertUser(user);//将用户注册信息插入Redis

        if(result1 == ReturnStatus.SUCCESS){//插入注册信息成功后，给用户发送邮件

            String random_string = hashh.genRandomChar(20); //随机生成hash值用来激活用户
            System.out.println(random_string);
            long beginTime = System.currentTimeMillis();//发送邮件时间
            long endTime = beginTime + 1800000;//验证时限为30分钟，30*60*1000
        }



        return "数据库异常，请检查服务器！";
    }

    /**
     * @param hash 激活所用哈希
     * @return 空或者null表示注册成功， 否则注册失败
     */
    @Override
    public String active(String hash) {



        return "规定时间内未激活，请重新注册";
    }

}
