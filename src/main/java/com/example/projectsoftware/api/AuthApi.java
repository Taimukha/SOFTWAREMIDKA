package com.example.projectsoftware.api;

import com.example.projectsoftware.model.UserModel;
import com.example.projectsoftware.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApi {

    private final MyUserService myUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel model) {
        myUserService.register(model);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/test")
    public String test() {
        return "Security Works";
    }
}
