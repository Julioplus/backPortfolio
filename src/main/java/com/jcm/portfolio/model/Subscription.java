package com.jcm.portfolio.model;

import com.jcm.portfolio.utils.enums.RenovationEnum;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subscription")
public class Subscription extends BaseModel {

  private LocalDate startFare;
  private LocalDate endFare;
  private float price;
  private RenovationEnum renew;

}
