package com.loyaltyone.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	@MappedSuperclass
	@EntityListeners(AuditingEntityListener.class)
	@JsonIgnoreProperties(
	        value = {"createdAt"},
	        allowGetters = true
	)
	public  class Timestamp implements Serializable {
	    private static final long serialVersionUID = 1L;

		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name="createdAt",nullable=false,updatable = false)
	    @CreatedDate
	    private Date createdAt;


	    public Date getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Date createdAt) {
	        this.createdAt = createdAt;
	    }

	}


