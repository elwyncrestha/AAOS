package com.elvin.aaos.core.model.repository;

import com.elvin.aaos.core.model.entity.User;
import com.elvin.aaos.core.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username=?1")
    User findByUsername(String username);

    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);

    @Query("select u from User u where u.status!=?1")
    List<User> findByStatusExcept(Status status);

}
