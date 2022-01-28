package br.silveira.conciliador.organizational.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.organizational.entity.ItemAverageCost;

@Repository
public interface ItemAverageCostRepository extends MongoRepository<ItemAverageCost, String> {
	
	public List<ItemAverageCost> findByCompanyId(String companyId);
	
	public List<ItemAverageCost> findBySku(List<String> sku);

}
