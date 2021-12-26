package br.silveira.conciliador.sysadmin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.sysadmin.entity.User;
@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	
	public User findByUsername(String username);

}
