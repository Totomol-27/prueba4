package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.EstatusEvento;
import com.isita.ccapper.app.interfaces.IEstatusEventoService;
import com.isita.ccapper.app.repository.EstatusEventoRepository;
import com.isita.ccapper.app.util.Constantes;

@Service
public class EstatusEventoService implements IEstatusEventoService {

	@Autowired
	private EstatusEventoRepository estatusEventoRepository;
	
	@Override
	public List<EstatusEvento> findAll(EstatusEvento estatusEvento, Integer page, Integer sizes, String tipoConsulta) {
		return estatusEventoRepository.findAll(estatusEvento, page, sizes, tipoConsulta);
	}

	@Override
	public EstatusEvento save(EstatusEvento estatusEvento) {
		return estatusEventoRepository.save(estatusEvento);
	}

	@Override
	public void actuliza(EstatusEvento estatusEvento) {
		List<EstatusEvento> findRegistro = estatusEventoRepository.findAll(estatusEvento, 0, 0, Constantes.TIPO_CONSULTA_L4);
		if(findRegistro.size() > 0) {
			throw new MissingHeaderInfoException(Constantes.CODIGO_MENSAJE+", ");
		}else {
			estatusEventoRepository.actuliza(estatusEvento);
		}
	}

	@Override
	public void delete(EstatusEvento estatusEvento) {
		estatusEventoRepository.delete(estatusEvento);
	}

}
