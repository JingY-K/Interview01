package com.work2022.interview.coindesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Expose(serialize = false)
    private Integer status;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
//    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(nullable = false, updatable = false)
    private String createBy;
    
    @Column(nullable = false)
    @UpdateTimestamp
//    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Column(nullable = false)
    private String lastUpdateBy;
    
    
    // @PrePersist, @PreUpdate
    
}
