package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.entity.RespuestaTexto;
import com.isita.ccapper.app.interfaces.IRespuestaTextoService;
import com.isita.ccapper.app.repository.RespuestaTextoRepository;

@Service
public class RespuestaTextoService implements IRespuestaTextoService {

	@Autowired
	private RespuestaTextoRepository respuestaTextoRepository;
	
	@Override
	public List<RespuestaTexto> findAll(RespuestaTexto respuestaTexto, String tipoConsulta) {
		return respuestaTextoRepository.findAll(respuestaTexto, tipoConsulta);
	}
	
	@Override
	public RespuestaTexto save(List<RespuestaTexto> listaRespuestaTexto) {
		RespuestaTexto repuestaTexto = new RespuestaTexto();
		for(int i=0;i<listaRespuestaTexto.size();i++) {
			this.respuestaTextoRepository.save(listaRespuestaTexto.get(i));
		}
		return repuestaTexto;
	}
     /*
	@Override
	public void actuliza(RespuestaTexto respuestaTexto) {
		respuestaTextoRepository.actuliza(respuestaTexto);
	}*/
}
