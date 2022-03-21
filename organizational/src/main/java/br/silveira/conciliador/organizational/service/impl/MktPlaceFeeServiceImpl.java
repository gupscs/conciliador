package br.silveira.conciliador.organizational.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.silveira.conciliador.organizational.dto.MktPlaceFeeDto;
import br.silveira.conciliador.organizational.entity.MktPlaceFee;
import br.silveira.conciliador.organizational.repository.MktPlaceFeeRepository;
import br.silveira.conciliador.organizational.service.MktPlaceFeeService;
import br.silveira.conciliador.organizational.util.MapperUtil;

@Service
public class MktPlaceFeeServiceImpl implements MktPlaceFeeService{
	
	private static final Logger log = LogManager.getLogger(MktPlaceFeeServiceImpl.class);
	
	@Autowired
	private MktPlaceFeeRepository mktPlaceFeeRepository;

	@Override
	public boolean saveMktPlaceFee(MktPlaceFeeDto mktPlaceFeeDto) {
		MktPlaceFee entity = null;
		if(StringUtils.hasText(mktPlaceFeeDto.getId())) {
			Optional<MktPlaceFee> entityOld = mktPlaceFeeRepository.findById(mktPlaceFeeDto.getId());
			if(entityOld.isPresent()) {
				entity = entityOld.get();
			}
		}
		entity = MapperUtil.mapperToEntity(entity, mktPlaceFeeDto);
		entity = mktPlaceFeeRepository.save(entity);
		log.info("MktPlaceFee save successfully - ID: "+entity.getId());
		
		return true;
	}

}
