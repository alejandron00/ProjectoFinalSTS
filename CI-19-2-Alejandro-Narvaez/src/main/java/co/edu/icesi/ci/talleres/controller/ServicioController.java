package co.edu.icesi.ci.talleres.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.talleres.model.Tmio1Bus;
import co.edu.icesi.ci.talleres.model.Tmio1Conductore;
import co.edu.icesi.ci.talleres.model.Tmio1Ruta;
import co.edu.icesi.ci.talleres.model.Tmio1Servicio;
import co.edu.icesi.ci.talleres.model.Tmio1ServicioPK;
import co.edu.icesi.ci.talleres.service.BusService;
import co.edu.icesi.ci.talleres.service.ConductorService;
import co.edu.icesi.ci.talleres.service.RutaService;
import co.edu.icesi.ci.talleres.service.ServicioService;

@Controller
public class ServicioController {

	private ServicioService servicioService;
	private ConductorService conductorService;
	private BusService busService;
	private RutaService rutaService;
	private Iterable<Tmio1Conductore> conductores;
	private Iterable<Tmio1Bus> buses;
	private Iterable<Tmio1Ruta> rutas;

	@Autowired
	public ServicioController(ServicioService servicioService, ConductorService conductorService, BusService busService,
			RutaService rutaService) {
		this.servicioService = servicioService;
		this.conductorService = conductorService;
		this.rutaService = rutaService;
		this.busService = busService;
		conductores = conductorService.findAll();
		buses = busService.findAll();
		rutas = rutaService.findAll();
	}

	@PostMapping("/servicios/add/")
	public String saveService(@Valid @ModelAttribute Tmio1ServicioPK tmio1ServicioPK, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String accion, Model modelo) throws Exception {
		if (!accion.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				modelo.addAttribute("conductores", conductores);
				modelo.addAttribute("buses", buses);
				modelo.addAttribute("rutas", rutas);
				return "servicios/add-servicio";
			} else {
				Tmio1Servicio ser = new Tmio1Servicio();
				ser.setId(tmio1ServicioPK);
				ser.setTmio1Conductore(conductorService.findById(tmio1ServicioPK.getCedulaConductor()));
				ser.setTmio1Bus(busService.findById(tmio1ServicioPK.getIdBus()));
				ser.setTmio1Ruta(rutaService.findById(tmio1ServicioPK.getIdRuta()));
				ser.setIdHash(ser.getId().hashCode());
				servicioService.save(ser);
			}
		}
		return "redirect:/servicios/";
	}

	@GetMapping("/servicios/")
	public String indexServicio(Model modelo) {
		modelo.addAttribute("servicios", servicioService.findAll());
		modelo.addAttribute("servicioSearched", new Tmio1Servicio());
		return "servicios/index";
	}

	@GetMapping("/servicios/add/")
	public String addService(Model modelo) {
		modelo.addAttribute("servicio", new Tmio1ServicioPK());
		modelo.addAttribute("conductores", conductorService.findAll());
		modelo.addAttribute("buses", busService.findAll());
		modelo.addAttribute("rutas", rutaService.findAll());
		return "servicios/add-servicio";
	}

	@PostMapping("/servicios/search/")
	public String searchServicio(Tmio1Servicio servicio, Model modelo) {
		try {
			Iterable<Tmio1Servicio> todos = servicioService.findAll();
			for (Tmio1Servicio ser : todos) {
				if (ser.getId().getFechaInicio().equals(servicio.getId().getFechaInicio())) {
					modelo.addAttribute("servicios", ser);
				}
			}
		} catch (Exception s) {
			modelo.addAttribute("servicios", servicioService.findAll());
		}
		modelo.addAttribute("servicioSearched", new Tmio1Servicio());
		return "servicios/index";
	}

	@GetMapping("/servicios/edit/{idHash}")
	public String showUpdateServices(@PathVariable("idHash") Integer id, Model modelo) {
		Tmio1Servicio servicio = servicioService.buscarPorId(id);
		if (servicio == null)
			throw new IllegalArgumentException("Invalid service Id:" + id);
		modelo.addAttribute("servicio", servicio);
		modelo.addAttribute("conductores", conductores);
		modelo.addAttribute("buses", buses);
		modelo.addAttribute("rutas", rutas);
		return "servicios/update-servicio";
	}

	@PostMapping("/servicios/edit/{idHash}")
	public String updateService(@PathVariable("idHash") Integer id,
			@RequestParam(value = "action", required = true) String accion,
			@Valid @ModelAttribute Tmio1ServicioPK tmio1ServicioPK, BindingResult bindingResult, Model modelo)
			throws Exception {
		if (accion != null && !accion.equals("Cancel")) {
			Tmio1Servicio servicio = servicioService.buscarPorId(id);
			if (servicio == null)
				throw new IllegalArgumentException("Invalid service Id:" + id);
			modelo.addAttribute("servicio", servicio);
			if (bindingResult.hasErrors()) {
				modelo.addAttribute("conductores", conductores);
				modelo.addAttribute("buses", buses);
				modelo.addAttribute("rutas", rutas);
				return "servicios/update-servicio";
			} else {
				Tmio1Servicio ser = servicioService.buscarPorId(id);
				servicioService.delete(ser);
				Tmio1Servicio servicio1 = new Tmio1Servicio();
				servicio1.setId(tmio1ServicioPK);
				servicio1.setTmio1Conductore(conductorService.findById(tmio1ServicioPK.getCedulaConductor()));
				servicio1.setTmio1Bus(busService.findById(tmio1ServicioPK.getIdBus()));
				servicio1.setTmio1Ruta(rutaService.findById(tmio1ServicioPK.getIdRuta()));
				servicio1.setIdHash(servicio1.getId().hashCode());
				servicioService.save(servicio1);
			}
		}
		return "redirect:/servicios/";
	}

}
