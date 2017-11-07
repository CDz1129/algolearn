package cn.cdz.java8.bean;

/**
 * User: Cdz
 * Date: 2017/5/24
 * Time: 15:26
 * 菜肴bean
 */
public class Dish {
    private String name;
    //是否素食
    private boolean vegetarian;
    //卡路里
    private int calories;
    private Type type;

    public Dish() {
    }

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }

    public enum Type{
        FISH,
        OTHER,
        MEAT
    }

    {
        String[] FISH = {"prawns","salmon"};
        String[] OTHER ={"french","fries","rice","season","fruit","pizza"};
        String[] MEAT = {"pork","beef","chicken"};
    }
}
