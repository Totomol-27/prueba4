package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
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

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="programacion")

@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(name = "alta_programacion", procedureName ="programacion_alt",
				parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name ="persona_id", type=Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name ="expositor", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "evento_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "sala_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_inicio", type = Timestamp.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_fin", type = Timestamp.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
				}),
		@NamedStoredProcedureQuery(name = "eliminar_programacion", procedureName = "programacion_act",
				parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name ="idprogramacion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "evento_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eliminacion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name ="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
				}),
		@NamedStoredProcedureQuery(name = "modificar_programacion", procedureName = "programacion_mod",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name ="persona_id", type=Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name ="expositor", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "evento_id", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "sala_id", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_inicio", type = Timestamp.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_fin", type = Timestamp.class),
//						@StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
				})
})

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "consulta_programacion",
		query = "{? = call programacion_con(?,?,?,?,?)}",
		callable = true,
		resultSetMapping = "programacion"
	)
})

@SqlResultSetMapping(
	name = "programacion",
	entities = {
			@EntityResult(
				entityClass = Programacion.class,
				fields = {
					@FieldResult(
						name = "id",
						column = "id"
					),
					@FieldResult(
						name = "persona",
						column = "persona_id"
					),
					@FieldResult(
						name = "expositor",
						column = "expositor"
					),
					@FieldResult(
						name = "evento",
						column = "evento_id"
					),
					@FieldResult(
						name = "sala",
						column = "sala_id"
					),
					@FieldResult(
						name = "nombre",
						column = "nombre"
					),
					@FieldResult(
						name = "descripcion",
						column = "descripcion"
					),
					@FieldResult(
						name = "fecha_inicio",
						column = "fecha_inicio"
					),
					@FieldResult(
						name = "fecha_fin",
						column = "fecha_fin"
					),
					@FieldResult(
						name = "estatus",
						column = "estatus"
					)
				}
			)
	}
)
public class Programacion implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param persona_id
	 * @param expositor
	 * @param evento_id
	 * @param sala_id
	 * @param nombre
	 * @param descripcion
	 * @param fecha_inicio
	 * @param fecha_fin
	 * @param estatus
	 */	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="persona_id")
	private Persona persona;
	
	private Integer expositor;
	
	@OneToOne
	@JoinColumn(name="evento_id")
	private Evento evento;
	
	@OneToOne
	@JoinColumn(name="sala_id")
	private Sala sala;
	
	private String nombre;
	private String descripcion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="America/Mexico_City")
	@Column(name ="fecha_inicio", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
	private Timestamp fecha_inicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="America/Mexico_City")
	@Column(name ="fecha_fin", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)	
	private Timestamp fecha_fin;
	
	private Integer estatus;

	public Programacion(Long id, Persona persona, Integer expositor, Evento evento, Sala sala, String nombre,
			String descripcion, Timestamp fecha_inicio, Timestamp fecha_fin, Integer estatus) {
		super();
		this.id = id;
		this.persona = persona;
		this.expositor = expositor;
		this.evento = evento;
		this.sala = sala;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.estatus = estatus;
	}

	public Programacion() {
		
	}
	
}
