package br.silveira.conciliador.integrator.mercadolivre.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MercadoLivreUserMeDto {
	
	
    public int id;
    public int user_id;
    public Object contact;
    public Object phone;
    public String address_line;
    public Object floor;
    public Object apartment;
    public String street_number;
    public String street_name;
    public String zip_code;
    public City city;
    public State state;
    public Country country;
    public Neighborhood neighborhood;
    public Municipality municipality;
    public SearchLocation search_location;
    public List<Object> types;
    public String comment;
    public String geolocation_type;
    public double latitude;
    public double longitude;
    public Object status;
    public Date date_created;
    public boolean normalized;
    public OpenHours open_hours;
	
	
	public class City{
	    public String id;
	    public String name;
	}

	public class State{
	    public String id;
	    public String name;
	}

	public class Country{
	    public String id;
	    public String name;
	}

	public class Neighborhood{
	    public Object id;
	    public Object name;
	}

	public class Municipality{
	    public Object id;
	    public Object name;
	}

	public class SearchLocation{
	    public State state;
	    public City city;
	    public Neighborhood neighborhood;
	}

	public class OnHolidays{
	    public List<Object> hours;
	    public String status;
	}

	public class OpenHours{
	    public OnHolidays on_holidays;
	}

	


	
}
