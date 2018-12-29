package com.DTP.dailyTimePlaner.util;

public class Diagram {
    private double percentShow;
    private double percentText;
    private String categoryName;

    public Diagram() {
    }

    public Diagram(double percentShow, double percentText, String categoryName) {
        this.percentShow = percentShow;
        this.percentText = percentText;
        this.categoryName = categoryName;
    }

    public double getPercentShow() {
        return percentShow;
    }

    public void setPercentShow(double percentShow) {
        this.percentShow = percentShow;
    }

    public double getPercentText() {
        return percentText;
    }

    public void setPercentText(double percentText) {
        this.percentText = percentText;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
