import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 这是一个对 SegmentSubcommand 构造函数进行测试的 JUnit 5 测试类。
 */
public class SegmentSubcommandTest {

    /**
     * 测试 TC_SS_VALID_01:
     * 测试当所有输入参数都完全有效时，构造函数能否成功创建一个对象实例。
     */
    @Test
    void testConstructor_whenAllInputsAreValid_shouldCreateObjectSuccessfully() {
        // 1. 准备 (Arrange) - 定义所有有效的输入值
        String origin = "PER";
        String destination = "SYD";
        String flightNumber = "QF580";
        String departureDate = "2025-11-20"; // 确保这是一个未来的日期
        char cabinType = 'Y';
        int numPeople = 2;

        // 2. 行动 (Act) & 3. 断言 (Assert) - 执行构造函数并断言它不会抛出任何异常
        assertDoesNotThrow(() -> {
            SegmentSubcommand segment = new SegmentSubcommand(origin, destination, flightNumber, departureDate, cabinType, numPeople);
            // 额外断言：确保返回的对象不是 null
            assertNotNull(segment, "The created object should not be null.");
        }, "Constructor should not throw an exception with valid inputs.");
    }

    /**
     * 测试 TC_SS_INVALID_NUM_PEOPLE_01:
     * 测试当预订人数 (NUM_PEOPLE) 超过允许的最大值10时，是否会抛出 SemanticError。
     */
    @Test
    void testConstructor_whenNumPeopleIsOutOfRange_shouldThrowSemanticError() {
        // 1. 准备 (Arrange)
        String origin = "PER";
        String destination = "SYD";
        String flightNumber = "QF580";
        String departureDate = "2025-11-20";
        char cabinType = 'Y';
        int invalidNumPeople = 11; // 无效的输入

        // 2. 行动 (Act) & 3. 断言 (Assert) - 断言执行构造函数时会抛出指定的异常
        assertThrows(SemanticError.class, () -> {
            new SegmentSubcommand(origin, destination, flightNumber, departureDate, cabinType, invalidNumPeople);
        }, "Should throw SemanticError when number of people is greater than 10.");
    }

    /**
     * 测试 TC_SS_INVALID_DATE_01:
     * 测试当出发日期 (DEPARTURE_DATE) 是一个过去的时间点时，是否会抛出 SemanticError。
     */
    @Test
    void testConstructor_whenDateIsInThePast_shouldThrowSemanticError() {
        // 1. 准备 (Arrange)
        String origin = "PER";
        String destination = "SYD";
        String flightNumber = "QF580";
        String invalidDepartureDate = "2024-10-07"; // 无效的输入
        char cabinType = 'Y';
        int numPeople = 2;

        // 2. 行动 (Act) & 3. 断言 (Assert)
        assertThrows(SemanticError.class, () -> {
            new SegmentSubcommand(origin, destination, flightNumber, invalidDepartureDate, cabinType, numPeople);
        }, "Should throw SemanticError when the departure date is in the past.");
    }

    /**
     * 测试 TC_SS_INVALID_FLIGHT_NUMBER_01:
     * 测试当航班号 (FLIGHT_NUMBER) 格式不正确时，是否会抛出 SyntacticError。
     */
    @Test
    void testConstructor_whenFlightNumberIsInvalidFormat_shouldThrowSyntacticError() {
        // 1. 准备 (Arrange)
        String origin = "PER";
        String destination = "SYD";
        String invalidFlightNumber = "QF12345"; // 无效的输入
        String departureDate = "2025-11-20";
        char cabinType = 'Y';
        int numPeople = 2;

        // 2. 行动 (Act) & 3. 断言 (Assert)
        assertThrows(SyntacticError.class, () -> {
            new SegmentSubcommand(origin, destination, invalidFlightNumber, departureDate, cabinType, numPeople);
        }, "Should throw SyntacticError for an invalid flight number format.");
    }
}
    