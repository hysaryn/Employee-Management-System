package net.javaguides.organizationservice.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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
