package com.yu.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings("unchecked")
@Component("usersDao")
public class UsersDao {
    
    private NamedParameterJdbcTemplate jdbc;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }
    
    public Session session() {
        return sessionFactory.getCurrentSession();
    }
    
    public void create(User user) {  
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        session().save(user);
    }

    public boolean exists(String username) {
        return jdbc.queryForObject(
                "select count(*) from users where username = :username", 
                new MapSqlParameterSource("username", username), Integer.class) > 0;
    }

    public List<User> getAllUsers() {
        return (List<User>)session().createQuery("from User").list();
    }
    
    
}
