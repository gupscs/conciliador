package br.silveira.conciliador.integrator.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.integrator.entity.QueuePayments;
@Repository
public interface QueuePaymentsRepository extends MongoRepository<QueuePayments, String>{

	@Query("{companyId: ?0, processStatus: ?1}")      
	public List<QueuePayments> findByCompanyIdAndProcessStatus(String companyId, Integer processStatus);


}
