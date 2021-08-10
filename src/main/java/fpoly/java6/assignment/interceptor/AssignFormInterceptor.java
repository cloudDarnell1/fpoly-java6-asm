package fpoly.java6.assignment.interceptor;

import fpoly.java6.assignment.model.*;
import fpoly.java6.assignment.service.CategoryService;
import fpoly.java6.assignment.model.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignFormInterceptor implements HandlerInterceptor {

    private CategoryService categoryService;

    public AssignFormInterceptor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("product", new Product());
        request.setAttribute("sugar", new Sugar());
        request.setAttribute("ice", new Ice());
        request.setAttribute("category", new Category());
        request.setAttribute("topping", new Topping());
        request.setAttribute("size", new Size());
        request.setAttribute("categories", categoryService.findAll());

        return true;
    }
}
