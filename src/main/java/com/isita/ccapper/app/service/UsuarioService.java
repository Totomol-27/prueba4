package com.isita.ccapper.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.PerfilPantalla;
import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.interfaces.IUsuarioService;
import com.isita.ccapper.app.model.Menu;
import com.isita.ccapper.app.repository.UsuarioRepository;
import com.isita.ccapper.app.util.Constantes;



@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
 	
	@Autowired
 	private PerfilPantallaService perfilPantallaService; 
 	
 	@Autowired
 	private PersonaService personaService;
	
 	@Autowired
	private ParticipanteService participanteService;
	

	public Usuario findByUsername(Usuario usuario, String tipo_consulta, boolean token){
		Usuario datosUsuario = new Usuario();
		Persona persona = new Persona();
		List<Usuario> informacionUsuario =  usuarioRepository.buscarUsuario(usuario,tipo_consulta);
		if(informacionUsuario.size()>0) {
			
			datosUsuario.setId(informacionUsuario.get(0).getId());
			datosUsuario.setUsuario(informacionUsuario.get(0).getUsuario());
			datosUsuario.setContrasena(informacionUsuario.get(0).getContrasena());
			datosUsuario.setAplicacionId(informacionUsuario.get(0).getAplicacionId());
			datosUsuario.setTipoUsuarioId(informacionUsuario.get(0).getTipoUsuarioId());
			datosUsuario.setCorreo(informacionUsuario.get(0).getCorreo());
			datosUsuario.setActivo(informacionUsuario.get(0).isActivo());
			datosUsuario.setIpOrigen(informacionUsuario.get(0).getIpOrigen());
			datosUsuario.setIntento(informacionUsuario.get(0).getIntento());
			datosUsuario.setHoraPrimerIntentoFallido(informacionUsuario.get(0).getHoraPrimerIntentoFallido());
			datosUsuario.setHoraBloqueo(informacionUsuario.get(0).getHoraBloqueo());
			persona =informacionUsuario.get(0).getPersona();
			datosUsuario.setPersona(persona);
			datosUsuario.setEstatus(informacionUsuario.get(0).getEstatus());
			datosUsuario.setPerfil(informacionUsuario.get(0).getPerfil());
			datosUsuario.setTipoBloqueo(informacionUsuario.get(0).getTipoBloqueo());
			datosUsuario= this.consultarPerfilesUsuario(datosUsuario, token);
		}else {
			datosUsuario = new Usuario();
		}
		
	    return datosUsuario;
		
	}
	
	

	
	private Usuario consultarPerfilesUsuario(Usuario usuario, Boolean token) {
		if(token) {
			int size = usuario.getPerfil().size();
			 Integer []perfilUsuario = new Integer[size];
			for(int i=0;i<usuario.getPerfil().size();i++) {
				perfilUsuario[i]=usuario.getPerfil().get(i).getId();
				System.out.println("perfiles----> "+usuario.getPerfil().get(i).getId());
			}
			
		
			PerfilPantalla perfilPantalla = new PerfilPantalla();
			perfilPantalla.setIdUsario(usuario.getId().intValue());
			List<PerfilPantalla> pantallasPerfil =perfilPantallaService.buscarPantalla(perfilPantalla, Constantes.TIPO_CONSULTA_L1);
			List<Menu> listaPantalla = new ArrayList<Menu>();
			Menu menuPantalla = new Menu();
			for(int i=0;i<pantallasPerfil.size();i++) {
				menuPantalla.setNombre(pantallasPerfil.get(i).getPantalla().getNombre());
				if(pantallasPerfil.get(i).getPantalla().getPantallaPadre()==0) {
					menuPantalla.setPantallaPadre(pantallasPerfil.get(i).getPantalla().getId().intValue());
				}else {
					menuPantalla.setPantallaPadre(pantallasPerfil.get(i).getPantalla().getPantallaPadre());
				}
				menuPantalla.setClase(pantallasPerfil.get(i).getPantalla().getPantallaPadre()==0?"padre":"hijo");
				listaPantalla.add(i, menuPantalla);
				 menuPantalla = new Menu();
			}
			pantallasPerfil= null;
			usuario.setMenu(listaPantalla);
			 menuPantalla = new Menu();
		}
		return usuario;
	}
	
	

	@Override
	public void delete(String usuarios) {
		usuarioRepository.actualizarUsuario(usuarios);
	}


	@Override
	public Usuario save(Usuario usuario) {
		Usuario user = new Usuario();
		Persona persona = new Persona();
		
		persona.setNss(usuario.getUsuario().trim());
		persona = this.personaService.buscarSeguroSocial(persona, Constantes.TIPO_CONSULTA_L6);
		if(persona.getId()==null) {
			throw new MissingHeaderInfoException("NSS_USUARIO_NO_EXISTE");
		}
		
		String password = this.generarPassword(usuario.getContrasena());
		String pwEnvio = usuario.getContrasena();
		
		usuario.setContrasena(password);
		user = this.findByUsername(usuario, Constantes.TIPO_CONSULTA_L5, false);
	
		if(user.getId()!=null) {
			if(user.getEstatus()==Constantes.REGISTRO_ELIMINADO) {
				usuario.setPersona(persona);
				usuario.setId(user.getId());
				usuarioRepository.ModificarUsuario(usuario,Constantes.VACIO);
				usuario.setCorreo(persona.getEmail());
				user.setContrasena(usuario.getContrasena());
				this.participanteService.envioEmail(null, usuario, pwEnvio);
			  return user;
			}else {
				throw new MissingHeaderInfoException("USUARIO YA EXISTE");
			}
		}
		
		usuario.setPersona(persona);
		
		this.participanteService.envioEmail(null, usuario, pwEnvio);
		return usuarioRepository.saveUsuario(usuario);
	}


	@Override
	public Usuario findById(Long id, String tipo_consulta) {
		Integer idUsuario = (int) (long)id;
		Usuario usuario = usuarioRepository. buscarPorIdUsuario(idUsuario,tipo_consulta);
		return usuario;
	}


	@Override
	public void update(Usuario usuario,String tipoModificacion) {
		this.usuarioRepository.ModificarUsuario(usuario,tipoModificacion);
	}


	@Override
	public List<Usuario> findAll(Integer pagina, Integer tamanio, String tipoConsulta) {
		return usuarioRepository.findAll(pagina, tamanio, tipoConsulta);
	}


	@Override
	public List<Usuario> nombreUsuarrio(String nombre, String tipoConsulta) {
		return usuarioRepository.buscarPorNombreUsuario(nombre, tipoConsulta);
	}


	
	private String  generarPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password); 
	}


	



	
	

}
