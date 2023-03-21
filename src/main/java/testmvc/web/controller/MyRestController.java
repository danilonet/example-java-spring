package testmvc.web.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@RequestMapping("/api/test")
	public String welcome() {
		return "{ciao:1}";
	}
}
