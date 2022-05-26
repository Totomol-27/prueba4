package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentSet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isita.ccapper.app.model.Menu;

import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="usuario") 

@NamedStoredProcedureQueries(value= {
		@NamedStoredProcedureQuery(name = "alta_usuario", procedureName = "usuario_alt",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="contrasena", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="aplicacion_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_usuario_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="correo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="activo", type = Boolean.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="ip_origen", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="intento", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="hora_primer_intento_fallido", type = Timestamp.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="hora_bloqueado", type = Timestamp.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="persona_id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "eliminar_usuario", procedureName = "usuario_act",
			parameters= {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="idsUsuarios", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
			}),
		@NamedStoredProcedureQuery(name = "proceso_usuario", procedureName = "usuario_pro",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="rango_minutos_bloqueo", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		
		@NamedStoredProcedureQuery(name = "modificar_usuario", procedureName = "usuario_mod",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="contrasena", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="aplicacion_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_usuario_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="correo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="activo", type = Boolean.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="ip_origen", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="intento", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="hora_primer_intento_fallido", type = Timestamp.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="hora_bloqueado", type = Timestamp.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_bloqueo", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_modificacion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		})
})

@NamedNativeQueries({
	@NamedNativeQuery(
		    name = "consulta_usuario",
		    query = "{? = call usuario_con( ?,?,?,?,?) }",
		    callable = true,
		    resultSetMapping = "usuario"
		)
})
@SqlResultSetMapping(
	    name = "usuario",
	    entities = {
	        @EntityResult(
	            entityClass = Usuario.class,
	            fields = {
	                @FieldResult( 
	                    name = "id", 
	                    column = "id"
	                ),
	                @FieldResult( 
	                    name = "usuario", 
	                    column = "usuario"
	                ),
	                @FieldResult( 
	                    name = "contrasena", 
	                    column = "contrasena"
	                ),
	                @FieldResult( 
		                    name = "aplicacionId", 
		                    column = "aplicacion_id"
		                ),
	                @FieldResult( 
		                    name = "tipoUsuarioId", 
		                    column = "tipo_usuario_id"
		                ),
	                @FieldResult( 
		                    name = "correo", 
		                    column = "correo"
		                ),
	                @FieldResult( 
		                    name = "activo", 
		                    column = "activo"
		                ),
	                @FieldResult( 
		                    name = "ipOrigen", 
		                    column = "ip_origen"
		                ),
	                @FieldResult( 
		                    name = "intento", 
		                    column = "intento"
		                ),
	                @FieldResult( 
		                    name = "horaPrimerIntentoFallido", 
		                    column = "hora_primer_intento_fallido"
		                ),
	                @FieldResult( 
		                    name = "horaBloqueo", 
		                    column = "hora_bloqueado"
		                ),
	                @FieldResult( 
		                    name = "persona", 
		                    column = "persona_id"
		                ),
	                @FieldResult( 
		                    name = "tipoBloqueo", 
		                    column = "tipo_bloqueo"
		                ),
	                @FieldResult( 
		                    name = "estatus", 
		                    column = "estatus"
		                )
	            }
	        )	 
	    }
	)
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param usuario
	 * @param contrasena
	 * @param aplicacionId
	 * @param tipoUsuarioId
	 * @param correo
	 * @param activo
	 * @param ipOrigen
	 */
	public Usuario(@Size(max = 200) String usuario, @Size(max = 200) String contrasena,
			@Size(max = 250) Integer aplicacionId, Integer tipoUsuarioId, @Size(max = 250) String correo,
			boolean activo, @Size(max = 15) String ipOrigen) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.aplicacionId = aplicacionId;
		this.tipoUsuarioId = tipoUsuarioId;
		this.correo = correo;
		this.activo = activo;
		this.ipOrigen = ipOrigen;
	}

	
	/**Contructor vacio**/
	 public Usuario() {
	 	super(); 
	 }


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;

	@Size(max=200)
	private String usuario;
	@Size(max=200)
	private String contrasena;
	 
	 @Size(max=250)
	 @Column(name="aplicacion_id")
	private Integer aplicacionId;
	 @Column(name="tipo_usuario_id")
	private Integer tipoUsuarioId;
	 
	@Size(max=250)
	private String correo;
	 
	private boolean activo;
	 
	@Size(max=15)
	 @Column(name="ip_origen")
	private String ipOrigen;
	 
	private Integer intento;
	
	@Column(name="hora_primer_intento_fallido")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp horaPrimerIntentoFallido;
	@Column(name="hora_bloqueado")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp horaBloqueo;
	@OneToOne
	@JoinColumn(name="persona_id")
	private Persona persona;
	
	@Column(name="tipo_bloqueo")
	private Integer tipoBloqueo;
	
	private Integer estatus;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_perfil",joinColumns=@JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="perfil_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"usuario_id", "perfil_id"})})
	private List<Perfil> Perfil;
	
	@Transient
	private List<Menu> menu;
	
}
