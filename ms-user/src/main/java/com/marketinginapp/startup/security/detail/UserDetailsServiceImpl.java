package com.marketinginapp.startup.security.detail;

import com.marketinginapp.startup.domain.entity.User;
import com.marketinginapp.startup.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User entity = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User by email not exists, email: %s", email)));

        Collection<? extends GrantedAuthority> authorities = entity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(entity.getEmail(),
                entity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
