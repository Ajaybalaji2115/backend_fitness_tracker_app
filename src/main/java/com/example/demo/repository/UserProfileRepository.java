// package com.example.demo.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.demo.model.UserProfile;


// public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
//     Optional<UserProfile> findByUserId(Long userId);
// }
// package com.example.demo.repository;

// import com.example.demo.model.UserProfile;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
//     UserProfile findByUser_Id(Long id);
// }
// package com.example.demo.repository;

// import com.example.demo.model.UserProfile;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
//     UserProfile findByUserId(Long userId);
// }
// UserProfileRepository.java (Repository)
package com.example.demo.repository;

import com.example.demo.model.UserProfile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
     Optional<UserProfile> findByUserId(Long userId);
}