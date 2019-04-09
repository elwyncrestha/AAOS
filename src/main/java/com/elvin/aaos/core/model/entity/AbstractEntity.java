package com.elvin.aaos.core.model.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> extends AbstractPersistable<PK> {

    private static final long serialVersionUID = 8453654076725018240L;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    @Version
    @Column
    private int version;

    @CreatedBy
    @OneToOne
    private User createdBy;

    @LastModifiedBy
    @OneToOne
    private User modifiedBy;

    public AbstractEntity() {
        setCreatedOn(new Date());
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
