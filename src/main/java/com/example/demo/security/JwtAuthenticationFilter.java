package com.example.demo.security;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {
// String path = request.getServletPath();
// if (path.equals("/users/register") || path.equals("/users/login") 
//     || path.startsWith("/api/auth/") ) {
//     // Skip JWT check for register/login/auth endpoints
//     filterChain.doFilter(request, response);
//     return;
// }
//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             Claims claims = Jwts.parser()
//                     .setSigningKey(secretKey.getBytes())
//                     .parseClaimsJws(token)
//                     .getBody();

//             String username = claims.getSubject();

//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 UsernamePasswordAuthenticationToken auth =
//                         new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

//                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//             }

//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// } //use this
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/users/register") || path.equals("/users/login")
                || path.startsWith("/api/auth/") ) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String role = claims.get("role", String.class); // ✅ Extract role

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority( role)) // ✅ Attach role
                        );

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(request, response);
    }
}











// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired; // ADDED: Import for Autowired annotation
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority; // ADDED: Import for SimpleGrantedAuthority
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collection; // ADDED: Import for Collection
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     // MODIFIED: Replaced @Value for the secret key with an injected JwtUtil
//     @Autowired
//     private JwtUtil jwtUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {
//  // Skip JWT validation for register and login
//  String path = request.getRequestURI();
//     if (path.equals("/users/register") || path.equals("/users/login") ||
//         path.equals("/api/auth/forgot-password") || path.equals("/api/auth/reset-password")) {
//         filterChain.doFilter(request, response); // skip JWT
//         return;
//     }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             // MODIFIED: Use JwtUtil to extract both username and role
//             String username = jwtUtil.extractUsername(token);
//             String role = jwtUtil.extractRole(token); // ADDED: This line extracts the user's role

//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 // MODIFIED: Create a collection of authorities with the extracted role
//                 Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

//                 // MODIFIED: Pass the authorities collection to the authentication token
//                 UsernamePasswordAuthenticationToken auth =
//                         new UsernamePasswordAuthenticationToken(username, null, authorities);

//                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//             }

//         } catch (Exception e) {
//             // ADDED: Log the exception for better debugging
//             System.out.println("JWT token validation failed: " + e.getMessage());
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collection;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Autowired
//     private JwtUtil jwtUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String path = request.getServletPath();

//         // Skip JWT check for public endpoints
//         if (path.equals("/users/register") || path.equals("/users/login") || path.startsWith("/api/auth/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             // Extract username and role from JWT
//             String username = jwtUtil.extractUsername(token);
//             String role = jwtUtil.extractRole(token); // Should return "TRAINER" or "GUEST"

//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(
//                         new SimpleGrantedAuthority("ROLE_" + role)
//                 );

//                 UsernamePasswordAuthenticationToken auth =
//                         new UsernamePasswordAuthenticationToken(username, null, authorities);

//                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//             }

//         } catch (Exception e) {
//             System.out.println("JWT validation failed: " + e.getMessage());
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }

// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {

//         String path = request.getServletPath();
//         if (path.equals("/users/register") || path.equals("/users/login") || path.startsWith("/api/auth/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);
//         try {
//             Claims claims = Jwts.parser()
//                     .setSigningKey(secretKey.getBytes())
//                     .parseClaimsJws(token)
//                     .getBody();

//             String username = claims.getSubject();
//             String role = claims.get("role", String.class);

//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                         username,
//                         null,
//                         Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
//                 );
//                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//             }

//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.example.demo.security;

// import com.example.demo.model.Role;
// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private JwtUtil jwtUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {

//         String path = request.getServletPath();
//         if (path.equals("/users/register") || path.equals("/users/login") || path.startsWith("/api/auth/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);
//         try {
//             String email = jwtUtil.extractUsername(token);
//             User user = userService.getUserByEmail(email).orElse(null);
//             if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {

//                 // Map Role enum to Spring Security authority
//                 SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
//                 UsernamePasswordAuthenticationToken authToken =
//                         new UsernamePasswordAuthenticationToken(email, null, Collections.singletonList(authority));

//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//             }
//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.example.demo.security;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Autowired
//     private UserService userService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {
//         String path = request.getServletPath();
//         if (path.equals("/users/register") || path.equals("/users/login") || path.startsWith("/api/auth/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);
//         try {
//             Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
//             String email = claims.getSubject();

//             if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 User user = userService.getUserByEmail(email).orElse(null);
//                 if (user != null) {
//                     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                             user.getEmail(), null, Collections.emptyList()
//                     );
//                     auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                     SecurityContextHolder.getContext().setAuthentication(auth);
//                 }
//             }

