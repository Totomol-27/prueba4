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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pantalla")

@NamedStoredProcedureQueries(value = {
	@NamedStoredProcedureQuery(name="alta_pantalla", procedureName = "pantalla_alt",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "clave", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "indice", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "pantalla_padre", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "aplicacion_id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "eliminar_pantalla", procedureName = "pantalla_act",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "idPantallas", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "modificar_pantalla", procedureName = "pantalla_mod",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "clave", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion",type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "indice", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "pantalla_padre", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "aplicacion_id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
		//@StoredProcedureParameter(mode = ParameterMode.IN, name = "tipo_modificacion", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
	})
})

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "consulta_pantalla",
		query = "{? = call pantalla_con(?,?,?,?,?,?,?)}",
		callable = true,
		resultSetMapping = "pantalla"
	)
})

@SqlResultSetMapping(
	    name = "pantalla",
	    entities = {
	        @EntityResult(
	            entityClass = Pantalla.class,
	            fields = {
	                @FieldResult( 
	                    name = "id", 
	                    column = "id"
	                ),
	                @FieldResult( 
	                    name = "clave", 
	                    column = "clave"
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
		                    name = "indice", 
		                    column = "indice"
		                ),
	                @FieldResult( 
		                    name = "pantallaPadre", 
		                    column = "pantalla_padre"
		                ),
	                @FieldResult( 
		                    name = "aplicacionId", 
		                    column = "aplicacion_id"
		                ),
	                @FieldResult( 
		                    name = "estatus", 
		                    column = "estatus"
		                )
	            }
	        )	 
	    }
	)
public class Pantalla implements Serializable{
	private static final long serialVersionUID = 1L;

	
	
	
	public Pantalla(){
		super();
	}

	public Pantalla(@NotNull Long id, @Size(max = 50) @NotNull String clave, @Size(max = 250) @NotNull String nombre,
			@Size(max = 250) @NotNull String descripcion, @NotNull Integer indice, @NotNull Integer pantallaPadre,
			@NotNull Integer aplicacionId, @Size(max = 1) @NotNull int estatus) {
		super();
		this.id = id;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indice = indice;
		this.pantallaPadre = pantallaPadre;
		this.aplicacionId = aplicacionId;
		this.estatus = estatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name ="id")
	private Long id;
	
	@Size(max=50)
	@NotNull
	@Column
	private String clave;
	
	@Size(max=250)
	@NotNull
	private String nombre;
	
	@Size(max=250)
	@NotNull
	private String descripcion;
	
	@NotNull
	private Integer indice;
	
	@NotNull
	@Column(name="pantalla_padre")
	private Integer pantallaPadre;
	
	@NotNull
	@Column(name="aplicacion_id")
	private Integer aplicacionId;
	
	@Size(max=1)
	@NotNull
	private int estatus;
	
	@Transient
	private Perfil perfil;
}
