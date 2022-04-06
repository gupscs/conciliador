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
	
	@Query(value="{ id : ?0 }", fields="{ id : 1 , companyId : 1 , seller : 1 ,  totalAmount : 1  , mktPlace : 1 , mktPlaceStatus : 1 , feeType : 1 , orderStatus : 1, orderItems : 1 , shippingCost : 1 , shippingMethodId : 1 , receiverZipcode : 1 , orderItems : 1}")
	public Optional<Order> getValuesById(String orderId);
}
