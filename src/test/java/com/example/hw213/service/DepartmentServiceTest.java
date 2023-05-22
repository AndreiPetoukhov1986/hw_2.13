package com.example.hw213.service;

import com.example.hw213.Employee;
import com.example.hw213.exception.EmployeeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;


    public static Stream<Arguments> maxSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 93_000),
                Arguments.of(2, 96_000),
                Arguments.of(3, 95_000));
    }

    public static Stream<Arguments> minSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 92_000),
                Arguments.of(2,  94_000),
                Arguments.of(3,  95_000)
        );
    }

    public static Stream<Arguments> printAllEmployeesForDepTestParams() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Илья Петров", 1, 92_000),
                                new Employee("Мария Петрова", 1, 93_000))
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Людмила Марьенко", 2, 94_000),
                                new Employee("Рустам Аверин", 2, 96_000))
                ),
                Arguments.of(
                        3,
                        Collections.singletonList(new Employee("Александр Марьенко", 3, 95_000))
                ),
                Arguments.of(
                        4,
                        Collections.emptyList())
        );
    }

    public static Stream<Arguments> sumSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 185_000),
                Arguments.of(2,  190_000),
                Arguments.of(3,  95_000),
                Arguments.of(4,  0)
        );
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.getAll()).thenReturn(
                List.of(
                        new Employee("Илья Петров", 1, 92_000),
                        new Employee("Мария Петрова", 1, 93_000),
                        new Employee("Людмила Марьенко", 2, 94_000),
                        new Employee("Александр Марьенко", 3, 95_000),
                        new Employee("Рустам Аверин", 2, 96_000)));
    }

    @ParameterizedTest
    @MethodSource("maxSalaryFromDepartmentTestParams")
    public void maxSalaryFromDepartmentTest(int dep, double expected) {
        Assertions.assertThat(departmentService.maxSalaryFromDepartment(dep))
                .isEqualTo(expected);
    }

    @Test
    public void maxSalaryFromDepartmentNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.maxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("minSalaryFromDepartmentTestParams")
    public void minSalaryFromDepartmentTest(int dep, double expected) {
        Assertions.assertThat(departmentService.minSalaryFromDepartment(dep))
                .isEqualTo(expected);
    }

    @Test
    public void minSalaryFromDepartmentNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.maxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("sumSalaryFromDepartmentTestParams")
    public void sumSalaryFromDepartmentTest(int dep, double expected) {
        Assertions.assertThat(departmentService.sumSalaryFromDepartment(dep))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("printAllEmployeesForDepTestParams")
    public void printAllEmployeesForDepTest(int dep, List<Employee> expected) {
        Assertions.assertThat(departmentService.printAllEmployeesForDep(dep))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void printAllEmployeesTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Илья Петров", 1, 92_000),
                        new Employee("Мария Петрова", 1, 93_000)),
                2,
                List.of(
                        new Employee("Людмила Марьенко", 2, 94_000),
                        new Employee("Рустам Аверин", 2, 96_000)),
                3,
                Collections.singletonList(
                        new Employee("Александр Марьенко", 3, 95_000)));
        Assertions.assertThat(departmentService.printAllEmployees())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}
