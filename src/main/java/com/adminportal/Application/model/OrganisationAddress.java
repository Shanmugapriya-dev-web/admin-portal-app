package com.adminportal.Application.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fa_organisation_address")
public class OrganisationAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_add_id")
	private Integer orgAddId;

	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "active_ind")
	private Integer activeInd;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
}
