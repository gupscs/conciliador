package br.silveira.conciliador.costcalc.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.silveira.conciliador.costcalc.dto.CalculationDto;
import br.silveira.conciliador.costcalc.dto.FixedCostDto;
import br.silveira.conciliador.costcalc.dto.ItemCalculationValuesDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;
import br.silveira.conciliador.costcalc.entity.OrderCostCalculation;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesDto;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.feignClient.dto.FixedCostDetailDto;
import br.silveira.conciliador.feignClient.dto.ItemAverageCostDto;
import br.silveira.conciliador.feignClient.dto.ItemCostDetailDto;
import br.silveira.conciliador.feignClient.dto.OrderCostCalculatedDto;
import br.silveira.conciliador.feignClient.dto.OrderItemDto;
import br.silveira.conciliador.feignClient.dto.OrderValuesDto;

public class OrderMapper {

	private static ModelMapper mapper = new ModelMapper();
	
	public static CompanyCostValuesRequestDto mapperToCompanyCostValuesRequestDto(OrderValuesDto dto) {
		CompanyCostValuesRequestDto ret = new CompanyCostValuesRequestDto();
		ret.setId(dto.getCompanyId());
		ret.setFeeType(dto.getFeeType());
		ret.setMarketPlace(dto.getMktPlace());
		List<String> items = new ArrayList<String>();
		for (OrderItemDto item : dto.getOrderItems()) {
			items.add(item.getSku());
		}
		ret.setSku(items);
		return ret;
	}

	public static OrderCalculationValuesDto mapperToOrderCalculationValuesDto(OrderValuesDto orderDto, CompanyCostValuesDto companyDto) {
		OrderCalculationValuesDto ret = new OrderCalculationValuesDto();
		ret.setCompanyId(orderDto.getCompanyId());
		ret.setOrderAmount(orderDto.getTotalAmount());
		ret.setOrderId(orderDto.getId());
		
		ret.setTaxCost(companyDto.getTaxCost());
		
		ret.setShippingCost(orderDto.getShippingCost());
		
		ret.setFixedCost(mapperFixedCostDto(companyDto));		
		ret.setItemCalculationValuesDto(mapperItemCalculationValuesDto(companyDto, orderDto));
		
		ret.setMktPlaceFees(companyDto.getMktPlaceFees());
		
		ret.setMktPlace(orderDto.getMktPlace());
		ret.setShippingMethodId(orderDto.getShippingMethodId());
		ret.setReceiverZipcode(orderDto.getReceiverZipcode());
		
		ret.setSellerId(orderDto.getSellerId());
		
		return ret;
		
	}

	private static List<ItemCalculationValuesDto> mapperItemCalculationValuesDto(CompanyCostValuesDto companyDto, OrderValuesDto orderDto) {
		
		List<ItemCalculationValuesDto> collectItemValues = orderDto.getOrderItems().stream().map(dto -> mapper.map(dto, ItemCalculationValuesDto.class)).collect(Collectors.toList());
		
		for(ItemCalculationValuesDto dto : collectItemValues) {
			for (ItemAverageCostDto it : companyDto.getItemAverageCost()) {
				if(dto.getSku().equalsIgnoreCase(it.getSku())) {
					dto.setAverageCost(it.getAverageCost());
					dto.setStartDate(it.getStartDate());
					break;
				}				
			}
		}
		
		return collectItemValues;
	}

	private static List<FixedCostDto> mapperFixedCostDto(CompanyCostValuesDto companyDto) {
		List<FixedCostDto> fixedCosts = new ArrayList<FixedCostDto>();
		for (br.silveira.conciliador.feignClient.dto.FixedCostDto fixedCostDto : companyDto.getFixedCost()) {
			FixedCostDto dto = new FixedCostDto();
			dto.setCostName(fixedCostDto.getCostName());
			dto.setRatioType(fixedCostDto.getRatioType());
			dto.setCostRateValue(fixedCostDto.getCost());
			fixedCosts.add(dto);
		}
		return fixedCosts;
	}

	public static OrderCostCalculatedDto mapperToOrderCostDto(OrderCalculationResultDto calRes) {
		OrderCostCalculatedDto ret = new OrderCostCalculatedDto();

		ret.setFixedTotalCost(calRes.getTotalFixedCost());
		ret.setItemTotalCost(calRes.getTotalItemCost());
		ret.setMktPlaceFee(calRes.getMktPlaceFee().getValue());
		ret.setShippingCost(calRes.getShippingCost().getValue());
		ret.setTax(calRes.getTax().getValue());
		ret.setId(calRes.getOrderId());
		
		ret.setFixedCostDetail(mapperFixedCostDetail(calRes));
		ret.setItemCostDetail(mapperItemCostDetail(calRes));
		
		return ret;
	}

	private static List<ItemCostDetailDto> mapperItemCostDetail(OrderCalculationResultDto calRes) {
		return calRes.getItemCostDetail().stream().map(o -> mapper.map(o, ItemCostDetailDto.class)).collect(Collectors.toList());
	}

	private static List<FixedCostDetailDto> mapperFixedCostDetail(OrderCalculationResultDto calRes) {
		List<FixedCostDetailDto> fixedCostDetail = new ArrayList<FixedCostDetailDto>();
		for (CalculationDto cal : calRes.getFixedCost()) {
			FixedCostDetailDto fdto = new FixedCostDetailDto();
			fdto.setCost(cal.getValue());
			fdto.setCostName(cal.getCalculationName());
			fixedCostDetail.add(fdto);
		}
		return fixedCostDetail;
	}

	public static OrderCostCalculation mapperToOrderCostCalculationEntity(OrderCalculationResultDto calculationRes) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(calculationRes, OrderCostCalculation.class);
	}

}
