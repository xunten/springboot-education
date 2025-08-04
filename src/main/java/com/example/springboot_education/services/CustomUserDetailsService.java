package com.example.springboot_education.services;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot_education.entities.UserRole;
import com.example.springboot_education.entities.Users;
import com.example.springboot_education.repositories.UsersJpaRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersJpaRepository userRepository;

    public CustomUserDetailsService(UsersJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("🔥 loadUserByUsername: " + username);
        // 🔥 Dùng method findByUsernameWithRoles (đã JOIN FETCH userRoles → role)
        Users user = userRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        // 🔥 Duyệt qua userRoles để lấy role name
        for (UserRole ur : user.getUserRoles()) {
            String roleName = ur.getRole().getName();

            // Nếu dùng @PreAuthorize("hasAuthority('Administrators')")
            authorities.add(new SimpleGrantedAuthority(roleName));

            // Nếu dùng @PreAuthorize("hasRole('Administrators')")
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
