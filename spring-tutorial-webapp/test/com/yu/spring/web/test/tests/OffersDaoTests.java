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

import com.yu.spring.web.dao.Offer;
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
public class OffersDaoTests {
    @Autowired
    private OffersDAO offersDao;
    
    @Autowired
    private UsersDao usersDao;
    
    @Autowired
    private DataSource dataSource;
    
    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }
    
    @Test
    public void testCreateOffer() {
        User user = new User("yxiao", "11111111", "Yu Xiao", "xiaoyuxqx@gmail.com", true, "user");
        usersDao.create(user);
        
        Offer offer = new Offer(user, "This is a test offer");
        assertThat(offersDao.create(offer), is(equalTo(true)));
        
        List<Offer> offers = offersDao.getOffers();
        
        assertThat(offers.size(), is(equalTo(1)));
        
        Offer retrieved = offers.get(0);
        
        assertThat(retrieved, is(equalTo(offer)));
        
        retrieved.setText("Update offer text");
        
        assertThat(offersDao.update(retrieved), is(equalTo(true)));
        
        Offer updated = offersDao.getOffer(retrieved.getId());
        
        assertThat(updated, is(equalTo(retrieved)));
        
        offersDao.delete(updated.getId());
        
        List<Offer> empty = offersDao.getOffers();
        
        assertThat(empty.size(), is(equalTo(0)));
    }

}
