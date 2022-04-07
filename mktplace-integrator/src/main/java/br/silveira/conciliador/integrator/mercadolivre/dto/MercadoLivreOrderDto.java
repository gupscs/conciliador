package br.silveira.conciliador.integrator.mercadolivre.dto;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MercadoLivreOrderDto {

	
	
	
	public long id;
    public Date date_created;
    public Date date_closed;
    public Date last_updated;
    private Date expiration_date;
    public Object manufacturing_ending_date;
    public Object comment;
    public Object pack_id;
    public Object pickup_id;
    public OrderRequest order_request;
    public Object fulfilled;
    public List<Object> mediations;
    public double total_amount;
    public int paid_amount;
    public Coupon coupon;
    public List<OrderItem> order_items;
    public String currency_id;
    public List<Payment> payments;
    public Shipping shipping;
    public String status;
    public StatusDetail status_detail;
    public List<String> tags;
    public Feedback feedback;
    public Seller seller;
    public Buyer buyer;
    public Taxes taxes;
	
    @Data
    public static class Taxes{
        private double amount;
        private String currency_id;
        private long id;
    }
    
    @Data
    public static class OrderRequest{
        private Object _return;
        private Object change;
    }
    
	@Data
	public static class StatusDetail {
		private String description;
		private String code;
	}
	
	@Data
	public static class Coupon{
		private long id ;
		private double amount;
	}

	@Data
	public static class Item {
		private String id;
		private String title;
		private String category_id;
		private Object variation_id;
		private String seller_custom_field;
		private List<Object> variation_attributes;
		private String warranty;
		private String condition;
		private String seller_sku;
		private double global_price;
		private double net_weight;
	}
	
	@Data
	public static class RequestedQuantity{
	    private int value;
	    private String measure;
	}

	@Data
	public static class OrderItem {
		   private Item item;
		   private double quantity;
		   private RequestedQuantity requested_quantity;
		   private int picked_quantity;
		   private double unit_price;
		   private double full_unit_price;
		   private String currency_id;
		   private Object manufacturing_days;
		   private double sale_fee;
		   private String listing_type_id;
	}

	@Data
	public static class Collector {
		private long id;
	}

	@Data
	public static class AtmTransferReference {
		private Object company_id;
		private Object transaction_id;
	}

	@Data
	public static class Payment {
		private long id;
		private long order_id;
		private long payer_id;
		private Collector collector;
		private String currency_id;
		private String status;
		private String status_code;
		private String status_detail;
		private double transaction_amount;
		private double shipping_cost;
		private int overpaid_amount;
		private double total_paid_amount;
		private double marketplace_fee;
		private double coupon_amount;
		private String date_created;
		private String date_last_modified;
		private long card_id;
		private String reason;
		private String activation_uri;
		private String payment_method_id;
		private int installments;
		private String issuer_id;
		private AtmTransferReference atm_transfer_reference;
		private Object coupon_id;
		private String operation_type;
		private String payment_type;
		private List<Object> available_actions;
		private double installment_amount;
		private Object deferred_period;
		private String date_approved;
		private String authorization_code;
		private String transaction_order_id;
	}

	@Data
	public static class State {
		private String id;
		private String name;
	}

	@Data
	public static class Country {
		private String id;
		private String name;
	}

	@Data
	public static class City {
		private String id;
		private String name;
	}

	@Data
	public static class ReceiverAddress {
		private long id;
		private String zip_code;
		private Object latitude;
		private Object longitude;
		private String street_number;
		private String street_name;
		private State state;
		private Object comment;
		private String address_line;
		private Country country;
		private City city;
	}

	@Data
	public static class Speed {
		private long shipping;
		private Object handling;
	}

	@Data
	public static class EstimatedDelivery {
		private Date date;
		private String time_from;
		private String time_to;
	}

	@Data
	public static class ShippingOption {
		private long id;
		private long shipping_method_id;
		private String name;
		private String currency_id;
		private double cost;
		private Speed speed;
		private EstimatedDelivery estimated_delivery;
	}

	@Data
	public static class ShippingItem {
		private String id;
		private String description;
		private int quantity;
		private String dimensions;
	}

	@Data
	public static class Shipping {
		private String substatus;
		private String status;
		private long id;
		private long service_id;
		private String currency_id;
		private String shipping_mode;
		private String shipment_type;
		private int sender_id;
		private Object picking_type;
		private ReceiverAddress receiver_address;
		private Date date_created;
		private double cost;
		private Date date_first_printed;
		private ShippingOption shipping_option;
		private List<ShippingItem> shipping_items;
	}

	@Data
	public static class Phone {
		private String area_code;
		private String number;
		private String extension;
		private boolean verified;
	}

	@Data
	public static class AlternativePhone {
		private String area_code;
		private String number;
		private String extension;
	}

	@Data
	public static class BillingInfo {
		private String doc_type;
		private String doc_number;
	}

	@Data
	public static class Buyer {
		private long id;
		private String nickname;
		private String first_name;
		private String last_name;
		private String email;
		private Phone phone;
		private AlternativePhone alternative_phone;
		private BillingInfo billing_info;
	}

	@Data
	public static class Seller {
		private long id;
		private String nickname;
		private String email;
		private Phone phone;
		private AlternativePhone alternative_phone;
		private String first_name;
		private String last_name;
	}

	@Data
	public static class Sale {
		private long id;
		private Date date_created;
		private boolean fulfilled;
		private String rating;
		private String status;
	}

	@Data
	public static class Purchase {
		private long id;
		private Date date_created;
		private boolean fulfilled;
		private String rating;
		private String status;
	}

	@Data
	public static class Feedback {
		private Sale sale;
		private Purchase purchase;
	}

	@Data
	public static class Value {
		private String id;
		private String name;
		private int results;
	}

}
