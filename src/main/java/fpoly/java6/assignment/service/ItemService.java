package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Item;
import fpoly.java6.assignment.model.Receipt;
import fpoly.java6.assignment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Transactional
	public void addItem(List<Item> items, Receipt receipt) {
		items.forEach(item -> {
			item.setReceipt(receipt);
			itemRepository.save(item);
		});
	}
}
