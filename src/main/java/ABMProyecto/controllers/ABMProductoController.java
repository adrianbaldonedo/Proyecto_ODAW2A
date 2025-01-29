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
import ABMProyecto.domain.Producto;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.domain.Tipo;
import ABMProyecto.services.ClienteService;
import ABMProyecto.services.ProductoService;
import ABMProyecto.services.ProveedorService;
import ABMProyecto.services.TipoService;

@Controller
@RequestMapping("/producto")
public class ABMProductoController {
      @Autowired
      ProductoService productoService;
       @Autowired
      ClienteService clienteService;
      @Autowired
      ProveedorService proveedorService;
      @Autowired
      TipoService tipoService;
    @GetMapping({"/",""})
    public String getAll(@RequestParam(required = false) Integer op, Model model) {
        model.addAttribute("listaProductos", productoService.obtenerTodos());
        model.addAttribute("listaClientes", clienteService.obtenerTodos());
        model.addAttribute("listaProveedores", proveedorService.obtenerTodos());
        model.addAttribute("listaTipo", tipoService.obtenerTodos());
        model.addAttribute("clienteSeleccionado", new Cliente(0, "Todos"));
        model.addAttribute("proveedorSeleccionado", new Proveedor(0, "Todos"));
        model.addAttribute("tipoSeleccionado", new Tipo(0, "Todos"));
        if (op != null) {
            switch (op) {
                case 1 -> model.addAttribute("msg", "Producto añadido correctamente");
                case 2 -> model.addAttribute("msg", "Producto editado correctamente");
                case 3 -> model.addAttribute("msg", "Producto borrado correctamente");
            }
        }
        return "listView";
    }
    @GetMapping("/nuevo")
     public String getNew(Model model) {
         model.addAttribute("Productoform", new Producto());
         model.addAttribute("listaClientes",clienteService.obtenerTodos());
         model.addAttribute("listaProveedores",proveedorService.obtenerTodos());
         model.addAttribute("listaTipo",tipoService.obtenerTodos());
         return "newView";
     }

    @PostMapping("/nuevo/submit")
    public String getNewSubmit(Producto producto, Model model) {
        productoService.añadir(producto);
        model.addAttribute("msg", "producto añadido correctamente");
        return "redirect:/";
    }
    @GetMapping("/editar/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        Producto productoForm;
        productoForm=productoService.obtenerporId(id);
        model.addAttribute("ProductoForm", productoForm);
        return "editView";
    }

    @PostMapping("/editar/submit")
    public String getEditSubmit(Producto productoFom, Model model) {
        productoService.editar(productoFom);
        return "redirect:/";
    }
    @GetMapping("/borrar/{id}")
    public String geDelete(@PathVariable Long id) {
        productoService.borrar(id);
        return "redirect:/newView";
    }
}



