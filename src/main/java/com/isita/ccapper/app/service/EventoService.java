package com.isita.ccapper.app.service;
import java.util.List;

import com.isita.ccapper.app.entity.Evento;
import com.isita.ccapper.app.interfaces.IEventoService;
import com.isita.ccapper.app.repository.EventoRepository;
import com.isita.ccapper.app.repository.ParticipanteRepository;
import com.isita.ccapper.app.repository.ProgramacionRepository;
import com.isita.ccapper.app.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService implements IEventoService{

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@Autowired
	private ProgramacionRepository programacionRepository;
	
	@Override
	public List<Evento> findAll(Integer pagina, Integer tamanio, String tipoConsulta) {
		return eventoRepository.findAll(pagina, tamanio, tipoConsulta);
	}

	@Override
	public Evento findById(Long id, String tipo_consulta) {
		Integer idEvento = (int)(long) id;
		return eventoRepository.buscarEventoById(idEvento, tipo_consulta);
	}

	@Override
	public Evento save(Evento evento) {
		return eventoRepository.saveEvento(evento);
	}

	@Override
	public void delete(String eventos) {
	//cuando se elimina un evento se elimina igual los participantes que esten incluidos en el evento eliminado
	participanteRepository.eliminarParticipante(eventos, Integer.parseInt(eventos), Constantes.DELETETYOETWO);
	//cuando se elimina un evento se elimina igual la programacion que este incluida en el evento eliminado
	programacionRepository.eliminarProgramacion(eventos,Integer.parseInt(eventos),  Constantes.DELETETYOETWO);
	eventoRepository.actualizarEvento(eventos);

	}

	@Override
	public void update(Evento evento, String tipoModificacion) {
		eventoRepository.modificarEvento(evento, tipoModificacion);
		
	}

	@Override
	public List<Evento> findByName(Evento evento, String tipoConsulta) {
		return eventoRepository.findByName(evento, tipoConsulta);
	}


}
