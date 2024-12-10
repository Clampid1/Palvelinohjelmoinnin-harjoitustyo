package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaskerRepository taskerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Tasker> tasker = Optional.ofNullable(taskerRepository.findByUsername(username));
        if (tasker.isPresent()) {
            var userObj = tasker.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj)).build();

        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Tasker tasker) {
        if (tasker.getRole() == null) {
            return new String[]{"USER"};
        }
        return tasker.getRole().split(",");
    }
}
