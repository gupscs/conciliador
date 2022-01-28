package br.silveira.conciliador.organizational.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.organizational.entity.FixedCost;

@Repository
public interface FixedCostRepository extends MongoRepository<FixedCost, String> {

	public List<FixedCost> findByCompanyId(String companyId);

}
