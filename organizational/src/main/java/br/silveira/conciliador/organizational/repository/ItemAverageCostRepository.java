package br.silveira.conciliador.organizational.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.organizational.entity.ItemAverageCost;

@Repository
public interface ItemAverageCostRepository extends MongoRepository<ItemAverageCost, String> {

	public List<ItemAverageCost> findByCompanyId(String companyId);

	@Query(value = "{ companyId : ?0 , sku :  {$in : ?1 } }")
	public List<ItemAverageCost> findByCompanyIdAndSku(String companyId, List<String> sku);

	public Optional<ItemAverageCost> findByCompanyIdAndSkuAndStartDateAndEnable(String companyId, String sku, Date startDate, Boolean enable);

}
