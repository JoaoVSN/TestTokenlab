package com.example.backtoken.config;

import com.example.backtoken.service.CustomDetailService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAthorizationFilter extends BasicAuthenticationFilter {
    private final CustomDetailService customDetailService;
    public JWTAthorizationFilter(AuthenticationManager authenticationManager, CustomDetailService customDetailService) {
        super(authenticationManager);
        this.customDetailService = customDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthToken(HttpServletRequest request){
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if(token == null) return null;
            String us_login = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            UserDetails userDetails = customDetailService.loadUserByUsername(us_login);
            return us_login != null?
                    new UsernamePasswordAuthenticationToken(us_login, null, userDetails.getAuthorities()):null;

    }
}
