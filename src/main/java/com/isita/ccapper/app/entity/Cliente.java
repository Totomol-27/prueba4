package com.isita.ccapper.app.entity;

import java.io.Serializable;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cliente")

@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(name="alta_cliente", procedureName="cliente_alt",
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "estado_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "giro", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "clave", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "rfc", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "telefono", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "correo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razon_social", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "municipio", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "colonia", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "calle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "numero_exterior", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
			}),
		@NamedStoredProcedureQuery(name = "eliminar_cliente", procedureName = "cliente_act",
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name ="idclientes", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
			}),
		@NamedStoredProcedureQuery(name = "modificar_cliente", procedureName = "cliente_mod", 
			parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "estado_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "giro", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "clave", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "rfc", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "telefono", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "correo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "razon_social", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "municipio", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "colonia", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "calle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "numero_exterior", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
			})
})

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "consulta_cliente",
		query = "{? = call cliente_con(?,?,?,?,?)}",
		callable = true,
		resultSetMapping = "cliente"
	)
})

@SqlResultSetMapping(
	name = "cliente",
	entities = {
			@EntityResult(
			entityClass = Cliente.class,
			fields = {
				@FieldResult(
					name = "id",
					column = "id"
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
					name = "estado_id",
					column = "estado_id"
				),
				@FieldResult(
					name = "giro",
					column = "giro"
				),
				@FieldResult(
					name = "clave",
					column = "clave"
				),
				@FieldResult(
					name = "rfc",
					column = "rfc"
				),
				@FieldResult(
					name = "telefono",
					column = "telefono"
				),
				@FieldResult(
					name = "correo",
					column = "correo"
				),
				@FieldResult(
					name = "razon_social",
					column = "razon_social"
				),
				@FieldResult(
					name = "municipio",
					column = "municipio"
				),
				@FieldResult(
					name = "colonia",
					column = "colonia"
				),
				@FieldResult(
					name = "calle",
					column = "calle"
				),
				@FieldResult(
					name = "numero_exterior",
					column = "numero_exterior"
				),
				@FieldResult(
					name = "estatus",
					column = "estatus"
				)
				
			}
		)
	}
)

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	

	public Cliente(@NotNull Long id, @NotNull @Size(max = 100) String nombre,
			@NotNull @Size(max = 250) String descripcion, @NotNull Integer estado_id,
			@NotNull @Size(max = 150) String giro, @NotNull @Size(max = 10) String clave,
			@NotNull @Size(max = 13) String rfc, @NotNull @Size(max = 10) String telefono,
			@NotNull @Size(max = 50) String correo, @NotNull @Size(max = 200) String razon_social,
			@NotNull @Size(max = 100) String municipio, @NotNull @Size(max = 100) String colonia,
			@NotNull @Size(max = 100) String calle, @NotNull @Size(max = 10) String numero_exterior,
			@NotNull Integer estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado_id = estado_id;
		this.giro = giro;
		this.clave = clave;
		this.rfc = rfc;
		this.telefono = telefono;
		this.correo = correo;
		this.razon_social = razon_social;
		this.municipio = municipio;
		this.colonia = colonia;
		this.calle = calle;
		this.numero_exterior = numero_exterior;
		this.estatus = estatus;
	}

	
	

	public Cliente() {
		super();
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Size(max=100)
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Size(max=250)
	@Column(name = "descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name = "estado_id")
	private Integer estado_id;
	
	@NotNull
	@Size(max=150)
	@Column(name = "giro")
	private String giro;
	
	@NotNull
	@Size(max=10)
	@Column(name = "clave")
	private String clave;
	
	@NotNull
	@Size(max=13)
	@Column(name = "rfc")
	private String rfc;
	
	@NotNull
	@Size(max=10)
	@Column(name = "telefono")
	private String telefono;
	
	@NotNull
	@Size(max=50)
	@Column(name = "correo")
	private String correo;
	
	@NotNull
	@Size(max=200)
	@Column(name = "razon_social")
	private String razon_social;
	
	@NotNull
	@Size(max=100)
	@Column(name = "municipio")
	private String municipio;
	
	@NotNull
	@Size(max=100)
	@Column(name = "colonia")
	private String colonia;
	
	@NotNull
	@Size(max=100)
	@Column(name = "calle")
	private String calle;
	
	@NotNull
	@Size(max=10)
	@Column(name = "numero_exterior")
	private String numero_exterior;
	
	@NotNull
	@Column(name = "estatus")
	private Integer estatus;
}
