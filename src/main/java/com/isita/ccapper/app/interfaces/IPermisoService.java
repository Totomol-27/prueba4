package com.isita.ccapper.app.interfaces;

import java.util.List;
import com.isita.ccapper.app.entity.Permiso;

public interface IPermisoService {

	
	public List<Permiso> listaPermiso(Permiso permiso,Integer page,Integer sizes, String tipoConsulta);
}
