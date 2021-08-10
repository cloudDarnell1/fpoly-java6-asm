package fpoly.java6.assignment.controller.admin;

import fpoly.java6.assignment.constant.Constant;
import fpoly.java6.assignment.model.User;
import fpoly.java6.assignment.modelform.UserLoginForm;
import fpoly.java6.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired private HttpSession session;

    @Autowired private UserService userService;

    @GetMapping("/login")
    public String showFormLogin(Model model) {

        if (session.getAttribute("admin") != null) {
            return "redirect:/admin/home";
        }

        model.addAttribute("userLoginForm", new UserLoginForm());
        return "page/admin/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginForm userLoginForm, Model model) {

        User login = userService.login(userLoginForm);

        if (login == null) {
            model.addAttribute("notify", "Tài khoản hoặc mật khẩu sai");
            return "page/admin/login";
        }

        if (!Constant.ADMIN_ROLE.equals(login.getRole())) {
            model.addAttribute("notify", "Bạn không có quyền vào đây");
            return "page/admin/login";
        }

        session.setAttribute("admin", login);
        return "redirect:/admin";
    }


    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }
}
