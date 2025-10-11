package ifellowFifthLesson.Tests.Task1;

import ifellowFifthLesson.dto.Character;
import ifellowFifthLesson.steps.Task1.RickAndMortySteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RickAndMortyTests {

    private final Logger log = LoggerFactory.getLogger(RickAndMortyTests.class);
    private final RickAndMortySteps steps = new RickAndMortySteps();

    @Test
    @DisplayName("1 задание")
    void rickAndMortyTest() {

        String lastEpisodeUrl = steps.getMortyLastEpisode();//Получаем последний эпизод с Морти
        Character morty = steps.getCharacterByName("Morty Smith");

        String lastCharacterUrl = steps.getLastCharacterFromEpisode(lastEpisodeUrl);//Получаем последнего персонажа из последнего эпизода

        Character lastCharacter = steps.getCharacterByUrl(lastCharacterUrl);//Получаем данные персонажа из предыдущего пункта

        log.info("Персонаж: {}, Локация: {}, Раса: {}", morty.getName(), morty.getLocation().getName(), morty.getSpecies());
        log.info("Последний персонаж: {}, Локация: {}, Раса: {}", lastCharacter.getName(), lastCharacter.getLocation().getName(), lastCharacter.getSpecies());

        boolean sameSpecies = lastCharacter.getSpecies().equals(morty.getSpecies()); //Раса персонажей
        boolean sameLocation = lastCharacter.getLocation().getName().equals(morty.getLocation().getName()); //Локация персонажей

        if (sameSpecies && sameLocation) { //Проверяем расу, локацию Морти и последнего персонажа в эпизоде
            log.info("Раса и локация совпадают.");
        } else {
            log.info("Раса или локация не совпадают.");
        }
    }
}

