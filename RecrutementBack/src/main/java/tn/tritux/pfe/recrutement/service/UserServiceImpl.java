package tn.tritux.pfe.recrutement.service;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.tritux.pfe.recrutement.config.JwtService;
import tn.tritux.pfe.recrutement.dto.request.LoginRequest;
import tn.tritux.pfe.recrutement.dto.request.SignUPRequest;
import tn.tritux.pfe.recrutement.dto.request.UserRequest;
import tn.tritux.pfe.recrutement.dto.response.InterviewResponse;
import tn.tritux.pfe.recrutement.dto.response.LoginResponse;
import tn.tritux.pfe.recrutement.dto.response.SignUPResponse;
import tn.tritux.pfe.recrutement.dto.response.UserResponse;
import tn.tritux.pfe.recrutement.entity.Interview;
import tn.tritux.pfe.recrutement.entity.Role;
import tn.tritux.pfe.recrutement.entity.User;
import tn.tritux.pfe.recrutement.repository.UserRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public SignUPResponse signUp(SignUPRequest signUPRequest) {
        if(repository.findByEmail(signUPRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        User user=null;
        user=User.builder()
                .prenom(signUPRequest.getPrenom())
                .nom(signUPRequest.getNom())
                .email(signUPRequest.getEmail())
                .image_profile(signUPRequest.getImage_profile())
                .motDePasse(passwordEncoder.encode(signUPRequest.getMotDePasse()))
                .role(Role.CANDIDAT)
                .build();
        repository.save(user);
        var accessToken=jwtService.generateToken(user);
        return SignUPResponse.builder()
                .accessToken(accessToken)
                .refreshToken(null)
                .id(user.getId())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user=repository.findByEmail(loginRequest.getEmail()).orElseThrow(
                ()->new RuntimeException("User not found")
        );
        if(!user.isEnabled()){
            throw new RuntimeException("User account is not enabled");
        }
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getMotDePasse()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (BadCredentialsException e){
            throw new RuntimeException("invalid credentials");
        }
        var accessToken=jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(null)
                .id(user.getId())
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
    @Override
    public UserResponse getUserById(Long id){
        User user=repository.findById(id).orElse(null);
        return UserResponse.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom()).
                image_profile(user.getImage_profile()).
                role(user.getRole().name()).
                email(user.getEmail()).build();
    }
    public void updateUserInformation(UserRequest userRequest){
        User user=repository.findById(userRequest.getId()).orElse(null);
        user.setNom(userRequest.getNom());
        user.setPrenom(userRequest.getPrenom());
        user.setEmail(userRequest.getEmail());
        if(userRequest.getImage_profile()!=null){
            user.setImage_profile(userRequest.getImage_profile());
        }
        repository.save(user);
    }
    public List<UserResponse> getRecruteurRH(){
        return repository.findAll().stream()
                .filter(u->u.getRole().equals(Role.RECRUTEUR_RH))
                .map(this::entityToResponse)
                .collect(Collectors.toList());

    }
    private UserResponse entityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setNom(user.getNom());
        userResponse.setPrenom(user.getPrenom());
        userResponse.setRole(user.getRole().name());
        userResponse.setImage_profile(user.getImage_profile());
        return userResponse;
    }
}