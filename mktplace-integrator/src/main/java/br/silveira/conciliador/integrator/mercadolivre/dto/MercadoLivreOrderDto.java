package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MercadoLivreOrderDto {
	
	    private String query;
	    private String display;
	    private Paging paging;
	    private List<Result> results;
	    private Sort sort;
	    private List<AvailableSort> available_sorts;
	    private List<Object> filters;
	    private List<AvailableFilter> available_filters;
	    
	    @Data
		public class Paging{
		    private int total;
		    private int offset;
		    private int limit;
		}

	    @Data
		public class StatusDetail{
		    private String description;
		    private String code;
		}

	    @Data
		public class Item{
		    private String id;
		    private String title;
		    private Object seller_custom_field;
		    private List<Object> variation_attributes;
		    private String category_id;
		    private Object variation_id;
		}

	    @Data
		public class OrderItem{
		    private String currency_id;
		    private Item item;
		    private double sale_fee;
		    private double quantity;
		    private double unit_price;
		}

	    @Data
		public class Collector{
		    private int id;
		}

	    @Data
		public class AtmTransferReference{
		    private Object company_id;
		    private Object transaction_id;
		}

	    @Data
		public class Payment{
		    private int id;
		    private int order_id;
		    private int payer_id;
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
		    private LocalDate date_created;
		    private LocalDate date_last_modified;
		    private int card_id;
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
		    private LocalDate date_approved;
		    private String authorization_code;
		    private String transaction_order_id;
		}

	    @Data
		public class State{
		    private String id;
		    private String name;
		}

	    @Data
		public class Country{
		    private String id;
		    private String name;
		}

	    @Data
		public class City{
		    private String id;
		    private String name;
		}

	    @Data
		public class ReceiverAddress{
		    private int id;
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
		public class Speed{
		    private int shipping;
		    private Object handling;
		}

	    @Data
		public class EstimatedDelivery{
		    private Date date;
		    private String time_from;
		    private String time_to;
		}

	    @Data
		public class ShippingOption{
		    private int id;
		    private int shipping_method_id;
		    private String name;
		    private String currency_id;
		    private double cost;
		    private Speed speed;
		    private EstimatedDelivery estimated_delivery;
		}

	    @Data
		public class ShippingItem{
		    private String id;
		    private String description;
		    private int quantity;
		    private String dimensions;
		}

	    @Data
		public class Shipping{
		    private String substatus;
		    private String status;
		    private long id;
		    private int service_id;
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
		public class Phone{
		    private String area_code;
		    private String number;
		    private String extension;
		    private boolean verified;
		}

	    @Data
		public class AlternativePhone{
		    private String area_code;
		    private String number;
		    private String extension;
		}

	    @Data
		public class BillingInfo{
		    private String doc_type;
		    private String doc_number;
		}
	    
	    @Data
		public class Buyer{
		    private int id;
		    private String nickname;
		    private String first_name;
		    private String last_name;
		    private String email;
		    private Phone phone;
		    private AlternativePhone alternative_phone;
		    private BillingInfo billing_info;
		}

	    @Data
		public class Seller{
		    private int id;
		    private String nickname;
		    private String email;
		    private Phone phone;
		    private AlternativePhone alternative_phone;
		    private String first_name;
		    private String last_name;
		}

	    @Data
		public class Sale{
		    private long id;
		    private Date date_created;
		    private boolean fulfilled;
		    private String rating;
		    private String status;
		}

	    @Data
		public class Purchase{
		    private long id;
		    private Date date_created;
		    private boolean fulfilled;
		    private String rating;
		    private String status;
		}

	    @Data
		public class Feedback{
		    private Sale sale;
		    private Purchase purchase;
		}

	    @Data
		public class Result{
		    private int id;
		    private String comments;
		    private String status;
		    private StatusDetail status_detail;
		    private LocalDate date_created;
		    private LocalDate date_closed;
		    private LocalDate expiration_date;
		    private LocalDate date_last_updated;
		    private boolean hidden_for_seller;
		    private String currency_id;
		    private List<OrderItem> order_items;
		    private double total_amount;
		    private List<Object> mediations;
		    private List<Payment> payments;
		    private Shipping shipping;
		    private Buyer buyer;
		    private Seller seller;
		    private Feedback feedback;
		    private List<String> tags;
		}

	    @Data
		public class Sort{
		    private String id;
		    private String name;
		}

	    @Data
		public class AvailableSort{
		    private String id;
		    private String name;
		}

	    @Data
		public class Value{
		    private String id;
		    private String name;
		    private int results;
		}

	    @Data
		public class AvailableFilter{
		    private String id;
		    private String name;
		    private String type;
		    private List<Value> values;
		}

}


