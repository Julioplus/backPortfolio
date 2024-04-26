package com.jcm.portfolio.model;

import com.jcm.portfolio.utils.enums.PaymentType;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userApp")
public class UserApp extends BaseModel {


  private String name;
  private String lastName;
  private String birthday;
  private Set<Book> favoriteBooks;
  private Map<Long, PaymentType> paymentTypes;
  private Subscription subscription;


}
