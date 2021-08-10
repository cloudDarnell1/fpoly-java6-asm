package fpoly.java6.assignment.interceptor;

import fpoly.java6.assignment.constant.Constant;
import fpoly.java6.assignment.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("admin");

        if (!Constant.ADMIN_ROLE.equals(user.getRole())) {
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }

        return true;
    }
}
