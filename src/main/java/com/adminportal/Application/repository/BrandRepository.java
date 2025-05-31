package com.adminportal.Application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adminportal.Application.model.Organisation;

@Repository
public interface BrandRepository extends JpaRepository<Organisation, Integer> {

	@Query(value = "select org_type_id,org_type from fa_organisation_type where active_ind=1", nativeQuery = true)
	public List<Object> getOrgType();
	
	@Query(value = "select org_type_id,org_type from fa_organisation_type where active_ind=1 and org_type_id in (1,2)", nativeQuery = true)
	public List<Object> getOrgTypeByType();

	@Query(value = "select org_id,org_name from fa_organisation o,fa_organisation_type ot where"
			+ " o.org_type_id=ot.org_type_id and o.active_ind=1; ", nativeQuery = true)
	public List<Object> getBrandOrganisation();

	@Query(value = "select * from fa_organisation where org_name=:companyName and active_ind=1;",nativeQuery = true)
	public Optional<Organisation> findByCompanyName(String companyName);
}
