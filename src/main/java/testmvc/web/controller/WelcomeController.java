package testmvc.web.controller;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
	/*
	 * se c'è @ResponseBody il metodo ritorna una stringa, altrimenti ritorna il nome sella view
	 */
	@ResponseBody
	@RequestMapping(
			path = "/" //il path della request deve essere /
			)
	public String welcome() {
		
		return "Ciao!";
	}
	
	@ResponseBody
	@RequestMapping(
			path = {"/v1", "v2"}, //il path della request deve essere /v1 o /v2
			method = RequestMethod.GET, //la richiesta deve essere col verbo GET
			params = {"nome=danilo", "version!=3"} //l'url deve contenere il parametro nome=danilo e version diverso da 3 per essere intercettata da questo metodo
			)
	public String welcomev1() {
		
		return "Ciao!";
	}
	
	/*
	 * al posto di RequestMapping si possono usare GetMaping, PostMapping, ....
	 */
	@ResponseBody
	@GetMapping("/getMapping")
	public String welcomev2() {
		
		return "Ciao!";
	}
	
	/**
	 * Querystring con HttpServletRequest
	 */
	@ResponseBody
	@GetMapping("/queryString")
	public String queryString(HttpServletRequest request) {
		
		return "Ciao! " + request.getParameter("name");
	}
	
	/**
	 * Querystring con RequestParameter
	 */
	@ResponseBody
	@GetMapping("/queryString2")
	public String queryString2(@RequestParam("nome") String nome) {
		
		return "Ciao! " + nome;
	}
	
	/**
	 * Session
	 */
	@ResponseBody
	@GetMapping("/session")
	public String session(HttpSession session) {
		Date dt = new Date();
		
		if (session.getAttribute("name") == null)
			session.setAttribute("name", dt.toString());
			
		return "Ciao! " + session.getAttribute("name");
	}
	
	
	/**
	 * forward con parametri
	 * nota: non c'è request body
	 */
	@GetMapping("/fw1")
	public String fw1(HttpServletRequest request) {
		
		request.setAttribute("nome", "Giancarlo");
		return "forward:/fw2";
	}
	
	/**
	 * forward2, riceve il parametro da fw1
	 */
	@ResponseBody
	@GetMapping("/fw2")
	public String fw2(@RequestAttribute("nome") String ilNome) {
		
		return "nome; " + ilNome;
	}
}
