package br.silveira.conciliador.orders.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.orders.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
	
	
	@Query(value="{ companyId : ?0 , mktPlace : ?1 , orderId : ?2 }", fields="{ id : 1 , insertDate : 1 , insertId : 1 }")
	public Optional<Order> findIdByCompanyIdAndMktPlaceAndOrderId(String companyId, MktPlaceEnum mktPlace, String orderId);
}
