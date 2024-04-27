package net.javaguides.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
  private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

  private EmployeeRepository employeeRepository;
//  private RestTemplate restTemplate;
  private WebClient webClient;
//  private APIClient apiClient;
  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = new Employee(
        employeeDto.getId(),
        employeeDto.getFirstName(),
        employeeDto.getLastName(),
        employeeDto.getEmail(),
        employeeDto.getDepartmentCode()
    );

    Employee savedEmployee = employeeRepository.save(employee);

    EmployeeDto savedEmployeeDto = new EmployeeDto(
        savedEmployee.getId(),
        savedEmployee.getFirstName(),
        savedEmployee.getLastName(),
        savedEmployee.getEmail(),
        savedEmployee.getDepartmentCode()
    );

    return savedEmployeeDto;
  }

//  @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
  @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
  @Override
  public APIResponseDto getEmployeeById(Long employeeId) {
    logger.info("inside getEmployeeById method");
    Employee employee = employeeRepository.findById(employeeId).get();

//    ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//        DepartmentDto.class);
//
//    DepartmentDto departmentDto = responseEntity.getBody();

    DepartmentDto departmentDto = webClient.get()
        .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
        .retrieve()
        .bodyToMono(DepartmentDto.class)
        .block();

//    DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

    EmployeeDto employeeDto = new EmployeeDto(
        employee.getId(),
        employee.getFirstName(),
        employee.getLastName(),
        employee.getEmail(),
        employee.getDepartmentCode()
    );

    APIResponseDto apiResponseDto = new APIResponseDto();
    apiResponseDto.setEmployee(employeeDto);
    apiResponseDto.setDepartment(departmentDto);

    return apiResponseDto;
  }

  public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
    logger.info("inside getDefaultDepartment method");
    Employee employee = employeeRepository.findById(employeeId).get();

    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentName("R&D Development");
    departmentDto.setDepartmentCode("RD0001");
    departmentDto.setDepartmentDescription("Research and Development Department");

    EmployeeDto employeeDto = new EmployeeDto(
        employee.getId(),
        employee.getFirstName(),
        employee.getLastName(),
        employee.getEmail(),
        employee.getDepartmentCode()
    );

    APIResponseDto apiResponseDto = new APIResponseDto();
    apiResponseDto.setEmployee(employeeDto);
    apiResponseDto.setDepartment(departmentDto);

    return apiResponseDto;

  }
}
