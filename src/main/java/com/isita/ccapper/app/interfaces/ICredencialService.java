package com.isita.ccapper.app.interfaces;

import com.isita.ccapper.app.entity.Credencial;

public interface ICredencialService {
	public Credencial save(Credencial credencial);
	
	public Credencial findByUserId(Credencial credencial, String tipoConsulta);
}
