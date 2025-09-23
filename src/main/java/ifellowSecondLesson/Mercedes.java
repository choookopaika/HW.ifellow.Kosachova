package ifellowSecondLesson;

public class Mercedes extends Car {
    Mercedes(String modelName, int yearOfIssue, String color, String gearbox, String engineType, double engineDisplacement){
        this.brand = "ifellowSecondLesson.Mercedes";
        this.modelName = modelName;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
        this.gearbox = gearbox;
        this.engineType = engineType;
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public void specialFeature() {
        System.out.println("Особенности ifellowSecondLesson.Mercedes-Benz включают в себя премиальное качество, роскошный дизайн, высокую производительность, передовые технологии (такие как система MBUX, системы помощи водителю и светодиодные фары), комфорт и безопасность");
    }
}
