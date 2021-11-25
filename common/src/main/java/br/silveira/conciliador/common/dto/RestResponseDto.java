package br.silveira.conciliador.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RestResponseDto<T> {

	private T data;
	
	private List<String> errors;

	public RestResponseDto() {}
	
	public RestResponseDto(T data, List<String> errors) {
		super();
		this.data = data;
		this.errors = errors;
	}
	
	public RestResponseDto( String error) {
		this.errors = new ArrayList<String>();
		errors.add(error);
	}
	
}
