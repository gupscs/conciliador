package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MercadoLivreUserInfoDto {

	public int id;
	public String nickname;
	public String registration_date;
	public String first_name;
	public String last_name;
	public String country_id;
	public String email;
	public Identification identification;
	public Address address;
	public Phone phone;
	public AlternativePhone alternative_phone;
	public String user_type;
	public ArrayList<String> tags;
	public Object logo;
	public int points;
	public String site_id;
	public String permalink;
	public String seller_experience;
	public SellerReputation seller_reputation;
	public BuyerReputation buyer_reputation;
	public Status status;
	public Credit credit;
	
	@Data
	public static class Identification {
		public String type;
		public String number;
	}
	@Data
	public static class Address {
		public String state;
		public String city;
		public String address;
		public String zip_code;
	}
	@Data
	public static class Phone {
		public String area_code;
		public String number;
		public String extension;
		public boolean verified;
	}
	@Data
	public static class AlternativePhone {
		public String area_code;
		public String number;
		public String extension;
	}
	@Data
	public static class Ratings {
		public int positive;
		public int negative;
		public int neutral;
	}
	@Data
	public static class Transactions {
		public String period;
		public int total;
		public int completed;
		public int canceled;
		public Ratings ratings;
		public Unrated unrated;
		public NotYetRated not_yet_rated;
	}
	@Data
	public static class SellerReputation {
		public Object level_id;
		public Object power_seller_status;
		public Transactions transactions;
	}
	@Data
	public static class Canceled {
		public Object total;
		public Object paid;
	}
	@Data
	public static class Unrated {
		public Object total;
		public Object paid;
	}
	@Data
	public static class NotYetRated {
		public Object total;
		public Object paid;
		public Object units;
	}
	@Data
	public static class BuyerReputation {
		public int canceled_transactions;
		public Transactions transactions;
		public ArrayList<Object> tags;
	}
	@Data
	public static class ImmediatePayment {
		public boolean required;
		public ArrayList<Object> reasons;
	}
	@Data
	public static class List {
		public boolean allow;
		public ArrayList<Object> codes;
		public ImmediatePayment immediate_payment;
	}
	@Data
	public static class Buy {
		public boolean allow;
		public ArrayList<Object> codes;
		public ImmediatePayment immediate_payment;
	}
	@Data
	public static class Sell {
		public boolean allow;
		public ArrayList<Object> codes;
		public ImmediatePayment immediate_payment;
	}
	@Data
	public static class Billing {
		public boolean allow;
		public ArrayList<Object> codes;
	}
	@Data
	public static class Status {
		public String site_status;
		public List list;
		public Buy buy;
		public Sell sell;
		public Billing billing;
		public boolean mercadopago_tc_accepted;
		public String mercadopago_account_type;
		public String mercadoenvios;
		public boolean immediate_payment;
		public boolean confirmed_email;
		public String user_type;
		public String required_action;
	}
	@Data
	public static class Credit {
		public int consumed;
		public String credit_level_id;
	}

}