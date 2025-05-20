package com.adminportal.Application.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "ts_user_master")
@Data
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "user_name")
	private String userName;
	private String password;
	@Column(name = "active_ind")
	private int activeInd;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	@Column(name = "updated_by")
	private int updatedBy;
	

}
