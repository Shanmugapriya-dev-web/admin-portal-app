package com.adminportal.Application.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "fa_organisation_contact")
public class OrganisationContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_contact_id")
    private int orgContactId;

    @Column(name = "org_id")
    private int orgId;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "mobile_no", length = 10)
    private String mobileNo;

    @Column(name = "email_id", length = 30)
    private String emailId;

    @Column(name = "active_ind")
    private int activeInd;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private int updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // --- Getters and Setters ---

    public int getOrgContactId() {
        return orgContactId;
    }

    public void setOrgContactId(int orgContactId) {
        this.orgContactId = orgContactId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(int activeInd) {
        this.activeInd = activeInd;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
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
		return "OrganisationContact [orgContactId=" + orgContactId + ", orgId=" + orgId + ", userName=" + userName
				+ ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", activeInd=" + activeInd + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ "]";
	}
    
}
