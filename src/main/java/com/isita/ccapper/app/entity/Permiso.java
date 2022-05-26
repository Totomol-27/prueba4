package com.isita.ccapper.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="permiso")
@NamedNativeQuery(
		name = "permiso_consulta", 
		query = "{?= call permiso_con( ?,?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_permiso"
		)
@SqlResultSetMapping(
		name = "resultado_permiso",
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
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Size(max=50)
	@NotNull
	@Column(name="clave")
	private String clave;
	
	@Size(max=250)
	@NotNull
	@Column(name="nombre")
	private String nombre;
	
	@Size(max=250)
	@NotNull
	@Column(name="descripcion")
	private String descripcion;
	
	@Size(max=1)
	@NotNull
	@Column(name="estatus")
	private int estatus;
	
	 @OneToOne(mappedBy = "permiso")
	 private Servicio servicio;

	public Permiso(@NotNull Long id, @Size(max = 50) @NotNull String clave, @Size(max = 250) @NotNull String nombre,
			@Size(max = 250) @NotNull String descripcion, @Size(max = 1) @NotNull int estatus) {
		super();
		this.id = id;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public Permiso() {
	}
	
	
	
}
