package br.silveira.conciliador.organizational.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.organizational.entity.Tax;

@Repository
public interface TaxRepository extends MongoRepository<Tax, String> {
	
	public List<Tax> findByCompanyId(String companyId);

}
