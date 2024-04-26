package com.jcm.portfolio.configuration;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@MapperConfig
public class MapperConfiguration {

  @Target({ElementType.METHOD})
  @Retention(RUNTIME)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  public @interface IgnoreAuditingFieldsMapper {

  }

}
