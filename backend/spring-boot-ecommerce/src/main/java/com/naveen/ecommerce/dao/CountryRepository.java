package com.naveen.ecommerce.dao;

import com.naveen.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("hhtp://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries" , path = "countries")
public interface CountryRepository extends JpaRepository<Country,Integer> {

}
