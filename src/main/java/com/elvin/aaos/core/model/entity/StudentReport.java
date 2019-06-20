package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "studentReport")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentReport extends BaseEntity<Long> {

    private static final long serialVersionUID = 8454362076721318240L;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile studentProfile;

    @Column(nullable = false)
    private double marksObtained;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public void setId(long id) {
        super.setId(id);
    }

}
