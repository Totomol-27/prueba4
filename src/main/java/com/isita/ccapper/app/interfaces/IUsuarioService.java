package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll(Integer pagina, Integer tamanio, String tipoConsulta);
	public Usuario findById(Long id, String tipo_consulta);
	public  Usuario findByUsername(Usuario usuario, String tipo_consulta, boolean token);
	public List<Usuario> nombreUsuarrio(String nombre, String tipoConsulta);
	public  Usuario save(Usuario usuario);
	public void delete(String usuarios);
	public void update(Usuario  usuario,String tipoModificacion);
	
}
