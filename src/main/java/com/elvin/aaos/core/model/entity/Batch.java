package com.elvin.aaos.core.model.entity;

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
@Table(name = "batch")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Batch extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453652076725118240L;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date formationDate;

    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentProfile> studentProfiles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "batch_course",
        joinColumns = {@JoinColumn(name = "batch_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private Set<Course> courses;

    @ManyToMany
    @JoinTable(name = "batch_exam",
        joinColumns = {@JoinColumn(name = "batch_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "id")})
    private Set<Exam> exams;

    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RoomSchedule> roomSchedules = new HashSet<>();

    public void setId(long id) {
        super.setId(id);
    }

}
