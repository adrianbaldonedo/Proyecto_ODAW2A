package ABMProyecto;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class abmController{
    @GetMapping({"/","/home","index"})
    public String Home(Model model){
        int anho=LocalDate.now().getYear();
        String miNombre="Panaderia Xuxan";
        model.addAttribute("nombre", miNombre);
        model.addAttribute("anho",anho);
        return "index.html";
    }
    @GetMapping("/quienes-somos")
        public String QuienesSomos(Model model){
            String somos="quienes-somos";
            model.addAttribute("quienes-somos", somos);
            return "quienes-somos.html";
        }
    @GetMapping("/contacta")
    public String Contacta(Model model){
        String contacto="contacta";
        model.addAttribute("contacta", contacto);
        return "contacta.html";
    }
    }
