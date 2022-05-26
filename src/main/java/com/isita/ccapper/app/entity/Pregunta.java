package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pregunta")
@NamedNativeQuery(
		name = "pregunta_consulta", 
		query = "{?= call pregunta_con( ?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_pregunta"
		)
@SqlResultSetMapping(
		name = "resultado_pregunta",
		entities = {
				@EntityResult(
						entityClass = Pregunta.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "seccionId", column = "seccion_id"
										),
								@FieldResult(
										name = "tipoPregunta", column = "tipo_pregunta_id"
										),
								@FieldResult(
										name = "indice", column = "indice"
										),
								@FieldResult(
										name = "pregunta", column = "pregunta"
										),
								@FieldResult(
										name = "preguntaPadre", column = "pregunta_padre"
										),
								@FieldResult(
										name = "opcion", column = "opcion"
										),
								@FieldResult(
										name = "estatus", column = "estatus"
										),
								@FieldResult(
										name = "tipo", column = "tipo"
										),
								@FieldResult(
										name = "longitud", column = "longitud"
										)
								}
						)
		}
)	
public class Pregunta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "seccion_id")
	private Integer seccionId;
//	@Column(name = "tipo_pregunta_id")
//	private Integer tipoPreguntaId;
	private Integer indice;
	private String pregunta;
	
	@Column(name = "pregunta_padre")
	private Integer preguntaPadre;
	private String opcion;
	private Integer estatus;
	
	@OneToOne
	@JoinColumn(name="tipo_pregunta_id")
	private TipoPregunta tipoPregunta;
	private String tipo;
	private Integer  longitud;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="pregunta_tipo_opcion",joinColumns=@JoinColumn(name="pregunta_id"),
	inverseJoinColumns=@JoinColumn(name="tipo_opcion_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"pregunta_id", "tipo_opcion_id"})})
	private List<TipoOpcion> tipoOpcion;
	
	public Pregunta(Long id, Integer seccionId, Integer indice, String pregunta, Integer preguntaPadre, String opcion,
			Integer estatus) {
		super();
		this.id = id;
		this.seccionId = seccionId;
		this.indice = indice;
		this.pregunta = pregunta;
		this.preguntaPadre = preguntaPadre;
		this.opcion = opcion;
		this.estatus = estatus;
//		this.tipoPregunta = tipoPregunta;
	}
	
	public Pregunta() {
//		this.tipoPreguntas = new ArrayList<>();
		this.tipoOpcion = new ArrayList<>();
	}	
	
}
