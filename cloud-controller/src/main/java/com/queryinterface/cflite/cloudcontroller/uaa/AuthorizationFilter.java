package com.queryinterface.cflite.cloudcontroller.uaa;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        var authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(UaaConstants.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token) && token.startsWith(UaaConstants.TOKEN_PREFIX)) {
            try {
                var signingKey = UaaConstants.SIGN_KEY.getBytes();

                var parser = Jwts.parserBuilder().setSigningKey(signingKey).build();
                var parsedToken = parser.parseClaimsJws(token.replace("bearer ", ""));
                var username = parsedToken
                        .getBody()
                        .getSubject();

                var authorities = ((List<?>) parsedToken.getBody()
                        .get("scope")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (!StringUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (ExpiredJwtException exception) {
                log.warn("Parse an expired JWT.");
            } catch (UnsupportedJwtException exception) {
                log.warn("Unsupported JWT. {}", exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Invalid JWT. {}", token, exception.getMessage());
            } catch (SignatureException exception) {
                log.warn("Invalid signature.");
            } catch (IllegalArgumentException exception) {
                log.warn("Empty or null JWT.");
            }
        }

        return null;
    }
}