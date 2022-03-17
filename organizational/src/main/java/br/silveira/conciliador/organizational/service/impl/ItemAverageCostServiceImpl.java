package br.silveira.conciliador.organizational.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.organizational.dto.ItemAverageCostDto;
import br.silveira.conciliador.organizational.entity.ItemAverageCost;
import br.silveira.conciliador.organizational.repository.ItemAverageCostRepository;
import br.silveira.conciliador.organizational.service.ItemAverageCostService;
import br.silveira.conciliador.organizational.util.MapperUtil;

@Service
public class ItemAverageCostServiceImpl implements ItemAverageCostService{
	
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
		return save != null;
	}

}
