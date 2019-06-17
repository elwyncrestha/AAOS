package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacherProfile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeacherProfile extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453652076721238240L;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String address;

    @Column
    private String mobileNumber;

    @Column
    private String education;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

//    @OneToMany(mappedBy = "teacherProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<RoomSchedule> roomSchedules = new HashSet<>();

    public void setId(long id) {
        super.setId(id);
    }

}
