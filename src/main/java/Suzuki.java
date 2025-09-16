public class Suzuki extends Car {
    Suzuki(String modelName, int yearOfIssue, String color, String gearbox, String engineType, double engineDisplacement){
        this.brand = "Suzuki";
        this.modelName = modelName;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
        this.gearbox = gearbox;
        this.engineType = engineType;
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public void specialFeature() {
        System.out.println("Особенности автомобилей Suzuki включают в себя акцент на компактные размеры, маневренность и доступность, а также надежность, практичность и привлекательный дизайн. Бренд известен благодаря своей индивидуальности, воплощенной в пяти ключевых ценностях: Дух, Спортивность, Прямота, Ценность и Восторг");
    }
}
