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
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;


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
        user.setId(3L);
        user.setCreatedAt(new Date());
        user.setCreatedBy(createdBy);
        User savedUser = userRepository.save(user);


        assertThat(savedUser.getId(), equalTo(2L));
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testFindByUsernameShouldReturnUser() {
        String username = "administrator";
        final User user = userRepository.findByUsername(username);
        assertThat(user.getUsername(), equalTo(username));
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testFindByEmailShouldReturnUser() {
        String email = "administrator@mail.com";
        final User user = userRepository.findByEmail(email);
        assertThat(user.getEmail(), equalTo(email));
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testFindByStatusExceptShouldReturnUserList() {
        List<User> users = userRepository.findByStatusExcept(Status.INACTIVE);
        assertThat(users, hasSize(2));
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testFindUserByIdShouldReturnUSer() {
        long id = 1L;
        final User user = userRepository.findUserById(id);
        assertThat(user.getId(), equalTo(id));
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testFindCountUsersByUserTypeShouldReturnValue() {
        final long count = userRepository.countUsersByUserType(UserType.TEACHER);
        assertThat(count, greaterThanOrEqualTo(1L));
    }

    @Test
    @DatabaseSetup("/dataset/user/user-config.xml")
    public void testFindAllByUserTypeAndStatusShouldReturnUserList() {
        final List<User> users = userRepository.findAllByUserTypeAndStatus(UserType.TEACHER, Status.ACTIVE);
        assertThat(users, hasSize(1));
    }
}
