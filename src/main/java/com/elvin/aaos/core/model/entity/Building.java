package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.BuildingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "building")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Building extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376725111240L;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1_000_000)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BuildingStatus status;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Room> rooms = new HashSet<>();

    public void setId(long id) {
        super.setId(id);
    }

}
