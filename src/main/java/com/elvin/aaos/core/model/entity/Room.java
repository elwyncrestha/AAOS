package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "aaos_room")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

}
