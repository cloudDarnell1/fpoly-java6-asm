package fpoly.java6.assignment.service;

import fpoly.java6.assignment.constant.Constant;
import fpoly.java6.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fpoly.java6.assignment.model.Receipt;
import fpoly.java6.assignment.repository.ReceiptRepository;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;

	@Autowired
	private HttpSession session;

	public Page<Receipt> findAll(Integer page) {
		PageRequest pageRequest = PageRequest.of(page - 1, Constant.ORDER_MAX_PRODUCT_PER_PAGE);
		return receiptRepository.findAll(pageRequest);
	}

	public Receipt addReceipt(Receipt receipt) {
		return receiptRepository.save(receipt);
	}

    public List<Receipt> findByUserId(String sort, String direction, Date date) {
		User user = (User) session.getAttribute("login");

		Sort.Direction dir = Sort.Direction.valueOf(direction);

		Sort st = Sort.by(dir, sort);

		if (date != null) {
			return receiptRepository.findByUserIdAndDate(user.getId(), date, st);
		}

		return receiptRepository.findByUserId(user.getId(), st);

	}
}
