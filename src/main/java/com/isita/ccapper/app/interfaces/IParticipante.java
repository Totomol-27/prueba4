package com.isita.ccapper.app.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.isita.ccapper.app.entity.Participante;
import com.isita.ccapper.app.entity.Usuario;

public interface IParticipante {

	public List<Participante> findAll(Participante participante,Integer page,Integer sizes, String tipoConsulta);
	public Participante save(Participante participante);
	public Participante savelistParticipante( String participantes,Integer idEvento,  String ipConfig);
	public void delete(String participantes, Integer idEvento, String tipoEliminacion);
	public Participante findById(Integer id, String tipoConsulta);
	public void envioEmail(MultipartFile file, Usuario user, String pass);
}
