package com.hotelapp.categoryRoom.db.sql.modeldata;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    @Column(unique = true)
    private String categoryName;
    private String categoryDescription;
    private BigDecimal basePrice;

    public static final class CategoryDataBuilder{
        private Long idCategory;
        private String categoryName;
        private String categoryDescription;
        private BigDecimal basePrice;


        public CategoryDataBuilder(){

        }

        public static CategoryData.CategoryDataBuilder aCategory(){
            return new CategoryData.CategoryDataBuilder();
        }

        public CategoryData.CategoryDataBuilder idCategory(Long idCategory){
            this.idCategory = idCategory;
            return this;
        }

        public CategoryData.CategoryDataBuilder categoryName(String categoryName){
            this.categoryName = categoryName;
            return this;
        }
        public CategoryData.CategoryDataBuilder categoryDescription(String categoryDescription){
            this.categoryDescription = categoryDescription;
            return this;
        }

        public CategoryData.CategoryDataBuilder basePrice(BigDecimal basePrice){
            this.basePrice = basePrice;
            return this;
        }


        public CategoryData build(){
            CategoryData categoryData = new CategoryData();
            categoryData.setIdCategory(idCategory);
            categoryData.setCategoryName(categoryName);
            categoryData.setCategoryDescription(categoryDescription);
            categoryData.setBasePrice(basePrice);
            return categoryData;
        }
    }
}
