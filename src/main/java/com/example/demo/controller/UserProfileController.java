// package com.example.demo.controller;
// import com.example.demo.model.*;
// import com.example.demo.service.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.*;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/profile")
// public class UserProfileController {

//     @Autowired
//     private UserProfileService profileService;



//     @PostMapping("/{userId}")
//     public ResponseEntity<UserProfile> createProfile(@PathVariable Long userId, @RequestBody UserProfile profile) {
//         return ResponseEntity.ok(profileService.create(userId, profile));
//     }

//     @PutMapping("/{userId}")
//     public ResponseEntity<UserProfile> updateProfile(@PathVariable Long userId, @RequestBody UserProfile profile) {
//         return ResponseEntity.ok(profileService.update(userId, profile));
//     }

//     @GetMapping("/{userId}")
//     public ResponseEntity<UserProfile> getProfile(@PathVariable Long userId) {
//         return ResponseEntity.ok(profileService.getByUserId(userId));
//     }
// }
// // package com.example.demo.controller;

// // import com.example.demo.model.UserProfile;
// // import com.example.demo.service.UserProfileService;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // @RestController
// // @RequestMapping("/api/profiles")
// // public class UserProfileController {

// //     private final UserProfileService userProfileService;

// //     public UserProfileController(UserProfileService userProfileService) {
// //         this.userProfileService = userProfileService;
// //     }

// //     // Create Profile
// //    @PostMapping("/{userId}")
// // public ResponseEntity<?> createProfile(
// //         @PathVariable Long userId,
// //         @RequestBody UserProfile profile) { // password is part of profile now
// //     try {
// //         UserProfile created = userProfileService.createProfile(userId, profile, profile.getPassword());
// //         return ResponseEntity.ok(created);
// //     } catch (RuntimeException e) {
// //         return ResponseEntity.badRequest().body(e.getMessage());
// //     }
// // }
// //     // Update Profile
// //     @PutMapping("/{userId}")
// //     public ResponseEntity<?> updateProfile(
// //             @PathVariable Long userId,
// //             @RequestBody UserProfile profile,
// //             @RequestParam String password) {
// //         try {
// //             UserProfile updated = userProfileService.updateProfile(userId, profile, password);
// //             return ResponseEntity.ok(updated);
// //         } catch (RuntimeException e) {
// //             return ResponseEntity.badRequest().body(e.getMessage());
// //         }
// //     }
// //     // Get Profile by UserId
// // @GetMapping("/{userId}")
// // public ResponseEntity<?> getProfile(@PathVariable Long userId) {
// //     UserProfile profile = userProfileService.getProfileByUserId(userId);
// //     if (profile == null) {
// //         return ResponseEntity.notFound().build(); // 404 if not exists
// //     }
// //     return ResponseEntity.ok(profile);
// // }

// // }
// package com.example.demo.controller;

// import com.example.demo.model.UserProfile;
// import com.example.demo.service.UserProfileService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/profiles")
// @CrossOrigin(origins = "http://localhost:3000")
// public class UserProfileController {

//     private final UserProfileService service;

//     public UserProfileController(UserProfileService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile) {
//         return ResponseEntity.ok(service.createProfile(profile));
//     }

//     @PutMapping("/{userId}")
//     public ResponseEntity<UserProfile> updateProfile(@PathVariable Long userId,
//                                                      @RequestBody UserProfile profile) {
//         return ResponseEntity.ok(service.updateProfile(userId, profile));
//     }

//     @GetMapping("/{userId}")
//     public ResponseEntity<UserProfile> getProfile(@PathVariable Long userId) {
//         UserProfile profile = service.getProfile(userId);
//         if (profile == null) return ResponseEntity.notFound().build();
//         return ResponseEntity.ok(profile);
//     }

//     @DeleteMapping("/{userId}")
//     public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
//         service.deleteProfile(userId);
//         return ResponseEntity.noContent().build();
//     }
// }
// UserProfileController.java (Controller)
// package com.example.demo.controller;

// import com.example.demo.model.UserProfile;
// import com.example.demo.service.UserProfileService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/user-profiles")
// public class UserProfileController {
//     private final UserProfileService service;

//     public UserProfileController(UserProfileService service) {
//         this.service = service;
//     }

//     @GetMapping
//     public List<UserProfile> getAllUserProfiles() {
//         return service.getAllUserProfiles();
//     }

//     @GetMapping("/{id}")
//     public UserProfile getUserProfile(@PathVariable Long id) {
//         return service.getUserProfile(id);
//     }

//     @PostMapping
//     public UserProfile createUserProfile(@RequestBody UserProfile profile) {
//         return service.saveUserProfile(profile);
//     }

//     @PutMapping("/{id}")
//     public UserProfile updateUserProfile(@PathVariable Long id, @RequestBody UserProfile profile) {
//         profile.setProfileId(id);
//         return service.saveUserProfile(profile);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteUserProfile(@PathVariable Long id) {
//         service.deleteUserProfile(id);
//     }
//     // ✅ New endpoint to fetch profile by userId
//     // @GetMapping("/by-user/{userId}")
//     // public UserProfile getProfileByUserId(@PathVariable Long userId) {
//     //     UserProfile profile = service.getUserProfileByUserId(userId);
//     //     if (profile == null) {
//     //         throw new RuntimeException("Profile not found for userId " + userId);
//     //     }
//     //     return profile;
//     // }
// //     @GetMapping("/by-user/{userId}")
// // public UserProfile getProfileByUserId(@PathVariable Long userId) {
// //     return service.getAllUserProfiles()
// //                   .stream()
// //                   .filter(p -> p.getUserId().equals(userId))
// //                   .findFirst()
// //                   .orElse(null);
// // }
// @GetMapping("/by-user/{userId}")
// public UserProfile getProfileByUserId(@PathVariable Long userId) {
//     UserProfile profile = service.getUserProfileByUserId(userId);
//     if (profile == null) {
//         throw new RuntimeException("Profile not found for userId " + userId);
//     }
//     return profile;
// }

// }
package com.example.demo.controller;

import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile profile) {
        return profileService.saveUserProfile(profile);
    }

    @PutMapping("/{id}")
    public UserProfile updateUserProfile(@PathVariable Long id, @RequestBody UserProfile profile) {
        profile.setProfileId(id);
        return profileService.saveUserProfile(profile);
    }

    @GetMapping("/by-user/{userId}")
    public UserProfile getProfileByUserId(@PathVariable Long userId) {
        UserProfile profile = profileService.getUserProfileByUserId(userId);
        if (profile == null) {
            throw new RuntimeException("Profile not found for userId " + userId);
        }
        return profile;
    }
}
