package com.example.gamifiedassignment;

public class Country {

    private String name;
private Integer area;
private Integer pop;
private Integer gdp;
private String capital;

    public String getName() {
        return name;
    }

    public Integer getArea() {
        return area;
    }

    public Integer getPop() {
        return pop;
    }

    public Integer getGdp() {
        return gdp;
    }

    public String getCapital() {
        return capital;
    }

    public String getPrimary_language() {
        return primary_language;
    }

    public String getFlag_url() {
        return flag_url;
    }

    private String primary_language;
private String flag_url;

public Country (String name, Integer area, Integer pop, Integer gdp, String capital, String primary_language,
                String flag_url){
    this.area = area;
    this.pop = pop;
    this.gdp = gdp;
    this.capital = capital;
    this.primary_language = primary_language;
    this.flag_url = flag_url;
    this.name = name;

}




}
