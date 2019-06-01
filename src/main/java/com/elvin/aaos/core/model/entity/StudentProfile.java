package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "studentProfile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentProfile extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376725118240L;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column
    private String address;

    @Column
    private String telephoneNumber;

    @Column
    private String mobileNumber;

    @Column
    private String parentMobileNumber;

    @Column
    private String parentEmail;

    @Column
    private String motherName;

    @Column
    private String motherContactNumber;

    @Column
    private String fatherName;

    @Column
    private String fatherContactNumber;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @OneToMany(mappedBy = "studentProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentReport> studentReports;

    @OneToMany(mappedBy = "studentProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentTransaction> studentTransactions;

    public void setId(long id) {
        super.setId(id);
    }

}
