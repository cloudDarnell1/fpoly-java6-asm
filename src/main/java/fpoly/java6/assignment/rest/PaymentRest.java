package fpoly.java6.assignment.rest;

import fpoly.java6.assignment.model.Item;
import fpoly.java6.assignment.model.Receipt;
import fpoly.java6.assignment.model.User;
import fpoly.java6.assignment.service.CartService;
import fpoly.java6.assignment.service.ItemService;
import fpoly.java6.assignment.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/payment")
public class PaymentRest {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private CartService cartService;

	@Autowired
	private HttpSession session;

	@PostMapping
	@ResponseBody
	public Map<String, String> pa(@RequestBody List<Item> items) {

		Map<String, String> output = new HashMap<>();

		try {
			// check login or not
			User login = (User) session.getAttribute("login");

			if (login == null) {
				throw new RuntimeException("no login");
			}

			// create a receipt
			Receipt receipt = new Receipt();
			receipt.setDate(new Date());
			receipt.setUser(login);

			// store receipt to DB
			Receipt receipt1 = receiptService.addReceipt(receipt);

			// store list items to DB with receipt
			itemService.addItem(items, receipt1);

			output.put("error", "false");
			output.put("message", "pay successfully!");
			output.put("status", "200");

		} catch (Exception e) {
			output.put("error", "true");
			output.put("message", e.getMessage());
			output.put("status", "500");

			if (e instanceof RuntimeException) {
				output.put("status", "2001");
			}
		}

		return output;

	}
}
