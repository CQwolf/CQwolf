package com.offcn.dao;

import com.offcn.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    //��¼��ѯ
    @Select("select * from user where username = #{username} and password = #{password}")
    public User checkUser(@Param("username") String username,@Param("password") String password);

    //ע�����
    @Insert("insert into user values(null,#{name},#{email},#{status},#{username},#{password},#{modifytime})")
    public int addUser(User user);

    //�����û�����ѯ
    @Select("select * from user where username = #{username}")
    public User findByUserName(String username);
}
