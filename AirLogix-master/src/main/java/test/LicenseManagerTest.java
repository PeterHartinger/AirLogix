//package test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import com.flightDomain.flightbook.models.*;
//import java.time.LocalDate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class LicenseManagerTest {
//
//    private License license;
//    private LocalDate issueDate;
//
//    @BeforeEach
//    public void setUp() {
//        issueDate = LocalDate.of(2021, 5, 20);
//        license = new License(1, "Pilot", issueDate, "12345", "FAA");
//    }
//
//    @Test
//    public void testGetLicenseID() {
//        assertEquals(1, license.getLicenseID());
//    }
//
//    @Test
//    public void testSetLicenseID() {
//        license.setLicenseID(2);
//        assertEquals(2, license.getLicenseID());
//    }
//
//    @Test
//    public void testGetType() {
//        assertEquals("Pilot", license.getType());
//    }
//
//    @Test
//    public void testSetType() {
//        license.setType("Co-Pilot");
//        assertEquals("Co-Pilot", license.getType());
//    }
//
//    @Test
//    public void testGetDateOfIssue() {
//        assertEquals(issueDate, license.getDateOfIssue());
//    }
//
//    @Test
//    public void testSetDateOfIssue() {
//        LocalDate newDate = LocalDate.of(2022, 1, 15);
//        license.setDateOfIssue(newDate);
//        assertEquals(newDate, license.getDateOfIssue());
//    }
//
//    @Test
//    public void testGetNumber() {
//        assertEquals("12345", license.getNumber());
//    }
//
//    @Test
//    public void testSetNumber() {
//        license.setNumber("54321");
//        assertEquals("54321", license.getNumber());
//    }
//
//    @Test
//    public void testGetAuthority() {
//        assertEquals("FAA", license.getAuthority());
//    }
//
//    @Test
//    public void testSetAuthority() {
//        license.setAuthority("EASA");
//        assertEquals("EASA", license.getAuthority());
//    }
//
//    @Test
//    public void testLicenseIDProperty() {
//        assertNotNull(license.licenseIDProperty());
//        assertEquals(1, license.licenseIDProperty().get());
//    }
//
//    @Test
//    public void testTypeProperty() {
//        assertNotNull(license.typeProperty());
//        assertEquals("Pilot", license.typeProperty().get());
//    }
//
//    @Test
//    public void testDateOfIssueProperty() {
//        assertNotNull(license.dateOfIssueProperty());
//        assertEquals(issueDate, license.dateOfIssueProperty().get());
//    }
//
//    @Test
//    public void testNumberProperty() {
//        assertNotNull(license.numberProperty());
//        assertEquals("12345", license.numberProperty().get());
//    }
//
//    @Test
//    public void testAuthorityProperty() {
//        assertNotNull(license.authorityProperty());
//        assertEquals("FAA", license.authorityProperty().get());
//    }
//
//    @Test
//    public void testToString() {
//        String expected = "Licence{type=Pilot, dateOfIssue=2021-05-20, number=12345, authority=FAA}";
//        assertEquals(expected, license.toString());
//    }
//}
