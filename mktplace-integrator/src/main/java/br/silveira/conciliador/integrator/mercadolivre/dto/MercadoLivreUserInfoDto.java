package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MercadoLivreUserInfoDto {
	
	
	public int id;
    public int user_id;
    public String contact;
    public String phone;
    public String address_line;
    public String floor;
    public String apartment;
    public String street_number;
    public String street_name;
    public String zip_code;
    public City city;
    public State state;
    public Country country;
    public Municipality municipality;
    public String comment;
    public String geolocation_type;
    public double latitude;
    public double longitude;
    public String status;
    public LocalDateTime date_created;
    public boolean normalized;
    
    @Data
	public class City{
	    public String id;
	    public String name;
	}

    @Data
	public class State{
	    public String id;
	    public String name;
	}

    @Data
	public class Country{
	    public String id;
	    public String name;
	}
    
    @Data
	public class Municipality{
	    public String id;
	    public String name;
	}
}
