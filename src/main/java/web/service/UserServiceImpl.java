package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dto.UserDto;
import web.model.User;
import web.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user;
        if (userDto.getId() != null) {
            user = userRepository.findById(userDto.getId()).orElse(new User());
        } else {
            user = new User();
        }

        user.setUsername(userDto.getUsername());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword()
                ))
                .orElse(null);
    }
}