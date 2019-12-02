package co.edu.icesi.ci.talleres.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.icesi.ci.talleres.validation.Validation1;
import java.util.List;


/**
 * The persistent class for the tmio1_buses database table.
 * 
 */
@Entity
@Table(name="tmio1_buses")
@NamedQuery(name="Tmio1Bus.findAll", query="SELECT t FROM Tmio1Bus t")
public class Tmio1Bus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TMIO1_BUSES_ID_GENERATOR", sequenceName="TMIO1_BUSES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMIO1_BUSES_ID_GENERATOR")
	private Integer id;

	@NotNull(groups = { Validation1.class})
	private Double capacidad;
	
	@NotBlank(groups = { Validation1.class})
	private String marca;

	@NotNull(groups = { Validation1.class})
	private Integer modelo;

	@NotBlank(groups = { Validation1.class})
	private String placa;

	private BusType tipo;

	//bi-directional many-to-one association to Tmio1Servicio
	@OneToMany(mappedBy="tmio1Bus")
	@JsonIgnore
	private List<Tmio1Servicio> tmio1Servicios;

	//bi-directional many-to-one association to Tmio1ServiciosSitio
	@OneToMany(mappedBy="tmio1Bus")
	@JsonIgnore
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitios;

	public Tmio1Bus() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getModelo() {
		return this.modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public BusType getTipo() {
		return this.tipo;
	}

	public void setTipo(BusType tipo) {
		this.tipo = tipo;
	}

	public List<Tmio1Servicio> getTmio1Servicios() {
		return this.tmio1Servicios;
	}

	public void setTmio1Servicios(List<Tmio1Servicio> tmio1Servicios) {
		this.tmio1Servicios = tmio1Servicios;
	}

	public Tmio1Servicio addTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().add(tmio1Servicio);
		tmio1Servicio.setTmio1Bus(this);

		return tmio1Servicio;
	}

	public Tmio1Servicio removeTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().remove(tmio1Servicio);
		tmio1Servicio.setTmio1Bus(null);

		return tmio1Servicio;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitios() {
		return this.tmio1ServiciosSitios;
	}

	public void setTmio1ServiciosSitios(List<Tmio1ServiciosSitio> tmio1ServiciosSitios) {
		this.tmio1ServiciosSitios = tmio1ServiciosSitios;
	}

	public Tmio1ServiciosSitio addTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().add(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Bus(this);

		return tmio1ServiciosSitio;
	}

	public Tmio1ServiciosSitio removeTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().remove(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Bus(null);

		return tmio1ServiciosSitio;
	}

}