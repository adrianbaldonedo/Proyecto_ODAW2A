package ABMProyecto.controllers;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.services.ProveedorService;
 @Controller
 @RequestMapping("/proveedor")
 public class ABMProveedorController {
    @Autowired
     public ProveedorService  proveedorervice;
         @GetMapping({"/",""})
    public String getAll(@RequestParam(required=false) Integer op,Model model){
        model.addAttribute("listaProveedor",proveedorervice.obtenerTodos());
       if(op!=null){
        switch(op) {
            case 1 ->model.addAttribute("msg","Proveedor añadido correctamente");
            case 2 ->model.addAttribute("msg","Proveedor editado correctamente");
            case 3 ->model.addAttribute("msg","Proveedor borrado correctamente");
            }
       }
       return "listViewproveedor";
    }
    @GetMapping("/nuevo")
         public String getNew(Model model) {
         model.addAttribute("ProveedorForm", new Proveedor());
         return "newViewproveedor";
     }

    @PostMapping("/nuevo/submit")
    public String getNewSubmit(Proveedor proveedor, Model model) {
        proveedorervice.añadir(proveedor);
        model.addAttribute("msg", "Proveedor añadido correctamente");
        return "redirect:/proveedor";
    }
    @GetMapping("/editar/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        Proveedor proveedorForm;
        proveedorForm=proveedorervice.obtenerporId(id);
        model.addAttribute("ProveedorForm",proveedorForm);
        return "/editViewproveedor";
    }
    @PostMapping("/editar/submit")
    public String getEditSubmit(Proveedor proveedorFom, Model model) {
        proveedorervice.editar(proveedorFom);
        return "redirect:/proveedor";
    }
    @GetMapping("/borrar/{id}")
    public String geDelete(@PathVariable Long id) {
        proveedorervice.borrar(id);
        return "redirect:/Proveedor";
    }
}

