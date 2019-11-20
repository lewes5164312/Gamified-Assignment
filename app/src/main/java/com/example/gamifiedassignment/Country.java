package com.example.gamifiedassignment;

public class Country {

    private Integer id;
    private String name;
private String area;
private String pop;
private String gdp;
private String capital;

    public String getDesc() {
        return desc;
    }

    private String desc;
    private String primary_language;
    private String flag_url;

    public Integer getId() {
        return id;
    }

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



public Country (Integer id, String name, String area, String pop, String gdp, String capital, String primary_language, String desc,
                String flag_url){
        this.id=id;
    this.name = name;
    this.area = area;
    this.pop = pop;
    this.gdp = gdp;
    this.capital = capital;
    this.primary_language = primary_language;
    this.desc = desc;
    this.flag_url = flag_url;


}

    public Country (String name){
        this.name = name;
    }




}
