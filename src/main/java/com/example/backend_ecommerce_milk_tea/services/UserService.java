package com.example.backend_ecommerce_milk_tea.services;

import com.example.backend_ecommerce_milk_tea.configs.JwtToken;
import com.example.backend_ecommerce_milk_tea.models.Users;
import com.example.backend_ecommerce_milk_tea.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    @Override
    public Users createUser(Users user) throws Exception{
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username is already taken.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserByUsername(String username) throws Exception{
        return userRepository.findByUsername(username);
    }

    @Override
    public String login(String username, String password) throws Exception{
        Users user = userRepository.findByUsername(username);
        if (user==null) {
            throw new Exception("invalid username");
        }

        UsernamePasswordAuthenticationToken authentication = new
                UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
        authenticationManager.authenticate(authentication);
        return jwtToken.generateToken(user);
    }

    @Override
    public Optional<Users> findUserByUsernameAndPassword(String username, String email){
        return userRepository.findByUsernameAndEmail(username, email);
    }
}
