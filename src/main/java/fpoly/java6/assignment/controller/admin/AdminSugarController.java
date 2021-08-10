package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.Sugar;
import fpoly.java6.assignment.service.SugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminSugarController {

    @Autowired
    private SugarService sugarService;

    @PostMapping("/sugar")
    public String submit(Sugar sugar) {
        sugarService.submit(sugar);
        return "redirect:/admin/resources-management#sugar";
    }

    @GetMapping("/sugar/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            sugarService.delete(id);
            attributes.addFlashAttribute("notify", "Xóa thanhf công");
        } catch (Exception e) {
            attributes.addFlashAttribute("notify", "Xóa thất bại");
        }
        return "redirect:/admin/resources-management#sugar";
    }

}
