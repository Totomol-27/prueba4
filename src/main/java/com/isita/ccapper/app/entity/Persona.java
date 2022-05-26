package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "persona")

@NamedStoredProcedureQueries(value = {
    @NamedStoredProcedureQuery(name = "alta_persona", procedureName = "persona_alt",
    parameters = {
    	@StoredProcedureParameter(mode = ParameterMode.IN, name = "cliente_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "apellido_paterno", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "apellido_materno", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "primer_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "segundo_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_nacimiento", type =  LocalDate.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sexo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "calle", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "numero_casa", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "colonia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigo_postal", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "municipio", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "grado_estudio", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "no_celular", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "no_telefono", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "corre_electronico", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado_civil", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "peso", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatura", type = Double.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nss", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "curp", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
    }),
    @NamedStoredProcedureQuery(name = "eliminar_persona", procedureName = "persona_act",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "idpersonas", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class),
    }),
    @NamedStoredProcedureQuery(name = "modificar_persona", procedureName = "persona_mod",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "cliente_id", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "apellido_paterno", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "apellido_materno", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "primer_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "segundo_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha_nacimiento", type =  LocalDate.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sexo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "calle", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "numero_casa", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "colonia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigo_postal", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "municipio", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "grado_estudio", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "no_celular", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "no_telefono", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "corre_electronico", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado_civil", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "peso", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatura", type = Double.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nss", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "curp", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_codigo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_mensaje", type = String.class)
    })
})

@NamedNativeQueries({
    @NamedNativeQuery(
        name = "consulta_persona",
        query = "{? = call persona_con(?,?,?,?,?,?,?,?)}",
        callable = true,
        resultSetMapping = "persona"
    )
})
@SqlResultSetMapping(
    name = "persona",
    entities = {
        @EntityResult(
            entityClass = Persona.class,
            fields = {
                @FieldResult(
                    name = "id",
                    column = "id"
                ),
                @FieldResult(
                	name = "cliente_id",
                	column = "cliente_id"
                ),
                @FieldResult(
                    name = "apellidoPaterno",
                    column = "apellido_paterno"
                ),
                @FieldResult(
                    name = "apellidoMaterno",
                    column = "apellido_materno"
                ),
                @FieldResult(
                    name = "primerNombre",
                    column = "primer_nombre"
                ),
                @FieldResult(
                    name = "segundoNombre",
                    column = "segundo_nombre"
                ),
                @FieldResult(
                    name = "fechaNacimiento",
                    column = "fecha_nacimiento"
                ),
                @FieldResult(
                    name = "sexo",
                    column = "sexo"
                ),
                @FieldResult(
                    name = "calle",
                    column = "calle"
                ),
                @FieldResult(
                    name = "numeroCasa",
                    column = "numero_casa"
                ),
                @FieldResult(
                    name = "colonia",
                    column = "colonia"
                ),
                @FieldResult(
                    name = "codigoPostal",
                    column = "codigo_postal"
                ),
                @FieldResult(
                    name = "municipio",
                    column = "municipio"
                ),
                @FieldResult(
                    name = "estado",
                    column = "estado"
                ),
                @FieldResult(
                    name = "gradoEstudio",
                    column = "grado_estudio"
                ),
                @FieldResult(
                    name = "numCelular",
                    column = "no_celular"
                ),
                @FieldResult(
                    name = "numTelefono",
                    column = "no_telefono"
                ),
                @FieldResult(
                    name = "email",
                    column = "correo_electronico"
                ),
                @FieldResult(
                    name = "estadoCivil",
                    column = "estado_civil"
                ),
                @FieldResult(
                    name = "peso",
                    column = "peso"
                ),
                @FieldResult(
                    name = "estatura",
                    column = "estatura"
                ),
                @FieldResult(
                    name = "nss",
                    column = "nss"
                ),
                @FieldResult(
                    name = "curp",
                    column = "curp"
                ),
                @FieldResult(
                    name = "estatus",
                    column = "estatus"
                )
                
            }
        )
    }
)

public class Persona  implements Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * @param cliente_id
    * @param apellido_paterno
    * @param apellido_materno
    * @param primer_nombre
    * @param segundo_nombre
    * @param fecha_nacimiento
    * @param sexo
    * @param calle
    * @param numero_casa
    * @param colonia
    * @param codigo_postal
    * @param municipio
    * @param estado
    * @param grado_estudio
    * @param no_celular
    * @param no_telefono
    * @param correo_electronico
    * @param estado_civil
    * @param peso
    * @param estatura
    * @param nss
    * @param curp
    * @param estatus
    */


    public Persona(Long id, Integer cliente_id, @Size(max = 250) String apellidoPaterno, @Size(max = 250) String apellidoMaterno,
		@Size(max = 200) String primerNombre, @Size(max = 200) String segundoNombre,LocalDate fechaNacimiento,
		@Size(max = 1) String sexo, @Size(max = 250) String calle, @Size(max = 8) String numeroCasa,
		@Size(max = 250) String colonia, Integer codigoPostal, @Size(max = 250) String municipio,
		@Size(max = 100) String estado, @Size(max = 250) String gradoEstudio, @Size(max = 10) String numCelular,
		@Size(max = 10) String numTelefono, @Size(max = 200) String email, @Size(max = 50) String estadoCivil,
		Integer peso, Double estatura, @Size(max = 100) String nss, @Size(max = 18) String curp, Integer estatus) {
	super();
	this.id = id;
	this.cliente_id = cliente_id;
	this.apellidoPaterno = apellidoPaterno;
	this.apellidoMaterno = apellidoMaterno;
	this.primerNombre = primerNombre;
	this.segundoNombre = segundoNombre;
	this.fechaNacimiento = fechaNacimiento;
	this.sexo = sexo;
	this.calle = calle;
	this.numeroCasa = numeroCasa;
	this.colonia = colonia;
	this.codigoPostal = codigoPostal;
	this.municipio = municipio;
	this.estado = estado;
	this.gradoEstudio = gradoEstudio;
	this.numCelular = numCelular;
	this.numTelefono = numTelefono;
	this.email = email;
	this.estadoCivil = estadoCivil;
	this.peso = peso;
	this.estatura = estatura;
	this.nss = nss;
	this.curp = curp;
	this.estatus = estatus;
}
   
   

    /*
    Constructor vacio
     */
    public Persona(){
        super();
    }




	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	private Integer cliente_id;

    @Size(max = 250)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Size(max = 250)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Size(max = 200)
    @Column(name = "primer_nombre")
    private String primerNombre;

    @Size(max = 200)
    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "fecha_nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Size(max = 1)
    private String sexo;

    @Size(max = 250)
    private String calle;

    @Size(max = 8)
    @Column(name = "numero_casa")
    private String numeroCasa;

    @Size(max = 250)
    private String colonia;

    @Column(name = "codigo_postal")
    private Integer codigoPostal;

    @Size(max = 250)
    private String municipio;

    @Size(max = 100)
    private String estado;

    @Size(max = 250)
    @Column(name = "grado_estudio")
    private String gradoEstudio;

    @Size(max = 10)
    @Column(name = "no_celular")
    private String numCelular;

    @Size(max = 10)
    @Column(name = "no_telefono")
    private String numTelefono;

    @Size(max = 200)
    @Column(name = "correo_electronico")
    private String email;

    @Size(max = 50)
    @Column(name = "estado_civil")
    private String estadoCivil;

    private Integer peso;

    private Double estatura;

    @Size(max = 100)
    private String nss;

    @Size(max = 18)
    private String curp;
    
    private Integer estatus;
    
}
