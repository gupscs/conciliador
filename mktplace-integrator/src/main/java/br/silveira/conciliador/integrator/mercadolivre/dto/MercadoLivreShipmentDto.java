package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MercadoLivreShipmentDto {

	public int id;
	public Object external_reference;
	public String status;
	public String substatus;
	public Date date_created;
	public Date last_updated;
	public int declared_value;
	public Dimensions dimensions;
	public Logistic logistic;
	public Source source;
	public String tracking_number;
	public Origin origin;
	public Destination destination;
	public LeadTime lead_time;
	public ArrayList<String> tags;

	@Data
	public static class Dimensions {
		public int height;
		public int width;
		public int length;
		public int weight;
	}

	@Data
	public static class Logistic {
		public String direction;
		public String mode;
		public String type;
	}

	@Data
	public static class Source {
		public String site_id;
		public String market_place;
		public Object application_id;
	}

	@Data
	public static class City {
		public String id;
		public String name;
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
	public static class Neighborhood {
		public Object id;
		public String name;
	}

	@Data
	public static class Municipality {
		public Object id;
		public Object name;
	}

	@Data
	public static class Agency {
		public Object carrier_id;
		public Object agency_id;
		public Object description;
		public Object phone;
		public Object open_hours;
	}

	@Data
	public static class ShippingAddress {
		public int address_id;
		public String address_line;
		public String street_name;
		public String street_number;
		public String comment;
		public String zip_code;
		public City city;
		public State state;
		public Country country;
		public Neighborhood neighborhood;
		public Municipality municipality;
		public Agency agency;
		public ArrayList<String> types;
		public int latitude;
		public int longitude;
		public String geolocation_type;
		public String delivery_preference;
	}

	@Data
	public static class Origin {
		public String type;
		public long sender_id;
		public ShippingAddress shipping_address;
	}

	@Data
	public static class Destination {
		public String type;
		public long receiver_id;
		public String receiver_name;
		public String receiver_phone;
		public Object comments;
		public ShippingAddress shipping_address;
	}

	@Data
	public static class ShippingMethod {
		public int id;
		public String name;
		public String type;
		public String deliver_to;
	}

	@Data
	public static class EstimatedScheduleLimit {
		public Object date;
	}

	@Data
	public static class Offset {
		public Date date;
		public int shipping;
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
		public Date date;
		public String unit;
		public Offset offset;
		public TimeFrame time_frame;
		public Object pay_before;
		public int shipping;
		public int handling;
		public Object schedule;
	}

	@Data
	public static class EstimatedDeliveryLimit {
		public Date date;
		public int offset;
	}

	@Data
	public static class EstimatedDeliveryFinal {
		public Date date;
		public int offset;
	}

	@Data
	public static class EstimatedDeliveryExtended {
		public Date date;
		public int offset;
	}

	@Data
	public static class EstimatedHandlingLimit {
		public Date date;
	}

	@Data
	public static class LeadTime {
		public int option_id;
		public ShippingMethod shipping_method;
		public String currency_id;
		public int cost;
		public double list_cost;
		public String cost_type;
		public int service_id;
		public String delivery_type;
		public EstimatedScheduleLimit estimated_schedule_limit;
		public EstimatedDeliveryTime estimated_delivery_time;
		public EstimatedDeliveryLimit estimated_delivery_limit;
		public EstimatedDeliveryFinal estimated_delivery_final;
		public EstimatedDeliveryExtended estimated_delivery_extended;
		public EstimatedHandlingLimit estimated_handling_limit;
		public ArrayList<String> delay;
	}

}
