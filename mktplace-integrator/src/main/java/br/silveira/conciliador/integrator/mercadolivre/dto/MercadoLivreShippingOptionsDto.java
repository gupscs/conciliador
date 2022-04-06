package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MercadoLivreShippingOptionsDto {

	public Destination destination;
	public ArrayList<Option> options;

	@Data
	public static class City {
		public Object id;
		public Object name;
	}

	@Data
	public static class State {
		public String id;
		public String name;
	}

	@Data
	public static class Country {
		public String id;
		public String name;
	}

	@Data
	public static class Destination {
		public String zip_code;
		public City city;
		public State state;
		public Country country;
	}

	@Data
	public static class Speed {
		public int shipping;
		public Object handling;
	}

	@Data
	public static class EstimatedDelivery {
		public Object date;
		public Object pay_before;
		public Object time_from;
		public Object time_to;
	}

	@Data
	public static class Offset {
		public Object date;
		public Object shipping;
	}

	@Data
	public static class TimeFrame {
		public Object from;
		@JsonProperty("to")
		public Object myto;
	}

	@Data
	public static class EstimatedDeliveryTime {
		public String type;
		public Object date;
		public int shipping;
		public Object handling;
		public String unit;
		public Offset offset;
		public TimeFrame time_frame;
		public Object pay_before;
	}

	@Data
	public static class Discount {
		public int rate;
		public String type;
		public int promoted_amount;
	}

	@Data
	public static class Option {
		public int id;
		public String name;
		public int shipping_method_id;
		public String currency_id;
		public double list_cost;
		public double cost;
		public String tracks_shipments_status;
		public String display;
		public Speed speed;
		public EstimatedDelivery estimated_delivery;
		public EstimatedDeliveryTime estimated_delivery_time;
		public Discount discount;
	}

}
