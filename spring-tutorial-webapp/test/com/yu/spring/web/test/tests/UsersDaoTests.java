package com.yu.spring.web.test.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yu.spring.web.dao.User;
import com.yu.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/yu/spring/web/config/dao-context.xml",
        "classpath:com/yu/spring/web/config/security-context.xml",
        "classpath:com/yu/spring/web/test/config/dataSource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDaoTests {
    
    @Autowired
    private UsersDao usersDao;
    
    @Autowired
    private DataSource dataSource;
    
    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
        jdbc.execute("delete from authorities");
    }
    
    @Test
    public void testCreateUser() {
        User user = new User("yxiao", "11111111", "xiaoyuxqx@gmail.com", true, "user");
        assertThat(usersDao.create(user), is(equalTo(true)));
        
        List<User> users = usersDao.getAllUsers();
        
        assertThat(users.size(), is(equalTo(1)));
        
        assertThat(usersDao.exists(user.getUsername()), is(equalTo(true)));
    
        assertThat(users.get(0), is(equalTo(user)));
    }
}
