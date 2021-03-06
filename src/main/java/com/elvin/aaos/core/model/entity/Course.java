package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376752111240L;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

//    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Module> modules = new HashSet<>();

//    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<StudentTransaction> studentTransactions = new HashSet<>();

    public void setId(long id) {
        super.setId(id);
    }

}
