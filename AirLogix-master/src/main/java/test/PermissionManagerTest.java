//package test;
//
//import com.flightDomain.flightbook.dao.PermissionDAO;
//import com.flightDomain.flightbook.managers.PermissionManager;
//import com.flightDomain.flightbook.models.Permission;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////public class PermissionManagerTest {
//
//    private PermissionManager permissionManager;
//    private Permission testPermission;
//
//    @BeforeEach
//    public void setUp() {
//        // Mock Connection object or initialize with real connection
//        Connection conn = null; // Replace with your actual database connection
//
//        permissionManager = new PermissionManager(conn);
//        testPermission = new Permission("TestRating", LocalDate.of(2024, 6, 25),
//                LocalDate.of(2025, 6, 25), "TestSignature");
//    }
//
//    @Test
//    public void testAddPermission() throws SQLException {
//        assertTrue(permissionManager.addPermission(testPermission));
//
//        // Check if the permission was added by retrieving all permissions and asserting the presence
//        List<Permission> permissions = permissionManager.getPermissions();
//        assertTrue(permissions.contains(testPermission));
//    }
//
//    @Test
//    public void testGetPermissions() {
//        List<Permission> permissions = permissionManager.getPermissions();
//        assertNotNull(permissions);
//        assertFalse(permissions.isEmpty());
//    }
//
//    @Test
//    public void testUpdatePermission() throws SQLException {
//        testPermission.setRating("UpdatedRating");
//
//        assertTrue(permissionManager.updatePermission(testPermission));
//
//        // Check if the permission was updated by retrieving it and asserting the updated fields
//        List<Permission> permissions = permissionManager.getPermissions();
//        Permission updatedPermission = permissions.stream()
//                .filter(p -> p.getPermissionID() == testPermission.getPermissionID())
//                .findFirst()
//                .orElse(null);
//        assertNotNull(updatedPermission);
//        assertEquals("UpdatedRating", updatedPermission.getRating());
//    }
//
//    @Test
//    public void testDeletePermission() throws SQLException {
//        assertTrue(permissionManager.deletePermission(testPermission.getPermissionID()));
//
//        // Check if the permission was deleted by retrieving all permissions and asserting absence
//        List<Permission> permissions = permissionManager.getPermissions();
//        assertFalse(permissions.contains(testPermission));
//    }
//}
