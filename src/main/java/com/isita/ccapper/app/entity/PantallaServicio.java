package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pantalla_servicio")

@NamedStoredProcedureQueries(value= {
		@NamedStoredProcedureQuery(name = "alta_pantalla_servicio", procedureName = "pantalla_servicio_alt",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="pantalla_id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="servicio_id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "eliminar_pantalla_servicio", procedureName = "pantalla_servicio_act",
			parameters= {
					@StoredProcedureParameter(mode = ParameterMode.IN, name="idservicios", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
			}),
		
		@NamedStoredProcedureQuery(name = "modificar_pantalla_servicio", procedureName = "pantalla_servicio_mod",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="pantalla_id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="servicio_id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		})
})

@NamedNativeQuery(
		name = "consulta_pantalla_servicio",
		query ="{?=call pantalla_servicio_con(?,?,?,?,?,?)}",
		callable = true,
		resultSetMapping ="pantalla_servicio"
		)
@SqlResultSetMapping(name = "pantalla_servicio",
	entities = {
		@EntityResult(
			entityClass = PantallaServicio.class,
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
				  name = "servicio", 
				  column = "servicio_id"
				  ),
				@FieldResult( 
				  name = "estatus", 
				  column = "estatus"
				 ),		
			}
		)		
	}
)
public class PantallaServicio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pantalla_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Pantalla pantalla;
	@NotNull
	@OneToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="servicio_id")
	private Servicio servicio;
	@NotNull
	@Size(max=1)
	private Integer estatus;
	@Transient
	private List<Servicio> serviciosPantalla;
	@Transient
	private String idServicio;
	
}
