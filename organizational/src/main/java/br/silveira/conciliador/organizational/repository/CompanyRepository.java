package br.silveira.conciliador.organizational.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.organizational.entity.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

	public List<Company> findAll();

	public List<Company> findByEnable(Boolean enable);

	@Query(value = "{ identificationNo : ?0 }", fields = "{ id : 1 , identificationNo : 1 }")
	public Optional<Company> findIdByIdentificationNo(String identificationNo);
	
	
	@Query(value = "{ id : ?0 }", fields = "{ id : 1 , taxCost : 1 }")
	public Optional<Company> findTaxCostById(String id);



}
