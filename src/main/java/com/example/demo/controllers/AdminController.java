package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Pago;
import com.example.demo.entities.SecurityRole;
import com.example.demo.entities.SecurityUser;
import com.example.demo.services.IPagoService;
import com.example.demo.services.IRoleService;
import com.example.demo.services.IUserService;

@Controller
public class AdminController {

	@Autowired
	IUserService userService;

	@Autowired
	IRoleService roleService;

	@Autowired
	IPagoService pagoService;

	@Autowired
	BCryptPasswordEncoder encoder;

	List<SecurityRole> roles = null;

	// METODO RENDERIZA ADMINfORM PARA SER VISUALIZADO EN EL NAVEGADOR .
	//
	// ***************************************************************************/
	// NOTAS:
	// 1- CONTIENE DOS OBJETOS DE LAS CLASES QUE SE UTILIZARAN PARA LA SEGURIDAD.
	// ***************************************************************************/
	//

	@GetMapping("/admin/form")
	public String adminFormPage(Model model, Authentication auth) {
		model.addAttribute("user", new SecurityUser());
		model.addAttribute("role", new SecurityRole());
		//model.addAttribute("usuario", auth.getName());
		return "pages/adminForm";
	}

	// METODO QUE SE ENCARGA DE PROCESAR LOS DATOS DEL FORMULARIO DE INSERCION DE
	// USUARIOS
	// REDIRECCIONA A LA PAGINA PRINCIPAR DEL ADMIN CUANDO EL PROCESO HA FINALIZADO
	//
	// ***************************************************************************/
	// NOTAS:
	// 1- UTILIZA LAS ENTITIS DE MANEJO DE SEGURIDAD Y SUS RESPECTIVOS SERVICES
	// ***************************************************************************/
	//

	@PostMapping("admin/form")
	public String userData(Model model, SecurityUser user, SecurityRole role) {

		roles = new ArrayList<>();
		roles.add(role);

		String password = user.getPassword();
		String passwordEncoder = encoder.encode(password);

		user.setPasswordEncoder(passwordEncoder);
		user.setRoles(roles);
		userService.createUser(user);

		System.err.println(user.toString() + " " + role.toString());
		return "redirect:/admin/home";
	}
	// METODO QUE MANEJA LA EDICION DEL FORMULARIO DE USUARIOS
	//
	//
	// ***************************************************************************/
	// NOTAS:
	// 1- variable 'LoginForm' solo para evaluacion por el momento, cuando se
	// coloquen los campos de usuario y contraseña, este campo se ira.
	// ***************************************************************************/
	//

	@GetMapping("/admin/form/{id}")
	public String editDataForm(Model model, @PathVariable long id) {
		SecurityUser user = null;
		SecurityRole role = null;

		if (id > 0) {
			user = userService.findById(id);
			role = roleService.findById(id);
		}
		model.addAttribute("titulo", "Spring Boot App Edit Form");
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		return "pages/adminForm";
	}

	@GetMapping("/admin/pago/vista/{id}")
	public String userDataPago(@PathVariable long id, Model model) {
		SecurityUser user = this.userService.findById(id);
		List<Pago> listaDePagos = new ArrayList<>();
		this.pagoService.readAll().forEach(pago -> {
			if (pago.getUsuario().equalsIgnoreCase(user.getUsername())) {
				listaDePagos.add(pago);
			}
		});

		System.err.println(listaDePagos.toString());
		model.addAttribute("titulo", "Spring Boot App User-vista-pago");
		model.addAttribute("pagos", listaDePagos);
		return "pages/vistaPago";
	}

	@GetMapping("/admin/home")
	public String adminHomePage(Model model) {
		model.addAttribute("titulo", "Spring Boot App admin Home");
		return "pages/adminHome";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		
		return "pages/Login";
	}

}
