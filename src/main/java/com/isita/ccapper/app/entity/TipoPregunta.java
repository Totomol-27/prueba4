package com.isita.ccapper.app.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tipo_pregunta")
//@NamedNativeQuery(
//		name = "tipopregunta_consulta", 
//		query = "{?= call tipo_pregunta_con( ? )}",
//		callable = true,
//		resultSetMapping = "resultado_tipopregunta"
//		)
//@SqlResultSetMapping(
//		name = "resultado_tipopregunta",
//		entities = {
//				@EntityResult(
//						entityClass = TipoPregunta.class,
//						fields = {
//								@FieldResult(
//										name = "id", column = "id"
//										),
//								@FieldResult(
//										name = "tipo", column = "tipo"
//										),
//								@FieldResult(
//										name = "descripcion", column = "descripcion"
//										),
//								@FieldResult(
//										name = "estatus", column = "estatus"
//										)		
//								}
//						)
//		}
//)
public class TipoPregunta  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	private String descripcion;
	private Integer estatus;
	public TipoPregunta(Long id, String tipo, String descripcion, Integer estatus) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}
	
	public TipoPregunta() {
	}

	
}
