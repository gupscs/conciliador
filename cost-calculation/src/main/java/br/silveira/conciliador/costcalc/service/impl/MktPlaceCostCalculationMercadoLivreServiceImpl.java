package br.silveira.conciliador.costcalc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.silveira.conciliador.costcalc.dto.CalculationDto;
import br.silveira.conciliador.costcalc.dto.FixedCostDto;
import br.silveira.conciliador.costcalc.dto.ItemCalculationValuesDto;
import br.silveira.conciliador.costcalc.dto.ItemCostDetailDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationResultDto;
import br.silveira.conciliador.costcalc.dto.OrderCalculationValuesDto;
import br.silveira.conciliador.costcalc.service.MktPlaceCostCalculationService;
import br.silveira.conciliador.costcalc.util.DoubleUtil;
import br.silveira.conciliador.feignClient.dto.MercadoLivreShipmentCostDto;
import br.silveira.conciliador.feignClient.dto.MktPlaceFeeDto;
import br.silveira.conciliador.feignClient.resource.MercadoLivreResource;

@Service("mktPlaceCostCalculationMercadoLivreServiceImpl")
public class MktPlaceCostCalculationMercadoLivreServiceImpl implements MktPlaceCostCalculationService {

	private static final Logger log = LogManager.getLogger(MktPlaceCostCalculationMercadoLivreServiceImpl.class);
	
	private static final String TAX_NAME = "TAX";

	private static final String SHIPPING_NAME = "SHIPPING";

	private static final Double LIMIT_FOR_FREE_SHIPMENT = 79.0;

	@Autowired
	private MercadoLivreResource mercadoLivreResource;

	@Override
	public OrderCalculationResultDto calculateOrder(OrderCalculationValuesDto dto) {
		
		CalculationDto tax = calculationTax(dto);
		List<CalculationDto> fixedCost = calculationFixedCost(dto); // TODO REVER ESSE CALCULO PORQUE O VALOR ACURADO É QUANDO FECHA O MES (colocar custo medio no ultimo mes)
		CalculationDto shippingCost = calculationShippingCost(dto);
		List<ItemCostDetailDto> itemCostDetail = calculateItemCost(dto, shippingCost);
		CalculationDto mktPlaceFee = calculateMktPlaceFee(dto, itemCostDetail);

		Double totalItemCost = calculateTotalItemCost(itemCostDetail);
		Double totalFixedCost = calculateTotalFixedCost(fixedCost);
		Double netAmount = calculateNetAmount(dto.getOrderAmount(), tax, shippingCost, totalItemCost, totalFixedCost);
		
		OrderCalculationResultDto ret = new OrderCalculationResultDto(dto.getOrderId(), dto.getCompanyId(), dto.getOrderAmount(), mktPlaceFee, totalItemCost, itemCostDetail, tax, totalFixedCost, fixedCost, shippingCost, netAmount);
		return ret;
	}

	private CalculationDto calculationShippingCost(OrderCalculationValuesDto dto) {
		
		CalculationDto retMockup = new CalculationDto();

		retMockup.setCalculationName(SHIPPING_NAME);
		retMockup.setCalculationDetail("mockup");
		retMockup.setValue(100.00);
		
		return retMockup;
		
		/**
		
		// TODO TESTAR OS VALORES SE TEM % DE DESCONTO PELA REPUTAÇÃO , VALIDAR OS PEDIDOS COM FRETE GRATIS
		Double shippingCostCalculation = 0.0;
		for (ItemCalculationValuesDto item : dto.getItemCalculationValuesDto()) {
			MercadoLivreShipmentCostDto shippingCost = mercadoLivreResource.getShippingCost(dto.getCompanyId(), Long.valueOf(dto.getSellerId()), item.getMktPlaceItemId(), dto.getReceiverZipcode(), item.getQuantity(), dto.getShippingMethodId());
			shippingCostCalculation = shippingCostCalculation + shippingCost.getShipmentCost();
		}

		CalculationDto ret = new CalculationDto();

		ret.setCalculationName(SHIPPING_NAME);

		// FRETE COBRADO DO CLIENTE LIMITE MENOR QUE O VALOR DO PEDIDO
		if (LIMIT_FOR_FREE_SHIPMENT.compareTo(dto.getOrderAmount()) < 0) {
			// FRETE GRATIS
			if (dto.getShippingCost().compareTo(0.0) < 0) {
				ret.setCalculationDetail(dto.getOrderAmount() + "(valor do pedido) > " + LIMIT_FOR_FREE_SHIPMENT + "(Limite frete grátis) : Frete grátis dado pelo vendedor");
				shippingCostCalculation = shippingCostCalculation * -1.00;
			} else {
				ret.setCalculationDetail(dto.getOrderAmount() + "(valor do pedido) > " + LIMIT_FOR_FREE_SHIPMENT + "(Limite frete grátis) : Frete pago pelo comprador");
			}
		} else {
			ret.setCalculationDetail(dto.getOrderAmount() + "(valor do pedido) > " + LIMIT_FOR_FREE_SHIPMENT + "(Limite frete grátis) : Frete grátis obrigatório pela regra do Mercado Livre");
			shippingCostCalculation = shippingCostCalculation * -1.00;
		}
		ret.setValue(DoubleUtil.round2decimals(shippingCostCalculation));
		return ret;
		**/
	}

