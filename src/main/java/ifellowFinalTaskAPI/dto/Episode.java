package ifellowFinalTaskAPI.dto;

import lombok.Data;

@Data
public class Episode {
    private int id;
    private String name;
    private String air_date;
    private String episode;
    private String[] characters;
    private String url;
    private String created; }