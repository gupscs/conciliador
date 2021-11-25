package br.silveira.conciliador.integrator.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.integrator.entity.MktPlaceIntegrationConfig;

@Repository
public interface MktPlaceIntegrationConfigRepository  extends MongoRepository<MktPlaceIntegrationConfig, String>{
	
	
	@Query("{companyId: ?0, marketPlace: ?1}")      
	public Optional<MktPlaceIntegrationConfig> findByCompanyIdAndMktPlace(String companyId, MktPlaceEnum marketPlace); 
	
	@Query("{mktPlaceUserId: ?0, marketPlace: ?1}")      
	public Optional<MktPlaceIntegrationConfig> findByMktPlaceUserIdAndMktPlace(String mktPlaceUserId, MktPlaceEnum marketPlace); 
	

}
