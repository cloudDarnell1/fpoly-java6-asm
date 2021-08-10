package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Sugar;
import fpoly.java6.assignment.repository.SugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SugarService {

	@Autowired
	private SugarRepository sugarRepository;
	
	public List<Sugar> findAll() {
		return sugarRepository.findAll();
	}
	
	public Sugar findById(Long id) {

		Optional<Sugar> sugarOptional = this.sugarRepository.findById(id);

		if (!sugarOptional.isPresent()) {
			throw new RuntimeException("not found sugar: " + id);
		}
		return sugarOptional.get();
	}

	@Transactional
	public void submit(Sugar sugar) {
		try {
			sugarRepository.save(sugar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void delete(Long id) {
		sugarRepository.deleteById(id);
	}
}
