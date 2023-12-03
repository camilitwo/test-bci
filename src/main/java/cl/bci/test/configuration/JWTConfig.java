package cl.bci.test.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTConfig {
    private static final byte[] SECRET_KEY = "camilogonzalezvillalobospruebabcijavajwt".getBytes();

    public String createToken(String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withIssuer("auth0")
                .withClaim("username", username)
                .withClaim("password", password)
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .withSubject(username)
                .sign(algorithm);
    }

    public Date getExpiration() {
        return new Date(System.currentTimeMillis() + 3600 * 1000);
    }

    public String getType() {
        return "Bearer";
    }
}
