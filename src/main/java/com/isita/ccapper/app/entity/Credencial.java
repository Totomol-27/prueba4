package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="credencial")

@NamedStoredProcedureQueries(value= {
	@NamedStoredProcedureQuery(name = "alta_token", procedureName = "credencial_alt",
			parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name="credencial_generado", 	type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="fecha_inicia", 	type = Timestamp.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="fecha_termina", 	type = Timestamp.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario_id", 		type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="direccion_ip", 	type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", 			type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", 				type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", 	type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", 	type = String.class)
		})
})
@NamedNativeQueries({
@NamedNativeQuery(
	    name = "consulta_token",
	    query = "{? = call credencial_con( ?,?,?) }",
	    callable = true,
	    resultSetMapping = "credencial"
	)
})
@SqlResultSetMapping(
	    name = "credencial",
	    entities = {
	        @EntityResult(
	            entityClass = Credencial.class,
	            fields = {
	                @FieldResult( 
	                    name = "id", 
	                    column = "id"
	                ),
	                @FieldResult( 
	                    name = "credencialGenerado", 
	                    column = "credencial_generado"
	                ),
	                @FieldResult( 
	                    name = "fechaInicia", 
	                    column = "fecha_inicia"
	                ),
	                @FieldResult( 
		                    name = "fechaTermina", 
		                    column = "fecha_termina"
		                ),
	                @FieldResult( 
		                    name = "usuarioId", 
		                    column = "usuario_id"
		                ),
	                @FieldResult( 
		                    name = "direccionIp", 
		                    column = "direccion_ip"
		                ),
	                @FieldResult( 
		                    name = "estatus", 
		                    column = "estatus"
		                )
	            }
	        )	 
	    }
	)

public class Credencial implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="credencial_generado")
	private String credencialGenerado;
	
	@Column(name="fecha_inicia")
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp fechaInicia;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="fecha_termina")
	private Timestamp fechaTermina;
	
	@Column(name="usuario_id")
	private Integer usuarioId;
	
	@Column(name="direccion_ip")
	private String direccionIp;
	
	private Integer estatus;
}
