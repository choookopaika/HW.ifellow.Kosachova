package ifellowFourthLesson.dto;

import lombok.Data;

@Data
public class Character {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Location location;
    private Location origin;
    private String image;
    private String[] episode;
    private String url;
    private String created; }