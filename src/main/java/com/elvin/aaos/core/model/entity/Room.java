package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Room extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622377825111240L;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private RoomType roomType;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status;

    @Transient
    private Long buildingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RoomSchedule> roomSchedules = new HashSet<>();

}
