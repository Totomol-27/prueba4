package com.isita.ccapper.app.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isita.ccapper.app.entity.Evento;
import com.isita.ccapper.app.entity.Participante;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.entity.UsuarioPerfil;
import com.isita.ccapper.app.interfaces.IParticipante;
import com.isita.ccapper.app.repository.ParticipanteRepository;
import com.isita.ccapper.app.repository.UsuarioPerfilRespository;
import com.isita.ccapper.app.repository.UsuarioRepository;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.EmailService;
import com.isita.ccapper.app.util.Utility;

@Service
public class ParticipanteService implements IParticipante{
	
	@Autowired
	private ParticipanteRepository participanteRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private UsuarioService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UsuarioPerfilRespository usuarioPerfilRespository;
	
	@Override
	public Participante save(Participante participante) {
		return participanteRepository.saveParticipante(participante);
	}

	
	public Participante eventos(Integer id, String tipoConsulta) {
		Participante participante = new Participante();
		return participante;
	}

	@Override
	public Participante findById(Integer id, String tipoConsulta) {
		return participanteRepository.buscarParticipanteById(id, tipoConsulta);
	}

	@Override
	public List<Participante> findAll(Participante participante, Integer page, Integer sizes, String tipoConsulta) {
		Persona objperson = new Persona();
		Evento objEvento = new Evento();
		if(participante.getPersona() == null) {
			objperson.setId((long) 0);
			participante.setPersona(objperson);
		}if(participante.getEvento() == null) {
			objEvento.setId((long) 0);
			participante.setEvento(objEvento);
		}
		
		
		return participanteRepository.findAll(participante, page, sizes, tipoConsulta);
	}

	@Override
	public Participante savelistParticipante(String participantes, Integer idEvento,String ipConfig) {
		List<Persona> listaPersonasSinUser= validarParticipante(participantes);
		this.saveUser(listaPersonasSinUser, ipConfig);
		return participanteRepository.saveListParticipante(participantes, idEvento);
	}
	
	public List<Persona> validarParticipante(String participantes){
		String [] arrayIdParticipantes = participantes.split(",");
		Usuario user = new Usuario();
		List<Persona> person = new ArrayList<Persona>();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Persona personas = new Persona();
		
		for(int i=0;i<arrayIdParticipantes.length;i++) {
			
			Long idPart = Long.parseLong(arrayIdParticipantes[i]);
			personas = this.personaService.findById(idPart, Constantes.TIPO_CONSULTA_C1);
			personas.getNss();
			user.setUsuario(personas.getNss());
			usuarios =  this.usuarioRepository.buscarUsuario(user, Constantes.TIPO_CONSULTA_L6);
			if(usuarios.size()<=0) {
				person.add(personas);
			}
			
		}
		
		return person;
	}
	
	public void saveUser(List<Persona> personas, String ipConfig) {
		List<Persona> person = new ArrayList<Persona>();
		person.addAll(personas);
		Usuario user = new Usuario();
		Usuario userResivo = new Usuario();
		Perfil perf =  new Perfil();
		Persona pers = new Persona();
		UsuarioPerfil userPerfil = new UsuarioPerfil(); 
	
		for(int i = 0; i<person.size() ; i++) {
			String password = Utility.generarPassword();
			pers.setId(Long.parseLong(person.get(i).getId().toString()));
			
			user.setUsuario(person.get(i).getNss());
			user.setContrasena(password);
			user.setAplicacionId(1);
			user.setTipoUsuarioId(4);
			user.setCorreo(personas.get(i).getEmail());
			user.setActivo(true);
			user.setIpOrigen(ipConfig);
			user.setIntento(0);
			user.setHoraPrimerIntentoFallido(Timestamp.valueOf(Constantes.FECHA_DEFAULT));
			user.setHoraBloqueo(Timestamp.valueOf(Constantes.FECHA_DEFAULT));
			user.setPersona(pers);
			user.setTipoBloqueo(0);
			user.setEstatus(0);
			
			userResivo = this.userService.save(user);
			userResivo.getId();
			perf.setId(4);
			userPerfil.setPerfil(perf);
			userPerfil.setUsuario(userResivo);
			this.usuarioPerfilRespository.savePerfilUsuario(userPerfil);
			this.envioEmail(null, user, password);
		}
	}
	
	@Override
	public void delete(String participantes, Integer idEvento, String tipoEliminacion) {
		participanteRepository.eliminarParticipante(participantes, idEvento, tipoEliminacion);
	}


	@Override
	public void envioEmail(MultipartFile file,Usuario user, String pas) {
		String pass = pas;		
		String userPawd = "Usuario: " + user.getUsuario() + " Contraseña: " + pass; 
		  try {
		      emailService.sendEmailTool("CCAPPER-ROCA", userPawd, 
		    		  user.getCorreo(), false, file);
		    } catch (Exception e) {	    	
		    	e.getMessage();
		      throw new RuntimeException("No se pudo enviar el email con el archivo. Error: " + e.getMessage());
		    }

	}
	

}
