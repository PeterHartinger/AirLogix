//package test;
//
//import com.flightDomain.flightbook.dao.UserDAO;
//import com.flightDomain.flightbook.managers.UserManager;
//import com.flightDomain.flightbook.models.User;
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
//public class UserManagerTest {
//
//    private UserManager userManager;
//    private User testUser;
//
//    @BeforeEach
//    public void setUp() {
//        // Mock Connection object or initialize with real connection
//        Connection conn = null; // Replace with your actual database connection
//
//        userManager = new UserManager(conn);
//        testUser = new User("TestUser", "123 Test St", LocalDate.of(1990, 1, 1), "TestCity", "TestCountry");
//    }
//
//    @Test
//    public void testAddUser() throws SQLException {
//        assertTrue(userManager.addUser(testUser));
//
//        // Check if the user was added by retrieving all users and asserting the presence
//        List<User> users = userManager.getUsers();
//        assertTrue(users.contains(testUser));
//    }
//
//    @Test
//    public void testGetUsers() {
//        List<User> users = userManager.getUsers();
//        assertNotNull(users);
//        assertFalse(users.isEmpty());
//    }
//
//    @Test
//    public void testUpdateUser() throws SQLException {
//        testUser.setAddress("Updated Address");
//
//        assertTrue(userManager.updateUser(testUser));
//
//        // Check if the user was updated by retrieving it and asserting the updated address
//        List<User> users = userManager.getUsers();
//        User updatedUser = users.stream()
//                .filter(u -> u.getId() == testUser.getId())
//                .findFirst()
//                .orElse(null);
//        assertNotNull(updatedUser);
//        assertEquals("Updated Address", updatedUser.getAddress());
//    }
//
//    @Test
//    public void testDeleteUser() throws SQLException {
//        assertTrue(userManager.deleteUser(testUser.getId()));
//
//        // Check if the user was deleted by retrieving all users and asserting absence
//        List<User> users = userManager.getUsers();
//        assertFalse(users.contains(testUser));
//    }
//}
