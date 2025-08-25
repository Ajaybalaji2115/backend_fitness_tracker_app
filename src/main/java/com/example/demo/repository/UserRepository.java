// package com.example.demo.repository;

// import com.example.demo.model.*;


// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.*;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;


// public interface UserRepository extends JpaRepository<User, Long> {
//     Optional<User> findByEmail(String email);
//     List<User> findByRole(Role role);
//     Page<User> findByRole(Role role, Pageable pageable);
//     Optional<User> findByName(String name);

// }
// package com.example.demo.repository;

// import com.example.demo.model.Role;
// import com.example.demo.model.User;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     Optional<User> findByEmail(String email);

//     Page<User> findAllByRole(Role role, Pageable pageable);
// }
package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

    // Only keep the paged version
    Page<User> findByRole(Role role, Pageable pageable);
}
