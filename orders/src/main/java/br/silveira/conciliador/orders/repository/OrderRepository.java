package br.silveira.conciliador.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.orders.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

}
