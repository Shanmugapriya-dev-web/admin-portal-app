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
	
	@Column(name = "address_line1")
	private String addLine1;
	
	@Column(name = "address_line2")
	private String addLine2;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
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

	public Integer getOrgAddId() {
		return orgAddId;
	}

	public void setOrgAddId(Integer orgAddId) {
		this.orgAddId = orgAddId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getAddLine1() {
		return addLine1;
	}

	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}

	public String getAddLine2() {
		return addLine2;
	}

	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Integer getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(Integer activeInd) {
		this.activeInd = activeInd;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "OrganisationAddress [orgAddId=" + orgAddId + ", orgId=" + orgId + ", addLine1=" + addLine1
				+ ", addLine2=" + addLine2 + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", activeInd=" + activeInd + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	
}
