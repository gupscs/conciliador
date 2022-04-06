package br.silveira.conciliador.costcalc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.costcalc.dto.OrderCalculationDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;
import br.silveira.conciliador.costcalc.entity.OrderCostCalcuation;
import br.silveira.conciliador.costcalc.factories.MktPlaceCostCalculationFactory;
import br.silveira.conciliador.costcalc.mapper.OrderMapper;
import br.silveira.conciliador.costcalc.repository.OrderCostCalculationRepository;
import br.silveira.conciliador.costcalc.service.OrderCostCalculationService;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesDto;
import br.silveira.conciliador.feignClient.dto.OrderCostCalculatedDto;
import br.silveira.conciliador.feignClient.dto.OrderValuesDto;
import br.silveira.conciliador.feignClient.resource.OrderResource;
import br.silveira.conciliador.feignClient.resource.OrganizationalResource;

@Service
public class OrderCostCalculationServiceImpl implements OrderCostCalculationService{

	private static final Logger log = LogManager.getLogger(OrderCostCalculationServiceImpl.class);
	
	private static final String MSG_CALCULATION_ORDER_SUCESS = "Order Id: %s calculate sucessfully"; 
	
	private static final String TAX_NAME = "TAX";
	
	private static final String SHIPPING_NAME = "SHIPPING";
	
	private static final Double LIMIT_FOR_FREE_SHIPMENT = 79.0;
	
	@Autowired
	private OrderResource orderResource ;
	
	@Autowired
	private OrganizationalResource organizationalResource;
	
	@Autowired
	private OrderCostCalculationRepository orderCostCalculationRepository; 
	
	@Autowired
	private MktPlaceCostCalculationFactory mktPlaceCostCalculationFactory;
	
	@Override
	public void calculateReturnOrder(OrderCalculationDto dto) {
		
		
	}

	@Override
	public void calculateOrder(OrderCalculationDto dto) throws Exception {
		
		OrderValuesDto orderValues = orderResource.getOrderValues(dto.getId());
		CompanyCostValuesDto companyCostValues = organizationalResource.getCompanyCostValues(OrderMapper.mapperToCompanyCostValuesRequestDto(orderValues));
		
		OrderCalculationValuesDto calculationDto = OrderMapper.mapperToOrderCalculationValuesDto(orderValues, companyCostValues);
		OrderCalculationResultDto calculationRes = mktPlaceCostCalculationFactory.getImpl(orderValues.getMktPlace()).calculateOrder(calculationDto);
		
		OrderCostCalcuation orderSaved = orderCostCalculationRepository.save(OrderMapper.mapperToOrderCostCalculationEntity(calculationRes));
		
		criar um serviço ou nova classe para comparação dos valores (criar novos campos)
		
		OrderCostCalculatedDto orderCost = OrderMapper.mapperToOrderCostDto(calculationRes);
		orderCost.setOrderCostCalcuationId(orderSaved.getId());
		orderCost.setUserId(dto.getUserId());
		orderResource.saveOrderCostCalculated(orderCost);
		
		log.info(String.format(MSG_CALCULATION_ORDER_SUCESS, dto.getId()));
		
	}


	@Override
	public void recalculateAllPendingOrder(String companyId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
