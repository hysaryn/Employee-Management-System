package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;
  private RestTemplate restTemplate;
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

  @Override
  public APIResponseDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).get();

    ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
        DepartmentDto.class);

    DepartmentDto departmentDto = responseEntity.getBody();

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
