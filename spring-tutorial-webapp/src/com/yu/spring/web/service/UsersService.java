package com.yu.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.yu.spring.web.dao.User;
import com.yu.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {

    private UsersDao usersDao;
    
    @Autowired
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }
    
    public void create(User user) {
        usersDao.create(user);
    }

    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    /**
     * Use security to protect this method.
     * @return
     */
    @Secured({"ROLE_ADMIN"})
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }
    
}
