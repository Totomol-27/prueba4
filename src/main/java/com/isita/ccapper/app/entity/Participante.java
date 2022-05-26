package com.isita.ccapper.app.entity;

import java.io.Serializable;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="participante")

@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(name = "participante_alta", procedureName="participante_alt",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "idParticipantes", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "idEvento", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
				}),
		@NamedStoredProcedureQuery(name = "eliminar_participante", procedureName="participante_act",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "idparticipantes", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "evento_id", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "eliminacion", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
				})
})

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "consulta_participante",
		query = "{? = call participante_con(?,?,?,?,?,?)}",
		callable = true,
		resultSetMapping = "participante"
	)
})

@SqlResultSetMapping(
	name = "participante",
	entities = {
			@EntityResult(
				entityClass = Participante.class,
				fields = {
						@FieldResult(
							name="id",
							column = "id"
						),
						@FieldResult(
							name = "persona",
							column = "persona_id"
						),
						@FieldResult(
							name = "evento",
							column = "evento_id"
						),
						@FieldResult(
							name = "estatus",
							column = "estatus"
						)
			}
		)
	}
)
public class Participante implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name="id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="persona_id")
	private Persona persona;
	
	@OneToOne
	@JoinColumn(name="evento_id")
	private Evento evento;
	
	private Integer estatus;
	
	public Participante(@NotNull Long id, Persona persona, Evento evento, Integer estatus) {
		super();
		this.id = id;
		this.persona = persona;
		this.evento = evento;
		this.estatus = estatus;
	}

	public Participante() {
		
	}

}



