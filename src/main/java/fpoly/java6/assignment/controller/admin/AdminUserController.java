package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.model.User;
import fpoly.java6.assignment.service.UserService;
import fpoly.java6.assignment.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired private UserService userService;

    @GetMapping("/user")
    public String userManagement(Model model, @RequestParam(defaultValue = "1") Integer admin_page, @RequestParam(defaultValue = "1") Integer user_id) {
        Page<User> adminAll = userService.findAllAdmin(admin_page);
        model.addAttribute("admins", adminAll.getContent());
        model.addAttribute("adminPages", PageUtils.getPages(adminAll.getTotalPages()));

        Page<User> userAll = userService.findAllUser(user_id);
        model.addAttribute("users", userAll.getContent());
        model.addAttribute("userPages", PageUtils.getPages(userAll.getTotalPages()));

        model.addAttribute("active", "user");
        return "page/admin/user-management";
    }

    @GetMapping("/user/disable/{id}")
    public String disable(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            userService.disable(id);
            attributes.addFlashAttribute("notify", "Vô hiệu hóa thành công");
        } catch ( Exception e) {
            attributes.addFlashAttribute("notify", "Vô hiệu hóa thất bại");
        }

        return "redirect:/admin/user";
    }

    @GetMapping("/user/enable/{id}")
    public String enable(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            userService.enable(id);
            attributes.addFlashAttribute("notify", "Bật thành công");
        } catch ( Exception e) {
            attributes.addFlashAttribute("notify", "Bật thất bại");
        }

        return "redirect:/admin/user";
    }

}
