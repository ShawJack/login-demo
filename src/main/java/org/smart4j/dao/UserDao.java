package org.smart4j.dao;

import org.junit.Test;
import org.smart4j.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ithink on 17-7-30.
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String MATCH_COUNT_SQL = "select count(*) from t_user where user_name=? and password=?;";
    private final static String FIND_USER_BY_USERNAME_SQL = "select * from t_user where user_name=?;";
    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set last_visit=?, last_ip=?, credits=? where user_id=?;";

    public int getMatchCount(String username, String password){
        int count = jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{username, password}, Integer.class);
        return count;
    }

    public User findUserbyUserName(String username){
        final User user = new User();

        jdbcTemplate.query(FIND_USER_BY_USERNAME_SQL, new Object[]{username}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));

            }
        });

        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});

    }

    @Test
    public void test(){
        final User user = new User();
        user.setUsername("admin");
        System.out.println(user.getUsername());
    }



}
