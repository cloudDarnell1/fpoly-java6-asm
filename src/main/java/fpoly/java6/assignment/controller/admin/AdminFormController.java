package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/form")
public class AdminFormController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SugarService sugarService;

    @Autowired
    private IceService iceService;

    @Autowired
    private ToppingService toppingService;

    @Autowired
    private SizeService sizeService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("active", "form");
        return "page/admin/form";
    }

    @GetMapping(params = "product_id")
    public String showFormProduct(@RequestParam(name = "product_id") Long id, Model model) {
        
        model.addAttribute("product", productService.findById(id));
        return "page/admin/form";
    }

    @GetMapping(params = "category_id")
    public String showFormCategory(@RequestParam(name = "category_id") Long id, Model model) {
        
        model.addAttribute("category", categoryService.findById(id));
        return "page/admin/form";
    }

    @GetMapping(params = "sugar_id")
    public String showFormSugar(@RequestParam(name = "sugar_id") Long id, Model model) {
        
        model.addAttribute("sugar", sugarService.findById(id));
        return "page/admin/form";
    }

    @GetMapping(params = "ice_id")
    public String showFormIce(@RequestParam(name = "ice_id") Long id, Model model) {
        
        model.addAttribute("ice", iceService.findById(id));
        return "page/admin/form";
    }

    @GetMapping(params = "size_id")
    public String showFormSize(@RequestParam(name = "size_id") Long id, Model model) {
        
        model.addAttribute("size", sizeService.findById(id));
        return "page/admin/form";
    }

    @GetMapping(params = "topping_id")
    public String showFormTopping(@RequestParam(name = "topping_id") Long id, Model model) {
        
        model.addAttribute("topping", toppingService.findById(id));
        return "page/admin/form";
    }

}
