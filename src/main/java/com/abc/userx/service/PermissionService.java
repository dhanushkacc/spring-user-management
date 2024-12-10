package com.abc.userx.service;

import com.abc.userx.entity.Permission;
import com.abc.userx.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission addPermission(Permission permission) {
        if (permission.getName() == null || permission.getName().isBlank()) {
            throw new RuntimeException("Permission name must not be empty");
        }
        if (permissionRepository.findByName(permission.getName()).isPresent()) {
            throw new RuntimeException("Permission with this name already exists");
        }
        return permissionRepository.save(permission);
    }

    public Permission getPermissionById(Integer permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    public void deletePermission(Integer permissionId) {
        if (!permissionRepository.existsById(permissionId)) {
            throw new RuntimeException("Permission not found");
        }
        permissionRepository.deleteById(permissionId);
    }
}
