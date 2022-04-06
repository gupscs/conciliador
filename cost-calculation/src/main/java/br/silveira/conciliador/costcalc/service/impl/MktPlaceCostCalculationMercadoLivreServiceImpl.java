package br.silveira.conciliador.costcalc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import br.silveira.conciliador.costcalc.dto.CalculationDto;
import br.silveira.conciliador.costcalc.dto.FixedCostDto;
import br.silveira.conciliador.costcalc.dto.ItemCalculationValuesDto;
import br.silveira.conciliador.costcalc.dto.ItemCostDetailDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;
import br.silveira.conciliador.costcalc.service.MktPlaceCostCalculationService;
import br.silveira.conciliador.feignClient.dto.ItemAverageCostDto;
import br.silveira.conciliador.feignClient.dto.MercadoLivreShipmentCostDto;
import br.silveira.conciliador.feignClient.resource.MercadoLivreResource;

public class MktPlaceCostCalculationMercadoLivreServiceImpl implements MktPlaceCostCalculationService {
	
	@Autowired
	private MercadoLivreResource mercadoLivreResource; 

	@Override
	public OrderCalculationResultDto calculateOrder(OrderCalculationValuesDto dto) {
		CalculationDto tax = calculationTax(dto);
		List<CalculationDto> fixedCost = calculationFixedCost(dto); //TODO REVER ESSE CALCULO PORQUE O VALOR ACURADO É QUANDO FECHA O MES

		CalculationDto shippingCost = calculationShippingCost(dto); --> feito

		List<ItemCostDetailDto> itemCostDetail = calculateItemCost(dto); --> pendente
		CalculationDto mktPlaceFee = calculateMktPlaceFee(dto);
		
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
		
		Double shippingCostCalculation = 0.0;
		for (ItemCalculationValuesDto item : dto.getItemCalculationValuesDto()) {
			MercadoLivreShipmentCostDto shippingCost = mercadoLivreResource.getShippingCost(dto.getCompanyId(), Long.valueOf(dto.getSellerId()), item.getMktPlaceItemId(), dto.getReceiverZipcode(), item.getQuantity(), dto.getShippingMethodId());
			shippingCostCalculation = shippingCostCalculation + shippingCost.getShipmentCost();
		}

		CalculationDto ret = new CalculationDto();
		ret.setValue(dto.getShippingCost() == null ? 0.0 : dto.getShippingCost() );
		ret.setCalculationName(SHIPPING_NAME);
		
		//FRETE COBRADO DO CLIENTE LIMITE MENOR QUE O VALOR DO PEDIDO
		if(LIMIT_FOR_FREE_SHIPMENT.compareTo(dto.getOrderAmount()) < 0) {
			//FRETE GRATIS 
			if(dto.getShippingCost().compareTo(0.0) < 0) {
				ret.setCalculationDetail(dto.getOrderAmount()+"(valor do pedido) > "+LIMIT_FOR_FREE_SHIPMENT+"(Limite frete grátis) : Frete grátis dado pelo vendedor");
			}else {
				ret.setCalculationDetail(dto.getOrderAmount()+"(valor do pedido) > "+LIMIT_FOR_FREE_SHIPMENT+"(Limite frete grátis) : Frete pago pelo comprador");
			}
		}else {
			ret.setCalculationDetail(dto.getOrderAmount()+"(valor do pedido) > "+LIMIT_FOR_FREE_SHIPMENT+"(Limite frete grátis) : Frete grátis obrigatório pela regra do Mercado Livre");
		}
	
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
		calc.setCalculationName(TAX_NAME);
		calc.setCalculationDetail(getTaxDetail(dto));
		//Tax Cost deve ser salvo como % em decimal 0,01 = 1%
		Double taxCalc = dto.getOrderAmount() * (dto.getTaxCost() / 100);
		calc.setValue(taxCalc);
		return calc;
	}

	private String getTaxDetail(OrderCalculationValuesDto dto) {
		StringBuffer sb = new StringBuffer();
		sb.append(dto.getOrderAmount());
		sb.append("(Order Amount) x ");
		sb.append(dto.getTaxCost());
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
			d.setMktPlaceFeeCost(calculo detalhado);
			ret.add(d);
		}
		
		return ret;
	}

	private CalculationDto calculateMktPlaceFee(OrderCalculationValuesDto dto) {
		
		se frete gratis, não cobra os 5 reais (Frete gratis = shippingCost < 0)
		
		-> Taxa Fixa = 5 reais x Quantidade Total de Itens
		
		-> Produtos KIT não cobra os 5 reais (calculo por linha do pedido)
		
		CalculationDto calc = new CalculationDto();
		calc.setCalculationName(dto.getFeeType());
		calc.setCalculationDetail(getFeeDetail(dto));
		//Tax Cost deve ser salvo como % em decimal 0,01 = 1%
		Double feeCalc = dto.getOrderAmount() * dto.getFeeAmount();
		calc.setValue(feeCalc);
		
		valores estao no nivel do item , calcular primeiro por item e depois apenas somar aqui
		
		Não esquecer dos 5 reias fixos, onde:
			
		Se ShippingCost > 0 , então soma 5 reais na comissão (colocar no log)
		
		Arredondar tudo para 2 casas decimais, todos os custos
		
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

}
