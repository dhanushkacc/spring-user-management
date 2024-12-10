package com.abc.userx.service;

import com.abc.userx.entity.Permission;
import com.abc.userx.entity.Role;
import com.abc.userx.repository.PermissionRepository;
import com.abc.userx.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        // Validate role name
        if (role.getName() == null || role.getName().isBlank()) {
            throw new RuntimeException("Role name must not be empty");
        }
        // Check uniqueness if desired
        return roleRepository.save(role);
    }

    public void deleteRole(Integer roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(roleId);
    }

    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public Role addPermissionToRole(Integer roleId, Integer permissionId) {
        Role role = getRoleById(roleId);
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        role.getPermissions().add(permission);
        return roleRepository.save(role);
    }

    public Role removePermissionFromRole(Integer roleId, Integer permissionId) {
        Role role = getRoleById(roleId);
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        if (!role.getPermissions().contains(permission)) {
            throw new RuntimeException("Role does not have this permission");
        }
        role.getPermissions().remove(permission);
        return roleRepository.save(role);
    }
}
