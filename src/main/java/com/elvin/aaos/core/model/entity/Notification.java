package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376752231330L;

    @ManyToOne
    private User user;

    private String title;

    private String description;

    private Status status;

    private String background;

    private String icon;

}
