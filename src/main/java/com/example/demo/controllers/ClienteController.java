package com.example.demo.controllers;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entities.Cheque;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Pago;
import com.example.demo.entities.SecurityUser;
import com.example.demo.services.IChequeService;
import com.example.demo.services.IClienteServices;
import com.example.demo.services.IPagoService;
import com.example.demo.services.IUserService;
import com.example.demo.util.PageRender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/api")
public class ClienteController {

	@Autowired
	IClienteServices ClienteService;

	@Autowired
	IChequeService ChequeServices;

	@Autowired
	IPagoService pagoService;

	@Autowired
	IUserService userService;

	List<Cheque> listOfCheques;

	// VARIABLES PARA LOS FORMULARIOS DE CREAR UN NUEVO CHEQUE
	private Cliente clienteObjNuevoCheque = new Cliente();
	private Cheque chequeObjNuevoCheque = new Cheque();
	// INDICARA CUAL FORMULARIO ESTARA VISIBLE
	private int formularioVisible = -1;
	// VARIABLES PARA EL FORMULARIO DE PAGAR UN CHEQUE
	private Cliente clientePagarCheque = new Cliente();
	private Cheque chequePagarCheque = new Cheque();
	// VARIABLES PARA BUSCAR UN CLIENTE YA EXISTENTE CUANDO SE CREA UN CHEQUE
	private PageRender<Cliente> clientesRender = null;
	private Page<Cliente> ClientesPage = null;

	// ---------------------------------------------------------------------------------------------//
	// LADO DEL LOGIN//
	// ---------------------------------------------------------------------------------------------//
	// LOGGIN DE LA PAGINA
	@GetMapping("/Login")
	public String Login_page(Model model) {
		return "/pages/Login";
	}

	// FORMULARIO PARA CUANDO SE HAGA LOG IN, SE ENVIE O AL HOME DEL ADMINISTRADOR O
	// AL HOME DEL USUARIO ESTANDAR
	@RequestMapping(value = "LoginForm", method = RequestMethod.POST)
	public String LoginForm_method(@RequestParam String Usuario, 
			@RequestParam String contrasenia, Authentication auth) {
		String redirect = null;
		if (auth.getPrincipal().toString().contains("USER")) {
			redirect= "Usuario/Home";
		}	
		else
		if(auth.getPrincipal().toString().contains("ADMIN")){
			redirect = "AdminHome";
		}
			return "redirect:/".concat(redirect);
		// return "/pages/Login";
	}

	// ---------------------------------------------------------------------------------------------//
	// LADO DEL ADMINISTRADOR//
	// ---------------------------------------------------------------------------------------------//
	// PAGINA HOME DEL ADMINISTRADOR
	@GetMapping("/AdminHome")
	public String AdminHome_page(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
		// BUSCAR TODO LOS USUARIOS PARA MOSTRARLOS AL CARGAR LA PAGINA
		Pageable pageRequest = PageRequest.of(page, 12);
		Page<SecurityUser> Usuarios = userService.FindAllUsers(pageRequest);
		PageRender<SecurityUser> pageRender = new PageRender<>("/AdminHome", Usuarios);

		// ENVIAR LOS DATOS A LA PLANTILLA
		model.addAttribute("page", pageRender);
		model.addAttribute("Usuarios", Usuarios);
		return "/pages/Admin_Templates/AdminHome";
	}

	// FORMULARIO PARA CUANDO SE HAGA UNA BUSQUEDA DE UN USUARIO
	@RequestMapping(value = "/BuscarUsurioForm", method = RequestMethod.POST)
	public String BuscarUsuarioForm_method(@RequestParam String BuscarUsurioForm) {
		return "redirect:/Administrador/Busqueda/" + BuscarUsurioForm + "/";
	}

	// ESTE METODO ES PARA CUANDO SE HAGA UNA BUSQUEDA DE USUARIOS POR EL CAMPO DE
	// NOMBRE, APELLIDO O CEDULA.
	@GetMapping("/Administrador/Busqueda/{texto}/")
	public String UsuariosBuscados_Page(@PathVariable(value = "texto") String texto, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		// BUSCAR TODO LOS USUARIOS PARA MOSTRARLOS AL REALIZAR UNA BUSQUEDA
		Pageable pageRequest = PageRequest.of(page, 12);
		Page<SecurityUser> Usuarios = userService.FindUsersByParameters(texto, pageRequest);
		PageRender<SecurityUser> pageRender = new PageRender<>("/Administrador/Busqueda/" + texto + "/", Usuarios);

		// ENVIAR LOS DATOS A LA PLANTILLA
		model.addAttribute("page", pageRender);
		model.addAttribute("Usuarios", Usuarios);
		return "/pages/Admin_Templates/AdminHome";
	}

