package com.elvin.aaos.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "studentTransaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentTransaction extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376725118240L;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private boolean isComplete;

    @Column(length = 1_000_000)
    private String remarks;

    public void setId(long id) {
        super.setId(id);
    }

}