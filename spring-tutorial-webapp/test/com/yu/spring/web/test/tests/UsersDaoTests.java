package com.yu.spring.web.test.tests;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

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
    
    private User user1 = new User("admin", "11111111", "Administrator", 
            "admin@google.com", true, "ROLE_ADMIN");
    private User user2 = new User("johnpurcell", "hellothere", "John Purcell", 
            "john@google.com", true, "ROLE_USER");
    private User user3 = new User("yxiao", "2333333333", "Yu Xiao", 
            "xiaoyuxqx@google.com", true, "ROLE_USER");
    private User user4 = new User("anqi", "666666666", "Anqi Zhang", 
            "zhanganqi@google.com", true, "ROLE_USER");
    
    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }
    
    @Test
    public void testCreateRetrieve() {
        usersDao.create(user1);
        List<User> users1 = usersDao.getAllUsers();
        assertThat("After assert: ", users1, hasSize(1));
        assertThat("Then retrieve user should be", users1.get(0), is(user1));
        
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);
        
        List<User> users2 = usersDao.getAllUsers();
        
        assertThat("Assert 3 more users", users2, hasSize(4));
        assertThat(users2, containsInAnyOrder(user1, user2, user3, user4));
    }
    
    // TODO - Reimplement this
    @Test
    public void testCreateUser() {
        User user = new User("yxiao", "11111111", "Yu Xiao", "xiaoyuxqx@gmail.com", true, "user");
        usersDao.create(user);
        
        List<User> users = usersDao.getAllUsers();
        
        assertThat(users.size(), is(equalTo(1)));
        
        assertThat(usersDao.exists(user.getUsername()), is(equalTo(true)));
    
        assertThat(users.get(0), is(equalTo(user)));
    }
}
