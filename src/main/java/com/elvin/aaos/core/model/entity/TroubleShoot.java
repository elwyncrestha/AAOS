package com.elvin.aaos.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.elvin.aaos.core.model.enums.Status;

@Entity
@Table(name = "troubleshoot")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TroubleShoot extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453652076586238240L;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 1_000_000)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
