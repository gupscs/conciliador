package br.silveira.conciliador.orders.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Shipping {

	private String shippingId;
	private String subStatus;
	private String status;
	private String serviceId;
	private String currency;
	private String shippingMode;
	private String shipmentType;
	private String senderId;
	private String pickingType;
	private Date createdDate;
	private Double cost;
	private Date firstPrintedDate;
	private Date estimatedDeliveryDate;
	private String estimatedDeliveryTimeFrom;
	private String estimatedDeliveryTimeTo;

}
