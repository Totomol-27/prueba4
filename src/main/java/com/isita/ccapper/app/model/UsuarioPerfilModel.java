package com.isita.ccapper.app.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class UsuarioPerfilModel {
	private Long id;
	@Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="usuarioModel.id", column = @Column(name="id") ),
            @AttributeOverride(name="usuarioModel.usuario", column = @Column(name="usuario") ),
            @AttributeOverride(name="usuarioModel.contrasena", column = @Column(name="contrasena") ),
            @AttributeOverride(name="usuarioModel.aplicacion", column = @Column(name="aplicacion") ),
            @AttributeOverride(name="usuarioModel.tipo_usuario_id", column = @Column(name="tipo_usuario_id") ),
            @AttributeOverride(name="usuarioModel.correo", column = @Column(name="correo") ),
            @AttributeOverride(name="usuarioModel.activo", column = @Column(name="activo") ),
            @AttributeOverride(name="usuarioModel.ip_origen", column = @Column(name="ip_origen") ),
            @AttributeOverride(name="usuarioModel.estatus", column = @Column(name="estatus") )
    } )
	private List<UsuarioModel> usuarioModel;
	@Embedded
	@AttributeOverrides( {
         @AttributeOverride(name="PerfilModel.id", column = @Column(name="id") ),
         @AttributeOverride(name="PerfilModel.clave", column = @Column(name="clave") ),
         @AttributeOverride(name="PerfilModel.nombre", column = @Column(name="nombre") ),
         @AttributeOverride(name="PerfilModel.descripcion", column = @Column(name="descripcion") ),
         @AttributeOverride(name="PerfilModel.estatus", column = @Column(name="estatus") )
	} )
	private List<PerfilModel> perfilModel;
	private Integer estatus;
	
	
	public UsuarioPerfilModel() {
		super();
	}

	
	
	public Long getId() {
		return id;
	}


	public UsuarioPerfilModel(Long id, List<UsuarioModel> usuarioModel, List<PerfilModel> perfilModel,
			Integer estatus) {
		super();
		this.id = id;
		this.usuarioModel = usuarioModel;
		this.perfilModel = perfilModel;
		this.estatus = estatus;
	}



	public void setId(Long id) {
		this.id = id;
	}


	public List<UsuarioModel> getUsuarioModel() {
		return usuarioModel;
	}


	public void setUsuarioModel(List<UsuarioModel> usuarioModel) {
		this.usuarioModel = usuarioModel;
	}


	public List<PerfilModel> getPerfilModel() {
		return perfilModel;
	}


	public void setPerfilModel(List<PerfilModel> perfilModel) {
		this.perfilModel = perfilModel;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	
	
}