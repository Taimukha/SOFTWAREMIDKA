package com.example.projectsoftware.service;

import com.example.projectsoftware.model.Permission;
import com.example.projectsoftware.model.UserModel;
import com.example.projectsoftware.repository.PermissionRepository;
import com.example.projectsoftware.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserModel user = userRepository.findByEmail(username);

        if(Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public void register(UserModel model){

        if (userRepository.findByEmail(model.getEmail()) == null) {

            model.setPassword(passwordEncoder.encode(model.getPassword()));

            List<Permission> perms =
                    List.of(permissionRepository.findByName("ROLE_USER"));

            model.setPermissions(perms);

            userRepository.save(model);
        }
    }
}
