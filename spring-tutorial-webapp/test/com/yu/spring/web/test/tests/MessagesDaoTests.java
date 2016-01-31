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

import com.yu.spring.web.dao.Message;
import com.yu.spring.web.dao.MessagesDao;
import com.yu.spring.web.dao.OffersDAO;
import com.yu.spring.web.dao.User;
import com.yu.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        "classpath:com/yu/spring/web/config/dao-context.xml",
        "classpath:com/yu/spring/web/config/security-context.xml",
        "classpath:com/yu/spring/web/test/config/dataSource.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDaoTests {
    @Autowired
    private OffersDAO offersDao;
    
    @Autowired
    private UsersDao usersDao;
    
    @Autowired
    private MessagesDao messagesDao;
    
    @Autowired
    private DataSource dataSource;
    
    private User user1 = new User("admin", "11111111", "Administrator", 
            "admin@google.com", true, "ROLE_ADMIN");
    private User user2 = new User("johnpurcell", "hellothere", "John Purcell", 
            "john@google.com", true, "ROLE_USER");
    
    Message message1 = new Message("Test Subject 1", "Test content 1", "Isoac Newton", "newton@gmail.com", user1.getUsername());
    Message message2 = new Message("Test Subject 1", "Test content 1", "Isoac Newton", "newton@gmail.com", user1.getUsername());
    Message message3 = new Message("Test Subject 1", "Test content 1", "Isoac Newton", "newton@gmail.com", user1.getUsername());
    
    
    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from offers");
        jdbc.execute("delete from messages");
        jdbc.execute("delete from users");
    }
    
    @Test
    public void testSave() {
        usersDao.create(user1);
        usersDao.create(user2);
        
        messagesDao.saveOrUpdate(message1);
        messagesDao.saveOrUpdate(message2);
        messagesDao.saveOrUpdate(message3);
        
        List<Message> messages = messagesDao.getMessages(user1.getUsername());
        assertThat(messages, hasSize(3));
        assertThat(messages, hasItems(message1, message2, message3));
    }
    
    @Test
    public void testDelete() {
        usersDao.create(user1);
        usersDao.create(user2);
        
        messagesDao.saveOrUpdate(message1);
        messagesDao.saveOrUpdate(message2);
        messagesDao.saveOrUpdate(message3);
        
        List<Message> messages = messagesDao.getMessages(user1.getUsername());
        
        Message toDelete = messages.get(1);
        
        assertThat("Before delete", messagesDao.getMessage(toDelete.getId()), is(not(nullValue())));
        
        messagesDao.delete(toDelete.getId());
        
        assertThat("After delete", messagesDao.getMessage(toDelete.getId()), is(nullValue()));
    }
    
}
