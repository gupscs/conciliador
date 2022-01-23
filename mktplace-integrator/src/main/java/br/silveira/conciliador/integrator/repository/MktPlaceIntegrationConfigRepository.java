package br.silveira.conciliador.integrator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;

@Repository
public interface MktPlaceIntegrationConfigRepository  extends MongoRepository<MktPlaceIntegrationConfig, String>{

	@Query("{companyId: ?0, marketPlace: ?1, authorizationCode: ?2 }")
	public Optional<MktPlaceIntegrationConfig> findByCompanyIdAndMktPlaceAndAuthorizationCode(String companyId, MktPlaceEnum marketPlace, String authorizationCode);

	@Query("{companyId: ?0, marketPlace: ?1, mktPlaceUserId: ?2}")      
	public Optional<MktPlaceIntegrationConfig> findByCompanyIdAndMktPlaceAndMktPlaceUserId(String companyId, MktPlaceEnum mercadoLivre, String mktPlaceUserId );

	@Query("{marketPlace: ?0, mktPlaceUserId: ?1}")    
	public Optional<MktPlaceIntegrationConfig> findByMktPlaceAndMktPlaceUserId(MktPlaceEnum marketPlace, String mktPlaceUserId);
	
	@Query(value = "{companyId: ?0, marketPlace: ?1}" , fields = "{companyId : 1, marketPlace : 1 ,mktPlaceUserId : 1 ,mktPlaceUsername : 1 ,insertDate : 1 ,insertId : 1 ,updateDate : 1 ,updateId : 1 ,enable : 1}")    
	public List<MktPlaceIntegrationConfig> findMktPlaceUsersByCompanyIdAndMarketPlaceOrderByMktPlaceUsername(String companyId, MktPlaceEnum marketPlace);
	

}
