package com.abc.userx.controller;

import com.abc.userx.service.RoleService;
import com.abc.userx.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> addPermissionToRole(@PathVariable Integer roleId, @PathVariable Integer permissionId) {
        var updatedRole = roleService.addPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> removePermissionFromRole(@PathVariable Integer roleId, @PathVariable Integer permissionId) {
        var updatedRole = roleService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }
}
