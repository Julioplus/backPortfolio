package com.jcm.portfolio.model;

import java.time.LocalDate;
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
@Document(collection = "bookLoans")
public class BookLoans extends BaseModel {


  private LocalDate startDate;
  private LocalDate endDate;
  private UserApp user;
  private Set<Book> books;


}
