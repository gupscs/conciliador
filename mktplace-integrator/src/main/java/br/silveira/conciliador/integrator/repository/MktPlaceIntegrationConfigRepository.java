package br.silveira.conciliador.integrator.repository;

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
	

}
