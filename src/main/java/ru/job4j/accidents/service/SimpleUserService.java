package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.data.AuthorityRepository;
import ru.job4j.accidents.repository.data.UserRepository;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorities;

    @Override
    public User save(User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        return userRepository.save(user);
    }
}
