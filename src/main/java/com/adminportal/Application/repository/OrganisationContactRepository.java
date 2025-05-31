package com.adminportal.Application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adminportal.Application.model.OrganisationContact;

public interface OrganisationContactRepository extends JpaRepository<OrganisationContact, Integer>{

	@Query(value = "select * from fa_organisation_contact where mobile_no=:mobileNo and active_ind=1;",nativeQuery = true)
	Optional<OrganisationContact> findByMobileNo(String mobileNo);
	
	@Query(value = "select * from fa_organisation_contact where email_id=:mailId and active_ind=1;",nativeQuery = true)
	Optional<OrganisationContact> findByMailId(String mailId);

}