	//	MUESTRA LOS DETALLES DEL 
	@GetMapping("/Ver/Pagos/{id}/{nombre}/{apellido}/")
	public String PagosRealizadosPoUsuario_Page(Model model, @PathVariable(value = "id") Long id,
			@PathVariable(value = "nombre") String nombre, @PathVariable(value = "apellido") String apellido,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		// BUSCAR TODO LOS PAGOS REALIZADOS POR EL USUARIO SELECCIONADO
		Pageable pageRequest = PageRequest.of(page, 12);
		Page<Pago> Pagos = pagoService.FindAllByChequeForUser(id.toString(), pageRequest);
		PageRender<Pago> pageRender = new PageRender<>("/Ver/Pagos/" + id + "/", Pagos);

		// ENVIAR LOS DATOS A LA PLANTILLA
		model.addAttribute("usuario", nombre + " " + apellido);
		model.addAttribute("page", pageRender);
		model.addAttribute("Pagos", Pagos);
		return "/pages/Admin_Templates/PagosRealizados";
	}

	// ---------------------------------------------------------------------------------------------//
	// LADO LEL USUARIO//
	// ---------------------------------------------------------------------------------------------//
	// PAGINA HOME DEL ADMINISTRADOR
	@GetMapping("/Usuario/Home")
	public String UserHome_page(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
		// BUSCAR TODO LOS CHEQUES PARA MOSTRARLOS AL CARGAR LA PAGINA
		Pageable pageRequest = PageRequest.of(page, 12);
		Page<Cheque> Cheques = ChequeServices.FindActivos(pageRequest);
		PageRender<Cheque> pageRender = new PageRender<>("/Usuario/Home/", Cheques);

		// ENVIAR LOS DATOS A LA PLANTILLA
		model.addAttribute("page", pageRender);
		model.addAttribute("Cheques", Cheques);
		return "/pages/User_Templates/UserHome";
	}

	// BUSCA LOS CHEQUES POR SU CODIGO
	@RequestMapping(value = "/BuscarChequeForm", method = RequestMethod.POST)
	public String BuscarCheque(@RequestParam String BuscarChequeForm) {
		return "redirect:/Usuario/Busqueda/Cheque/" + BuscarChequeForm + "/";
	}

	// ESTE METODO ES PARA CUANDO SE HAGA UNA BUSQUEDA DE CHEQUE POR EL CAMPO DE
	// CODIGO
	@GetMapping("/Usuario/Busqueda/Cheque/{texto}/")
	public String ChequesBuscados_Page(@PathVariable(value = "texto") String texto, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		// BUSCAR TODO LOS USUARIOS PARA MOSTRARLOS AL REALIZAR UNA BUSQUEDA
		Pageable pageRequest = PageRequest.of(page, 12);
		Page<Cheque> cheques = ChequeServices.FindAllByParameters(texto, pageRequest);
		PageRender<Cheque> pageRender = new PageRender<>("/Usuario/Busqueda/Cheque/" + texto + "/", cheques);

		// ENVIAR LOS DATOS A LA PLANTILLA
		model.addAttribute("page", pageRender);
		model.addAttribute("Cheques", cheques);
		return "/pages/User_Templates/UserHome";
	}

	// FORMULARIO PARA CUANDO SE HAGA UNA BUSQUEDA DE UN USUARIO EN LA VENTANA DE
	// CREAR CHEQUE CON USUARIO EXISTENTE
	//
	// ***************************************************************************/
	// NOTAS:
	// 1- variable 'BuscarClienteForm' solo para VER que texto a introducido por el
	// momento
	// ***************************************************************************/
	//
	@RequestMapping(value = "/BuscarClienteForm", method = RequestMethod.POST)
	public String BuscarCliente(@RequestParam String BuscarClienteForm) {
		return "redirect:/Usuario/Busqueda/Cliente/" + BuscarClienteForm + "/";
	}

	// ESTE METODO ES PARA CUANDO SE HAGA UNA BUSQUEDA DE USUARIOS POR EL CAMPO DE
	// NOMBRE, APELLIDO O CEDULA, EN LA VENTANDA DE CREAR UN NUEVO CHEQUE CON
	// USUARIO EXISTENTE
	@GetMapping("/Usuario/Busqueda/Cliente/{texto}/")
	public String ClientesBuscados_Page(@PathVariable(value = "texto") String texto, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page) {
		// BUSCAR TODO LOS USUARIOS PARA MOSTRARLOS AL REALIZAR UNA BUSQUEDA
		Pageable pageRequest = PageRequest.of(page, 50);
		ClientesPage = ClienteService.FindAllByParameters(texto, pageRequest);
		clientesRender = new PageRender<>("/Usuario/Busqueda/Cliente/" + texto + "/", ClientesPage);

		// ENVIAR LOS DATOS A LA PLANTILLA
		model.addAttribute("page", clientesRender);
		model.addAttribute("Clientes", ClientesPage);
		return "redirect:/Cheque";
	}

