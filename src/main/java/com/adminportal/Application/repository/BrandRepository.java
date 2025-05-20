package com.adminportal.Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adminportal.Application.model.Organisation;

@Repository
public interface BrandRepository extends JpaRepository<Organisation, Integer>{
	
	@Query(value = "select org_type_id,org_type from fa_organisation_type where active_ind=1",nativeQuery = true)
	public List<Object> getOrgType();
}
