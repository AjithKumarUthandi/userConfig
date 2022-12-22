package com.rough.todo.security.filter;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class ExceptionHanderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException exp) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username doesn't exist");
            response.getWriter().flush();
        } catch (JWTVerificationException exp) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Token not valid");
            response.getWriter().flush();
        } catch (RuntimeException exp) {
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            response.getWriter().write("Bad Request");
            response.getWriter().flush();
        }
    }

}
