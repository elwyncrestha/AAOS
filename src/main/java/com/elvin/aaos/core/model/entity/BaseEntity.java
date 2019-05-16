package com.elvin.aaos.core.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Data
@Getter
@Setter
public abstract class BaseEntity<PK extends Serializable> extends AbstractPersistable<PK> {

    private static final long serialVersionUID = 8453654076725018240L;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;

    @Version
    @Column
    private int version;

    @CreatedBy
    @OneToOne
    private User createdBy;

    @LastModifiedBy
    @OneToOne
    private User modifiedBy;

}
