package com.isita.ccapper.app.interfaces;

import java.util.List;
import com.isita.ccapper.app.entity.Sucursal;

public interface ISucursalService {

	public List<Sucursal> findAll(Sucursal sucursal,Integer page, Integer sizes,  String tipoConsulta);
}
