package br.silveira.conciliador.orders.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private LocalDate createdDate;
	private Double cost;
	private LocalDate firstPrintedDate;
	private LocalDate estimatedDeliveryDate;
	private LocalTime estimatedDeliveryTimeFrom;
	private LocalTime estimatedDeliveryTimeTo;

}
