
/**
 * The AuthEntryPointJwt class implements the Spring Security AuthenticationEntryPoint interface
 * to handle unauthorized access attempts to protected API endpoints. It sends an HTTP 401 Unauthorized
 * response to the client.
 */
package com.dental.lab.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Sends an HTTP 401 Unauthorized response to the client with an error message.
     *
     * @param request       The HttpServletRequest object.
     * @param response      The HttpServletResponse object.
     * @param authException The AuthenticationException object that caused the error.
     * @throws IOException      if an input or output exception occurs.
     * @throws ServletException if a servlet exception occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }

}

