package com.hotelapp.categoryRoom.db.sql.modeldata;

import com.hotelapp.categoryRoom.dto.model.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String categoryName;
    private Double basePrice;

    public static final class CategoryDataBuilder{
        private Long idCategory;
        private String categoryName;
        private Double basePrice;

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

        public CategoryData.CategoryDataBuilder basePrice(Double basePrice){
            this.basePrice = basePrice;
            return this;
        }

        public CategoryData build(){
            CategoryData categoryData = new CategoryData();
            categoryData.setIdCategory(idCategory);
            categoryData.setCategoryName(categoryName);
            categoryData.setBasePrice(basePrice);
            return categoryData;
        }
    }
}
