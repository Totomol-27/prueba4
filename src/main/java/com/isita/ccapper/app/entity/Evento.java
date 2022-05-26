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
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedNativeQuery;

import org.hibernate.annotations.NamedNativeQueries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "evento")

@NamedStoredProcedureQueries(value = {
    @NamedStoredProcedureQuery(name = "alta_evento", procedureName = "evento_alt",
    parameters = {        
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "tipo_evento_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "responsable_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_inicio", type = Timestamp.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_fin", type = Timestamp.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "total_participantes", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
    }),
    @NamedStoredProcedureQuery(name = "eliminar_evento", procedureName = "evento_act",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ideventos", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
    }),
    @NamedStoredProcedureQuery(name = "modificar_evento", procedureName = "evento_mod",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus_evento_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "tipo_evento_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "responsable_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_inicio", type = Timestamp.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_fin", type = Timestamp.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "descripcion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "total_participantes", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
    })
})

@NamedNativeQueries({
    @NamedNativeQuery(
        name = "consulta_evento",
        query = "{? = call evento_con(?,?,?,?,?,?,?,?)}",
        callable = true,
        resultSetMapping = "evento"
    )
})

@SqlResultSetMapping(
    name = "evento",
    entities = {
        @EntityResult(
            entityClass = Evento.class,
            fields = {
                @FieldResult(
                    name = "id",
                    column = "id"
                ),               
                @FieldResult(
                    name = "estatusEvent",
                    column = "estatus_evento_id"
                ),
                @FieldResult(
                    name = "tipoEvento",
                    column = "tipo_evento_id"
                ),
                @FieldResult(
                	name = "responsable_id",
                	column = "responsable_id"
                ),
                @FieldResult(
                    name = "fechaInicio",
                    column = "fecha_inicio"
                ),
                @FieldResult(
                    name = "fechaFin",
                    column = "fecha_fin"
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
                    name = "totalParticipantes",
                    column = "total_participantes"
                ),
                @FieldResult(
                    name = "estatus",
                    column = "estatus"
                )
            }
        )
    }
)

public class Evento implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * @param responsable_id
     * @param estatus_evento_id
     * @param tipo_evento_id
     * @param fecha_inicio
     * @param fecha_fin
     * @param nombre
     * @param descripcion
     * @param total_participantes
     * @param estatus
     */


    
    
    
    /*Constructor vacio*/
	public Evento() {
		super(); 
	}


	public Evento(Long id, @NotNull EstatusEvento estatusEvent, @NotNull TipoEvento tipoEvento, Integer responsable_id,
			Date fechaInicio, Date fechaFin, @Size(max = 200) String nombre, @Size(max = 250) String descripcion,
			Integer totalParticipantes, Integer estatus) {
		super();
		this.id = id;
		this.estatusEvent = estatusEvent;
		this.tipoEvento = tipoEvento;
		this.responsable_id = responsable_id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.totalParticipantes = totalParticipantes;
		this.estatus = estatus;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@NotNull
	@OneToOne
	@JoinColumn(name = "estatus_evento_id")
    private EstatusEvento estatusEvent;

	@NotNull
	@OneToOne
	@JoinColumn(name = "tipo_evento_id")
    private TipoEvento tipoEvento;
    
//	@NotNull
//	@OneToOne
//	@JoinColumn(name = "responsable_id")
	@Column(name = "responsable_id")
    private Integer responsable_id;

    @Column(name = "fecha_inicio", nullable = false, updatable = false)
    @CreationTimestamp
    private Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false, updatable = false)
    @CreationTimestamp
    private Date fechaFin;

    @Size(max = 200)
    private String nombre;

    @Size(max = 250)
    private String descripcion;

    @Column(name = "total_participantes")
    private Integer totalParticipantes;

    @Column(name = "estatus")
    private Integer estatus;
}
