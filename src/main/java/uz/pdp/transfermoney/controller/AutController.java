package uz.pdp.transfermoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.transfermoney.common.LoginDto;
import uz.pdp.transfermoney.common.ResponseData;
import uz.pdp.transfermoney.entity.Users;
import uz.pdp.transfermoney.security.JwtProvider;
import uz.pdp.transfermoney.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AutController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {

        Users details = authService.loadUserByUsername(loginDto.getUsername());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )

            );
            return ResponseData.response(jwtProvider.generateToken(details));

        } catch (BadCredentialsException e){
            return ResponseData.response("Bad credentials", HttpStatus.BAD_REQUEST);
        }
    }

}
