package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Ice;
import fpoly.java6.assignment.repository.IceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IceService {

	@Autowired
	private IceRepository iceRepository;
	
	public List<Ice> findAll() {
		return iceRepository.findAll();
	}
	
	public Ice findById(Long id) {

		Optional<Ice> iceOptional = this.iceRepository.findById(id);
		if (!iceOptional.isPresent()) {
			throw new RuntimeException("not found id "+id);
		}
		return iceOptional.get();
	}

	@Transactional
    public void submit(Ice ice) {
		try {
			iceRepository.save(ice);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Transactional
	public void delete(Long id) {
		iceRepository.deleteById(id);
	}
}
