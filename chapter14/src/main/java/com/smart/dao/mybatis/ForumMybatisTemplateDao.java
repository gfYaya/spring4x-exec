package com.smart.dao.mybatis;

import com.smart.domain.Forum;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ForumMybatisTemplateDao {

    private SqlSessionTemplate sessionTemplate;

    @Autowired
    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    public Forum getForum(int forumId) {
        return (Forum) sessionTemplate.selectOne(
                "com.smart.dao.mybatis.ForumMybatisDao.getForum",
                forumId);
    }

    public Forum getForum2(int forumId) {
        /* 获取接口实例,如果mybatis中的spring 配置文件applicationContext-mybatis.xml中配置了
         * org.mybatis.spring.mapper.MapperScannerConfigurer,那么会自动扫描,将这些映射接口
         * 转换为spring的Bean,可以直接用于service */
        ForumMybatisDao forumMybatisDao = sessionTemplate.getMapper(ForumMybatisDao.class);
        return forumMybatisDao.getForum(forumId);
    }
}
