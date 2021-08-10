package fpoly.java6.assignment.service;

import fpoly.java6.assignment.constant.Constant;
import fpoly.java6.assignment.model.User;
import fpoly.java6.assignment.modelform.UserLoginForm;
import fpoly.java6.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAllAdmin(Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, Constant.ORDER_MAX_PRODUCT_PER_PAGE);

        Page<User> all = userRepository.findAllByRole(Constant.ADMIN_ROLE,pageRequest);

        return all;
    }

    public Page<User> findAllUser(Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, Constant.ORDER_MAX_PRODUCT_PER_PAGE);

        Page<User> all = userRepository.findAllByRole(Constant.USER_ROLE,pageRequest);

        return all;
    }

    public User login(UserLoginForm user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    @Transactional
    public User save(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException(user.getUsername()+ " exist!");
        }

        if ("".equals(user.getRole()) || user.getRole() == null) {
            user.setRole(Constant.USER_ROLE);
        }

        user.setActive(true);
        return userRepository.save(user);
    }

    @Transactional
    public void disable(Long id) {
        Integer row = userRepository.disable(id);

        if (row != 1) {
            throw new RuntimeException("disable user id: "+id+" failed!");
        }

    }

    @Transactional
    public void enable(Long id) {
        Integer row = userRepository.enable(id);

        if (row != 1) {
            throw new RuntimeException("enable user id: "+id+" failed!");
        }
    }
}
