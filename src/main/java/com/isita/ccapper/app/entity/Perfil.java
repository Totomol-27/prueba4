package com.isita.ccapper.app.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="perfil")

@NamedStoredProcedureQueries( value = {
		@NamedStoredProcedureQuery(name = "perfil_alta", procedureName = "perfil_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="clave", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "perfil_modificar", procedureName = "perfil_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="clave", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "perfil_baja", procedureName = "perfil_baj",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
	}
		
)
@NamedNativeQuery(
		name = "perfil_consulta", 
		query = "{?= call perfil_con( ?,?,?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_perfil"
		)
@SqlResultSetMapping(
		name = "resultado_perfil",
		entities = {
				@EntityResult(
						entityClass = Perfil.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "clave", column = "clave"
										),
								@FieldResult(
										name = "nombre", column = "nombre"
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
public class Perfil  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Size(max=50)
	@NotNull
	private String clave;
	
	@Size(max=250)
	@NotNull
	private String nombre;
	
	@Size(max=250)
	@NotNull
	private String descripcion;
	
	@Size(max=1)
	@NotNull
	private int estatus;
	
	@Transient
	private Usuario usuario;
	
	public Perfil(Integer id, @Size(max = 50) @NotNull String clave, @Size(max = 250) @NotNull String nombre,
			@Size(max = 250) @NotNull String descripcion, @Size(max = 1) @NotNull int estatus ) {
		super();
		this.id = id;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Perfil() {
		
	}
	
  /*@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="perfil_pantalla",joinColumns=@JoinColumn(name="perfil_id"),
	inverseJoinColumns=@JoinColumn(name="pantalla_id"))
	private List<Pantalla> perfilPantalla;*/

	
	
}
