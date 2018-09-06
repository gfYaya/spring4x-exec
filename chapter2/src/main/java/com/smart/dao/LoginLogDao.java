package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;

    //保存登录日志
    private final static String INSERT_LOGIN_LOG_SQL= "INSERT INTO t_login_log(user_id,ip,login_datetime) VALUES(?,?,?)";

    public void insertLoginLog(LoginLog loginLog){
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
    }

}
