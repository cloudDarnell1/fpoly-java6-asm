package fpoly.java6.assignment.config;

import fpoly.java6.assignment.interceptor.AdminLoginInterceptor;
import fpoly.java6.assignment.interceptor.AssignFormInterceptor;
import fpoly.java6.assignment.interceptor.LoginInterceptor;
import fpoly.java6.assignment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // user must login before
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/logout", "/history")
                .excludePathPatterns("/admin/login")
                .order(1);

        // admin must login before
//        registry.addInterceptor(new AdminLoginInterceptor())
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin/login")
//                .order(1);

        // assign object before show form
        registry.addInterceptor(new AssignFormInterceptor(categoryService))
                .addPathPatterns("/admin/**")
                .order(1);
    }
}
