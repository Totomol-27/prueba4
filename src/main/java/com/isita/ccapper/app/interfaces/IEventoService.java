package com.isita.ccapper.app.interfaces;
import java.util.List;

import com.isita.ccapper.app.entity.Evento;

public interface IEventoService {
    public List<Evento> findAll(Integer pagina, Integer tamanio, String tipoConsulta);
    public Evento findById(Long id, String tipo_consulta);
    public Evento save(Evento evento);
    public void delete(String eventos);
    public void update (Evento evento, String tipoModificacion);
    public List<Evento> findByName (Evento evento, String tipoConsulta);
}
