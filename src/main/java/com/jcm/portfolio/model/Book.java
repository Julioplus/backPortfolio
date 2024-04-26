package com.jcm.portfolio.model;

import com.jcm.portfolio.utils.enums.CategoryEnum;
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
@Document(collection = "bookApp")
public class Book extends BaseModel {


  private String name;
  private String isbn;
  private String editorial;
  private String author;
  private LocalDate publicationDate;
  private Long stock;
  private Set<CategoryEnum> category;

}
