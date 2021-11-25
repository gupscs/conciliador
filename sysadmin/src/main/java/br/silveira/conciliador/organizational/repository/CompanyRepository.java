package br.silveira.conciliador.organizational.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.organizational.entity.Company;
@Repository
public interface CompanyRepository extends MongoRepository<Company, String>{
	
	public List<Company> findAll();
	
	public List<Company> findByEnable(Boolean enable);

}
