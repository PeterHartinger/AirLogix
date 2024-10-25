//package test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import com.flightDomain.flightbook.models.Airplane;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class AirplaneManagerTest {
//
//    private Airplane airplane;
//
//    @BeforeEach
//    public void setUp() {
//        airplane = new Airplane(123, "Boeing 737", "N12345", "Turbofan");
//    }
//
//    @Test
//    public void testGetPlaneID() {
//        assertEquals(123, airplane.getPlaneID());
//    }
//
//    @Test
//    public void testSetPlaneID() {
//        airplane.setPlaneID(456);
//        assertEquals(456, airplane.getPlaneID());
//    }
//
//    @Test
//    public void testGetType() {
//        assertEquals("Boeing 737", airplane.getType());
//    }
//
//    @Test
//    public void testSetType() {
//        airplane.setType("Airbus A320");
//        assertEquals("Airbus A320", airplane.getType());
//    }
//
//    @Test
//    public void testGetRegistration() {
//        assertEquals("N12345", airplane.getRegistration());
//    }
//
//    @Test
//    public void testSetRegistration() {
//        airplane.setRegistration("N54321");
//        assertEquals("N54321", airplane.getRegistration());
//    }
//
//    @Test
//    public void testGetEngineType() {
//        assertEquals("Turbofan", airplane.getEngineType());
//    }
//
//    @Test
//    public void testSetEngineType() {
//        airplane.setEngineType("Turboprop");
//        assertEquals("Turboprop", airplane.getEngineType());
//    }
//
//    @Test
//    public void testPlaneIDProperty() {
//        assertNotNull(airplane.planeIDProperty());
//        assertEquals(123, airplane.planeIDProperty().get());
//    }
//
//    @Test
//    public void testTypeProperty() {
//        assertNotNull(airplane.typeProperty());
//        assertEquals("Boeing 737", airplane.typeProperty().get());
//    }
//
//    @Test
//    public void testRegistrationProperty() {
//        assertNotNull(airplane.registrationProperty());
//        assertEquals("N12345", airplane.registrationProperty().get());
//    }
//
//    @Test
//    public void testEngineTypeProperty() {
//        assertNotNull(airplane.engineTypeProperty());
//        assertEquals("Turbofan", airplane.engineTypeProperty().get());
//    }
//
//    @Test
//    public void testToString() {
//        String expected = "Airplane{planeId=123, type=Boeing 737, registration=N12345, engineType=Turbofan}";
//        assertEquals(expected, airplane.toString());
//    }
//}
