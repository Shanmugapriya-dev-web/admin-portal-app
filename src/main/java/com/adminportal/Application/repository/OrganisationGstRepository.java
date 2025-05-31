package com.adminportal.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.Application.model.OrganisationGst;

@Repository
public interface OrganisationGstRepository extends JpaRepository<OrganisationGst, Integer>{

}
