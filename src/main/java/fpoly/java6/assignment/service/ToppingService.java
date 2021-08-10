package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Topping;
import fpoly.java6.assignment.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToppingService {

	@Autowired
	private ToppingRepository toppingRepository;
	
	public List<Topping> findAll() {
		List<Topping> list = Optional.ofNullable(toppingRepository.findAll()).orElseGet(ArrayList::new);
		return list;
	}
	
	public Topping findById(Long id) {
		Optional<Topping> toppingOptional = this.toppingRepository.findById(id);
		if (!toppingOptional.isPresent()) {
			throw new RuntimeException("not found topping: " + id);
		}
		return toppingOptional.get();
	}

	@Transactional
    public void submit(Topping topping) {
		try {
			toppingRepository.save(topping);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Transactional
    public void delete(Long id) {
		toppingRepository.deleteById(id);
	}
}
