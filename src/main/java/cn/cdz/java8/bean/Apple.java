package cn.cdz.java8.bean;

/**
 * User: Cdz
 * Date: 2017/5/23
 * Time: 10:17
 */
public class Apple {
    private String color;
    private Integer weight;

    public Apple() {
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
