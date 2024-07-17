package tn.tritux.pfe.recrutement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.tritux.pfe.recrutement.config.JwtService;
import tn.tritux.pfe.recrutement.dto.request.LoginRequest;
import tn.tritux.pfe.recrutement.dto.request.SignUPRequest;
import tn.tritux.pfe.recrutement.dto.response.LoginResponse;
import tn.tritux.pfe.recrutement.dto.response.SignUPResponse;
import tn.tritux.pfe.recrutement.entity.User;
import tn.tritux.pfe.recrutement.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        logger.info("Mockito annotations initialized");
    }

    @Test
    void testSignUp(){
        SignUPRequest request = new SignUPRequest();
        request.setEmail("test@gmail.com");

        logger.info("Testing sign-up with email: {}", request.getEmail());

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(jwtService.generateToken(any(User.class))).thenReturn("token");

        SignUPResponse response = userService.signUp(request);

        assertNotNull(response);
        assertEquals("token", response.getAccessToken());
        verify(userRepository, times(1)).save(any(User.class));

        logger.info("Sign-up test passed for email: {}", request.getEmail());
    }

    @Test
    void testLogin(){
        LoginRequest request = new LoginRequest();
        request.setEmail("test@gmail.com");
        request.setMotDePasse("password");

        logger.info("Testing login with email: {}", request.getEmail());

        User user = new User();
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("token");
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken("test@gmail.com", "password"));

        LoginResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("token", response.getAccessToken());
        verify(authenticationManager, times(1)).authenticate(any(Authentication.class));

        logger.info("Login test passed for email: {}", request.getEmail());
    }
}
