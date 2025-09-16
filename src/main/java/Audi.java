public class Audi extends Car {
    Audi(String modelName, int yearOfIssue, String color, String gearbox, String engineType, double engineDisplacement){
        this.brand = "Audi";
        this.modelName = modelName;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
        this.gearbox = gearbox;
        this.engineType = engineType;
        this.engineDisplacement = engineDisplacement;
    }
    @Override
    public void specialFeature() {
        System.out.println("Особенность автомобилей Audi заключается в их элегантном дизайне, инновационных технологиях (включая передовое освещение и интеллектуальные системы), высокой динамике и, конечно, в фирменной системе полного привода quattro, обеспечивающей превосходную управляемость и безопасность");
    }
}
