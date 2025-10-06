package com.wecp.progressive.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wecp.progressive.service.impl.UserLoginServiceImpl;
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    
    private final JwtUtil jwtUtil;
    private final UserLoginServiceImpl userLoginServiceImpl;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil, UserLoginServiceImpl userLoginServiceImpl) {
        this.jwtUtil = jwtUtil;
        this.userLoginServiceImpl = userLoginServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            String username = jwtUtil.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetailsObj = userLoginServiceImpl.loadUserByUsername(username);

                if(jwtUtil.validateToken(token, userDetailsObj)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetailsObj,null,userDetailsObj.getAuthorities()
                    );

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                
                }
                
            }

            
        }

        filterChain.doFilter(request, response);
    }
    
    
}
