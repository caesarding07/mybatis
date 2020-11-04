package dao;

import domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /*单字段查询*/
    User selectById(int userId);
    /*新增*/
    int add(User user);
    /*全部输出查询*/
    List<User> selectListByXML();
    /*模糊查询*/
    List<User> selectByMobileAndUsernameLike(@Param("mobile") String mobile,@Param("username") String userName);
}