	// MUESTRA EL FORMULARIO PARA AGREGAR UN CHEQUE
	@GetMapping("/Cheque")
	public String UserCheque_page(Model model) {
		this.clienteObjNuevoCheque.setIdiomas("Español,");
		this.chequeObjNuevoCheque.setActivo(1);
		model.addAttribute("cliente", clienteObjNuevoCheque);
		model.addAttribute("cheque", chequeObjNuevoCheque);
		model.addAttribute("visible", formularioVisible);
		if (clientesRender != null) {
			model.addAttribute("page", clientesRender);
			model.addAttribute("Clientes", ClientesPage);
		}
		return "/pages/User_Templates/UserVerCrearEditarCheque";
	}

	// EN CASO DE QUE SE ESTE SELECCIONANDO UN CLIENTE YA EXISTENTE SE HARA ESTE
	// PROCESO
	@GetMapping("/ClienteSeleccionado/{id}")
	public String ClienteSeleccionado_page(@PathVariable(value = "id") String id) {
		this.clienteObjNuevoCheque = ClienteService.FindById(Long.parseLong(id));
		formularioVisible = 2;
		return "redirect:/Cheque";
	}

	// PARA CUANDO SE ENVIEN LOS FORMULARIOS QUE ALMACENARAN LOS DATOS DEL CLIENTE Y
	// DEL CHEQUE
	@RequestMapping(value = "/CrearCheque", method = RequestMethod.POST)
	public String GuardarCliente_form(Cliente cliente, Cheque cheque) {
		// VERIFICO QUE EL OBJETO cliente QUE VIENE DEL FORMULARIO NO ESTE VACIO, SI NO
		// ESTA VACIO SE INICIALIZA LA VARIABLE "cliente" PARA QUE LOS DATOS NO SE
		// PIERDAN
		if (cliente.getCedula() != null) {
			this.clienteObjNuevoCheque = cliente;
		}
		if (cheque.getMonto() != null) {
			this.chequeObjNuevoCheque = cheque;
		}

		formularioVisible++;
		return "redirect:/Cheque";
	}

	// PARA RETROCEDER ENTRE PANELES Y QUE SE MUESTREN LOS FORMULARIOS CORRECTOS Y
	// AL FINAL LIMPIAR LOS OBJETOS
	@RequestMapping(value = "/GestionarVariables", method = RequestMethod.POST)
	public String GestionarVariables(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(value = "opcion") int opcion) {
		switch (opcion) {
		case 0:
			// BUSCAR TODO LOS CLIENTES PARA MOSTRARLOS AL CARGAR LA PAGINA ASISELECCIONARLO
			// Y NO CREAR UN CLIENTE YA CREADO
			Pageable pageRequest = PageRequest.of(page, 50);
			ClientesPage = ClienteService.FindAll(pageRequest);
			clientesRender = new PageRender<>("/Cheque", ClientesPage);
		case 1:
		case 2:
			formularioVisible = opcion;
			return "redirect:/Cheque";
		case 3:
			// ANTES DE GUARDAR EL CHEQUE, JUSTO AL MOMENTO DE GUARDAR EL CLIENTE, RETORNO
			// EL OBJETO CON SU ID PARA PODER ALMACENARLO EN LA TABLA DE CHEQUE
			chequeObjNuevoCheque.setCliente(ClienteService.Save(clienteObjNuevoCheque));
			ChequeServices.Save(chequeObjNuevoCheque);
		case 4:
			// PARA CANCELAR LA CREACION DEL CHEQUE
			clienteObjNuevoCheque = new Cliente();
			chequeObjNuevoCheque = new Cheque();
			formularioVisible = -1;
			return "redirect:/Usuario/Home";
		}
		return "";
	}

	// MUESTRA LOS DETALLES DEL CHEQUE SELECCIONADO
	@GetMapping("/VerCheque/{idCheque}")
	public String VerCheque_Page(Model model, @PathVariable(value = "idCheque") long idCheque) {
		chequePagarCheque = ChequeServices.FindById(idCheque);
		clientePagarCheque = ClienteService.FindById(chequePagarCheque.getCliente().getId());
		model.addAttribute("cheque", chequePagarCheque);
		model.addAttribute("cliente", clientePagarCheque);
		return "/pages/User_Templates/VerCheque";
	}

