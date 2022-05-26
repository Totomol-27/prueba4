package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.FieldResult;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.EntityResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="perfil_pantalla")

@NamedStoredProcedureQueries(value= {
		@NamedStoredProcedureQuery(name = "alta_perfil_pantalla", procedureName = "perfil_pantalla_alt",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="pantalla_id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="perfil_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "eliminar_perfil_pantalla", procedureName = "perfil_pantalla_act",
			parameters= {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="idspantallas", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="perfil_id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
			}),
		
		@NamedStoredProcedureQuery(name = "modificar_perfil_pantalla", procedureName = "perfil_pantalla_mod",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="pantalla_id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="perfil_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		})
})

@NamedNativeQuery(
	    name = "consulta_perfil_pantalla",
	    query = "{? = call perfil_pantalla_con( ?,?,?,?,?) }",
	    callable = true,
	    resultSetMapping = "perfil_pantalla"
	)
@SqlResultSetMapping(
	    name = "perfil_pantalla",
	    entities = {
	        @EntityResult(
	            entityClass = PerfilPantalla.class,
	            fields = {
	                @FieldResult( 
	                    name = "id", 
	                    column = "id"
	                ),
	                @FieldResult( 
	                    name = "pantalla", 
	                    column = "pantalla_id"
	                ),
	                @FieldResult( 
		                    name = "perfil", 
		                    column = "perfil_id"
		                ),
	                @FieldResult( 
		                    name = "estatus", 
		                    column = "estatus"
		                )
	            }
	        )	 
	    }
	)
public class PerfilPantalla implements Serializable{


	 private static final long serialVersionUID = 1L;
	 @NotNull
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 @NotNull
	 
	 @OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="pantalla_id")
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	 private Pantalla pantalla;
	 @NotNull

	 @OneToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="perfil_id")
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	 private Perfil perfil;
	 
	 @NotNull
	 @Size(max=1)
	 private Integer estatus;

	 @Transient
	 private Integer idUsario;
 
	 @Transient
 	private List<Pantalla> pantallasPerfil;
	 
	 @Transient
	 private String idPantallas;

}
