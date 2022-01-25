package br.silveira.conciliador.organizational.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompanyDaoImpl implements CompanyDao {
	
	@Autowired
	private MongoTemplate mt;
	
	public void teste() {
		mt.a
	}

}
