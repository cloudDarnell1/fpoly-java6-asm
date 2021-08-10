package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.Category;
import fpoly.java6.assignment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category-management")
    public String category(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("active", "category");
        return "page/admin/category-management";
    }

    @PostMapping("/category")
    public String submit(Category category) {
        categoryService.submit(category);
        return "redirect:/admin/category-management";
    }

    @GetMapping("/category/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        try {
            categoryService.delete(id);
            attributes.addFlashAttribute("notify", "Xóa loại sản phẩm thành công");
        } catch (Exception e) {
            attributes.addFlashAttribute("notify", "Xóa thất bại");
        }

        return "redirect:/admin/category-management";
    }

}
