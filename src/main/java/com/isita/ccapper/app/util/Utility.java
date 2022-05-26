package com.isita.ccapper.app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.query.procedure.internal.ProcedureParameterImpl;

import com.isita.ccapper.app.model.ModelError;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import io.micrometer.core.instrument.util.IOUtils;
/**
 * 
 * @author IsitaPC
 *
 */
public class Utility {
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Properties readPropertiesFile(String fileName) throws IOException {
	    FileInputStream fis = null;
	    Properties prop = null;
	    try {
	       fis = new FileInputStream(fileName);
	       prop = new Properties();
	       prop.load(fis);
	    } catch(FileNotFoundException fnfe) {
	       fnfe.printStackTrace();
	    } catch(IOException ioe) {
	       ioe.printStackTrace();
	    } finally {
	       fis.close();
	    }
	    return prop;
	 }
	/**
	 * 
	 * @param listaUri
	 * @return
	 */
	public static  String[] convertirListaAarray(List<String> listaUri) {
		String [] uri=new String[listaUri.size()];
		uri = listaUri.toArray(uri);
		return uri;
	}
	
	/**
	 * método que habilita los parametros nulos con parametros de salida 
	 * @param storedProcedureQuery
	 * @param ParametrosIN
	 */
	@SuppressWarnings("rawtypes")
	public static void setStoreProcedureEnableNullParametersWithParametersOut(StoredProcedureQuery storedProcedureQuery, Long ParametrosIN) {
		int numeroParametros = (int) (long)ParametrosIN;
	    if (storedProcedureQuery == null || storedProcedureQuery.getParameters() == null)
	        return;
	   
	    int iterador =0;
	    for (javax.persistence.Parameter<?> parameter : storedProcedureQuery.getParameters()) {
	    	iterador++;
	    	if(iterador>numeroParametros) {
	    		break;
	    	}
	        ((ProcedureParameterImpl) parameter).enablePassingNulls(true);
	    }
	}  
	
	/**
	 * Método que habilita para que los parametros que sean nulos 
	 * @param storedProcedureQuery
	 */
	@SuppressWarnings("rawtypes")
	public static void setStoreProcedureEnableNullParametersWithoutParametersOut(StoredProcedureQuery storedProcedureQuery) {
	    if (storedProcedureQuery == null || storedProcedureQuery.getParameters() == null)
	        return;
	    for (javax.persistence.Parameter<?> parameter : storedProcedureQuery.getParameters()) {
	        ((ProcedureParameterImpl) parameter).enablePassingNulls(true);
	    }
	} 
	/**
	 * 
	 * @param codigo de error que recibo desde base de datos
	 * @param mensaje de error que recibo de base de datos
	 * @return retorna un objeto con el codigo y el mensaje de error
	 */
	public static ModelError errores(String codigo, String mensaje) {
		ModelError errores = new ModelError();
		errores.setCodigo_error(codigo);
		errores.setMensaje_error(mensaje);
		return errores;
	}
	
	/**
	 * 
	 * @return metodo retonar fecha en la que se genero el método
	 */
	public static String  generarFecha(){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		Date tempDate = cal.getTime();
		Timestamp timestamp = new Timestamp(tempDate.getTime());
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 		
		return formatter.format(timestamp);
	}
	/**
	 * 
	 * @param tiempoExpiracionToken
	 * @return la fecha en la que expira el token en fecha minutos y segundos 
	 */
	public static String fechaExpiracionToken(int tiempoExpiracionToken) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		Date tempDate;
		
		int segundosToken = tiempoExpiracionToken;
		int minutos =0;
		int segundos=0;
		if(segundosToken>=60) {
			minutos= segundosToken/60;
		}else {
			segundos = segundosToken;
		}
	
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minutos);
		cal.set(Calendar.SECOND,cal.get(Calendar.SECOND)+segundos);
		tempDate = cal.getTime();
		Timestamp timestamp = new Timestamp(tempDate.getTime());
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return formatter.format(timestamp);
	}
	public static String tiempoFecha(int tiempoExpiracionToken) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		Date tempDate;
		
		int segundosToken = tiempoExpiracionToken;
		int minutos =0;
		int segundos=0;
		if(segundosToken>=60) {
			minutos= segundosToken/60;
		}else {
			segundos = segundosToken;
		}
	
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minutos);
		cal.set(Calendar.SECOND,cal.get(Calendar.SECOND)+segundos);
		tempDate = cal.getTime();
		Timestamp timestamp = new Timestamp(tempDate.getTime());
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return formatter.format(timestamp);
	}
	
	public static Date convertirStringAdate(String fechaFormatoTimestamp) {
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date convertido = null;
		try {
			convertido = fechaHora.parse(fechaFormatoTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertido;
	}
	
	public static String leerTemplate(String nombreTemplate) throws Exception {
		InputStream inputStream = null;
		String contenido = null;
		File file = null;		
		file = ResourceUtils.getFile("classpath:templates/"+nombreTemplate);
		if(file == null)
			throw new Exception("No se encontro archivo especificado"); 
		if(!file.exists())
			throw new Exception("No se encontro archivo especificado");
		
		inputStream = new FileInputStream(file);
		contenido = IOUtils.toString(inputStream);
		
		return contenido;
	}
	
	public static String remplezarDatosTemplate(Map<String, String> map, String contenido) {		
		for (String item : map.keySet()) {
			contenido = contenido.replace(item, map.get(item));
		}		
		return contenido;
	}
	
	/**
	 * 
	 * @param 
	 * @return Genera Password Aleatorio
	 */
	 
	 public static String generarPassword() {
		    int leftLimit = 48; // numeral '0'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();
		 
		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();
		 
		    System.out.println(generatedString);
		    
		    return generatedString;
	 }
}
