package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UserDetailsServiceImpls implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmailIgnoreCase(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Не найден Пользователь");
        }

        return new UserDetailsImpl(user.get());
    }
}
