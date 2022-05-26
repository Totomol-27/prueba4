package com.isita.ccapper.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estatus_sala")
@NamedStoredProcedureQueries( value = {

		@NamedStoredProcedureQuery(name = "estatus_sala_alta", procedureName = "estatus_sala_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "estatus_sala_modificar", procedureName = "estatus_sala_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "estatus_sala_baja", procedureName = "estatus_sala_baj",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
	}
		
)
@NamedNativeQuery(
		name = "estatus_sala_consulta", 
		query = "{?= call estatus_sala_con( ?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_estatus_sala"
		)
@SqlResultSetMapping(
		name = "resultado_estatus_sala",
		entities = {
				@EntityResult(
						entityClass = EstatusSala.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "descripcion", column = "descripcion"
										),
								@FieldResult(
										name = "estatus", column = "estatus"
										)
								}
						)
		}
)
public class EstatusSala {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name="estatus")
	private int estatus;
	
	public EstatusSala(Long id, String descripcion, int estatus) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public EstatusSala() {
		
	}
	
	
}
