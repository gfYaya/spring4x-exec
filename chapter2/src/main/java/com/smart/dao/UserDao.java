package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //通过Spring注解定义一个dao
public class UserDao {
    private JdbcTemplate jdbcTemplate;


    /*  Config methods may have an arbitrary name and any number of arguments;
     * each of those arguments will be autowired with a matching bean in the Spring container.
     */
    @Autowired //自动注入JdbcTemplate Bean  //为何要使用自动装配?参数传入的就是实参,难道在传入之前不用实例化,在传入该函数的时候会辅助实参进行实例化?
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password){
        String sqlStr = "SELECT count(*) FROM t_user where user_name = ? and passowrd = ? ";
        return jdbcTemplate.queryForInt(sqlStr, new Object[]{userName, password});
    }

}
