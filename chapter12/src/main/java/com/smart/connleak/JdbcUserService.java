package com.smart.connleak;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Service("jdbcUserService")
public class JdbcUserService {
    final static Logger logger = Logger.getLogger(JdbcUserService.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void logon(String userName) {
        Connection conn = null;
        try {
            /* 后续没有显示释放这个链接.这个链接不是logon()方法事务上下文线程绑定的连接.所以如果开发者没有手动释放,
             * 则这个连接将永久的占用,处于active状态,造成连接泄漏
             */
            conn = jdbcTemplate.getDataSource().getConnection();
            //DataSourceUtils 能从当前事务上下文中获取绑定的数据库连接,JdbcTemplate也是通过这个来获取的
            //这里面直接使用事务上下文绑定的那个连接,而不是新开辟一个连接
            //todo:在没有事务上下文环境的时候,使用getConnection()获取连接,依然会造成数据库连接泄漏,需要自己显示释放链接
//          conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            
            String sql = "UPDATE t_user SET last_logon_time=? WHERE user_name =?";
            jdbcTemplate.update(sql, System.currentTimeMillis(), userName);
            Thread.sleep(1000);//②模拟程序代码的执行时间
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //todo 在无事务上下文环境的时候,需要关闭连接
            //DataSourceUtils.releaseConnection(conn,jdbcTemplate.getDataSource());
        }

    }


    public static void asynchrLogon(JdbcUserService userService, String userName) {
        UserServiceRunner runner = new UserServiceRunner(userService, userName);
        runner.start();
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reportConn(BasicDataSource basicDataSource) {
        /* function:getNumIdle
         * [Read Only] The current number of idle connections that are waiting
         * to be allocated from this data source.
         * function:getNumActive
         * [Read Only] The current number of active connections that have been
         * allocated from this data source.
         */
        //System.out.println("连接数[active:idle]-[" + basicDataSource.getNumActive()+":"+basicDataSource.getNumIdle()+"]");
        logger.info("连接数[active:idle]-[" + basicDataSource.getNumActive()+":"+basicDataSource.getNumIdle()+"]");
    }

    private static class UserServiceRunner extends Thread {
        private JdbcUserService userService;
        private String userName;

        public UserServiceRunner(JdbcUserService userService, String userName) {
            this.userService = userService;
            this.userName = userName;
        }

        public void run() {
            userService.logon(userName);
        }
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/connleak/applicatonContext.xml");
        JdbcUserService userService = (JdbcUserService) ctx.getBean("jdbcUserService");

        BasicDataSource basicDataSource = (BasicDataSource) ctx.getBean("dataSource");
        JdbcUserService.reportConn(basicDataSource);
        
        JdbcUserService.asynchrLogon(userService, "tom");
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.asynchrLogon(userService, "john");
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);

    }
}