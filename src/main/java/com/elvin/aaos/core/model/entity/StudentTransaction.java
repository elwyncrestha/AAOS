package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private boolean complete;

    @Column(length = 1_000_000)
    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate = new Date();

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public void setId(long id) {
        super.setId(id);
    }

}
