package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "aaos_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453652076725018240L;

    @Column
    private String fullName;

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

}
