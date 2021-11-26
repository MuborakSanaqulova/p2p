package uz.pdp.transfermoney.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.transfermoney.entity.Users;

import java.util.Date;

@Component
public class JwtProvider {

    long expireTime = 36_000_000;
    String secretKey = "entityPayloadRepositoryServiceControllerConfigCommonSecurityXaaXaaaaaa";

    public String generateToken(Users users) {
        String token = Jwts
                .builder()
                .setSubject(users.getUsername())
                .claim(users.getId().toString(), users.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        String username = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }

}
