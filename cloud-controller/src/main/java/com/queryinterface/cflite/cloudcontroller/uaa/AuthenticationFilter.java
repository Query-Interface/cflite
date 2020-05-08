package com.queryinterface.cflite.cloudcontroller.uaa;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;

            setFilterProcessesUrl("/api/uaa/oauth/token");
        }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {
        var user = ((User) authentication.getPrincipal());
        // TODO: provide real authorities
        String scope = "uaa.user cloud_controller.read password.write cloud_controller.write";

        var signingKey = UaaConstants.SIGN_KEY.getBytes();

        final String jti = UUID.randomUUID().toString();
        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", UaaConstants.TOKEN_TYPE)
                .setId(jti)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 86400*1000))  //24 hours
                .claim("user_name", user.getUsername())
                .claim("user_id", user.getId().toString())
                .claim("origin", user.getOrigin())
                .claim("scope", Stream.of(scope.split(" ")).collect(Collectors.toList()))
                .compact();
        AuthToken authToken = new AuthToken();
        authToken.setScope(scope);
        authToken.setAccess_token(token);
        authToken.setToken_type("bearer");
        authToken.setJti(jti);
        authToken.setRefresh_token(jti+"-r");

        response.addHeader(UaaConstants.TOKEN_HEADER, UaaConstants.TOKEN_PREFIX + token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var om = new ObjectMapper();
        try {
            response.getWriter().write(om.writeValueAsString(authToken));
        } catch (IOException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
