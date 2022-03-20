package br.silveira.conciliador.organizational.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.organizational.dto.ItemAverageCostDto;
import br.silveira.conciliador.organizational.entity.ItemAverageCost;
import br.silveira.conciliador.organizational.repository.ItemAverageCostRepository;
import br.silveira.conciliador.organizational.service.ItemAverageCostService;
import br.silveira.conciliador.organizational.util.MapperUtil;

@Service
public class ItemAverageCostServiceImpl implements ItemAverageCostService{
	
	private static final Logger log = LogManager.getLogger(ItemAverageCostServiceImpl.class);
	
	@Autowired
	private ItemAverageCostRepository itemAverageCostRepository; 

	@Override
	public boolean saveItemAverageCost(ItemAverageCostDto itemAverageCostDto) {
		ItemAverageCost entity = null;
		Optional<ItemAverageCost> entityCheck = itemAverageCostRepository.findByCompanyIdAndSkuAndStartDateAndEnable(itemAverageCostDto.getCompanyId(), itemAverageCostDto.getSku(), itemAverageCostDto.getStartDate(), true);
		if(entityCheck.isEmpty()) {
			entity = MapperUtil.mapperToEntity(itemAverageCostDto) ; 
			entity.setInsertDate(new Date());
		}else {
			entity = entityCheck.get();
			entity.setUpdateId(itemAverageCostDto.getInsertId());
			entity.setUpdateDate(new Date());
			entity.setAverageCost(itemAverageCostDto.getAverageCost());
		}
		
		ItemAverageCost save = itemAverageCostRepository.save(entity);
		log.info("Item Average Cost save successfully!! - ID: "+save.getId());
		return save != null;
	}

}
