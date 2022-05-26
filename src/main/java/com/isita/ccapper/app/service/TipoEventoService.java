package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.TipoEvento;
import com.isita.ccapper.app.interfaces.ITipoEventoService;
import com.isita.ccapper.app.repository.TipoEventoRepository;
import com.isita.ccapper.app.util.Constantes;

@Service
public class TipoEventoService implements ITipoEventoService {
	
	@Autowired
	private TipoEventoRepository tipoEventoRepository;
	
	@Override
	public List<TipoEvento> findAll(TipoEvento tipoEvento, Integer page, Integer sizes, String tipoConsulta) {
		return tipoEventoRepository.findAll(tipoEvento, page, sizes, tipoConsulta);
	}

	@Override
	public TipoEvento save(TipoEvento tipoEvento) {
		return tipoEventoRepository.save(tipoEvento);
	}

	@Override
	public void actuliza(TipoEvento tipoEvento) {
		TipoEvento eventoBus = new TipoEvento();
		List<TipoEvento> findRegistro = tipoEventoRepository.findAll(tipoEvento, 0, 0, Constantes.TIPO_CONSULTA_L4);
		if (findRegistro.size() > 0) {
			throw new MissingHeaderInfoException(Constantes.CODIGO_MENSAJE+", ");
		}else {
			tipoEventoRepository.actuliza(tipoEvento);
		}
	}

	@Override
	public void delete(TipoEvento tipoEvento) {
		tipoEventoRepository.delete(tipoEvento);
	}
	

}
