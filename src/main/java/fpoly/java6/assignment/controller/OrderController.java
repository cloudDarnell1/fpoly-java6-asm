package fpoly.java6.assignment.controller;

import fpoly.java6.assignment.model.Product;
import fpoly.java6.assignment.service.*;
import fpoly.java6.assignment.service.*;
import fpoly.java6.assignment.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ToppingService toppingService;
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private IceService iceService;
	
	@Autowired
	private SugarService sugarService;

	@Autowired
	private CartService cartService;

	@Autowired
	HttpSession session;
	
	@GetMapping
	public String orderProduct(@RequestParam(required = false, name = "category_id") Long categoryId,
							   @RequestParam(defaultValue = "1") Integer page,
							   Model model) {

		if (categoryId == null || categoryId == 0) {
			categoryId = categoryService.findOne().getId();
		}

		Page<Product> allByCategoryId = productService.findAllByCategoryId(categoryId, page);

		model.addAttribute("pages", PageUtils.getPages(allByCategoryId.getTotalPages()));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", allByCategoryId.getContent());
		model.addAttribute("carts", cartService.getAll());
		model.addAttribute("category", categoryService.findById(categoryId));
		return "layout/order-layout";
	}
	
}
