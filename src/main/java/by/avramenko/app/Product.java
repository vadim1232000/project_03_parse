package by.avramenko.app;

public class Product {
    private String scalematesUrl;
    private String brand;
    private int scale;
    private String brandCatno;
    private String name;
    private String boxartUrl;
    private String year;
    private String description;

    public Product(){}

    public String getScalematesUrl() {
        return scalematesUrl;
    }

    public void setScalematesUrl(String scalematesUrl) {
        this.scalematesUrl = scalematesUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getBrandCatno() {
        return brandCatno;
    }

    public void setBrandCatno(String brandCatno) {
        this.brandCatno = brandCatno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoxartUrl() {
        return boxartUrl;
    }

    public void setBoxartUrl(String boxartUrl) {
        this.boxartUrl = boxartUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
