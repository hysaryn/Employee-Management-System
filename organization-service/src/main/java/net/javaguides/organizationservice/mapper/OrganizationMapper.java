package net.javaguides.organizationservice.mapper;

import net.javaguides.organizationservice.dto.OrganizationDto;
import net.javaguides.organizationservice.entity.Organization;

public class OrganizationMapper {
  public static OrganizationDto mapToOrganizationDto(Organization organization) {
    return new OrganizationDto(
      organization.getId(),
      organization.getOrganizationName(),
      organization.getOrganizationDescription(),
      organization.getOrganizationCode(),
      organization.getOrganizationCreatedDate()
    );
  }

  public static Organization mapToOrganization(OrganizationDto organizationDto) {
    Organization organization = new Organization();
    organization.setId(organizationDto.getId());
    organization.setOrganizationName(organizationDto.getOrganizationName());
    organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
    organization.setOrganizationCode(organizationDto.getOrganizationCode());
    organization.setOrganizationCreatedDate(organizationDto.getOrganizationCreatedDate());
    return organization;
  }

}
