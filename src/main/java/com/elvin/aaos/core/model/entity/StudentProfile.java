package com.elvin.aaos.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "studentProfile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentProfile extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376725118240L;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @OneToMany(mappedBy = "studentProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentReport> studentReports;

    @OneToMany(mappedBy = "studentProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentTransaction> studentTransactions;

    public void setId(long id) {
        super.setId(id);
    }

}
