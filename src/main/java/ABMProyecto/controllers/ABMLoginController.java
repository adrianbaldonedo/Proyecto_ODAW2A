package ABMProyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ABMLoginController {
    @GetMapping("/signin")
    public String showLogin() {
        return "signView";
    }
    @GetMapping("/signout")
    public String showLogout() {
        return "signoutView";
    }
    
}
