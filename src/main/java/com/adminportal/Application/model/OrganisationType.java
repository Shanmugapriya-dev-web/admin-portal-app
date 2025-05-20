package com.adminportal.Application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fa_organisation_type")
public class OrganisationType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_type_id")
	private Integer orgTypeId;

	@Column(name = "org_type")
	private String orgType;

	@Column(name = "active_ind")
	private int activeInd;

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public int getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(int activeInd) {
		this.activeInd = activeInd;
	}

	@Override
	public String toString() {
		return "OrganisationType [orgTypeId=" + orgTypeId + ", orgType=" + orgType + ", activeInd=" + activeInd + "]";
	}

}
