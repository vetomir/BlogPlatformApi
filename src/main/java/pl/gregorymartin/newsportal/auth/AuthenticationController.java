package pl.gregorymartin.newsportal.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.newsportal.auth.dto.AuthenticationRequest;
import pl.gregorymartin.newsportal.auth.dto.AuthenticationResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    ResponseEntity<AuthenticationResponse> createToken(@RequestBody @Valid AuthenticationRequest authRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            var user = (UserDetails) auth.getPrincipal();
            return ResponseEntity.ok(new AuthenticationResponse(tokenService.generateNewToken(user)));

        }catch (BadCredentialsException e){
            throw new IllegalArgumentException("Password is invalid");
        }

    }
}
