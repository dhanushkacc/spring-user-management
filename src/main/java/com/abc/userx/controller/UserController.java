package com.abc.userx.controller;

import com.abc.userx.dto.ReqRes;
import com.abc.userx.entity.OurUsers;
import com.abc.userx.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersManagementService usersManagementService;

    @GetMapping("/me")
    public ResponseEntity<ReqRes> getMyProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/me")
    public ResponseEntity<ReqRes> updateMyInfo(@RequestBody OurUsers updatedUser) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // 1. Fetch current user
        ReqRes currentUserRes = usersManagementService.getMyInfo(currentEmail);
        if (currentUserRes.getOurUsers() == null) {
            return ResponseEntity.status(404).body(currentUserRes);
        }

        OurUsers currentUser = currentUserRes.getOurUsers();
        updatedUser.setRole(currentUser.getRole());//user cant update the role

        ReqRes result = usersManagementService.updateUserProfile(currentUser.getId(), updatedUser);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
