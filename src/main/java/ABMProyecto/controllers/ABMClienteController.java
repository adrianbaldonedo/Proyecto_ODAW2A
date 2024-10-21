package ABMProyecto.controllers;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ABMProyecto.domain.Cliente;
import ABMProyecto.services.ClienteServiceImplmen;
 @Controller
 @RequestMapping("/cliente")
 public class ABMClienteController {
    @Autowired
     public ClienteServiceImplmen clienteService;
         @GetMapping({"/",""})
    public String getAll(@RequestParam(required=false) Integer op,Model model){
        model.addAttribute("listaClientes",clienteService.obtenerTodos());
       if(op!=null){
        switch(op) {
            case 1 ->model.addAttribute("msg","Cliente añadido correctamente");
            case 2 ->model.addAttribute("msg","Cliente editado correctamente");
            case 3 ->model.addAttribute("msg","Cliente borrado correctamente");
            }
       }
       return "listViewcliente";
    }
     public String getNew(Model model) {
         model.addAttribute("ClienteForm", new Cliente());
         return "/newViewcliente";
     }
    @PostMapping("/nuevo/submit")
    public String getNewSubmit(Cliente cliente, Model model) {
        clienteService.añadir(cliente);
        model.addAttribute("msg", "Cliente añadido correctamente");
        return "redirect:/newViewcliente";
    }
    @GetMapping("/editar/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        Cliente clienteForm;
        clienteForm=clienteService.obtenerporId(id);
        model.addAttribute("ClienteForm", clienteForm);
        return "/editViewcliente";
    }
    @GetMapping("/contacta")
    public String Contacta(Model model) {
        model.addAttribute("formInfo", new Cliente());
        return "contacta";
    }
    @PostMapping("/editar/submit")
    public String getEditSubmit(Cliente ClienteFom, Model model) {
        clienteService.editar(ClienteFom);
        return "redirect:/editViewcliente";
    }
    @GetMapping("/borrar/{id}")
    public String geDelete(@PathVariable Long id) {
        clienteService.borrar(id);
        return "redirect:/Clientes";
    }
}

