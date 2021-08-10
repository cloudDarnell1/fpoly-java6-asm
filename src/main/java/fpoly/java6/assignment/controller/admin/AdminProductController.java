package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.Product;
import fpoly.java6.assignment.service.ImageService;
import fpoly.java6.assignment.service.ProductService;
import fpoly.java6.assignment.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/product-management")
    public String product(Model model, @RequestParam(defaultValue = "1") Integer page ) {
        Page<Product> result = productService.findAll(page);
        model.addAttribute("products", result.getContent());
        model.addAttribute("pages", PageUtils.getPages(result.getTotalPages()));
        model.addAttribute("active", "product");
        return "page/admin/product-management";
    }

    @GetMapping("/form-product/{id}")
    public String showFormProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "page/admin/form";
    }

    @PostMapping("/product")
    public String submit(Product product, @RequestParam(name = "file") MultipartFile multipartFile) {

        String path = imageService.store(multipartFile);
        if (!path.isEmpty()) {
            product.setImage(path);
        }
        productService.submit(product);
        return "redirect:/admin/product-management";
    }

    @GetMapping("/product/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);
            redirectAttributes.addFlashAttribute("notify", "Xóa thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("notify", "Xóa thất bại!");
        }
        return "redirect:/admin/product-management";
    }
}
