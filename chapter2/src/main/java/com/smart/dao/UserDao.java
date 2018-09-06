package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository //通过Spring注解定义一个dao
public class UserDao {
    private JdbcTemplate jdbcTemplate; //使用JdbcTemplate,本身需要一个DataSource,配置在smart-context.xml中

    private  final static String MATCH_COUNT_SQL = " SELECT count(*) FROM t_user  " +  //final,编译期间会进行优化,其实只是有一个字符串对象生成
            " WHERE user_name =? and password=? ";
    private  final static String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
            " last_visit=?,last_ip=?,credits=?  WHERE user_id =?";


    /*  Config methods may have an arbitrary name and any number of arguments;
     * each of those arguments will be autowired with a matching bean in the Spring container.
     */
    //为何要使用自动装配?参数传入的就是实参,难道在传入之前不用实例化,在传入该函数的时候会辅助实参进行实例化?
    //answer by 益达:此方法相当于在setter上使用自动装配(autowired),意图是在jdbcTmplate实现自动装配的时候,同时执行此方法进行set(setter注入),我对此持怀疑态度.
    //answer by Intopass:这个用处就是 Spring构建Dao时注入一个JdbcTemplate,如果是单例，那么会一开始就构造好。
    @Autowired //自动注入JdbcTemplate Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password){
        //String sqlStr = "SELECT count(*) FROM t_user where user_name = ? and passowrd = ? ";
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password},Integer.class);
    }

    public User findUserByUserName(final String userName){
        final User user = new User();
        String sqlStr = " SELECT user_id,user_name,credits FROM t_user WHERE user_name =? ";
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                //匿名内部类实现的回调函数
                new RowCallbackHandler() {
                    //负责将查询的结果从ResultSet装载到类似于领域对象的实例中
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                }
        );
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }

}
