package com.yu.spring.web.test.tests;

import static org.junit.Assert.*;

import javax.activation.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@ActiveProfiles("dev")
@ContextConfiguration(locations = {
        //"classpath:com/yu/spring/web/config/dao-context.xml",
        //"classpath:com/yu/spring/web/config/security-context.xml",
        "classpath:com/yu/spring/web/test/config/dataSource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Repository
public class UserDaoTests {
    @Autowired
    private DataSource dataSource;
    
    @Test
    public void testCreateUser() {
        System.out.println("data source: " + dataSource);
        assertEquals("Dummy test", 1, 1);
    }
}
