package br.silveira.conciliador.integrator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.integrator.entity.QueueNotImplemented;
@Repository
public interface QueueNotImplementedRepository extends MongoRepository<QueueNotImplemented, String>{

}
