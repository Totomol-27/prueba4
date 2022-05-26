package com.isita.ccapper.app.entity;

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
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

///import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario_perfil")
@NamedStoredProcedureQueries(value= {
		@NamedStoredProcedureQuery(name = "alta_usuario_perfil", procedureName = "usuario_perfil_alt",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario_id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="perfil_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "eliminar_usuario_perfil", procedureName = "usuario_perfil_act",
			parameters= {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="idsperfiles", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario_id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
			}),
		
		@NamedStoredProcedureQuery(name = "modificar_usuario_perfil", procedureName = "usuario_perfil_mod",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario_id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="perfil_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		})
})

@NamedNativeQuery(
	    name = "consulta_usuario_perfil",
	    query = "{? = call usuario_perfil_con(?,?,?,?) }",
	    callable = true,
	    resultSetMapping = "usuario_perfil"
	)
@SqlResultSetMapping(
	    name = "usuario_perfil",
	    entities = {
	        @EntityResult(
	            entityClass = UsuarioPerfil.class,
	            fields = {
	                @FieldResult( 
	                    name = "id", 
	                    column = "id"
	                ),
	                @FieldResult( 
	                    name = "usuario", 
	                    column = "usuario_id"
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
public class UsuarioPerfil{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name="id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="usuario_id")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Usuario usuario;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="perfil_id")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Perfil perfil;
	
	@NotNull
	@Column(name="estatus")
	private Integer estatus;
	
	@Transient
	private List<Perfil> listaPerfil;
	
	@Transient
	private String idsPerfil;

	public UsuarioPerfil() {
	}


}
