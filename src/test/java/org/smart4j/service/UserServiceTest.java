package org.smart4j.service;

import org.smart4j.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ithink on 17-7-31.
 */
@ContextConfiguration("classpath:/smart4j-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void hasMatchedUser(){
        boolean b1 = userService.hasMatchedUser("admin", "123456");
        boolean b2 = userService.hasMatchedUser("admin", "11111");

        Assert.assertTrue(b1);
        Assert.assertFalse(b2);
    }

    @Test
    public void findUserByUsername(){
        User user = userService.findUserByUsername("admin");
        Assert.assertEquals(user.getUsername(), "admin");
    }
}
