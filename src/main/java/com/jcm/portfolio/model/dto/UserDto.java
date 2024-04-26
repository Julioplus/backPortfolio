package com.jcm.portfolio.model.dto;

import com.jcm.portfolio.model.Book;
import com.jcm.portfolio.model.Subscription;
import com.jcm.portfolio.utils.enums.PaymentType;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String id;
  private String name;
  private String lastName;
  private String birthday;
  private Set<Book> favoriteBooks;
  private Map<Long, PaymentType> paymentTypes;
  private Subscription subscription;

}
