package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Category;
import fpoly.java6.assignment.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findOne() {
		return categoryRepository.findOne();
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);

		if (!categoryOptional.isPresent()) {
			throw new RuntimeException("not found category: " + id);
		}

		return categoryOptional.get();
	}

	@Transactional
	public void submit(Category category) {
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
}
