package com.adminportal.Application.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fa_organisation_gst")
public class OrganisationGst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_gst_id")
	private Integer orgGstId;

	@Column(name = "org_id", nullable = false)
	private Integer orgId;

	@Column(name = "gst_no", length = 15)
	private String gstNo;

	@Column(name = "average_brand_purchase", precision = 20, scale = 2)
	private BigDecimal averageBrandPurchase;

	@Column(name = "last_first_month", length = 100)
	private String lastFirstMonth;

	@Column(name = "last_first_month_purchase", precision = 20, scale = 2)
	private BigDecimal lastFirstMonthPurchase;
	
	@Column(name = "last_second_month", length = 100)
	private String lastSecondMonth;

	@Column(name = "last_second_month_purchase", precision = 20, scale = 2)
	private BigDecimal lastSecondMonthPurchase;

	@Column(name = "last_third_month", length = 100)
	private String lastThirdMonth;

	@Column(name = "last_third_month_purchase", precision = 20, scale = 2)
	private BigDecimal lastThirdMonthPurchase;

	@Column(name = "last_fourth_month", length = 100)
	private String lastFourthMonth;

	@Column(name = "last_fourth_month_purchase", precision = 20, scale = 2)
	private BigDecimal lastFourthMonthPurchase;

	@Column(name = "last_fifth_month", length = 100)
	private String lastFifthMonth;

	@Column(name = "last_fifth_month_purchase", precision = 20, scale = 2)
	private BigDecimal lastFifthMonthPurchase;

	@Column(name = "last_sixth_month", length = 100)
	private String lastSixthMonth;

	@Column(name = "last_sixth_month_purchase", precision = 20, scale = 2)
	private BigDecimal lastSixthMonthPurchase;

	@Column(name = "active_ind", nullable = false)
	private Integer activeInd = 0;

	@Column(name = "created_by", nullable = false)
	private Integer createdBy = 0;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "updated_by", nullable = false)
	private Integer updatedBy = 0;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	public Integer getOrgGstId() {
		return orgGstId;
	}

	public void setOrgGstId(Integer orgGstId) {
		this.orgGstId = orgGstId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public BigDecimal getAverageBrandPurchase() {
		return averageBrandPurchase;
	}

	public void setAverageBrandPurchase(BigDecimal averageBrandPurchase) {
		this.averageBrandPurchase = averageBrandPurchase;
	}

	public String getLastSecondMonth() {
		return lastSecondMonth;
	}

	public void setLastSecondMonth(String lastSecondMonth) {
		this.lastSecondMonth = lastSecondMonth;
	}

	public BigDecimal getLastSecondMonthPurchase() {
		return lastSecondMonthPurchase;
	}

	public void setLastSecondMonthPurchase(BigDecimal lastSecondMonthPurchase) {
		this.lastSecondMonthPurchase = lastSecondMonthPurchase;
	}

	public String getLastThirdMonth() {
		return lastThirdMonth;
	}

	public void setLastThirdMonth(String lastThirdMonth) {
		this.lastThirdMonth = lastThirdMonth;
	}

	public BigDecimal getLastThirdMonthPurchase() {
		return lastThirdMonthPurchase;
	}

	public void setLastThirdMonthPurchase(BigDecimal lastThirdMonthPurchase) {
		this.lastThirdMonthPurchase = lastThirdMonthPurchase;
	}

	public String getLastFourthMonth() {
		return lastFourthMonth;
	}

	public void setLastFourthMonth(String lastFourthMonth) {
		this.lastFourthMonth = lastFourthMonth;
	}

	public BigDecimal getLastFourthMonthPurchase() {
		return lastFourthMonthPurchase;
	}

	public void setLastFourthMonthPurchase(BigDecimal lastFourthMonthPurchase) {
		this.lastFourthMonthPurchase = lastFourthMonthPurchase;
	}

	public String getLastFifthMonth() {
		return lastFifthMonth;
	}

	public void setLastFifthMonth(String lastFifthMonth) {
		this.lastFifthMonth = lastFifthMonth;
	}

	public BigDecimal getLastFifthMonthPurchase() {
		return lastFifthMonthPurchase;
	}

	public void setLastFifthMonthPurchase(BigDecimal lastFifthMonthPurchase) {
		this.lastFifthMonthPurchase = lastFifthMonthPurchase;
	}

	public String getLastSixthMonth() {
		return lastSixthMonth;
	}

	public void setLastSixthMonth(String lastSixthMonth) {
		this.lastSixthMonth = lastSixthMonth;
	}

	public BigDecimal getLastSixthMonthPurchase() {
		return lastSixthMonthPurchase;
	}

	public void setLastSixthMonthPurchase(BigDecimal lastSixthMonthPurchase) {
		this.lastSixthMonthPurchase = lastSixthMonthPurchase;
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

	public String getLastFirstMonth() {
		return lastFirstMonth;
	}

	public void setLastFirstMonth(String lastFirstMonth) {
		this.lastFirstMonth = lastFirstMonth;
	}

	public BigDecimal getLastFirstMonthPurchase() {
		return lastFirstMonthPurchase;
	}

	public void setLastFirstMonthPurchase(BigDecimal lastFirstMonthPurchase) {
		this.lastFirstMonthPurchase = lastFirstMonthPurchase;
	}

	@Override
	public String toString() {
		return "OrganisationGst [orgGstId=" + orgGstId + ", orgId=" + orgId + ", gstNo=" + gstNo
				+ ", averageBrandPurchase=" + averageBrandPurchase + ", lastFirstMonth=" + lastFirstMonth
				+ ", lastFirstMonthPurchase=" + lastFirstMonthPurchase + ", lastSecondMonth=" + lastSecondMonth
				+ ", lastSecondMonthPurchase=" + lastSecondMonthPurchase + ", lastThirdMonth=" + lastThirdMonth
				+ ", lastThirdMonthPurchase=" + lastThirdMonthPurchase + ", lastFourthMonth=" + lastFourthMonth
				+ ", lastFourthMonthPurchase=" + lastFourthMonthPurchase + ", lastFifthMonth=" + lastFifthMonth
				+ ", lastFifthMonthPurchase=" + lastFifthMonthPurchase + ", lastSixthMonth=" + lastSixthMonth
				+ ", lastSixthMonthPurchase=" + lastSixthMonthPurchase + ", activeInd=" + activeInd + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ "]";
	}

}
