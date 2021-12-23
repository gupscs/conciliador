package br.silveira.conciliador.organizational.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.organizational.entity.Company;
@Repository
public interface CompanyRepository extends MongoRepository<Company, String>{
	
	public List<Company> findAll();
	
	public List<Company> findByEnable(Boolean enable);

	@Query(value="{ id : ?0 }", fields="{ id : 1 , identificationNo : 1 ,  shortName : 1 ,  fixedCost : 1 , tax : 1, itemAverageCost : 1 ,  mktPlaceFees : 1  }")
	public Optional<Company> findCompanyCostValuesById(String companyId);
	
	@Query(value="{ id : ?0  , mktPlaceFees.marketPlace : ?1 , mktPlaceFees.feeType  : ?2}", fields="{ id : 1 , identificationNo : 1 ,  shortName : 1 ,  fixedCost : 1 , tax : 1, itemAverageCost : 1 ,  mktPlaceFees : 1  }")
	public Optional<Company> findCompanyCostValuesByIdAndMktPlaceAndFeeType(String companyId, MktPlaceEnum marketPlace, String feeType);

	@Query(value="{ id : ?0  , itemAverageCost.sku : ?1 }", fields="{ id : 1 , identificationNo : 1 ,  shortName : 1 ,  fixedCost : 1 , tax : 1, itemAverageCost : 1 ,  mktPlaceFees : 1  }")
	public Optional<Company> findCompanyCostValuesByIdAndSku(String companyId, List<String> sku);

	@Query(value="{ id : ?0  , mktPlaceFees.marketPlace : ?1 , mktPlaceFees.feeType  : ?2 , itemAverageCost.sku : {$in: ?3 }", fields="{ id : 1 , identificationNo : 1 ,  shortName : 1 ,  fixedCost : 1 , tax : 1, itemAverageCost : 1 ,  mktPlaceFees : 1  }")
	public Optional<Company> findCompanyCostValuesByIdAndMktPlaceAndFeeTypeAndSku(String companyId, MktPlaceEnum marketPlace, String feeType, List<String> sku);


}
