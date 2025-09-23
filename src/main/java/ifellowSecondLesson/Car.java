package ifellowSecondLesson;

public abstract class Car {
    protected String brand;
    protected String modelName;
    protected int yearOfIssue;
    protected String color;
    protected String gearbox;
    protected String engineType;
    protected double engineDisplacement;

    public void getInfo(){
        System.out.println("Марка: " + brand);
        System.out.println("Модель: " + modelName);
        System.out.println("Год выпуска: " + yearOfIssue);
        System.out.println("Цвет: " + color);
        System.out.println("Коробка передач: " + gearbox);
        System.out.println("Тип двигателя: " + engineType);
        System.out.println("Объем двигателя: " + engineDisplacement);
    }

    public abstract void specialFeature();
}


