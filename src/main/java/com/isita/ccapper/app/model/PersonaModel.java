package com.isita.ccapper.app.model;

import java.sql.Timestamp;
import java.util.Date;

public class PersonaModel {

    private Long id;
    private String apellido_paterno;
    private String apellido_materno;
    private String primer_nombre;
    private String segundo_nombre;
    private Date fecha_nacimiento;
    private String sexo;
    private String calle;
    private String numero_casa;
    private String colonia;
    private Integer codigo_postal;
    private String municipio;
    private String estado;
    private String grado_estudio;
    private String no_celular;
    private String no_telefono;
    private String corre_electronico;
    private String estado_civil;
    private Integer peso;
    private Integer estatura;
    private String nss;
    private String curp;
    private Timestamp rowstartdate;
    private Integer estatus;
    
    
	public PersonaModel() {
		super();
	}


	public PersonaModel(Long id, String apellido_paterno, String apellido_materno, String primer_nombre,
			String segundo_nombre, Date fecha_nacimiento, String sexo, String calle, String numero_casa, String colonia,
			Integer codigo_postal, String municipio, String estado, String grado_estudio, String no_celular,
			String no_telefono, String corre_electronico, String estado_civil, Integer peso, Integer estatura,
			String nss, String curp, Timestamp rowstartdate, Integer estatus) {
		super();
		this.id = id;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.fecha_nacimiento = fecha_nacimiento;
		this.sexo = sexo;
		this.calle = calle;
		this.numero_casa = numero_casa;
		this.colonia = colonia;
		this.codigo_postal = codigo_postal;
		this.municipio = municipio;
		this.estado = estado;
		this.grado_estudio = grado_estudio;
		this.no_celular = no_celular;
		this.no_telefono = no_telefono;
		this.corre_electronico = corre_electronico;
		this.estado_civil = estado_civil;
		this.peso = peso;
		this.estatura = estatura;
		this.nss = nss;
		this.curp = curp;
		this.rowstartdate = rowstartdate;
		this.estatus = estatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getApellido_paterno() {
		return apellido_paterno;
	}


	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}


	public String getApellido_materno() {
		return apellido_materno;
	}


	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}


	public String getPrimer_nombre() {
		return primer_nombre;
	}


	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}


	public String getSegundo_nombre() {
		return segundo_nombre;
	}


	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}


	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}


	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getNumero_casa() {
		return numero_casa;
	}


	public void setNumero_casa(String numero_casa) {
		this.numero_casa = numero_casa;
	}


	public String getColonia() {
		return colonia;
	}


	public void setColonia(String colonia) {
		this.colonia = colonia;
	}


	public Integer getCodigo_postal() {
		return codigo_postal;
	}


	public void setCodigo_postal(Integer codigo_postal) {
		this.codigo_postal = codigo_postal;
	}


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getGrado_estudio() {
		return grado_estudio;
	}


	public void setGrado_estudio(String grado_estudio) {
		this.grado_estudio = grado_estudio;
	}


	public String getNo_celular() {
		return no_celular;
	}


	public void setNo_celular(String no_celular) {
		this.no_celular = no_celular;
	}


	public String getNo_telefono() {
		return no_telefono;
	}


	public void setNo_telefono(String no_telefono) {
		this.no_telefono = no_telefono;
	}


	public String getCorre_electronico() {
		return corre_electronico;
	}


	public void setCorre_electronico(String corre_electronico) {
		this.corre_electronico = corre_electronico;
	}


	public String getEstado_civil() {
		return estado_civil;
	}


	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}


	public Integer getPeso() {
		return peso;
	}


	public void setPeso(Integer peso) {
		this.peso = peso;
	}


	public Integer getEstatura() {
		return estatura;
	}


	public void setEstatura(Integer estatura) {
		this.estatura = estatura;
	}


	public String getNss() {
		return nss;
	}


	public void setNss(String nss) {
		this.nss = nss;
	}


	public String getCurp() {
		return curp;
	}


	public void setCurp(String curp) {
		this.curp = curp;
	}


	public Timestamp getRowstartdate() {
		return rowstartdate;
	}


	public void setRowstartdate(Timestamp rowstartdate) {
		this.rowstartdate = rowstartdate;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}


    
    
}
