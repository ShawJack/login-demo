package org.smart4j.service;

import org.smart4j.dao.LoginLogDao;
import org.smart4j.dao.UserDao;
import org.smart4j.domain.LoginLog;
import org.smart4j.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ithink on 17-7-31.
 */
@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean hasMatchedUser(String username, String password){
        return userDao.getMatchCount(username, password) > 0;
    }

    public User findUserByUsername(String username){
        return userDao.findUserbyUserName(username);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(user.getCredits()+5);

        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(user.getLastVisit());

        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
