package com.isita.ccapper.app.interfaces;
import com.isita.ccapper.app.entity.PerfilPantalla;

public interface IPerfilPantallaService {
	
	public PerfilPantalla findById(Long id, String tipoConsulta);
	
	public PerfilPantalla LoadPantallasDePerfil(Long idPerfil, String tipoConsulta);
	
	public PerfilPantalla save(PerfilPantalla perfilPantalla);

	
	public void update(PerfilPantalla perfilPantalla);
	
	public void delete(String listaPantallas, Long perfilId);
}
