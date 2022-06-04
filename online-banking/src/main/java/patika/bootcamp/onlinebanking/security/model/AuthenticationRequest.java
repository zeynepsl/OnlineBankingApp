package patika.bootcamp.onlinebanking.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
	private String email;
	private String password;
}