	private List<CalculationDto> calculationFixedCost(OrderCalculationValuesDto dto) {
		List<CalculationDto> ret = new ArrayList<CalculationDto>();
		for (FixedCostDto fixedCostDto : dto.getFixedCost()) {
			CalculationDto calc = new CalculationDto();
			calc.setCalculationName(fixedCostDto.getCostName());
			calc.setValue(fixedCostDto.getCostRateValue());
			calc.setCalculationDetail("RatioType: " + fixedCostDto.getRatioType());
			ret.add(calc);
		}

		return ret;
	}

	private CalculationDto calculationTax(OrderCalculationValuesDto dto) {
		CalculationDto calc = new CalculationDto();
		calc.setCalculationName(TAX_NAME);
		calc.setCalculationDetail(getTaxDetail(dto));
		// Tax Cost deve ser salvo como % em decimal 0,01 = 1%
		Double taxCalc = dto.getOrderAmount() * (dto.getTaxCost() / 100);
		calc.setValue(taxCalc);
		return calc;
	}

	private List<ItemCostDetailDto> calculateItemCost(OrderCalculationValuesDto dto, CalculationDto shippingCost) {
		Double fixedFeeCost = 5.00;
		if (shippingCost.getValue().compareTo(0.0) < 0) { //TODO VERIFICAR SE É KIT (TENTAR DESCOBRIR COMO FAZER)
			fixedFeeCost = 0.00;
		}

		Map<String, Double> feeSales = new HashMap<String, Double>();
		for (MktPlaceFeeDto mktPlaceFeeDto : dto.getMktPlaceFees()) {
			feeSales.put(mktPlaceFeeDto.getFeeType(), mktPlaceFeeDto.getFee() / 100); // master is saved as %
		}

		List<ItemCostDetailDto> ret = new ArrayList<ItemCostDetailDto>();
		for (ItemCalculationValuesDto item : dto.getItemCalculationValuesDto()) {
			ItemCostDetailDto d = new ItemCostDetailDto();
			d.setSku(item.getSku());
			d.setCost(item.getAverageCost());
			
			Double feeCalculation = fixedFeeCost + (item.getTotalPrice() * feeSales.get(item.getFeeTypeId()));
			d.setMktPlaceFeeCost(DoubleUtil.round2decimals(feeCalculation));
			d.setCalculationDetail(getFeeDetail(dto, shippingCost, fixedFeeCost, item, feeSales));
			ret.add(d);
		}

		return ret;
	}

	private CalculationDto calculateMktPlaceFee(OrderCalculationValuesDto dto, List<ItemCostDetailDto> itemCostDetail) {
		StringBuffer sb = new StringBuffer();
		Double value = 0.0;
		
		for (ItemCostDetailDto itemCostDetailDto : itemCostDetail) {
			sb.append(itemCostDetailDto.getCalculationDetail()).append("\n");
			value = value + itemCostDetailDto.getMktPlaceFeeCost();
		}
		
		CalculationDto calc = new CalculationDto();
		calc.setCalculationName("FEE");
		calc.setCalculationDetail(sb.toString());
		calc.setValue(DoubleUtil.round2decimals(value));
		
		return calc;
	}
	
	private Double calculateNetAmount(Double orderAmount, CalculationDto tax, CalculationDto shippingCost, Double totalItemCost, Double totalFixedCost) {
		Double ret = orderAmount - tax.getValue() - shippingCost.getValue() - totalItemCost - totalFixedCost;
		return ret;
	}
	
	private Double calculateTotalFixedCost(List<CalculationDto> fixedCost) {
		if (fixedCost == null || fixedCost.isEmpty())
			return 0.0;
		return fixedCost.stream().mapToDouble(f -> f.getValue()).sum();
	}
	
	private Double calculateTotalItemCost(List<ItemCostDetailDto> itemCostDetail) {
		if (itemCostDetail == null || itemCostDetail.isEmpty())
			return 0.0;
		return itemCostDetail.stream().mapToDouble(f -> f.getCost()).sum();
	}

	private String getFeeDetail(OrderCalculationValuesDto dto, CalculationDto shippingCost, Double fixedFeeCost, ItemCalculationValuesDto item, Map<String, Double> feeSales) {
		StringBuffer sb = new StringBuffer();
		sb.append("Custo Fixo = ").append(fixedFeeCost).append(" ( Frete = ").append(shippingCost.getValue()).append(")");
		sb.append("| Comissão = ").append(fixedFeeCost).append("(Custo Fixo) + ").append(item.getTotalPrice()).append("(Valor do Item) x ");
		sb.append(feeSales.get(item.getFeeTypeId())).append("% (comissão = ").append(item.getFeeTypeId()).append(")");
		return sb.toString();
	}
	
	private String getTaxDetail(OrderCalculationValuesDto dto) {
		StringBuffer sb = new StringBuffer();
		sb.append(dto.getOrderAmount());
		sb.append("(Order Amount) x ");
		sb.append(dto.getTaxCost());
		sb.append("(Tax %)");
		return sb.toString();
	}


}
