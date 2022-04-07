package br.silveira.conciliador.costcalc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.costcalc.entity.OrderCostCalculation;

@Repository
public interface OrderCostCalculationRepository extends MongoRepository<OrderCostCalculation, String>{

	
	
}
