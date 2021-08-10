package fpoly.java6.assignment.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        Object login = session.getAttribute("admin");

        if (login == null) {
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }

        return true;
    }
}
