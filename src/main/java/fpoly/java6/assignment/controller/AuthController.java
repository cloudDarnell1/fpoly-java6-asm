package fpoly.java6.assignment.controller;

import fpoly.java6.assignment.model.User;
import fpoly.java6.assignment.modelform.UserLoginForm;
import fpoly.java6.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String showFormLogin(Model model) {

        if (session.getAttribute("login") != null) {

            return "redirect:/home";
        }

        model.addAttribute("userLoginForm", new UserLoginForm());
        return "page/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginForm userLoginForm, Model model) {

        User login = userService.login(userLoginForm);

        if (login == null) {
            model.addAttribute("notify", "ten dang nhap hoac tai khoan sai");
            return "page/login";
        }

        if (!login.isActive()) {
            model.addAttribute("notify", "tai khoan dang bi khoa");
            return "page/login";
        }

        session.setAttribute("login", login);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/home";
    }
}
