package ifellowFifthLesson.Tests.Task1;

import ifellowFifthLesson.dto.Character;
import ifellowFifthLesson.steps.Task1.RickAndMortySteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RickAndMortyTests {

    private final RickAndMortySteps steps = new RickAndMortySteps();

    @Test
    @DisplayName("1 задание")
    void testMortyLastEpisodeAndCharacterData() {

        String lastEpisodeUrl = steps.getMortyLastEpisode();//Получаем последний эпизод с Морти
        Character morty = steps.getCharacterByName("Morty Smith");

        String lastCharacterUrl = steps.getLastCharacterFromEpisode(lastEpisodeUrl);//Получаем последнего персонажа из последнего эпизода

        Character lastCharacter = steps.getCharacterByUrl(lastCharacterUrl);//Получаем данные персонажа из предыдущего пункта

        System.out.println("Персонаж: " + morty.getName() + "\nЛокация: " + morty.getLocation().getName() + "\nРаса: " + morty.getSpecies());
        System.out.println("\nПоследний персонаж: " + lastCharacter.getName() + "\nЛокация: " + lastCharacter.getLocation().getName() + "\nРаса: " + lastCharacter.getSpecies());

        boolean sameSpecies = lastCharacter.getSpecies().equals(morty.getSpecies()); //Раса персонажей
        boolean sameLocation = lastCharacter.getLocation().getName().equals(morty.getLocation().getName()); //Локация персонажей

        if (sameSpecies && sameLocation) { //Проверяем расу, локацию Морти и последнего персонажа в эпизоде
            System.out.println("\nРаса и локация совпадают.");
        } else {
            System.out.println("\nРаса или локация не совпадают.");
        }
    }
}

