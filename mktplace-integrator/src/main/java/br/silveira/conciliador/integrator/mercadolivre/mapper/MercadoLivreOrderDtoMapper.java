package br.silveira.conciliador.integrator.mercadolivre.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import br.silveira.conciliador.feignClient.dto.LocationDto;
import br.silveira.conciliador.feignClient.dto.OrderDto;
import br.silveira.conciliador.feignClient.dto.OrderItemDto;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.AlternativePhone;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Buyer;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.OrderItem;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Phone;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Seller;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentCostDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShipmentDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreShippingOptionsDto;

public class MercadoLivreOrderDtoMapper {

	public static OrderDto mapperToOrderDto(MercadoLivreOrderDto order, MercadoLivreShipmentDto shipment, QueueDto dto) {
		OrderDto d = new OrderDto();
		d.setCompanyId(dto.getCompanyId());
		d.setMktPlace(dto.getMarketPlace());
		d.setOrderId(String.valueOf(order.getId()) );
		d.setCurrency(order.getCurrency_id() );
		d.setClosedDate(order.getDate_closed());
		d.setCreatedDate(order.getDate_created() );
		d.setExpirationDate(order.getExpiration_date() );
		d.setLastUpdatedDate(order.getLast_updated() );
		d.setTotalAmount(order.getTotal_amount() );
		d.setMktPlaceStatus(order.getStatus() );
		d.setBuyer(mapperToLocationDto(order.getBuyer()));
	    d.setSeller(mapperToLocationDto(order.getSeller()));
	    d.setInsertDate(new Date());
	    d.setInsertId("AUTO");
	    //TODO VERIFICAR O FEEDBACK COMO TRATAR
	    d.setOrderItems(mapperToOrderItemDto(order.getOrder_items()));
	    
	    d.setShippingMethodId(shipment.getShipping_option().getShipping_method_id());
	    d.setReceiverZipcode(shipment.getReceiver_address().getZip_code());
		return d;
	}

	public static List<OrderItemDto> mapperToOrderItemDto(List<OrderItem> order_items) {
		List<OrderItemDto> dto = new ArrayList<OrderItemDto>();
		for (OrderItem i : order_items) {
			OrderItemDto d = new OrderItemDto();
			d.setMktPlaceItemId(i.getItem().getId());
			d.setCategory(i.getItem().getCategory_id());
			d.setCurrency(i.getCurrency_id());
			d.setQuantity(i.getQuantity());
			d.setSaleFee(i.getSale_fee());
			d.setSku(i.getItem().getSeller_sku() == null ? i.getItem().getSeller_custom_field() : i.getItem().getSeller_sku() );
			d.setTitle(i.getItem().getTitle());
			d.setTotalPrice(i.getFull_unit_price());
			d.setFeeTypeId(i.getListing_type_id());
			d.setUnitPrice(i.getUnit_price());
			dto.add(d);
		}
		return dto;
	}

	public static LocationDto mapperToLocationDto(Seller seller) {
		LocationDto dto = new LocationDto();
		dto.setId(String.valueOf(seller.getId()));
		dto.setName(seller.getFirst_name() + (StringUtils.hasLength(seller.getLast_name())?" "+seller.getLast_name():""));
		dto.setNickname(seller.getNickname());
		dto.setPhoneNo(formatPhone(seller.getPhone()));
		dto.setAltPhoneNo(formatPhone(seller.getAlternative_phone()));
		dto.setEmail(seller.getEmail());
		return dto;
	}

	public static LocationDto mapperToLocationDto(Buyer buyer) {
		LocationDto dto = new LocationDto();
		dto.setId(String.valueOf(buyer.getId()));
		dto.setName(buyer.getFirst_name() + (StringUtils.hasLength(buyer.getLast_name())?" "+buyer.getLast_name():""));
		dto.setNickname(buyer.getNickname());
		dto.setPhoneNo(formatPhone(buyer.getPhone()));
		dto.setAltPhoneNo(formatPhone(buyer.getAlternative_phone()));
		dto.setEmail(buyer.getEmail());
		return dto;
	}

	private static String formatPhone(AlternativePhone phone) {
		if(phone == null) return null;
		StringBuilder ret = new StringBuilder();
		if(phone.getArea_code()!=null) ret.append(phone.getArea_code());
		if(phone.getExtension()!=null)  ret.append(phone.getExtension());
		if(phone.getNumber()!=null) ret.append(phone.getNumber());
		return ret.toString();
	}

	private static String formatPhone(Phone phone) {
		if(phone == null) return null;
		StringBuilder ret = new StringBuilder();
		if(phone.getArea_code()!=null) ret.append(phone.getArea_code());
		if(phone.getExtension()!=null)  ret.append(phone.getExtension());
		if(phone.getNumber()!=null) ret.append(phone.getNumber());
		return ret.toString();
	}
}
