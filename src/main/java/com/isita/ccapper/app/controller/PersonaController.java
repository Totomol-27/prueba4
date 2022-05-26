package com.isita.ccapper.app.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.service.PersonaService;
import com.isita.ccapper.app.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;


    @GetMapping("buscar/{id}")
	public List<Persona> findById(@PathVariable Long id, @RequestParam String tipo_consulta){
			System.out.println("PersonaController buscar por id "+id+" Parama: "+tipo_consulta);
			Persona persona = personaService.findById(id,tipo_consulta);
			List<Persona> personas = new ArrayList<>();
			personas.add(persona);
			return personas;
    }
    
    @GetMapping("img/{id}")
    public ResponseEntity<?> verFoto(HttpServletResponse response, @PathVariable Long id) throws IOException{
    	
        Persona persona = personaService.findById(id,"L2");
        String tmpdir = System.getProperty("user.home");
        String nombreCarpeta = persona.getNss().toString();
        String rutaBase = tmpdir + "/ccapper/" + nombreCarpeta;
        String rutaCompleta = rutaBase+"/"+ "foto.jpg";
        File directorios = new File(rutaCompleta);

        InputStream inputStream = new FileInputStream(directorios);
        byte[] bytews = StreamUtils.copyToByteArray(inputStream);
        Resource imagen = new ByteArrayResource(bytews);
        return ResponseEntity.ok()
		.contentType(MediaType.IMAGE_JPEG)
		.body(imagen);
    }

	public static byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		return os.toByteArray();
	}
    
    @GetMapping("/findAll")
    public List<Persona> findAll(@RequestParam Integer pagina, @RequestParam Integer tamanio, @RequestParam String tipoConsulta){
        return personaService.findAll(pagina, tamanio, tipoConsulta);
    }


    /*
     * Metodo findByNameClienteId, hace la busqueda por el nombre de la persona
     * ,haciendo un filtro por el cliente al que pertenece 
     */
    @GetMapping("/nombreCliente")
    public List<Persona> findByNameClienteId(@RequestParam Integer clienteId, 
    		@RequestParam String nombre,  @RequestParam String tipoConsulta){
        return personaService.findByNameClienteId(clienteId, nombre, tipoConsulta);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona save(@RequestBody Persona persona){
    	System.out.println("PersonaController Create: "+persona);
        return personaService.save(persona);
    }

    @DeleteMapping(value = "/{listaPersona}")
    public ResponseEntity<?> delete(@PathVariable String listaPersona){
    	System.out.println("PersonaController ID persona a eliminar: "+listaPersona);
        Map<String, Object> response = new HashMap<>();
        try{
            this.personaService.delete(listaPersona);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar la persona de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","La persona se elimino con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public void update(@RequestBody Persona persona, String tipoModificacion){
        this.personaService.update(persona, tipoModificacion);
    }
    
    @GetMapping("/listPersonas")
    public List<Persona> ObtenerLstPersonas(@RequestParam String tipoConsulta){
    	return personaService.ListPerson(tipoConsulta);
    }
    
    @GetMapping("/personaEvento")
    public List<Persona>PersonasEvento(@RequestParam Integer id, @RequestParam String tipoConsulta ){
    	return personaService.listEvento(id, tipoConsulta);
    }
    
    @GetMapping("/namePersona")
   public List<Persona>findByName(@RequestParam String nombre, @RequestParam String tipoConsulta){
    	return personaService.findByName(nombre, tipoConsulta);
    }
    
    
    @PostMapping("/create-foto")
    public ResponseEntity<?> saveFoto(@RequestParam Integer id, @RequestParam String nombre, 
    		@RequestParam("archivo") MultipartFile archivo) throws IOException{	
    	
    	String tipo_consulta = Constantes.TIPO_CONSULTA_L2;
    	Persona persona = personaService.findById(id.longValue(),tipo_consulta);
    	if( persona.getNss() == null || archivo.isEmpty()) {
    		return	ResponseEntity.notFound().build();
    	}
    	
        String tmpdir = System.getProperty("user.home");
        String nombreCarpeta = persona.getNss().toString();
        String rutaBase = tmpdir + "/ccapper/" + nombreCarpeta;
        String rutaCompleta = rutaBase+"/"+ nombre+"."+"foto.jpg";
        File directorios = new File(rutaCompleta);
        File input = new File(rutaCompleta);    
        InputStream in = new ByteArrayInputStream(archivo.getBytes());
		BufferedImage image =ImageIO.read(in);   
		int a = image.getHeight();
		int b = image.getWidth();
		if(b == 2000 || a == 2000) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
        
        if(input.exists()){
        	input.delete();
		}
        
        if (!directorios.exists()) {
            if (directorios.mkdirs()) {
            	try{

                    BufferedImage result = new BufferedImage(
                    		 image.getWidth(),
                             image.getHeight(),
                            BufferedImage.TYPE_INT_RGB);
                    result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
                    ImageIO.write(result, "jpg", directorios);
            	}catch (Exception e) {
            		  e.printStackTrace();
				}
            } else {
                System.out.println("Error al crear directorios");
            }
        }
        return ResponseEntity.ok().body(persona);    
    }
    
    @GetMapping("nSeguridadSocial/{nss}")
    public List<Persona>buscarNss(@PathVariable String nss, @RequestParam String tipoConsulta){
    	return personaService.buscarNss(nss, tipoConsulta);
    }
    
    @GetMapping("/NSeguroSocial")
    public List<Persona> validSeguroSocial(@RequestParam String nss, @RequestParam String tipoConsulta) {
    	return personaService.buscarNss(nss , tipoConsulta);
    }
    
    
}
