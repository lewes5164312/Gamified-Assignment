package com.example.gamifiedassignment;

public class Country {

    private String name;
private String area;
private String pop;
private String gdp;
private String capital;
    private String primary_language;
    private String flag_url;

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getPop() {
        return pop;
    }

    public String getGdp() {
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



public Country (String name, String area, String pop, String gdp, String capital,
                String flag_url){
    this.name = name;
    this.area = area;
    this.pop = pop;
    this.gdp = gdp;
    this.capital = capital;
    this.flag_url = flag_url;


}

    public Country (String name){
        this.name = name;
        area = "1000";
        pop = "4000";
        gdp = "44122";
        capital = "abcd";
        flag_url = "sfasf.com/rs.jpg";
    }




}
