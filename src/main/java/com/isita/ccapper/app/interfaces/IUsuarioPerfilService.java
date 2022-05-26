package com.isita.ccapper.app.interfaces;
import com.isita.ccapper.app.entity.UsuarioPerfil;

public interface IUsuarioPerfilService {
	public UsuarioPerfil findById(Long id, String tipoConsulta);
	public UsuarioPerfil LoadPerfilesUsuario(Long idUsuario, String tipoConsulta);
	public UsuarioPerfil save(UsuarioPerfil usuarioPerfil);
	public void update(UsuarioPerfil usuarioPerfil);
	public void delete(String listaPerfiles,Long usuarioId);
}
