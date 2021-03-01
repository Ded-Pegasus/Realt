package com.innowise.realt.service;

import com.innowise.realt.repository.UserRepository;
import com.innowise.realt.repository.domain.Role;
import com.innowise.realt.repository.domain.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUser() {
        User user = new User();
        boolean isUserCreated = userService.addUser(user);
        Assert.assertTrue(isUserCreated);

        Assert.assertTrue(CoreMatchers.is(user.getRoles())
                .matches(Collections.singleton(Role.USER)));
    }

    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername("user_1");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("user_1");

        boolean isUserCreated = userService.addUser(user);
        Assert.assertFalse(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(0))
                .save(ArgumentMatchers.any(User.class));
    }
}