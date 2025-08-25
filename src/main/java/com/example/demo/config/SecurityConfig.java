// package com.example.demo.config;



// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// import com.example.demo.security.JwtAuthenticationFilter;



// import java.util.Arrays;

// import org.springframework.http.HttpMethod;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // //         http
// // //                 .csrf(csrf -> csrf.disable())
// // //                 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
// // // .authorizeHttpRequests(auth -> auth
// // //     .requestMatchers("/users/register", "/users/login").permitAll()
// // //      .requestMatchers("/api/auth/**").permitAll()
// // //     //  .requestMatchers(HttpMethod.POST).permitAll()
// // //     .requestMatchers(HttpMethod.GET).permitAll()
// // //     .requestMatchers(HttpMethod.PUT).permitAll()
// // //     .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
// // //     .anyRequest().authenticated()
// // // )
// // //                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // //                 .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

// // //         return http.build();
// //    http
// //             .csrf().disable()
// //             .cors()
// //             .and()
// //             .authorizeHttpRequests()
// //                 .anyRequest().permitAll(); // allow everyone to call all endpoints
// //         return http.build();
// //     }
// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     // http
//     //     .csrf().disable()
//     //     .cors()
//     //     .and()
//     //     .authorizeHttpRequests(auth -> auth
//     //         .requestMatchers("/users/register", "/users/login").permitAll() // public
//     //         .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll() // public
//     //         .anyRequest().authenticated() // all other endpoints require JWT
//     //     )
//     //     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//     //     .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

//     // return http.build();
//     http
//     .csrf().disable()
//     .cors()
//     .and()
//     .authorizeHttpRequests(auth -> auth
//         .requestMatchers("/users/register", "/users/login").permitAll()       // public
//         .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()   // anyone can view exercises
//         .requestMatchers(HttpMethod.GET, "/users/role/**").permitAll()         // anyone can view users by role
//         .anyRequest().authenticated()                                           // all other endpoints require JWT
//     )
//     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//     .addFilterBefore(jwtAuthenticationFilter, 
//         org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

// return http.build();

// }

//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(
//         "http://localhost:5173", // Vite
//         "http://localhost:5174", // if you run on 5174
//         "http://localhost:3000"  // CRA
//     ));

//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .cors()
//             .and()
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/users/register", "/users/login").permitAll()   // public
//                 .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll() // anyone can view exercises
//                 .anyRequest().authenticated()  // POST, PUT, DELETE require JWT
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtAuthenticationFilter, 
//                 org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:5173",
//                 "http://localhost:5174",
//                 "http://localhost:3000"
//         ));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .cors()
//             .and()
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/users/register", "/users/login").permitAll()         // public
//                 .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()     // anyone can view exercises
//                 .anyRequest().authenticated()                                           // all other endpoints require JWT
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtAuthenticationFilter,
//                     org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:5173",
//                 "http://localhost:5174",
//                 "http://localhost:3000"
//         ));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     // -------------------- Password Encoder --------------------
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     // -------------------- Security Filter Chain --------------------
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/users/register", "/users/login").permitAll()
//                 .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()
//                  .requestMatchers(HttpMethod.PUT, "/api/workout-assignments/**").hasAnyRole("TRAINER", "ADMIN")
//     .requestMatchers(HttpMethod.DELETE, "/api/workout-assignments/**").hasAnyRole("TRAINER", "ADMIN")
//                 // All other endpoints require authentication
//                 .anyRequest().authenticated()
//             )
//             // Stateless session
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             // Add JWT filter before UsernamePasswordAuthenticationFilter
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // -------------------- CORS Configuration --------------------
//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:5173",
//                 "http://localhost:5174",
//                 "http://localhost:3000"
//         ));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/users/register", "/users/login").permitAll()
//                 .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()
//                 // Only TRAINER and ADMIN can modify assignments
//                 .requestMatchers(HttpMethod.POST, "/api/workout-assignments/**").hasAnyRole("TRAINER", "ADMIN")
//                 .requestMatchers(HttpMethod.PUT, "/api/workout-assignments/**").hasAnyRole("TRAINER", "ADMIN")
//                 .requestMatchers(HttpMethod.DELETE, "/api/workout-assignments/**").hasAnyRole("TRAINER", "ADMIN")
//                 // All other endpoints require authentication
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:5173",
//                 "http://localhost:5174",
//                 "http://localhost:3000"
//         ));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/users/register", "/users/login").permitAll()
//                 .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()
//                 // Only TRAINER and ADMIN can modify assignments
//                 .requestMatchers(HttpMethod.POST, "/api/workout-assignments/**").hasAnyAuthority("TRAINER", "ADMIN")
//                 .requestMatchers(HttpMethod.PUT, "/api/workout-assignments/**").hasAnyAuthority("TRAINER", "ADMIN")
//                 .requestMatchers(HttpMethod.DELETE, "/api/workout-assignments/**").hasAnyAuthority("TRAINER", "ADMIN")
//                 // All other endpoints require authentication
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:5173",
//                 "http://localhost:5174",
//                 "http://localhost:3000"
//         ));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import java.util.Arrays;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .authorizeHttpRequests(auth -> auth
//                 // Public endpoints
//                 .requestMatchers("/users/register", "/users/login").permitAll()
//                 .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()
                
