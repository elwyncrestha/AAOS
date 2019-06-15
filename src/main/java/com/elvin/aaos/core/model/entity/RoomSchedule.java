package com.elvin.aaos.core.model.entity;

import com.elvin.aaos.core.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;

@Entity
@Table(name = "roomSchedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoomSchedule extends BaseEntity<Long> {

    private static final long serialVersionUID = 8453622376748718240L;

    @Enumerated(EnumType.STRING)
    @Column
    private DayOfWeek dayOfWeek;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherProfile teacherProfile;

    public void setId(long id) {
        super.setId(id);
    }

}