//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.example.demo.security;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Autowired
//     private UserService userService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {

//         String path = request.getServletPath();

//         // Skip filter for public endpoints
//         if (path.equals("/api/users/register") || path.equals("/api/users/login") || path.startsWith("/api/auth/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             // No token provided
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7); // Remove "Bearer " prefix

//         try {
//             // Parse JWT claims
//             Claims claims = Jwts.parser()
//                     .setSigningKey(secretKey.getBytes())
//                     .parseClaimsJws(token)
//                     .getBody();

//             String email = claims.getSubject();

//             if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 // Load user from database
//                 User user = userService.getUserByEmail(email).orElse(null);

//                 if (user != null) {
//                     // Create authentication object
//                     UsernamePasswordAuthenticationToken authToken =
//                             new UsernamePasswordAuthenticationToken(
//                                     user.getEmail(),
//                                     null,
//                                     Collections.emptyList() // You can add roles/authorities here
//                             );

//                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//                     // Set authentication in security context
//                     SecurityContextHolder.getContext().setAuthentication(authToken);
//                 }
//             }

//         } catch (Exception e) {
//             // Invalid token
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("Invalid or expired JWT token");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.example.demo.security;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Autowired
//     private UserService userService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {

//         String path = request.getServletPath();

//         // Skip JWT check for login and register endpoints
//         if (path.equals("/users/register") || path.equals("/users/login") || path.startsWith("/api/auth/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             // No token provided, just continue filter chain (guest request)
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7); // Remove "Bearer " prefix

//         try {
//             // Parse JWT claims
//             Claims claims = Jwts.parser()
//                     .setSigningKey(secretKey.getBytes())
//                     .parseClaimsJws(token)
//                     .getBody();

//             String email = claims.getSubject();

//             if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 // Load user from DB by email
//                 User user = userService.getUserByEmail(email).orElse(null);

//                 if (user != null) {
//                     UsernamePasswordAuthenticationToken authToken =
//                             new UsernamePasswordAuthenticationToken(
//                                     user.getEmail(),
//                                     null,
//                                     Collections.emptyList() // you can add roles if needed
//                             );

//                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//                     SecurityContextHolder.getContext().setAuthentication(authToken);
//                 }
//             }

//         } catch (Exception e) {
//             // Token invalid or expired
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("Invalid or expired JWT token");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
// package com.example.demo.security;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Autowired
//     private UserService userService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain) throws ServletException, IOException {

//         String path = request.getServletPath();

//         // Skip JWT check for login/register
//         if (path.equals("/users/register") || path.equals("/users/login")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             Claims claims = Jwts.parser()
//                     .setSigningKey(secretKey.getBytes())
//                     .parseClaimsJws(token)
//                     .getBody();

//             String email = claims.getSubject();

//             if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 User user = userService.getUserByEmail(email).orElse(null);
//                 if (user != null) {
//                     UsernamePasswordAuthenticationToken auth =
//                             new UsernamePasswordAuthenticationToken(
//                                     user.getEmail(), null, Collections.emptyList()
//                             );
//                     auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                     SecurityContextHolder.getContext().setAuthentication(auth);
//                 }
//             }
//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             response.getWriter().write("Invalid or expired JWT token");
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }







// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;
// import java.util.List;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Autowired
//     private JwtUtil jwtUtil;

//     @Value("${jwt.secret}")
//     private String secretKey;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String path = request.getServletPath();
//         if (path.equals("/users/register") || path.equals("/users/login") 
//                 || path.startsWith("/api/auth/") || path.startsWith("/users/role/")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7);

//         try {
//             String username = jwtUtil.extractUsername(token);
//             String role = jwtUtil.extractRole(token);  // 👈 we set this in generateToken()

//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 // 👇 attach role with ROLE_ prefix
//                 List<SimpleGrantedAuthority> authorities =
//                         Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

//                 UsernamePasswordAuthenticationToken auth =
//                         new UsernamePasswordAuthenticationToken(username, null, authorities);

//                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(auth);
                 
//             }
//            // Always print what is in the SecurityContext
// if (SecurityContextHolder.getContext().getAuthentication() != null) {
//     System.out.println("🔑 Request: " + request.getRequestURI());
//     System.out.println("   Username: " + SecurityContextHolder.getContext().getAuthentication().getName());
//     System.out.println("   Roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
// } else {
//     System.out.println("🔒 Request: " + request.getRequestURI() + " - No Authentication");
// }

//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }
