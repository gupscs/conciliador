package br.silveira.conciliador.costcalc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.common.constant.RestTagConstant;
import br.silveira.conciliador.costcalc.dto.CalculationDto;
import br.silveira.conciliador.costcalc.dto.FixedCostDto;
import br.silveira.conciliador.costcalc.dto.ItemAverageCostDto;
import br.silveira.conciliador.costcalc.dto.ItemCostDetailDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;
import br.silveira.conciliador.costcalc.entity.OrderCostCalcuation;
import br.silveira.conciliador.costcalc.mapper.OrderMapper;
import br.silveira.conciliador.costcalc.repository.OrderCostCalculationRepository;
import br.silveira.conciliador.costcalc.service.OrderCostCalculationService;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesDto;
import br.silveira.conciliador.feignClient.dto.OrderCostDto;
import br.silveira.conciliador.feignClient.dto.OrderValuesDto;
import br.silveira.conciliador.feignClient.resource.OrderResource;
import br.silveira.conciliador.feignClient.resource.OrganizationalResource;
import javassist.NotFoundException;

@Service
public class OrderCostCalculationServiceImpl implements OrderCostCalculationService{

	private static final Logger log = LogManager.getLogger(OrderCostCalculationServiceImpl.class);
	
	private static final String MSG_CALCULATION_ORDER_SUCESS = "Order Id: %s calculate sucessfully"; 
	
	@Autowired
	private OrderResource orderController ;
	
	@Autowired
	private OrganizationalResource organizationalController;
	
	@Autowired
	private OrderCostCalculationRepository orderCostCalculationRepository; 
	
	@Override
	public void calculateReturnOrder(OrderCalculationDto dto) {
		
		
	}

	@Override
	public void calculateOrder(OrderCalculationDto dto) throws Exception {
		OrderValuesDto orderValues = getOrderValues(dto);
		CompanyCostValuesDto companyCostValues = getCompanyCostValues(orderValues);
		
		OrderCalculationValuesDto calculationDto = OrderMapper.mapperToOrderCalculationValuesDto(orderValues, companyCostValues);
		OrderCalculationResultDto calculationRes = calculateOrder(calculationDto);
		
		OrderCostCalcuation orderSaved = orderCostCalculationRepository.save(OrderMapper.mapperToOrderCostCalculationEntity(calculationRes));
		
		OrderCostDto orderCost = OrderMapper.mapperToOrderCostDto(calculationRes);
		orderCost.setOrderCostCalcuationId(orderSaved.getId());
		orderCost.setUserId(dto.getUserId());
		orderController.saveOrderCost(orderCost);
		
		log.info(String.format(MSG_CALCULATION_ORDER_SUCESS, dto.getId()));
		
	}


	@Override
	public void recalculateAllPendingOrder(String companyId) {
		// TODO Auto-generated method stub
		
	}
	
	public OrderCalculationResultDto calculateOrder(OrderCalculationValuesDto dto) {
		CalculationDto mktPlaceFee = calculateMktPlaceFee(dto);
		List<ItemCostDetailDto> itemCostDetail = calculateItemCost(dto);
		CalculationDto tax = calculationTax(dto);
		List<CalculationDto> fixedCost = calculationFixedCost(dto);
		CalculationDto shippingCost = calculationShippingCost(dto);
		Double totalItemCost = calculateTotalItemCost(itemCostDetail);
		Double totalFixedCost = calculateTotalFixedCost(fixedCost);
		Double netAmount = calculateNetAmount(dto.getOrderAmount(), tax, shippingCost, totalItemCost, totalFixedCost);
		OrderCalculationResultDto ret = new OrderCalculationResultDto(dto.getOrderId(), dto.getCompanyId(), dto.getOrderAmount(), mktPlaceFee, totalItemCost, itemCostDetail, tax, totalFixedCost, fixedCost, shippingCost, netAmount);
		return ret;
	}

	private Double calculateNetAmount(Double orderAmount, CalculationDto tax, CalculationDto shippingCost, Double totalItemCost, Double totalFixedCost) {
		Double ret = orderAmount - tax.getValue() - shippingCost.getValue() - totalItemCost - totalFixedCost;
		return ret;
	}

	private Double calculateTotalFixedCost(List<CalculationDto> fixedCost) {
		if(fixedCost == null || fixedCost.isEmpty()) return 0.0;
		return fixedCost.stream().mapToDouble(f -> f.getValue()).sum();
	}

	private Double calculateTotalItemCost(List<ItemCostDetailDto> itemCostDetail) {
		if(itemCostDetail == null || itemCostDetail.isEmpty()) return 0.0;
		return itemCostDetail.stream().mapToDouble(f -> f.getCost()).sum();
	}

