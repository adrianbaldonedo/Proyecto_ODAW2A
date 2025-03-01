package ABMProyecto.controllers;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ABMProyecto.domain.Tipo;
import ABMProyecto.services.TipoService;
 @Controller
 @RequestMapping("/tipo")
 public class ABMTipoController {
    @Autowired
     public TipoService tiposervice;
         @GetMapping({"/",""})
    public String getAll(@RequestParam(required=false) Integer op,Model model){
        model.addAttribute("listaTipo",tiposervice.obtenerTodos());
       if(op!=null){
        switch(op) {
            case 1 ->model.addAttribute("msg","Tipo añadido correctamente");
            case 2 ->model.addAttribute("msg","Tipo editado correctamente");
            case 3 ->model.addAttribute("msg","Tipo borrado correctamente");
            }
       }
       return "listViewtipo";
    }
    @GetMapping("/nuevo")
     public String getNew(Model model) {
         model.addAttribute("TipoForm", new Tipo());
         return "newViewtipo";
     }

    @PostMapping("/nuevo/submit")
    public String getNewSubmit(Tipo tipo, Model model) {
        tiposervice.añadir(tipo);
        model.addAttribute("msg", "Tipo añadido correctamente");
        return "redirect:/tipo";
    }
    @GetMapping("/editar/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        Tipo tipoForm;
        tipoForm=tiposervice.obtenerporId(id);
        model.addAttribute("TipoForm",tipoForm);
        return "/editViewtipo";
    }
    @PostMapping("/editar/submit")
    public String getEditSubmit(Tipo tipoFom, Model model) {
        tiposervice.editar(tipoFom);
        return "redirect:/tipo";
    }
    @GetMapping("/borrar/{id}")
    public String geDelete(@PathVariable Long id) {
        tiposervice.borrar(id);
        return "redirect:/tipo";
    }
}

