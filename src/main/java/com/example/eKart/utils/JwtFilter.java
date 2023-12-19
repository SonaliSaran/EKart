package com.example.eKart.utils;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    Logger logger = LoggerFactory.getLogger(getClass());
    private String token = null;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String userName = null;
        try {
            //	System.out.println("entered try ");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                System.out.println("token----------" + token);
                userName = jwtUtil.getUsernameFromToken(token);

                logger.info("userName :" + userName);
            }
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jwtAuthenticationProvider.loadUserByUsername(userName);
                if (jwtUtil.isTokenExpired(token)) {
                    System.out.println("token is expired");
                } else if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }

            }

        } catch (Exception ex) {
            //System.out.println("entered catch");
            if (ex.getMessage().contains("JWT expired at")) {
                System.out.println("jwt expired");
                // throw new
                // JwtExpirationException(Response.Status.BAD_GATEWAY,ResponseConstants.JWT_EXPIRATION_MESSAGE);

            }

        }
        filterChain.doFilter(request, response);
    }

}
