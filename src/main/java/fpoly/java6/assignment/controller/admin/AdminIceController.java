package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.Ice;
import fpoly.java6.assignment.service.IceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminIceController {

    @Autowired
    private IceService iceService;

    @PostMapping("/ice")
    public String submit(Ice ice) {
        iceService.submit(ice);
        return "redirect:/admin/resources-management#ice";
    }

    @GetMapping("/ice/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            iceService.delete(id);
            redirectAttributes.addFlashAttribute("notify", "Xóa thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("notify", "Xóa thất bại!");
        }

        return "redirect:/admin/resources-management#ice";
    }

}
