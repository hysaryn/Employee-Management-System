package net.javaguides.employeeservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
  private long id;
  private String organizationName;
  private String organizationDescription;
  private String organizationCode;
  private LocalDateTime organizationCreatedDate;

}
