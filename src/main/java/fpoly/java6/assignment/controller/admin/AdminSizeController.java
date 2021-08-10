package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.Size;
import fpoly.java6.assignment.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminSizeController {

    @Autowired
    private SizeService sizeService;

    @PostMapping("/size")
    public String submit(Size size) {
        sizeService.submit(size);
        return "redirect:/admin/resources-management#size";
    }

    @GetMapping("/size/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            sizeService.delete(id);
            attributes.addFlashAttribute("notify", "Xoa thành công");
        } catch (Exception e) {
            attributes.addFlashAttribute("notify", "Xoa thất bại");
        }
        return "redirect:/admin/resources-management#size";
    }

}
