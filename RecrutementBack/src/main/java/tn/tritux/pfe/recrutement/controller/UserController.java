package tn.tritux.pfe.recrutement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tritux.pfe.recrutement.dto.request.LoginRequest;
import tn.tritux.pfe.recrutement.dto.request.SignUPRequest;
import tn.tritux.pfe.recrutement.dto.request.UserRequest;
import tn.tritux.pfe.recrutement.dto.response.LoginResponse;
import tn.tritux.pfe.recrutement.dto.response.SignUPResponse;
import tn.tritux.pfe.recrutement.dto.response.UserResponse;
import tn.tritux.pfe.recrutement.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response=userService.login(request);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/signup")
    public ResponseEntity<SignUPResponse> signup(@RequestBody SignUPRequest request){
        SignUPResponse response=userService.signUp(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        UserResponse response=userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public void updateUserInformation(@RequestBody UserRequest userRequest){
        userService.updateUserInformation(userRequest);
    }
    @GetMapping("/rh")
    public ResponseEntity<List<UserResponse>>getRecruteurRH(){
        return ResponseEntity.ok(userService.getRecruteurRH());
    }
}
