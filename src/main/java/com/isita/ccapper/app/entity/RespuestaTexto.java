package com.isita.ccapper.app.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "respuesta_texto")
@NamedStoredProcedureQueries(value = {
		


		@NamedStoredProcedureQuery(name = "respuesta_texto_alta", procedureName = "respuesta_texto_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="pregunta_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="persona_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="respuesta", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name ="fecha_creacion", type = Date.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "respuesta_texto_modificar", procedureName = "respusta_texto_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="pregunta_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="persona_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name="respuesta", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN,  name ="fecha_creacion", type = Date.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
				
			
})
})

@NamedNativeQuery(
		name = "respuesta_texto_consulta", 
		query = "{?= call respuesta_texto_con( ?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_respuesta_texto"
		)
@SqlResultSetMapping(
		name = "resultado_respuesta_texto",
		entities = {
				@EntityResult(
						entityClass = RespuestaOpcion.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "pregunta", column = "pregunta_id"
										),
								@FieldResult(
										name = "persona", column = "persona_id"
										),
								@FieldResult(
										name = "respuesta", column = "respuesta"
										),
								@FieldResult(
										name = "fechaCreacion", column = "fecha_creacion"
										)
								}
						)
		}
)


public class RespuestaTexto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="persona_id")
	private Persona persona;
	
	@Column(name="respuesta")
	private String respuesta;
	
	@Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Date fechaCreacion;

	

	/*@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="respuesta_texto",joinColumns=@JoinColumn(name="pregunta_id"),
	inverseJoinColumns=@JoinColumn(name="persona_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"pregunta_id", "persona_id"})})
	private List<Persona> persona;*/

	
	public RespuestaTexto() {
		//this.persona = new ArrayList<>();
	}



	public RespuestaTexto(Long id, Pregunta pregunta, Persona persona, String respuesta, Timestamp fechaCreacion) {
		super();
		this.id = id;
		this.pregunta = pregunta;
		this.persona = persona;
		this.respuesta = respuesta;
		this.fechaCreacion = fechaCreacion;
	}



	


	
	

}
