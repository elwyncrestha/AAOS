package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.BaseTest;
import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.web.utility.auth.Authorities;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testSaveShouldSaveUser() {

        final User createdBy = userRepository.findUserById(1L);
        System.out.println(createdBy.getFullName());
        final User user = new User(
                "Elvin Shrestha",
                "elwyncrestha",
                "12345678",
                "elwyncrestha@gmail.com",
                UserType.SUPERADMIN,
                Authorities.ROLE_AUTHENTICATED + "," + Authorities.ROLE_ADMINISTRATOR,
                Status.ACTIVE,
                "Asia/Kathmandu"
        );
        user.setId(2L);
        user.setCreatedAt(new Date());
//        user.setCreatedBy(createdBy);
        User savedUser = userRepository.save(user);


        assertThat(savedUser.getId(), equalTo(2L));
    }
}
