public class Toyota extends Car {
    Toyota(String modelName, int yearOfIssue, String color, String gearbox, String engineType, double engineDisplacement){
        this.brand = "Toyota";
        this.modelName = modelName;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
        this.gearbox = gearbox;
        this.engineType = engineType;
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public void specialFeature() {
        System.out.println("Особенности Toyota включают: высокую надежность и долговечность, что подтверждается высокой долей старых автомобилей на дорогах, а также инновационные технологии и системы безопасности, такие как Toyota Safety Sense и круговой обзор.");
    }
}
