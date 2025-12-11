package kz.com.nurzandars.message_system.service;

import kz.com.nurzandars.message_system.module.User;
import kz.com.nurzandars.message_system.repository.UserRepository;
import kz.com.nurzandars.message_system.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existUser -> {
                    existUser.setEmail(user.getEmail());
                    existUser.setUsername(user.getUsername());
                    existUser.setPassword(user.getPassword());
                return  userRepository.save(existUser);
                }).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        userRepository.deleteById(id);

    }
}
