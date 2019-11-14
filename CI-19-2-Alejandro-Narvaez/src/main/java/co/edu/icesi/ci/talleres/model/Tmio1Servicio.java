package co.edu.icesi.ci.talleres.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the tmio1_servicios database table.
 * 
 */
@Entity
@Table(name="tmio1_servicios")
@NamedQuery(name="Tmio1Servicio.findAll", query="SELECT t FROM Tmio1Servicio t")
public class Tmio1Servicio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idHash;
	
	@EmbeddedId
	private Tmio1ServicioPK id;

	//bi-directional many-to-one association to Tmio1Bus
	@ManyToOne
	@JoinColumn(name="id_bus", insertable=false, updatable=false)
	private Tmio1Bus tmio1Bus;

	//bi-directional many-to-one association to Tmio1Conductore
	@ManyToOne
	@JoinColumn(name="cedula_conductor", insertable=false, updatable=false)
	private Tmio1Conductore tmio1Conductore;

	//bi-directional many-to-one association to Tmio1Ruta
	@ManyToOne
	@JoinColumn(name="id_ruta", insertable=false, updatable=false)
	private Tmio1Ruta tmio1Ruta;

	public Tmio1Servicio() {
		
	}
	public Integer getIdHash() {
		return idHash;
	}

	public void setIdHash(Integer idHash) {
		this.idHash = idHash;
	}

	public Tmio1ServicioPK getId() {
		return id;
	}

	public void setId(Tmio1ServicioPK id) {
		this.id = id;
	}

	public Tmio1Bus getTmio1Bus() {
		return tmio1Bus;
	}

	public void setTmio1Bus(Tmio1Bus tmio1Bus) {
		this.tmio1Bus = tmio1Bus;
	}

	public Tmio1Conductore getTmio1Conductore() {
		return tmio1Conductore;
	}

	public void setTmio1Conductore(Tmio1Conductore tmio1Conductore) {
		this.tmio1Conductore = tmio1Conductore;
	}

	public Tmio1Ruta getTmio1Ruta() {
		return tmio1Ruta;
	}

	public void setTmio1Ruta(Tmio1Ruta tmio1Ruta) {
		this.tmio1Ruta = tmio1Ruta;
	}

}