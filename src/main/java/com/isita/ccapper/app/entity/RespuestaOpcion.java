package com.isita.ccapper.app.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "respuesta_opciones")
@NamedStoredProcedureQueries(value = {
		


		@NamedStoredProcedureQuery(name = "respuesta_opcion_alta", procedureName = "respuesta_opciones_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="pregunta_tipo_opcion_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="opciones_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="persona_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="opcion_elegida", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="opcion_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name = "fecha_creacion", type = Date.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "respuesta_opciones_modificar", procedureName = "respusta_opciones_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="opciones_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="preguta_tipo_opcion_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="persona_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="opcion_elegida", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name = "fecha_creacion", type = Date.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
			
})
@NamedNativeQuery(
		name = "respuesta_opciones_consulta", 
		query = "{?= call respuesta_opciones_con( ?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_respuesta_opciones"
		)
@SqlResultSetMapping(
		name = "resultado_respuesta_opciones",
		entities = {
				@EntityResult(
						entityClass = RespuestaOpcion.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "preguntaTipoOpcionId", column = "pregunta_tipo_opcion_id"
										),
								@FieldResult(
										name = "opcionesId", column = "opciones_id"
										),
								@FieldResult(
										name = "personaId", column = "persona_id"
										),
								@FieldResult(
										name = "opcionElegida", column = "opcion_elegida"
										),
								@FieldResult(
										name = "fechaCreacion", column = "fecha_creacion"
										)
								}
						)
		}
)

public class RespuestaOpcion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="pregunta_tipo_opcion_id")
	private Integer preguntaTipoOpcionId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="opciones_id")
	private Opcion opcion;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="persona_id")
	private Persona persona;
	
	@Column(name="opcion_elegida")
	private String opcionElegida;
	
	
	@Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Date fechaCreacion;
	
	@Transient
	private List<Opcion> listOpciones;
	
	@Transient
	private TipoPregunta tipoPregunta;

	public RespuestaOpcion() {
	}



	public RespuestaOpcion(Long id, Integer preguntaTipoOpcionId, Opcion opcion, Persona persona, String opcionElegida,
			Date fechaCreacion) {
		super();
		this.id = id;
		this.preguntaTipoOpcionId = preguntaTipoOpcionId;
		this.opcion = opcion;
		this.persona = persona;
		this.opcionElegida = opcionElegida;
		this.fechaCreacion = fechaCreacion;
	}
	
}









	


