package com.isita.ccapper.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoPreguntaModelo {
	
	private String type;
	private String name;
	private String label;
	public TipoPreguntaModelo(String type, String name, String label) {
		super();
		this.type = type;
		this.name = name;
		this.label = label;
	}
	public TipoPreguntaModelo() {
		super();
	}
	
	

}
