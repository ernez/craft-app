package com.ernez.craftapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "work_offers")
public class WorkOffer extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal repairerPrice;

    private Timestamp startTimeStamp;

    private Timestamp endTimeStamp;

    private int DaysCountNeeded;

    private int hoursCountNeeded;

    @ManyToOne(fetch = FetchType.LAZY)
    private Work work;
}
