package co.edu.icesi.ci.talleres.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tmio1_rutas database table.
 * 
 */
@Entity
@Table(name="tmio1_rutas")
@NamedQuery(name="Tmio1Ruta.findAll", query="SELECT t FROM Tmio1Ruta t")
public class Tmio1Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TMIO1_RUTAS_ID_GENERATOR", sequenceName="TMIO1_RUTAS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMIO1_RUTAS_ID_GENERATOR")
	private Integer id;

	private String activa;

	private String descripcion;

	@Column(name="dia_fin")
	private BigDecimal diaFin;

	@Column(name="dia_inicio")
	private BigDecimal diaInicio;

	@Column(name="hora_fin")
	private BigDecimal horaFin;

	@Column(name="hora_inicio")
	private BigDecimal horaInicio;

	private String numero;

	//bi-directional many-to-one association to Tmio1Servicio
	@OneToMany(mappedBy="tmio1Ruta")
	@JsonIgnore
	private List<Tmio1Servicio> tmio1Servicios;

	//bi-directional many-to-one association to Tmio1ServiciosSitio
	@OneToMany(mappedBy="tmio1Ruta")
	@JsonIgnore
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitios;

	//bi-directional many-to-one association to Tmio1SitiosRuta
	@OneToMany(mappedBy="tmio1Ruta1")
	@JsonIgnore
	private List<Tmio1SitiosRuta> tmio1SitiosRutas1;

	//bi-directional many-to-one association to Tmio1SitiosRuta
	@OneToMany(mappedBy="tmio1Ruta2")
	@JsonIgnore
	private List<Tmio1SitiosRuta> tmio1SitiosRutas2;

	public Tmio1Ruta() {
	}

	public List<Tmio1Servicio> getTmio1Servicios() {
		return this.tmio1Servicios;
	}

	public void setTmio1Servicios(List<Tmio1Servicio> tmio1Servicios) {
		this.tmio1Servicios = tmio1Servicios;
	}

	public Tmio1Servicio addTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().add(tmio1Servicio);
		tmio1Servicio.setTmio1Ruta(this);

		return tmio1Servicio;
	}

	public Tmio1Servicio removeTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().remove(tmio1Servicio);
		tmio1Servicio.setTmio1Ruta(null);

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
		tmio1ServiciosSitio.setTmio1Ruta(this);

		return tmio1ServiciosSitio;
	}

	public Tmio1ServiciosSitio removeTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().remove(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Ruta(null);

		return tmio1ServiciosSitio;
	}

	public List<Tmio1SitiosRuta> getTmio1SitiosRutas1() {
		return this.tmio1SitiosRutas1;
	}

	public void setTmio1SitiosRutas1(List<Tmio1SitiosRuta> tmio1SitiosRutas1) {
		this.tmio1SitiosRutas1 = tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta addTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().add(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Ruta1(this);

		return tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta removeTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().remove(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Ruta1(null);

		return tmio1SitiosRutas1;
	}

	public List<Tmio1SitiosRuta> getTmio1SitiosRutas2() {
		return this.tmio1SitiosRutas2;
	}

	public void setTmio1SitiosRutas2(List<Tmio1SitiosRuta> tmio1SitiosRutas2) {
		this.tmio1SitiosRutas2 = tmio1SitiosRutas2;
	}

	public Tmio1SitiosRuta addTmio1SitiosRutas2(Tmio1SitiosRuta tmio1SitiosRutas2) {
		getTmio1SitiosRutas2().add(tmio1SitiosRutas2);
		tmio1SitiosRutas2.setTmio1Ruta2(this);

		return tmio1SitiosRutas2;
	}

	public Tmio1SitiosRuta removeTmio1SitiosRutas2(Tmio1SitiosRuta tmio1SitiosRutas2) {
		getTmio1SitiosRutas2().remove(tmio1SitiosRutas2);
		tmio1SitiosRutas2.setTmio1Ruta2(null);

		return tmio1SitiosRutas2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(BigDecimal diaFin) {
		this.diaFin = diaFin;
	}

	public BigDecimal getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(BigDecimal diaInicio) {
		this.diaInicio = diaInicio;
	}

	public BigDecimal getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(BigDecimal horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(BigDecimal horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}