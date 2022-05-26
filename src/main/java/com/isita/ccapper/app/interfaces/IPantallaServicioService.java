package com.isita.ccapper.app.interfaces;


import java.util.List;

import com.isita.ccapper.app.entity.PantallaServicio;
public interface IPantallaServicioService {
	
	public PantallaServicio findById(PantallaServicio pantallaServicio, String tipoConsulta); 
	public List<PantallaServicio> buscarServicios(PantallaServicio pantallaServicio, String tipoConsulta);
	public  PantallaServicio save(PantallaServicio pantallaServicio);
	public void delete(String listaServicios);
	public void update(PantallaServicio  pantallaServicio);
	public PantallaServicio buscarServiciosDePantalla(PantallaServicio pantallaServicio, String tipoConsulta);
	
}
