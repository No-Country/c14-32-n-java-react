package com.hotelapp.categoryRoom.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long idCategory;
    private String categoryName;
    private String categoryDescription;
    private BigDecimal basePrice;

  public static final class CategoryBuilder{
      private Long idCategory;
      private String categoryName;
      private String categoryDescription;
      private BigDecimal basePrice;

      public CategoryBuilder(){

      }

      public static CategoryBuilder aCategory(){
          return new CategoryBuilder();
      }

      public CategoryBuilder idCategory(Long idCategory){
          this.idCategory = idCategory;
          return this;
      }

      public CategoryBuilder categoryName(String categoryName){
          this.categoryName = categoryName;
          return this;
      }

      public CategoryBuilder categoryDescription(String categoryDescription){
          this.categoryDescription = categoryDescription;
          return this;
      }

      public CategoryBuilder basePrice(BigDecimal basePrice){
          this.basePrice = basePrice;
          return this;
      }

      public Category build(){
          Category category = new Category();
          category.setIdCategory(idCategory);
          category.setCategoryName(categoryName);
          category.setCategoryDescription(categoryDescription);
          category.setBasePrice(basePrice);
          return category;
      }
  }
}