package com.isita.ccapper.app.authentication;

import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.isita.ccapper.app.util.Constantes;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {
	 private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
	    private String BAD_REQUEST = "BAD_REQUEST";
	    private String PANTALLA_YA_EXISTE_CON_EL_PERFIL_SOLICITADO="PANTALLA_YA_EXISTE_CON_EL_PERFIL_SOLICITADO";
	    private String PEFILES_EXISTE = "PEFILES_EXISTE";
	    private String TIPO_ERROR ="";
	    private String EVENTO_EXISTS = "EVENTO EXISTENTE CON LOS MISMOS VALORES";
	     
	    @ExceptionHandler(RecordNotFoundException.class)
	    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
	                        (RecordNotFoundException ex, WebRequest request) 
	    {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	     
	    @ExceptionHandler(MissingHeaderInfoException.class)
	    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(MissingHeaderInfoException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        String tipoError[] = ex.getLocalizedMessage().split(",");
	        if(tipoError[0].equals("00002")) {
	        	TIPO_ERROR = PANTALLA_YA_EXISTE_CON_EL_PERFIL_SOLICITADO;
	        }else if(tipoError[0].equals("00003")) {
	        	TIPO_ERROR = PEFILES_EXISTE;
	        }else if (tipoError[0].equals(Constantes.CODIGO_MENSAJE)) {
	        	TIPO_ERROR = EVENTO_EXISTS;
	        }
	        else{
	        	TIPO_ERROR =BAD_REQUEST;
	        }
	        details.add(ex.getLocalizedMessage().substring(6,ex.getLocalizedMessage().length()));
	        ErrorResponse error = new ErrorResponse(TIPO_ERROR, details);
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
}
