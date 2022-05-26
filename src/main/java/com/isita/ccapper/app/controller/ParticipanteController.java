package com.isita.ccapper.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isita.ccapper.app.entity.Evento;
import com.isita.ccapper.app.entity.Participante;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.interfaces.IParticipante;
import com.isita.ccapper.app.model.MyCellModel;
import com.isita.ccapper.app.model.RespuestaMensaje;
import com.isita.ccapper.app.service.ParticipanteService;
import com.isita.ccapper.app.util.ExcelPOI;


@RestController
@RequestMapping("/participante")
public class ParticipanteController {
	private String fileLocation;

	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private  IParticipante iParticipante;
	
	@Autowired
	private ExcelPOI excelPOI;
	
	@ResponseStatus(HttpStatus.CREATED)
	public Participante save(@RequestBody Participante participante) {
		return participanteService.save(participante);
	}
	
	@PostMapping("/createParticipantes")
	@ResponseStatus(HttpStatus.CREATED)
	public Participante saveParticipante(@RequestParam String participantes,  @RequestParam Integer idEvento, @RequestParam String ipConfig) {
		return participanteService.savelistParticipante(participantes, idEvento, ipConfig);
	}
	
	@GetMapping("/{id}")
	public Participante findAll(@PathVariable Long id) {
			Participante objParticipante  = new Participante();
			Participante objParticipantes  = new Participante();
			objParticipantes.setId(id);
			int page = 0;
			int sizes = 0;
			String tipoConsulta = "C1";
		List <Participante> objParticipa = this.participanteService.findAll(objParticipantes, page, sizes, tipoConsulta);
		objParticipante = objParticipa.get(0);
		return objParticipante;
	}
	
	@GetMapping("/consultaParticipantes")
	public List<Participante> findAll(Integer idEvento,Integer page, Integer sizes, String tipoConsulta) {
		Participante objParticipante  = new Participante();
		Evento objEvento = new Evento();
		
		objEvento.setId(idEvento.longValue());
		objParticipante.setEvento(objEvento);
		return participanteService.findAll(objParticipante, page, sizes, tipoConsulta);
	}
	
	@DeleteMapping(value = "/{participantes}")
	public ResponseEntity<?> delete(@PathVariable String participantes,@RequestParam Integer idEvento,@RequestParam String tipoEliminacion){
		Map<String, Object> response = new HashMap<>();
		try {
			this.participanteService.delete(participantes, idEvento, tipoEliminacion);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar al participante");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "participante eliminado");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/personaId/{id}")
	public Participante findById(@PathVariable Integer id, @RequestParam String tipoConsulta) {
		Participante participante = participanteService.findById(id, tipoConsulta);
		return participante;
	}
	
	
	@PostMapping("/correo")
	public ResponseEntity<RespuestaMensaje> cargarArchivo(@RequestParam("file") MultipartFile file) {
	    String mensaje = "";
	    Usuario user = new Usuario();
	    try {
	    	iParticipante.envioEmail(file, user,"");
	    	mensaje = "Correo enviado";
	      return ResponseEntity.status(HttpStatus.OK).body(new RespuestaMensaje(mensaje));
	    } catch (Exception e) {
	    	System.out.println(e);
	    	mensaje = "No se fue posible enviar el correo";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new RespuestaMensaje(mensaje));
	    }
	  }
	
	@PostMapping("/archivoExcel")
	public String uploadFile(Model model, MultipartFile file) throws IOException {
		System.out.println("Se ejecuto el metodo");
		
		InputStream in = file.getInputStream(); 
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() -1 )+file.getOriginalFilename();
		FileOutputStream f = new FileOutputStream(fileLocation);
		int ch = 0;
		while((ch = in.read()) != -1) {
			f.write(ch);
		}
		f.flush();
		f.close();
		
		if(fileLocation != null) {
			System.out.print(fileLocation);
			if(fileLocation.endsWith(".xlsx" ) || fileLocation.endsWith(".xls")) {
				Map<Integer, List<MyCellModel>> data = excelPOI.readExcel(fileLocation);
				System.out.println("INFORMACION DEL ARCHIVO EXCEL PARTICIPANTE CONTROLLER LINEA 142"+data);
			}
		}
		model.addAttribute("message", "File: "+file.getOriginalFilename()+ "archivo subido");
		return file.getOriginalFilename();
	}
	
}
