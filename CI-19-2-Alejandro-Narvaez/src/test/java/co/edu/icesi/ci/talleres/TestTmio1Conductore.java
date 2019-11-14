package co.edu.icesi.ci.talleres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.ci.talleres.dao.DAOBus;
import co.edu.icesi.ci.talleres.dao.DAOConductor;
import co.edu.icesi.ci.talleres.dao.DAORuta;
import co.edu.icesi.ci.talleres.dao.DAOServicio;
import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;

@SpringBootTest(classes = Ci192TalleresApplication.class)
@RunWith(SpringRunner.class)
@Rollback
public class TestTmio1Conductore {

	@Autowired
	private DAOConductor bus;

	@Autowired
	private DAORuta rutad;
	@Autowired
	private DAOBus busd;


	@Autowired
	private DAOServicio serviciod;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void escenario() {
		assertNotNull(bus);
		Tmio1Conductore talumno = new Tmio1Conductore();
		talumno.setApellidos("Alvarez");
		talumno.setNombre("Gabriel");
		talumno.setCedula("1234");
		Date data = new Date();
		Date data1 = new Date();
		data.setDate(12);
		data1.setDate(20);
		talumno.setFechaContratacion(data1);
		talumno.setFechaNacimiento(data);
		bus.save(talumno);
	}
	// --------------------------------------------------------------------
	// PRUEBA
	// --------------------------------------------------------------------
	public void escenario2() throws ParseException {
		Tmio1Ruta rut = new Tmio1Ruta();
		rut.setActiva("si");
		rut.setDiaInicio(new BigDecimal(20));
		rut.setDiaFin(new BigDecimal(26));
		rut.setNumero("123");
		rut.setDescripcion("descrpcion");
		rut.setHoraInicio(new BigDecimal(180));
		rut.setHoraFin(new BigDecimal(200));

		rutad.save(rut);

		Tmio1Bus bu = new Tmio1Bus();
		bu.setMarca("Mazda");
		bu.setModelo(123);
		bu.setPlaca("IPA666");
		bu.setCapacidad(20.0);

		busd.save(bu);

		Tmio1Conductore condu = new Tmio1Conductore();
		condu.setCedula("123456");
		condu.setApellidos("Morales");
		condu.setNombre("Larvo");
		condu.setFechaNacimiento(format.parse("1998-12-01"));
		condu.setFechaContratacion(format.parse("1999-12-01"));

		bus.save(condu);

		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("123456");
		pk.setIdBus(bu.getId());
		pk.setIdRuta(rut.getId());
		pk.setFechaInicio(format.parse("2003-12-10"));
		pk.setFechaFin(format.parse("2003-12-20"));

		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setTmio1Bus(bu);
		serv.setTmio1Conductore(condu);
		serv.setId(pk);
		serv.setTmio1Ruta(rut);

		serviciod.save(serv);

		pk = new Tmio1ServicioPK();
		pk.setCedulaConductor("123456");
		pk.setIdBus(bu.getId());
		pk.setIdRuta(rut.getId());
		pk.setFechaInicio(format.parse("2003-12-05"));
		pk.setFechaFin(format.parse("2003-12-25"));

		serv = new Tmio1Servicio();
		serv.setTmio1Bus(bu);
		serv.setTmio1Conductore(condu);
		serv.setId(pk);
		serv.setTmio1Ruta(rut);

		serviciod.save(serv);

	}



	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void ConductoresServiciosTest() throws ParseException {
	escenario2();
	Date fecha= format.parse("2003-12-17");
		assertNotNull(bus.buscarConductoresConServicios(fecha).get(0));

	}
	
	// ------------------------------------------------------------------------
	// FINAL DE LA PRUEBA
	// ------------------------------------------------------------------------
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void agregarTest() {
		assertNotNull(bus);
		Tmio1Conductore talumno = new Tmio1Conductore();
		talumno.setApellidos("Alvarez");
		talumno.setNombre("Gabriel");
		talumno.setCedula("1234");
		bus.save(talumno);
		assertNotNull(bus.findAll().get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {
		assertNotNull(bus);
		Tmio1Conductore talumno = new Tmio1Conductore();
		talumno.setApellidos("Alvarez");
		talumno.setNombre("Gabriel");
		talumno.setCedula("1234");
		Date data = new Date();
		Date data1 = new Date();
		data.setDate(12);
		data1.setDate(20);
		talumno.setFechaContratacion(data1);
		talumno.setFechaNacimiento(data);
		bus.save(talumno);
		bus.delete(bus.findAll().get(0));
		assertEquals(0, bus.findAll().size());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buscarNombreTest() {
		this.escenario();
		assertNotNull(bus.buscarPorNombre("Gabriel").get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void buscarApellidoTest() {
		this.escenario();
		assertNotNull(bus.buscarPorApellido("Alvarez").get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {
		assertNotNull(bus);
		Tmio1Conductore talumno = new Tmio1Conductore();
		talumno.setApellidos("Alvarez");
		talumno.setNombre("Gabriel");
		talumno.setCedula("1234");
		bus.save(talumno);
		talumno.setApellidos("IPA");
		talumno.setNombre("Alverto");
		talumno.setCedula("1234");
		bus.update(talumno);
		assertNotNull(bus.findAll());
		assertEquals(1, bus.buscarPorApellido("IPA").size());
	}
}
