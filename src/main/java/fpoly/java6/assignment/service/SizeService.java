package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Size;
import fpoly.java6.assignment.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

	@Autowired
	private SizeRepository sizeRepository;
	
	public List<Size> findAll() {
		return sizeRepository.findAll();
	}
	
	public Size findById(Long id) {
		Optional<Size> sizeOptional = sizeRepository.findById(id);

		if (!sizeOptional.isPresent()) {
			throw new RuntimeException("not found size: " + id);
		}
		return sizeOptional.get();
	}

	@Transactional
    public void submit(Size size) {
		try {
			sizeRepository.save(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Transactional
    public void delete(Long id) {
		sizeRepository.deleteById(id);
	}
}
