package com.adminportal.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.Application.model.OrganisationAddress;

@Repository
public interface OrganisationAddressRepository extends JpaRepository<OrganisationAddress, Integer>{

}
