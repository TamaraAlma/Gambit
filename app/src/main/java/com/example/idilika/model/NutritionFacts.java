package com.example.idilika.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class NutritionFacts {
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("calories")
    @Expose
    private Double calories;
    @SerializedName("protein")
    @Expose
    private Double protein;
    @SerializedName("fat")
    @Expose
    private Double fat;
    @SerializedName("carbohydrates")
    @Expose
    private Double carbohydrates;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

}

