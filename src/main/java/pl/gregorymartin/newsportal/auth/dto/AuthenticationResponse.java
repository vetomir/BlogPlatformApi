package pl.gregorymartin.newsportal.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public
class AuthenticationResponse {
    private final String token;
}
