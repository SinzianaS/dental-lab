
/**
 * The AuthTokenFilter class is a filter that intercepts incoming HTTP requests and
 * attempts to extract the JWT token from the Authorization header. If a valid token
 * is found, it sets the user's authentication information in the Spring Security
 * context so that the user is authorized to access protected resources.
 */
package com.dental.lab.security.jwt;


import com.dental.lab.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthTokenFilter extends OncePerRequestFilter {
    /**
     * The JwtUtils instance used to validate and extract data from JWT tokens.
     */
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * The UserDetailsServiceImpl instance used to load the user's details from
     * the database given a username.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    /**
     * The logger instance for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    /**
     * This method intercepts incoming HTTP requests and attempts to extract the JWT token
     * from the Authorization header. If a valid token is found, it sets the user's
     * authentication information in the Spring Security context so that the user is authorized
     * to access protected resources.
     *
     * @param request     The HTTP request object.
     * @param response    The HTTP response object.
     * @param filterChain The filter chain to pass the request through.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * This method extracts the JWT token from the Authorization header of the HTTP request.
     *
     * @param request The HTTP request object.
     * @return The JWT token, or null if it is not found.
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}

