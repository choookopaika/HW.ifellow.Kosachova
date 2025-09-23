package ifellowSecondLesson;

public class BMW extends Car {
    BMW(String modelName, int yearOfIssue, String color, String gearbox, String engineType, double engineDisplacement){
        this.brand = "ifellowSecondLesson.BMW";
        this.modelName = modelName;
        this.yearOfIssue = yearOfIssue;
        this.color = color;
        this.gearbox = gearbox;
        this.engineType = engineType;
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public void specialFeature() {
        System.out.println("Особенности ifellowSecondLesson.BMW включают: динамику и управляемость благодаря точно настроенной подвеске и мощным двигателям, премиальный дизайн экстерьера и интерьера, обширные опции персонализации и широкий выбор моделей, а также интеллектуальные системы помощи водителю и передовые технологии, такие как электрификация и сенсорные дисплеи");
    }
}
