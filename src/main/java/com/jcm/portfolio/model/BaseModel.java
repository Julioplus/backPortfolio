package com.jcm.portfolio.model;

import dev.morphia.annotations.Id;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class BaseModel {

  @Id
  String id;

  @CreatedDate
  private LocalDateTime createdDate;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @LastModifiedBy
  private String lastModifiedBy;

}
