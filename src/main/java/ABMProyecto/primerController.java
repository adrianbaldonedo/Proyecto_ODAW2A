package ABMProyecto;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class primerController {
    @GetMapping({"/","/home","index"})
    public String Home(Model model) {
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
    @GetMapping("/productos")
    public final String Productos(final Model model){
        model.addAttribute("produdctos",ListaProducto());
        return "productos.html";
    }
     private final ArrayList<String>ListaProducto() {
        final var productos=new ArrayList<String>();
        productos.add("Pan");
        productos.add("Bolleria");
        productos.add("Cafes");
        productos.add("Desayunos");
        productos.add("Licores");
        productos.add("Pasteles");
        return productos;
    }
      
}
