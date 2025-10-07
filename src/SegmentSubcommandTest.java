import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SegmentSubcommandTest {


    @Test
    void testConstructor_whenAllInputsAreValid_shouldCreateObjectSuccessfully() {
        String origin = "PER";
        String destination = "SYD";
        String flightNumber = "QF580";
        String departureDate = "2025-12-20";
        char cabinType = 'Y';
        int numPeople = 2;

        assertDoesNotThrow(() -> {
            SegmentSubcommand segment = new SegmentSubcommand(origin, destination, flightNumber, departureDate, cabinType, numPeople);
            assertNotNull(segment, "The created object should not be null.");
        }, "Constructor should not throw an exception with valid inputs.");
    }

    @Test
    void testConstructor_whenNumPeopleIsOutOfRange_shouldThrowSemanticError() {
        String origin = "PER";
        String destination = "SYD";
        String flightNumber = "QF580";
        String departureDate = "2025-12-20";
        char cabinType = 'Y';
        int invalidNumPeople = 11;

        assertThrows(SemanticError.class, () -> {
            new SegmentSubcommand(origin, destination, flightNumber, departureDate, cabinType, invalidNumPeople);
        }, "Should throw SemanticError when number of people is greater than 10.");
    }

    @Test
    void testConstructor_whenDateIsInThePast_shouldThrowSemanticError() {
        String origin = "PER";
        String destination = "SYD";
        String flightNumber = "QF580";
        String invalidDepartureDate = "2024-10-07"; // 无效的输入
        char cabinType = 'Y';
        int numPeople = 2;

        assertThrows(SemanticError.class, () -> {
            new SegmentSubcommand(origin, destination, flightNumber, invalidDepartureDate, cabinType, numPeople);
        }, "Should throw SemanticError when the departure date is in the past.");
    }

    @Test
    void testConstructor_whenFlightNumberIsInvalidFormat_shouldThrowSyntacticError() {
        String origin = "PER";
        String destination = "SYD";
        String invalidFlightNumber = "QF12345";
        String departureDate = "2025-12-20";
        char cabinType = 'Y';
        int numPeople = 2;

        assertThrows(SyntacticError.class, () -> {
            new SegmentSubcommand(origin, destination, invalidFlightNumber, departureDate, cabinType, numPeople);
        }, "Should throw SyntacticError for an invalid flight number format.");
    }
}
    