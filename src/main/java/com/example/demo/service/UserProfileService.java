
package com.example.demo.service;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public List<UserProfile> getAllUserProfiles() {
        return repository.findAll();
    }

    public UserProfile getUserProfile(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserProfile saveUserProfile(UserProfile profile) {
        return repository.save(profile);
    }

    public void deleteUserProfile(Long id) {
        repository.deleteById(id);
    }
    //  // ✅ New method to fetch by userId
    // public UserProfile getUserProfileByUserId(Long userId) {
    //     return repository.findByUserId(userId).orElse(null);
    // }
    public UserProfile getUserProfileByUserId(Long userId) {
    return repository.findByUserId(userId).orElse(null);
}

}
