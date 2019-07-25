package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "module")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Module extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376752111330L;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

//    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<TeacherProfile> teacherProfiles = new HashSet<>();

//    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Exam> exams = new HashSet<>();

//    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<StudentReport> studentReports = new HashSet<>();

    public void setId(long id) {
        super.setId(id);
    }
}
