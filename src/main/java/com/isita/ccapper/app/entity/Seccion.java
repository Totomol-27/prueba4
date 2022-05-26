package com.isita.ccapper.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="seccion")
@NamedNativeQuery(
		name = "seccion_consulta", 
		query = "{?= call seccion_con( ?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_seccion"
		)
@SqlResultSetMapping(
		name = "resultado_seccion",
		entities = {
				@EntityResult(
						entityClass = Seccion.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "cuestionarioId", column = "cuestionario_id"
										),
								@FieldResult(
										name = "indice", column = "indice"
										),
								@FieldResult(
										name = "seccion", column = "tipo_seccion"
										),
								@FieldResult(
										name = "descripcion", column = "descripcion"
										)
												
								}
						)
		}
)	
public class Seccion implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cuestionario_id")
	private Integer cuestionarioId;
	
	private Integer indice;
	
	@Column(name = "tipo_seccion")
	private String seccion;
	
	private String descripcion;
	
	public Seccion(Long id, Integer cuestionarioId, Integer indice, String seccion, String descripcion) {
		super();
		this.id = id;
		this.cuestionarioId = cuestionarioId;
		this.indice = indice;
		this.seccion = seccion;
		this.descripcion = descripcion;
	}

	public Seccion() {
		
	}
	
	

}
