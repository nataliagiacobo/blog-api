package br.com.blog.controller;

import br.com.blog.config.TokenService;
import br.com.blog.dto.TokenResponse;
import br.com.blog.model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestHeader(name="Authorization") String basicAuth) throws Exception {
        String pair=new String(Base64.decodeBase64(basicAuth.substring(6)));
        String userName=pair.split(":")[0];
        String password=pair.split(":")[1];

        UsernamePasswordAuthenticationToken autheticate = new UsernamePasswordAuthenticationToken(userName, password);

        Authentication authentication = null;


        try {
            authentication = authenticationManager.authenticate(autheticate);
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException(e.getMessage());
        }
        catch (Exception e)
        {
            throw new Exception("Erro na autenticação");
        }

        String token = tokenService.tokenGenerate((User) authentication.getPrincipal());

        TokenResponse response = new TokenResponse(
                token,
                "Bearer",
                ((User) authentication.getPrincipal()).getId(),
                ((User) authentication.getPrincipal()).getUsername());

        return ResponseEntity.ok().body(response);
    }
}
