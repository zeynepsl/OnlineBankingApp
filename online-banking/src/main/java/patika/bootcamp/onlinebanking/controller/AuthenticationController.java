package patika.bootcamp.onlinebanking.controller;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.onlinebanking.security.UserDetail;
import patika.bootcamp.onlinebanking.security.helper.JWTHelper;
import patika.bootcamp.onlinebanking.security.model.AuthenticationRequest;
import patika.bootcamp.onlinebanking.security.model.AuthenticationResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;

    @PostMapping(path = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail()
        		, authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        String token = jwtHelper.generate(authenticationRequest.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(token,
                userDetail
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet())));
    }
}