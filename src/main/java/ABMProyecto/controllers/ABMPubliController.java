package ABMProyecto.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import ABMProyecto.domain.FormInfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
@Controller
@RequestMapping("public")
public class ABMPubliController {
    @GetMapping("/home")
    public String showHome(Model model) {
        int anho = LocalDate.now().getYear();
        String miNombre="Panaderia Xuxan";
        model.addAttribute("nombre", miNombre);
        model.addAttribute("anho", anho);
        return "index";
    }
    @GetMapping("/quienes-somos")
    public String showQuienesSomos() {
        return "quienes-somos";
    }
    @GetMapping("/contacta")
    public String Contacta(Model model) {
        model.addAttribute("formInfo", new FormInfo());
        return "/contacta";
    }
    @PostMapping("")
    public String contacta(@Valid FormInfo formInfo,BindingResult bindingResult,Model model) {
        model.addAttribute("nombre", formInfo.getNombre());
        model.addAttribute("email", formInfo.getEmail());
        model.addAttribute("valores", formInfo.getValores());
        model.addAttribute("aceptaCondiciones", formInfo.getAceptaCondiciones());
        model.addAttribute("formInfo",new FormInfo());
        return "contacta";
    }
    
    // @GetMapping("/productos")
    // public String showproductos(Model model) {
    //     return "productos";
    // }
}
