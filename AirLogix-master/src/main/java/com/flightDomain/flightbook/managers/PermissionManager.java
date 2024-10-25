package com.flightDomain.flightbook.managers;

import com.flightDomain.flightbook.dao.PermissionDAO;
import com.flightDomain.flightbook.models.Permission;

import java.sql.Connection;
import java.util.List;

public class PermissionManager {
    private PermissionDAO permissionDAO;

    public PermissionManager(Connection conn) {
        this.permissionDAO = new PermissionDAO(conn);
    }

    public boolean addPermission(Permission permission) {
        return permissionDAO.insertPermission(permission);
    }

    public List<Permission> getPermissions() {
        return permissionDAO.getAllPermissions();
    }

    public boolean updatePermission(Permission permission) {
        return permissionDAO.updatePermission(permission);
    }

    public boolean deletePermission(int permissionId) {
        return permissionDAO.deletePermission(permissionId);
    }
}
