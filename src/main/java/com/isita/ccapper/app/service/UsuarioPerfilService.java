package com.isita.ccapper.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.PantallaServicio;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.entity.UsuarioPerfil;
import com.isita.ccapper.app.interfaces.IUsuarioPerfilService;
import com.isita.ccapper.app.repository.UsuarioPerfilRespository;
import com.isita.ccapper.app.util.Constantes;

@Service
public class UsuarioPerfilService implements IUsuarioPerfilService {
	@Autowired
	private UsuarioPerfilRespository usuarioPerfilRepository;
	@Autowired
	private PerfilService perfilService;
	
	@Override
	public UsuarioPerfil findById(Long id, String tipoConsulta) {
		UsuarioPerfil usuarioPerfil = new  UsuarioPerfil();
		usuarioPerfil.setId(id);
		
		List<UsuarioPerfil> usuarioPerfill  = this.usuarioPerfilRepository.buscarPerfilUsuario(usuarioPerfil, tipoConsulta);
		 new  UsuarioPerfil();
		 if(usuarioPerfill.size()>0) {
			 usuarioPerfil = usuarioPerfill.get(0);
		 }
		return usuarioPerfil;
	}

	@Override
	public UsuarioPerfil LoadPerfilesUsuario(Long idUsuario, String tipoConsulta) {
		Perfil perfil = new Perfil();
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		perfil.setUsuario(usuario);
		List<Perfil>listaPerfil =this.perfilService.buscarPerfilesDeUsuario(perfil, tipoConsulta);
		UsuarioPerfil usuarioPerfil = new  UsuarioPerfil();
		if(listaPerfil.size()>Constantes.NUMERO_CERO) {
			usuarioPerfil.setListaPerfil(listaPerfil);
		}
		return usuarioPerfil;
	}

	@Override
	public UsuarioPerfil save(UsuarioPerfil usuarioPerfil) {
	List<UsuarioPerfil> listaExistentes=this.validarPerfilesSiExisten(usuarioPerfil);
	UsuarioPerfil guardarUsuarioPerfil = new UsuarioPerfil();
		if(listaExistentes.size()==0) {
			String arrayPerfil[] = usuarioPerfil.getIdsPerfil().split(",");
			Perfil perfil = new Perfil();
			for(int i=0;i<arrayPerfil.length;i++) {
				perfil.setId(Integer.parseInt(arrayPerfil[i]));
				usuarioPerfil.setPerfil(perfil);
				guardarUsuarioPerfil =	this.usuarioPerfilRepository.savePerfilUsuario(usuarioPerfil);
			}
		}else {
			String perfiles =this.retornarPerfilesxistentes(listaExistentes);
			throw new MissingHeaderInfoException("00003,"+perfiles);
		}
		
		return guardarUsuarioPerfil;
	}

	@Override
	public void update(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfilRepository.modificarPerfilUsuario(usuarioPerfil);
	}

	@Override
	public void delete(String listaPerfiles, Long usuarioId) {
		this.usuarioPerfilRepository.eliminarPerfilUsuario(listaPerfiles, usuarioId);
	}
	
	public List<UsuarioPerfil> validarPerfilesSiExisten(UsuarioPerfil usuarioPerfil){
		String perfiles[] = usuarioPerfil.getIdsPerfil().split(",");
		Perfil perfil = new Perfil();
		List<UsuarioPerfil> listaUsuarioPerfilRepetidos = new ArrayList<UsuarioPerfil>();
		UsuarioPerfil objUsuarioPerfil = new UsuarioPerfil();
		for(int i=0;i<perfiles.length;i++) {
			perfil.setId(Integer.parseInt(perfiles[i]));
			usuarioPerfil.setPerfil(perfil);
			List<UsuarioPerfil> listaUsuarioPerfil = this.usuarioPerfilRepository.buscarPerfilUsuario(usuarioPerfil, Constantes.TIPO_CONSULTA_L2);
			if(listaUsuarioPerfil.size()>0) {
				objUsuarioPerfil = listaUsuarioPerfil.get(0);
				perfil.setNombre(listaUsuarioPerfil.get(0).getPerfil().getNombre());
				objUsuarioPerfil.setPerfil(perfil);
				listaUsuarioPerfilRepetidos.add(objUsuarioPerfil);
			}
			objUsuarioPerfil = new UsuarioPerfil();
		}
		return listaUsuarioPerfilRepetidos; 
	}
	
	public String retornarPerfilesxistentes(List<UsuarioPerfil> listaUsuarioPerfil) {
		StringBuilder perfiles = new StringBuilder();
		for(int j=0;j<listaUsuarioPerfil.size();j++) {
			perfiles.append(perfiles.length()!=0?","+listaUsuarioPerfil.get(j).getPerfil().getNombre():
				listaUsuarioPerfil.get(j).getPerfil().getNombre());
		
		}
		return perfiles.toString();
	}
	
}
