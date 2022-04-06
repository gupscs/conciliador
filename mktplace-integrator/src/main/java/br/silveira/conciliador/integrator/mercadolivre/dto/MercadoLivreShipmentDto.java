package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MercadoLivreShipmentDto {

	public ArrayList<Object> substatus_history;
	public int receiver_id;
	public double base_cost;
	public StatusHistory status_history;
	public String type;
	public Object return_details;
	public int sender_id;
	public String mode;
	public double order_cost;
	public int service_id;
	public ArrayList<ShippingItem> shipping_items;
	public String tracking_number;
	public CostComponents cost_components;
	public long id;
	public String tracking_method;
	public Date last_updated;
	public Object comments;
	public String substatus;
	public Date date_created;
	public Object date_first_printed;
	public String created_by;
	public Object application_id;
	public ShippingOption shipping_option;
	public ArrayList<String> tags;
	public SenderAddress sender_address;
	public Object return_tracking_number;
	public String site_id;
	public Object carrier_info;
	public String market_place;
	public ReceiverAddress receiver_address;
	public long order_id;
	public String status;
	public String logistic_type;

	@Data
	public static class StatusHistory {
		public Object date_shipped;
		public Object date_returned;
		public Object date_delivered;
		public Object date_first_visit;
		public Object date_not_delivered;
		public Object date_cancelled;
		public Date date_handling;
		public Date date_ready_to_ship;
	}

	@Data
	public static class DimensionsSource {
		public String origin;
		public String id;
	}

	@Data
	public static class ShippingItem {
		public int quantity;
		public DimensionsSource dimensions_source;
		public String description;
		public String id;
		public String dimensions;
	}

	@Data
	public static class CostComponents {
		public double loyal_discount;
		public int special_discount;
		public int compensation;
		public int gap_discount;
		public int ratio;
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
		public Date date;
		public Date pay_before;
		public Object schedule;
		public String unit;
		public Offset offset;
		public int shipping;
		public TimeFrame time_frame;
		public int handling;
		public String type;
	}

	@Data
	public static class ShippingOption {
		public double cost;
		public Date estimated_schedule_limit;
		public int shipping_method_id;
		public Date estimated_delivery_final;
		public Date buffering;
		public double list_cost;
		public Date estimated_delivery_limit;
		public String delivery_type;
		public Date estimated_handling_limit;
		public EstimatedDeliveryTime estimated_delivery_time;
		public String name;
		public long id;
		public Date estimated_delivery_extended;
		public String currency_id;
	}

	@Data
	public static class Country {
		public String id;
		public String name;
	}

	@Data
	public static class City {
		public String id;
		public String name;
	}

	@Data
	public static class Municipality {
		public Object id;
		public Object name;
	}

	@Data
	public static class State {
		public String id;
		public String name;
	}

	@Data
	public static class Neighborhood {
		public Object id;
		public String name;
	}

	@Data
	public static class SenderAddress {
		public Country country;
		public String address_line;
		public ArrayList<String> types;
		public Object agency;
		public City city;
		public String geolocation_type;
		public int latitude;
		public Municipality municipality;
		public String street_name;
		public String zip_code;
		public String geolocation_source;
		public Object intersection;
		public String street_number;
		public String comment;
		public int id;
		public State state;
		public Neighborhood neighborhood;
		public Date geolocation_last_updated;
		public int longitude;
	}

	@Data
	public static class ReceiverAddress {
		public Country country;
		public String address_line;
		public ArrayList<String> types;
		public Object agency;
		public City city;
		public String geolocation_type;
		public double latitude;
		public Municipality municipality;
		public String street_name;
		public String zip_code;
		public String geolocation_source;
		public String delivery_preference;
		public Object intersection;
		public String street_number;
		public String receiver_name;
		public Object comment;
		public int id;
		public State state;
		public Neighborhood neighborhood;
		public Date geolocation_last_updated;
		public String receiver_phone;
		public double longitude;
	}

}