	private CalculationDto calculationShippingCost(OrderCalculationValuesDto dto) {
		// TODO Verificar a logica de calculo do shipping
		CalculationDto ret = new CalculationDto();
		ret.setValue(0.0);
		ret.setCalculationName("Shipping Cost not implemented");
		ret.setCalculationDetail("Shipping Cost not implemented");
		return ret;
	}

	private List<CalculationDto> calculationFixedCost(OrderCalculationValuesDto dto) {
		List<CalculationDto> ret = new ArrayList<CalculationDto>();
		for (FixedCostDto fixedCostDto : dto.getFixedCost()) {
			CalculationDto calc = new CalculationDto();
			calc.setCalculationName(fixedCostDto.getCostName());
			calc.setValue(fixedCostDto.getCostRateValue());
			calc.setCalculationDetail("RatioType: "+fixedCostDto.getRatioType());
			ret.add(calc);
		}
		
		return ret;
	}

	private CalculationDto calculationTax(OrderCalculationValuesDto dto) {
		CalculationDto calc = new CalculationDto();
		calc.setCalculationName(dto.getTax().getTaxName());
		calc.setCalculationDetail(getTaxDetail(dto));
		//Tax Cost deve ser salvo como % em decimal 0,01 = 1%
		Double taxCalc = dto.getOrderAmount() * dto.getTax().getCost();
		calc.setValue(taxCalc);
		return calc;
	}

	private String getTaxDetail(OrderCalculationValuesDto dto) {
		StringBuffer sb = new StringBuffer();
		sb.append(dto.getOrderAmount());
		sb.append("(Order Amount) x ");
		sb.append(dto.getTax().getCost());
		sb.append("(Tax %)");
		return sb.toString();
	}

	private List<ItemCostDetailDto> calculateItemCost(OrderCalculationValuesDto dto) {
		List<ItemCostDetailDto> ret = new ArrayList<ItemCostDetailDto>();
		for (ItemAverageCostDto item : dto.getItemAverageCost()) {
			ItemCostDetailDto d = new ItemCostDetailDto();
			d.setSku(item.getSku());
			d.setCost(item.getAverageCost());
			d.setCalculationDetail("-");
			ret.add(d);
		}
		
		return ret;
	}

	private CalculationDto calculateMktPlaceFee(OrderCalculationValuesDto dto) {
		CalculationDto calc = new CalculationDto();
		calc.setCalculationName(dto.getFeeType());
		calc.setCalculationDetail(getFeeDetail(dto));
		//Tax Cost deve ser salvo como % em decimal 0,01 = 1%
		Double feeCalc = dto.getOrderAmount() * dto.getFeeAmount();
		calc.setValue(feeCalc);
		return calc;
	}

	private String getFeeDetail(OrderCalculationValuesDto dto) {
		StringBuffer sb = new StringBuffer();
		sb.append(dto.getOrderAmount());
		sb.append("(Order Amount) x ");
		sb.append(dto.getFeeAmount());
		sb.append("(Fee %) | Fee Type: ");
		sb.append(dto.getFeeType());
		return sb.toString();
	}

	private CompanyCostValuesDto getCompanyCostValues(OrderValuesDto orderValues) throws Exception, NotFoundException {
		ResponseEntity<CompanyCostValuesDto> companyCostValues = organizationalController.getCompanyCostValues(OrderMapper.mapperToCompanyCostValuesRequestDto(orderValues));
		if(companyCostValues.getStatusCode().isError()) {
			throw new Exception("get company cost values - Company ID: "+orderValues.getCompanyId()+" - Errors: "+companyCostValues.getHeaders().getFirst(RestTagConstant.HD_ERROR_MSG_TAG));
		}else if(companyCostValues.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new NotFoundException("Order not found for Company ID: "+orderValues.getCompanyId());	
		}
		return companyCostValues.getBody();
	}
	
	private OrderValuesDto getOrderValues(OrderCalculationDto dto) throws Exception, NotFoundException {
		ResponseEntity<OrderValuesDto> orderValues = orderController.getOrderValues(dto.getId());		
		if(orderValues.getStatusCode().isError()) {
			throw new Exception("get order values error - ID: "+dto.getId()+" - Errors: "+orderValues.getHeaders().getFirst(RestTagConstant.HD_ERROR_MSG_TAG));
		}else if(orderValues.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new NotFoundException("Order not found - ID: "+dto.getId());	
		}
		return orderValues.getBody();
	}
}
