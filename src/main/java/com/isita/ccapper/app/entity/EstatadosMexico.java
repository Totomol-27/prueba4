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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estado")
@NamedNativeQuery(
		name = "estado_consulta", 
		query = "{?= call estado_con( ?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_estados"
		)
@SqlResultSetMapping(
		name = "resultado_estados",
		entities = {
				@EntityResult(
						entityClass = EstatadosMexico.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "nombreEstado", column = "est_nombre"
										),
								@FieldResult(
										name = "abreviaEstado", column = "est_abrevi"
										),
								@FieldResult(
										name = "paisId", column = "pais_id"
										),
								@FieldResult(
										name = "estadoPais", column = "est_pais"
										),
								@FieldResult(
										name = "regionEstado", column = "est_region"
										)
								}
						)
		}
)
public class EstatadosMexico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="est_nombre")
	private String nombreEstado;
	
	@Column(name="est_abrevi")
    private String abreviaEstado;
	
	@Column(name="pais_id")
    private Integer paisId;
	
	@Column(name="est_pais")
    private String estadoPais;
	
	@Column(name="est_region")
    private String regionEstado;

	public EstatadosMexico(@NotNull Long id, String nombreEstado, String abreviaEstado, Integer paisId,
			String estadoPais, String regionEstado) {
		super();
		this.id = id;
		this.nombreEstado = nombreEstado;
		this.abreviaEstado = abreviaEstado;
		this.paisId = paisId;
		this.estadoPais = estadoPais;
		this.regionEstado = regionEstado;
	}

	public EstatadosMexico() {
	
	}
	
	
}
