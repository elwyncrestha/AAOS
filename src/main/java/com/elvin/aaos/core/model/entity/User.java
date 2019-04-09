package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;

import javax.persistence.*;

@Entity
@Table(name = "aaos_user")
public class User extends AbstractEntity<Long> {

    private static final long serialVersionUID = 8453652076725018240L;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private String authority;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private Status status;

    @Column
    private String timeZone;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
