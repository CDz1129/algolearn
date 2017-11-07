package cn.cdz.java8.bean;

/**
 * User: Cdz
 * Date: 2017/5/27
 * Time: 11:38
 * 交易员bean
 */
public class Trader {
    private final String name;
    private final String city;

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {

        return name;
    }

    public String getCity() {
        return city;
    }
}
