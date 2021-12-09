package br.silveira.conciliador.costcalc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.costcalc.entity.OrderCostCalcuation;

@Repository
public interface OrderCostCalculationRepository extends MongoRepository<OrderCostCalcuation, String>{

	
	
}
