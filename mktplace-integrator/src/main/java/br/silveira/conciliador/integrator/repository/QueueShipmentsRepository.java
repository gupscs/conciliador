package br.silveira.conciliador.integrator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.integrator.entity.QueueShipments;
@Repository
public interface QueueShipmentsRepository extends MongoRepository<QueueShipments, String>{

}
