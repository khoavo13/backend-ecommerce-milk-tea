package com.example.backend_ecommerce_milk_tea.configs;


import com.example.backend_ecommerce_milk_tea.models.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    //    @Value("${api.prefix}")
    private final String apiPrefix="api";
    private final UserDetailsService userDetailsService;
    private final JwtToken jwtTokenUtil;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            System.out.println("Processing request: " + request.getMethod() + " " + request.getServletPath());
            if(isBypassToken(request)) {
                filterChain.doFilter(request, response); //enable bypass
                return;
            }

            if (request.getServletPath().startsWith("/v3/api-docs") ||
                    request.getServletPath().startsWith("/swagger-ui")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "authHeader null or not started with Bearer");
                return;
            }
            final String token = authHeader.substring(7);
            final String username = jwtTokenUtil.extractUsername(token);
            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                Users userDetails = (Users) userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response); //enable bypass
        }catch (Exception e) {
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
        }
    }
    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
//                Pair.of(String.format("%s/products**", apiPrefix), "GET"),
//                Pair.of(String.format("%s/categories**", apiPrefix), "GET"),
//                Pair.of(String.format("%s/users/register", apiPrefix), "POST"),
//                Pair.of(String.format("%s/users/refresh-token", apiPrefix), "POST"),
               Pair.of(String.format("%s/user/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/user/register", apiPrefix), "POST"),
                Pair.of(String.format("swagger-ui/**", apiPrefix), "GET")
        );
        for(Pair<String, String> bypassToken: bypassTokens) {
            if (request.getServletPath().contains(bypassToken.getFirst()) &&
                    request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }
        return false;
    }
}
