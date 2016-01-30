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
    
    private User user1 = new User("admin", "11111111", "Administrator", 
            "admin@google.com", true, "ROLE_ADMIN");
    private User user2 = new User("johnpurcell", "hellothere", "John Purcell", 
            "john@google.com", true, "ROLE_USER");
    private User user3 = new User("yxiao", "2333333333", "Yu Xiao", 
            "xiaoyuxqx@google.com", true, "ROLE_USER");
    private User user4 = new User("anqi", "666666666", "Anqi Zhang", 
            "zhanganqi@google.com", false, "ROLE_USER");
    
    private Offer offer1 = new Offer(user1, "This is a test offer.");
    private Offer offer2 = new Offer(user1, "This is a test offer.");
    private Offer offer3 = new Offer(user2, "This is a test offer.");
    private Offer offer4 = new Offer(user3, "This is a test offer.");
    private Offer offer5 = new Offer(user3, "This is a test offer.");
    private Offer offer6 = new Offer(user3, "This is a test offer.");
    private Offer offer7 = new Offer(user4, "This is a test offer for a user that is not enabled.");
    
    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");
    }
    
    @Test
    public void testCreateRetrieve() { 
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);
        
        offersDao.saveOrUpdate(offer1);
        
        assertThat(offersDao.getOffers(), hasSize(1));
        assertThat(offersDao.getOffers(), hasItem(offer1));
        
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);

        assertThat(offersDao.getOffers(), hasSize(6));
    }
    
    @Test
    public void testGetUsername() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);
        
        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        
        // Retrieve offers exists.
        assertThat(offersDao.getOffers(user3.getUsername()), hasSize(3));
        
        // Retrieve offers does NOT exist.
        assertThat(offersDao.getOffers("Non-existed user"), is(empty()));
        
        // Retrieve only one offer
        assertThat(offersDao.getOffers(user2.getUsername()), hasSize(1));
    }
    
    @Test
    public void testUpdate() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);
        
        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
                
        offer3.setText("This offer has updated text.");
        offersDao.saveOrUpdate(offer3);

        Offer retrieved = offersDao.getOffer(offer3.getId());
        assertThat(retrieved, is(equalTo(offer3)));
    }
    
    @Test
    public void testDelete() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);
        
        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer3);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        
        assertThat(offersDao.getOffer(offer2.getId()), is(not(nullValue())));
        offersDao.delete(offer2.getId());
        assertThat(offersDao.getOffer(offer2.getId()), is(nullValue()));
    }
    
    @Test
    public void testCreateOffer() {
        User user = new User("yxiao", "11111111", "Yu Xiao", "xiaoyuxqx@gmail.com", true, "user");
        usersDao.create(user);
        
        Offer offer = new Offer(user, "This is a test offer");
        offersDao.saveOrUpdate(offer);
        
        List<Offer> offers = offersDao.getOffers();
        
        assertThat(offers.size(), is(equalTo(1)));
        
        Offer retrieved = offers.get(0);
        
        assertThat(retrieved, is(equalTo(offer)));
        
        retrieved.setText("Update offer text");
        
        offersDao.saveOrUpdate(retrieved);
        
        Offer updated = offersDao.getOffer(retrieved.getId());
        
        assertThat(updated, is(equalTo(retrieved)));
        
        // Test get by username
        Offer anotherOffer = new Offer(user, "This is another test offer");
        
        offersDao.saveOrUpdate(anotherOffer);
        
        List<Offer> offersFromYuXiao = offersDao.getOffers("yxiao");
        
        assertThat(offersFromYuXiao.size(), is(equalTo(2)));
        assertThat(offersFromYuXiao, hasItems(new Offer[] {updated, anotherOffer}));
        
        // Test delete offers.
        for (Offer offerToDel : offersFromYuXiao) {
            offersDao.delete(offerToDel.getId());
        }
        
        List<Offer> empty = offersDao.getOffers();
        
        assertThat(empty.size(), is(equalTo(0)));
    }

}
