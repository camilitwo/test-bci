/*
 * Test-BCI
 * Microservicio backend para manipular información de usuarios
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package cl.bci.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PhoneDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-12-02T09:56:06.322979-03:00[America/Santiago]")
public class PhoneDTO {

  private Long idPhone;

  private String number;

  private String citycode;

  private String contrycode;
}

