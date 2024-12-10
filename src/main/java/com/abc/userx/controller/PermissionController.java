package com.abc.userx.controller;

import com.abc.userx.entity.Permission;
import com.abc.userx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @PostMapping
    public ResponseEntity<?> addPermission(@RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.addPermission(permission));
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> deletePermission(@PathVariable Integer permissionId) {
        permissionService.deletePermission(permissionId);
        return ResponseEntity.ok("Permission deleted successfully");
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<?> getPermissionById(@PathVariable Integer permissionId) {
        return ResponseEntity.ok(permissionService.getPermissionById(permissionId));
    }
}
