package com.isita.ccapper.app.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.entity.TipoPregunta;
import com.isita.ccapper.app.model.TipoPreguntaModelo;
import com.isita.ccapper.app.util.Constantes;

@Repository
public class PreguntaRepository {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Pregunta> consultaPregunta(Pregunta pregunta, String tipoConsulta) {
			List <Pregunta> resultList = this.entityManager.createNamedQuery("pregunta_consulta")
			.setParameter(1, pregunta.getId()!=null?pregunta.getId().intValue():Constantes.NUMERO_CERO)
			.setParameter(2, pregunta.getSeccionId() instanceof Integer?pregunta.getSeccionId():Constantes.NUMERO_CERO)
			.setParameter(3, tipoConsulta).getResultList();
			List<Pregunta>  preguntas= resultList;		
		return preguntas;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoPreguntaModelo> findAlls(Pregunta pregunta,String tipoConsulta){
		if(pregunta.getId() == null) {
			pregunta.setId((long) 0);			
		}
		long l=pregunta.getId();
		List <Pregunta> resultListas = this.consultaPregunta(pregunta, tipoConsulta);
		List <TipoPregunta> resultList = this.entityManager.createNamedQuery("tipopregunta_consulta")
				.setParameter(1, tipoConsulta).getResultList();
				List<TipoPregunta> tipopreguntas= resultList;	
		ArrayList<TipoPreguntaModelo> todos = new ArrayList<TipoPreguntaModelo>();
		ArrayList<Pregunta> todas = new ArrayList<Pregunta>();
		resultListas.remove(0);
		resultListas.remove(1);
		resultListas.remove(2);
		resultListas.remove(3);
	
		
		resultListas.get(0).toString();
		todas.addAll(resultListas);
		System.out.println(resultList.iterator());
		Object[] nombres = resultListas.toArray();
		nombres[1].toString();
		System.out.println(nombres[1]);
		System.out.println(todas);
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<TipoPregunta> tipopregunta(String tipoConsulta){
		
		List <TipoPregunta> resultList = this.entityManager.createNamedQuery("tipopregunta_consulta")
		.setParameter(1, tipoConsulta).getResultList();
		List<TipoPregunta> tipopreguntas= resultList;	
				
//		ArrayList<TipoPreguntaModelo> todos = new ArrayList<TipoPreguntaModelo>();
//		ArrayList<Pregunta> todas = new ArrayList<Pregunta>();
		
		
		return tipopreguntas;
	}
}
