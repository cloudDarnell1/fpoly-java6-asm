package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.Topping;
import fpoly.java6.assignment.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminToppingController {

    @Autowired
    private ToppingService toppingService;

    @PostMapping("/topping")
    public String submit(Topping topping) {
        toppingService.submit(topping);
        return "redirect:/admin/resources-management#topping";
    }

    @GetMapping("/topping/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            toppingService.delete(id);
            redirectAttributes.addFlashAttribute("notify", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("notify", "Xóa thất bại");
        }
        return "redirect:/admin/resources-management#topping";
    }

}
