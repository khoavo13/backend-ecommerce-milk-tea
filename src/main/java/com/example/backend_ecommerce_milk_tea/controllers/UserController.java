package com.example.backend_ecommerce_milk_tea.controllers;

import com.example.backend_ecommerce_milk_tea.exceptions.ResourceNotFoundException;
import com.example.backend_ecommerce_milk_tea.models.Users;
import com.example.backend_ecommerce_milk_tea.responses.ApiResponse;
import com.example.backend_ecommerce_milk_tea.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> index() {
        ApiResponse apiResponse=ApiResponse.builder()
                .data(userService.getAllUsers())
                .message("get all successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Users user) throws Exception {
        ApiResponse apiResponse=ApiResponse.builder()
                .data(userService.login(user.getUsername(), user.getPassword()))
                .message("login successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody Users user, BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Users user1 = userService.createUser(user);
        ApiResponse apiResponse=ApiResponse.builder()
                .data(user1)
                .message("Insert successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestParam String username, @RequestParam String email) throws ResourceNotFoundException {
        Optional<Users> user = userService.findUserByUsernameAndPassword(username, email);
        if (user.isPresent()){
            ApiResponse apiResponse=ApiResponse.builder()
                    .data(user)
                    .message("Search user successfully")
                    .status(HttpStatus.OK.value())
                    .build();
            return ResponseEntity.ok().body(apiResponse);
        }
        else {
            throw new ResourceNotFoundException("User khong tim thay");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id,@Valid @RequestBody Users updatedUser, BindingResult bindingResult) throws ResourceNotFoundException {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }

        Users user = userService.updateUser(id, updatedUser);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(user)
                .message("Updated user successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) throws ResourceNotFoundException {
        Users student = userService.getUserById(id);
        if (student == null) {
            throw new ResourceNotFoundException("User khong tim thay voi id: " + id);
        }
        userService.deleteStudent(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(null)
                .message("Deleted user successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
