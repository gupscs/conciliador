package br.silveira.conciliador.costcalc.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.silveira.conciliador.costcalc.dto.CalculationDto;
import br.silveira.conciliador.costcalc.dto.FixedCostDto;
import br.silveira.conciliador.costcalc.dto.ItemAverageCostDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;
import br.silveira.conciliador.costcalc.dto.TaxDto;
import br.silveira.conciliador.costcalc.entity.OrderCostCalcuation;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesDto;
import br.silveira.conciliador.feignClient.dto.CompanyCostValuesRequestDto;
import br.silveira.conciliador.feignClient.dto.FixedCostDetailDto;
import br.silveira.conciliador.feignClient.dto.ItemCostDetailDto;
import br.silveira.conciliador.feignClient.dto.MktPlaceFeeDto;
import br.silveira.conciliador.feignClient.dto.OrderCostDto;
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
		
		TaxDto tax = new TaxDto();
		tax.setCost(companyDto.getTax().getCost());
		tax.setTaxName(companyDto.getTax().getTaxName());
		tax.setTaxStart(companyDto.getTax().getTaxStart());
		ret.setTax(tax);
		
		ret.setFixedCost(mapperFixedCostDto(companyDto));		
		ret.setItemAverageCost(mapperItemAverageCost(companyDto));
		
		for (MktPlaceFeeDto fee : companyDto.getMktPlaceFees()) {
			if(orderDto.getFeeType().equalsIgnoreCase(fee.getFeeType())) {
				ret.setFeeType(fee.getFeeType());
				ret.setFeeAmount(fee.getFee());
				break;
			}
		}
		
		return ret;
		
	}

	private static List<ItemAverageCostDto> mapperItemAverageCost(CompanyCostValuesDto companyDto) {
		List<ItemAverageCostDto> itemAverageCost = new ArrayList<ItemAverageCostDto>();
		for (br.silveira.conciliador.feignClient.dto.ItemAverageCostDto it : companyDto.getItemAverageCost()) {
			ItemAverageCostDto idt = new ItemAverageCostDto();
			idt.setAverageCost(it.getAverageCost());
			idt.setSku(it.getSku());
			idt.setStartDate(it.getStartDate());
			itemAverageCost.add(idt);
		}
		return itemAverageCost;
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

	public static OrderCostDto mapperToOrderCostDto(OrderCalculationResultDto calRes) {
		OrderCostDto ret = new OrderCostDto();

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
		List<ItemCostDetailDto> itemCostDetail = new ArrayList<ItemCostDetailDto>();
		for (br.silveira.conciliador.costcalc.dto.ItemCostDetailDto it : calRes.getItemCostDetail()) {
			ItemCostDetailDto item = new ItemCostDetailDto();
			item.setCost(it.getCost());
			item.setSku(it.getSku());
			item.setTitle(it.getTitle());
			itemCostDetail.add(item);
		}
		return itemCostDetail;
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

	public static OrderCostCalcuation mapperToOrderCostCalculationEntity(OrderCalculationResultDto calculationRes) {
		return mapper.map(calculationRes, OrderCostCalcuation.class);
	}

}