//                 // Workout assignment endpoints
//                 .requestMatchers(HttpMethod.POST, "/api/workout-assignments/**").hasAnyAuthority("TRAINER","ADMIN")
//                 .requestMatchers(HttpMethod.PUT, "/api/workout-assignments/**").hasAnyAuthority("TRAINER","ADMIN")
//                 .requestMatchers(HttpMethod.DELETE, "/api/workout-assignments/**").hasAnyAuthority("TRAINER","ADMIN")

//                 // All other endpoints require authentication
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:5173",
//                 "http://localhost:5174",
//                 "http://localhost:3000"
//         ));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//         configuration.setAllowedHeaders(Arrays.asList("*"));
//         configuration.setAllowCredentials(true);

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/users/role/**").permitAll()
                .requestMatchers("/users/register", "/users/login").permitAll()
                .requestMatchers("/api/auth/**").permitAll()

                 .requestMatchers(HttpMethod.POST, "/fitness-goals/**").permitAll()
                 
                 .requestMatchers(HttpMethod.GET, "/fitness-goals/**").permitAll()
      
                 
                 .requestMatchers(HttpMethod.DELETE, "/fitness-goals/**").permitAll()
                 
                 .requestMatchers(HttpMethod.PUT, "/fitness-goals/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/notifications/**").permitAll()
                 
                 .requestMatchers(HttpMethod.GET, "/api/notifications/**").permitAll()
                 
                 .requestMatchers(HttpMethod.DELETE, "/api/notifications/**").permitAll()
                 
                 .requestMatchers(HttpMethod.PUT, "/api/notifications/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/exercise-library/**").permitAll()
                // .requestMatchers(HttpMethod.POST, "/api/workout-assignments/**").hasAnyAuthority("TRAINER","ADMIN")
                // .requestMatchers(HttpMethod.PUT, "/api/workout-assignments/**").hasAnyAuthority("TRAINER","ADMIN")
                // .requestMatchers(HttpMethod.DELETE, "/api/workout-assignments/**").hasAnyAuthority("TRAINER","ADMIN")
             .requestMatchers(HttpMethod.POST, "/api/workout-assignments")
           
   .permitAll()
 .requestMatchers(HttpMethod.POST, "/api/add-user").permitAll()

 .requestMatchers(HttpMethod.GET, "/api/workout-assignments/**")
    .hasAnyRole("TRAINER", "ADMIN")
     .requestMatchers(HttpMethod.DELETE, "/api/workout-assignments/**")
    .hasAnyRole("TRAINER", "ADMIN")
                // .requestMatchers("/fitness-goals/**").hasAnyAuthority("USER", "TRAINER", "ADMIN") 
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
