package com.example.demo.endpoints.endpoints;

import com.example.demo.endpoints.endpoints.dto.AuthRequest;
import com.example.demo.endpoints.infrastructure.util.JwtUtil;
import com.example.demo.endpoints.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/admin")
    @PreAuthorize("@AuthorizedSecurity.hasRules('ADMIN')")
    public String admin(){
        return "Usuario Administrador";
    }

    @GetMapping("/user")
    @PreAuthorize("@AuthorizedSecurity.hasRules('USER', 'ADMIN')")
    public String user(){
        return "Usuario Comum";
    }

    @PostMapping("/authenticate")
    public String authentication(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }catch (Exception exception){
            throw new Exception("invalid username/password");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
