package org.teamwe.carrent.utils.serviceUtil;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.utils.ReturnStatus;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class ResisTest {

    @Resource
    private RedisTemplate<String,User> redisTemplate;//redis封装Java对象



    public static  String host = null;

    public  static  String password = null;

    @Autowired
    public ResisTest(@Value("${spring.redis.host}") String host,@Value("${spring.redis.password}") String password){
        ResisTest.host = host;
        ResisTest.password = password;
    }


    public void saveUserO(String key, User user){

        redisTemplate.opsForValue().set(key,user,3000,TimeUnit.SECONDS);//插入整个user对象，key为随机字符的hash值
        System.out.println("c存储user"+user.toString());

    }

    public  User getUserO(String key){
        User user = null;
         user = redisTemplate.opsForValue().get(key);//获得user对象
        System.out.println("取出user"+user.toString());

        return user;
    }
//连接到Redis服务器
    public static Jedis jdeisConnection() {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis(host);
        jedis.auth(password);
       return jedis;
    }
//关闭连接
    public static void disConnection(Jedis jedis){
        if(jedis != null){
            jedis.disconnect();
        }

    }
//插入一条String类型的记录
    public  void insertString(String key,String value){
        Jedis jedis = ResisTest.jdeisConnection();
        //简单的key-value 存储
        jedis.set(key, value);
        jedis.expire(key,300);
        System.out.println("插入redis一条string："+jedis.get(key));
        jedis.disconnect();
    }




//插入一条user记录，key设置为email
    public  int insertUser(User user,String hash_random){

        Jedis jedis = ResisTest.jdeisConnection();

        String  email = user.getEmail();
        String  password = user.getPassword();
        String  name = user.getName();
        String  licence = user.getLicence();
        String  head = user.getHead();
        String  phone = user.getPhone();
        int type = user.getType();
        int credit = user.getCredit();
        int isvalidated = user.getIsvalidated();
        int status = user.getStatus();
        int points = user.getPoints();

        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("Email",email);
        userMap.put("Password",password);
        userMap.put("Name",name);
        userMap.put("Licence",licence);
        userMap.put("Head",head);
        userMap.put("Phone",phone);
        userMap.put("Type",String.valueOf(type));
        userMap.put("Credit",String.valueOf(credit));
        userMap.put("Isvalidated",String.valueOf(isvalidated));
        userMap.put("Status",String.valueOf(status));
        userMap.put("Points",String.valueOf(points));

        String result = null;
        result = jedis.hmset(hash_random, userMap);


        if(result == null) //判断是否插入成功
            return ReturnStatus.FAILURE;

//        //mapkey个数
//        System.out.println(String.format("len:%d", jedis.hlen(email)));
//        //map中的所有键值
//        System.out.println(String.format("keys: %s", jedis.hkeys(email) ));
//        //map中的所有value
//        System.out.println(String.format("values: %s", jedis.hvals(email) ));

        jedis.expire(email,3000); //设置key值为email的记录生存时间为10秒

        jedis.disconnect();

        return ReturnStatus.SUCCESS;



    }
//删除主键为key的记录
    public  boolean delete(String key){
        Jedis jedis = ResisTest.jdeisConnection();
        long result = jedis.del(key);//del返回long值

        System.out.println("删除主键为"+key+"的记录");

        jedis.disconnect();

       if(result > 0)
           return  true;

       return  false;


    }
//依据激活码的hash值获得该用户的所有注册信息
    public User getUser(String key_hash){
        Jedis jedis = ResisTest.jdeisConnection();
        User user = new User();

        Iterator<String> iter = jedis.hkeys(key_hash).iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = jedis.hget(key_hash, key);
            //System.out.println(key + ":" + value);

//            try {
//                Map<String,Object> map=new HashMap<String,Object>();
//                map.put("key",key);
//                map.put("value",value);
//                String expression="user.set"+key+"("+value+")";
//                Object code = convertToCode(expression,map);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

            judge(key, value, user);
        }

        System.out.println("从redis中读取到的user"+user.toString());

       jedis.disconnect();
        return user;
    }

    public boolean isExist(String key){
        Jedis jedis = ResisTest.jdeisConnection();
        Boolean isexist = jedis.exists(key);

        jedis.disconnect();
        return isexist;

    }

    public String getString(String key){//获得key——value对中的value
        String value = null;
        Jedis jedis = ResisTest.jdeisConnection();

        value= jedis.get(key);
        jedis.disconnect();


        return value;
    }


//    public static Object convertToCode(String jexlExp,Map<String,Object> map){
//        JexlEngine jexl=new JexlEngine();
//        Expression e = jexl.createExpression(jexlExp);
//        JexlContext jc = new MapContext();
//        for(String key:map.keySet()){
//            jc.set(key, map.get(key));
//        }
//        if(null==e.evaluate(jc)){
//            return "";
//        }
//        return e.evaluate(jc);
//    }
    //依据key的值，完成相应的User属性的赋值
    //其实一开始我是拒绝的 是韩硕写的
    //@author 韩硕
    public void judge(String pattern ,String value,User user) {

        switch (pattern) {
            case "Email":
                user.setEmail(value);
                break;
            case "Password":
                user.setPassword(value);
                break;
            case "Type":
                user.setType(Integer.valueOf(value));
                break;
            case "Status":
                user.setStatus(Integer.valueOf(value));
                break;
            case "Isvalidated":
                user.setIsvalidated(Integer.valueOf(value));
                break;
            case "Name":
                user.setName(value);
                break;
            case "Licence":
                user.setLicence(value);
                break;
            case "head":
                user.setHead(value);
                break;
            case "Phone":
                user.setPhone(value);
                break;
            case "Credit":
                user.setCredit(Integer.valueOf(value));
                break;
            case "Points":
                user.setPoints(Integer.valueOf(value));
                break;


        }
    }
    public static void main(String[] args) {
        //System.out.println(new ResisTest().getUser("1553741667@qq.com"));
        //new ResisTest().delete("1553741667@qq.com");
        System.out.println(password);



    }


}
