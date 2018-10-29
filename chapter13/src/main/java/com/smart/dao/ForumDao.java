package com.smart.dao;

import com.smart.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ForumDao {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void initDb() {
		String sql = "create table t_user(user_id int primary key,user_name varchar(60))";
		jdbcTemplate.execute(sql);
	}

	/**
	 * 更新一条数据
	 * 
	 * @param forum
	 */
	public void addForum(final Forum forum) {
		//由于sql需要在内部类使用,所以设置为final
		final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES(?,?)";
		Object[] params = new Object[] { forum.getForumName(),
				forum.getForumDesc() };
		// 方式1
		// jdbcTemplate.update(sql, params);

		// 方式2
		// jdbcTemplate.update(sql, params,new
		// int[]{Types.VARCHAR,Types.VARCHAR});

		// 方式3
//		jdbcTemplate.update(sql, new PreparedStatementSetter() {
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, forum.getForumName());
//				ps.setString(2, forum.getForumDesc());
//			}
//		});

		// 方式4
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, forum.getForumName());
				ps.setString(2, forum.getForumDesc());
				return ps;
			}
		}, keyHolder);
		forum.setForumId(keyHolder.getKey().intValue());
	}

	public void addForumByNamedParams(final Forum forum) {
		//BeanPropertySqlParameterSource提供参数源,':forumName'=>forum.forumName,':forumDesc' =>forum.forumDesc
		final String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES(:forumName,:forumDesc)";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(forum);
		//也可以如此做
//		SqlParameterSource sps = new MapSqlParameterSource().
//				addValue("forumName",forum.getForumName()).
//				addValue("forumDesc",forum.getForumDesc());
		namedParameterJdbcTemplate.update(sql, sps);
	}

	/**
	 * 批量更新数据
	 * 
	 * @param forums
	 */
	public void addForums(final List<Forum> forums) {
		final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES(?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return forums.size();
			}

			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
				Forum forum = forums.get(index);
				ps.setString(1, forum.getForumName());
				ps.setString(2, forum.getForumDesc());
			}
		});
	}

	/**
	 * 根据ID获取Forum对象
	 * 
	 * @param forumId
	 * @return
	 */
	public Forum getForum(final int forumId) {
		String sql = "SELECT forum_name,forum_desc FROM t_forum WHERE forum_id=?";
		final Forum forum = new Forum();

		jdbcTemplate.query(sql, new Object[] { forumId },
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						forum.setForumId(forumId);
						forum.setForumName(rs.getString("forum_name"));
						forum.setForumDesc(rs.getString("forum_desc"));
					}
				});
		return forum;
	}

	public List<Forum> getForums(final int fromId, final int toId) {
		String sql = "SELECT forum_id,forum_name,forum_desc FROM t_forum WHERE forum_id between ? and ?";
		// 方法1：使用RowCallbackHandler接口
		 final List<Forum> forums = new ArrayList<Forum>();
		 jdbcTemplate.query(sql,new Object[]{fromId,toId},new RowCallbackHandler(){
		 	public void processRow(ResultSet rs) throws SQLException {
		 		Forum forum = new Forum();
		  		forum.setForumId(rs.getInt("forum_id"));
		  		forum.setForumName(rs.getString("forum_name"));
		  		forum.setForumDesc(rs.getString("forum_desc"));
		  		forums.add(forum);
		  	}
		 });
		 return forums;


		//方法2:使用RowMapper
		/*return jdbcTemplate.query(sql, new Object[] { fromId, toId },
				new RowMapper<Forum>() {
					public Forum mapRow(ResultSet rs, int index)
							throws SQLException {
						Forum forum = new Forum();
						forum.setForumId(rs.getInt("forum_id"));
						forum.setForumName(rs.getString("forum_name"));
						forum.setForumDesc(rs.getString("forum_desc"));
						return forum;
					}
				});*/

		//todo JDBC查询数据返回一个ResultSet结果集时,JDBC并不会将所有匹配的数据一次性加载到JVM中,只是返回一批数据,当通过
		//resultSet.next()游标滚动集超过数据范围,JDBC再获取一批数据,这样以"批量化和串行化"避免大结果集处理JVM大量内存开销.而RowMapper
		//采用的方式是将结果集中的所有数据都放到一个List<T>对象中,这样会占用大量的JVM内存,甚至直接引发OOM异常.而RowCallbackhandler接口,
		//在processRow()接口方法内部一边获取数据一边完成处理,这样数据就不会在内存中堆积,就可大大减少JVM内存的占用.
		//todo 可以将RowMapper看作采用批量化数据处理策略,RowCallbackHandler则采用流化处理.
		//todo RowSet与ResultSet不一样,是一次性装载所有的数据
	}

	int getForumNum() {
		return 0;
	};
}
