package fpoly.java6.assignment.service;

import fpoly.java6.assignment.constant.Constant;
import fpoly.java6.assignment.model.Product;
import fpoly.java6.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Page<Product> findAll(Integer page) {
		PageRequest pageRequest = PageRequest.of(page-1, Constant.ORDER_MAX_PRODUCT_PER_PAGE);

		Page<Product> all = productRepository.findAll(pageRequest);

		return all;
	}

	public Product findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			throw new RuntimeException("not found product: "+id);
		}
		return productOptional.get();
	}

	public Page<Product> findAllByCategoryId(Long id, Integer page) {


		PageRequest pageRequest = PageRequest.of(page-1, Constant.ORDER_MAX_PRODUCT_PER_PAGE);

		return productRepository.findAllByCategoryId(id, pageRequest);
	}

	@Transactional
	public Product submit(Product product) {
		try {
			return productRepository.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> findByName(String name) {
		return this.productRepository.findByName("%"+name+"%");
	}

	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
