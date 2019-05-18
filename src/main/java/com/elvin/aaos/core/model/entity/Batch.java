package com.elvin.aaos.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aaos_batch")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Batch extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453652076725118240L;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentProfile> studentProfiles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "aaos_batch_course",
        joinColumns = {@JoinColumn(name = "batch_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private Set<Course> courses;

}