	// MUESTRA PA PAGINA PARA HACER EL PAGO, Y REALIZA LOS PROCESOS PARA CREAR EL
	// PAGO.
	@RequestMapping(value = "/Pago")
	public String CrearPago_Page(Model model) {
		// CALCULAR LA DIFERENCIA DE DIAS ENTRE LA FECHA QUE SE HIZO EL CHEQUE Y LA
		// FECHA ACTUAL
		String fechaInicio = chequePagarCheque.getFechaDeEmision();
		Date fechaFin = new Date((new java.util.Date()).getTime());
		// BUSCAR LA DIFERENCIA DE DIAS
		int total = ChequeServices.DaysBetweenDates(fechaInicio.toString(), fechaFin.toString());
		// calcularle el 5% del pago
		double porciento = Integer.parseInt(chequePagarCheque.getMonto()) * 0.05;
		// BUSCAR NUMERO DE PAGOS REALIZADO A ESTE CHEQUE
		int totalPagos = pagoService.FindAllByChequeId((chequePagarCheque.getId()) + "");
		// Quincenas Restantes
		int QuncenasRestantes = (total / 15) - totalPagos;
		QuncenasRestantes = QuncenasRestantes >= 0 ? QuncenasRestantes : 0;
		// INTERES QUE DEBE SEGUN LAS QUINCENAS QUE DEBE
		double InteresTotal = porciento * QuncenasRestantes;
		// MONTO TOTAL QUE DEBE PAGAR
		double pagoTotal = 0;
		if (InteresTotal > 0) {
			pagoTotal = InteresTotal + Integer.parseInt(chequePagarCheque.getMonto());
		}

		// PASAR DETALLES A LA VISTA
		model.addAttribute("dias", total);
		model.addAttribute("pagosHechos", totalPagos);
		model.addAttribute("quincenas", QuncenasRestantes);
		model.addAttribute("pagoTotal", pagoTotal);
		model.addAttribute("InteresTotal", InteresTotal);
		model.addAttribute("porciento", porciento);
		model.addAttribute("Cheque", chequePagarCheque);
		model.addAttribute("Cliente", clientePagarCheque);
		return "/pages/User_Templates/PagarCheque";
	}

	// AL MOMENTO DE REALIZAR EL PAGO GUARDA EL PAGO EN LA BD Y REDIRIGE AL USUARIO
	// A HOME
	@RequestMapping(value = "/RelizarPago", method = RequestMethod.POST)
	public String RealizarPago_Forms(@RequestParam String pagoTotal) {
		Pago pago = new Pago();
		pago.setMontoPagado(pagoTotal);
		pago.setCheeque(chequePagarCheque.getId() + "");
		pagoService.Save(pago);
		return "redirect:/Usuario/Home";
	}

	// ESTE METODO ES PARA CUANDO SE HAGA UNA BUSQUEDA DE USUARIOS POR EL CAMPO DE
	// NOMBRE, APELLIDO O CEDULA.
	@GetMapping("/RelizarSaldo/{id}")
	public String UsuariosBuscados_Pagea(@PathVariable(value = "id") Long id) {
		Cheque chequeAInabilitar = ChequeServices.FindById(id);
		chequeAInabilitar.setActivo(0);
		ChequeServices.save(chequeAInabilitar);
		return "redirect:/Usuario/Home";
	}
	// ---------------------------------------------------------------------------------------------//
	// ---------------------------------------------------------------------------------------------//

	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("titulo", "Home page");
		return "/pages/home";
	}

	@GetMapping("/vista")
	public String viewPage(Model model) {
		model.addAttribute("titulo", "Spring Boot Application vista");
		model.addAttribute("clientes", ClienteService.readAllCliente());
		return "pages/vista";
	}

	@GetMapping("/vista/{id}")
	public String myViewPage(@PathVariable long id, Model model) {
		Cliente cliente = null;
		listOfCheques = new ArrayList<>();

		if (id > 0) {
			cliente = ClienteService.getClienteById(id);
		}

		// todos cheques pertenecientes al cliente

		for (Cheque cheque : ChequeServices.readAllcheques()) {

			if (cheque.getCliente().getId() == id) {
				listOfCheques.add(cheque);
			}
		}
		// log.info(listOfCheques.toString());

		model.addAttribute("cliente", cliente);
		model.addAttribute("cheques", listOfCheques);

		return "pages/miVista";
	}

	@GetMapping("/form")
	public String formPage(Model model) {
		model.addAttribute("titulo", "Form page");
		model.addAttribute("cheque", new Cheque());
		model.addAttribute("cliente", new Cliente());
		return "pages/form";
	}

	@PostMapping("/form")
	public String formData(Cheque cheque, Cliente cliente) {
		cheque.setCliente(cliente);
		// log.info(cheque.toString());
		System.err.println(cheque.toString());

		return "redirect:home";
	}

	@GetMapping("/form/{id}")
	public String formUpdate(@PathVariable long id, Model model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = ClienteService.getClienteById(id);
		}
		model.addAttribute("titulo", "Spring Boot Application update");
		model.addAttribute("cliente", cliente);
		return "pages/editForm";
	}

}
