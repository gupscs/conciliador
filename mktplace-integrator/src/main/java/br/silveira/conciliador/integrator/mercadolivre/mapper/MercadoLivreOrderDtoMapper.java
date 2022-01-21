package br.silveira.conciliador.integrator.mercadolivre.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import br.silveira.conciliador.feignClient.dto.LocationDto;
import br.silveira.conciliador.feignClient.dto.OrderDto;
import br.silveira.conciliador.feignClient.dto.OrderItemDto;
import br.silveira.conciliador.feignClient.dto.ShippingDto;
import br.silveira.conciliador.integrator.dto.QueueDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.AlternativePhone;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Buyer;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.OrderItem;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Phone;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Seller;
import br.silveira.conciliador.integrator.mercadolivre.dto.MercadoLivreOrderDto.Shipping;

public class MercadoLivreOrderDtoMapper {

	public static OrderDto mapperToOrderDto(MercadoLivreOrderDto order, QueueDto dto) {
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
		d.setStatus(order.getStatus() );
		d.setShipping(mapperToShippingDto(order.getShipping()));
		d.setBuyer(mapperToLocationDto(order.getBuyer()));
	    d.setSeller(mapperToLocationDto(order.getSeller()));
	    d.setInsertDate(new Date());
	    d.setInsertId("AUTO");
	    d.setFeeType("mapear com o pedido - pendente");
	    //TODO VERIFICAR O FEEDBACK COMO TRATAR
	    d.setOrderItems(mapperToOrderItemDto(order.getOrder_items()));

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
			d.setSku(i.getItem().getSeller_sku());
			d.setTitle(i.getItem().getTitle());
			d.setTotalPrice(i.getFull_unit_price());
			d.setTypeId(null);
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

	public static ShippingDto mapperToShippingDto(Shipping shipment) {
		if(shipment == null) return null;
		ShippingDto dto = new ShippingDto();
		dto.setShippingId(String.valueOf(shipment.getId()));
	    dto.setSubStatus(shipment.getSubstatus());
	    dto.setStatus(shipment.getStatus());
	    dto.setServiceId(String.valueOf(shipment.getService_id()));
	    dto.setCurrency(shipment.getCurrency_id());
	    dto.setShippingMode(shipment.getShipping_mode());
	    dto.setShipmentType(shipment.getShipment_type());
	    dto.setSenderId(String.valueOf(shipment.getSender_id()));
	    dto.setPickingType(String.valueOf(shipment.getPicking_type()));
	    dto.setCreatedDate(shipment.getDate_created());
	    dto.setCost(shipment.getCost());
	    dto.setFirstPrintedDate(shipment.getDate_first_printed());
	    if(shipment.getShipping_option() != null && shipment.getShipping_option().getEstimated_delivery() != null) {
		    dto.setEstimatedDeliveryDate(shipment.getShipping_option().getEstimated_delivery().getDate());
		    dto.setEstimatedDeliveryTimeFrom(shipment.getShipping_option().getEstimated_delivery().getTime_from());
		    dto.setEstimatedDeliveryTimeTo(shipment.getShipping_option().getEstimated_delivery().getTime_to());
	    }
		return dto;
	}

}
