package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isita.ccapper.app.entity.RespuestaOpcion;
import com.isita.ccapper.app.interfaces.IRespuestaOpcionService;
import com.isita.ccapper.app.repository.RespuestaOpcionRepository;
import com.isita.ccapper.app.util.Constantes;

@Service
public class RespuestaOpcionService implements IRespuestaOpcionService {
	
	@Autowired
	private RespuestaOpcionRepository respuestaOpcionRepository;
	
	@Override
	public List<RespuestaOpcion> findAll(RespuestaOpcion respuestaOpcion, String tipoConsulta) {
		return respuestaOpcionRepository.findAll(respuestaOpcion, tipoConsulta);
	}

	@Override
	public RespuestaOpcion save(List<RespuestaOpcion> listRespuestaOpcion) {
		RespuestaOpcion respuestaOpcion = new RespuestaOpcion();
		
		for(int i =0;i<listRespuestaOpcion.size();i++) {
			if(listRespuestaOpcion.get(i).getTipoPregunta().getTipo().equals(Constantes.TIPO_RESPUESTA_SELECCION_UNICO)) {
				this.respuestaOpcionRepository.save(listRespuestaOpcion.get(i));
			}else if(listRespuestaOpcion.get(i).getTipoPregunta().getTipo().equals(Constantes.TIPO_RESPUESTA_SELECCION_MULTIPLE)) {
				for(int j=0;j<listRespuestaOpcion.get(i).getListOpciones().size();j++) {
					respuestaOpcion.setPreguntaTipoOpcionId(listRespuestaOpcion.get(i).getPreguntaTipoOpcionId());
					respuestaOpcion.setOpcion(listRespuestaOpcion.get(i).getListOpciones().get(j));
					respuestaOpcion.setPersona(listRespuestaOpcion.get(i).getPersona());
					respuestaOpcion.setOpcionElegida(listRespuestaOpcion.get(i).getListOpciones().get(j).getNombre());
					respuestaOpcion.setFechaCreacion(listRespuestaOpcion.get(i).getFechaCreacion());
					this.respuestaOpcionRepository.save(respuestaOpcion);
					respuestaOpcion = new RespuestaOpcion();
				}
			}
		}
		return respuestaOpcion;
	}
	
    /*
	@Override
	public void actuliza(RespuestaOpcion respuestaOpcion) {
		respuestaOpcionRepository.actuliza(respuestaOpcion);
	}*/
}
