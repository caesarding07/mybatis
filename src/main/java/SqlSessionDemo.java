import dao.UserMapper;
import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SqlSessionDemo {
    public static void main(String[] args) throws IOException{
        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
//        路径开头要加上"/"
        String resource= "/config/mybatis-config.xml";
//        JAVA中getResourceAsStream这个方法是用来获取配置文件
        InputStream inputStream= Resource.class.getResourceAsStream((resource));

        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession=sqlSessionFactory.openSession()){

            UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
            /*查询输出id为1的记录*/
            System.out.println("查询输出id为1的记录");
            User user=userMapper.selectById(1);
            System.out.println(user.toString());

            /*新增*/
            System.out.println("新增");
            User user1 = new User();
            user1.setUsername("dingtao");
            user1.setPassword("12345");
            user1.setMobile("17395711569");
            int state= userMapper.add(user1);
            System.out.println(user1.toString());

            /*全部查询输出*/
            System.out.println("全部查询输出");
            List<User> userList = userMapper.selectListByXML();
//            Iterable<String> iterable = (Iterable<String>) userList.iterator();
//            while (iterable.iterator().hasNext()){
//                System.out.println(iterable.iterator().next());
//            }
            System.out.println(userList.toString());

            /*模糊查询*/
            System.out.println("模糊查询");
            User user2 = new User();
            user2.setMobile("17395711569");
            user2.setUsername("dingtao");
            List<User> userList1 = userMapper.selectByMobileAndUsernameLike(user2.getMobile(),user2.getUsername());
            System.out.println(userList1.toString());
        }

    }

}
