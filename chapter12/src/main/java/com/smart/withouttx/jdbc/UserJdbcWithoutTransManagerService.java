/**
 *  * Book软件公司, 版权所有 违者必究
 * Copyright 2010 
 * 2010-2-18
 */
package com.smart.withouttx.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.List;
import java.util.Map;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Service("userService")
public class UserJdbcWithoutTransManagerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addScore(String userName,int toAdd){
        String sql = "UPDATE t_user u SET u.score = u.score + ? WHERE user_name =?";
        jdbcTemplate.update(sql,toAdd,userName);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/withouttx/jdbc/jdbcWithoutTx.xml");
        UserJdbcWithoutTransManagerService service = (UserJdbcWithoutTransManagerService)ctx.getBean("userService");
        JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();
        //检查数据源autoCommit的设置 并不是默认设置为false,取决于数据源的配置
        System.out.println("autoCommit:"+ basicDataSource.getDefaultAutoCommit());
        //插入一条记录，初始分数为10
        jdbcTemplate.execute("INSERT INTO t_user(user_name,password,score,last_logon_time) VALUES('tom','123456',10,"+System.currentTimeMillis()+")");
        //调用工作在无事务环境下的服务类方法,将分数添加20分
        service.addScore("tom",20);
        //查看此时用户的分数
        //如果设置自动提交为false,则执行下一行会报错,因为写操作未执行成功,拿不到结果,ncorrect result size: expected 1, actual 0 ,EmptyResultDataAccessException
        //int score = jdbcTemplate.queryForObject("SELECT score FROM t_user WHERE user_name ='tom'", Integer.class);
        Integer score = null;
        //依然抛出异常
        /*Map temp = jdbcTemplate.queryForMap("SELECT score FROM t_user WHERE user_name ='tom'");
        if(!temp.isEmpty()){
            score = (Integer)temp.get("score");
        }*/

        //使用list有效
        List<Integer> tmp = jdbcTemplate.queryForList("SELECT score FROM t_user WHERE user_name ='tom'",Integer.class);
        if(!tmp.isEmpty()) score = (Integer)tmp.get(0);
        System.out.println("score:"+score);
        jdbcTemplate.execute("DELETE FROM t_user WHERE user_name='tom'");
    }
}
