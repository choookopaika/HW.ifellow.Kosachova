package ifellowFinalTaskAPISteps;

import ifellowFinalTaskAPIPages.RickAndMortyPage;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ifellowFinalTaskAPI.dto.Character;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RickAndMortySteps {

    private final Logger log = LoggerFactory.getLogger(RickAndMortySteps.class);
    private final RickAndMortyPage rickAndMortyPage = new RickAndMortyPage();

    private Character morty;
    private String lastEpisodeUrl;
    private String lastCharacterUrl;
    private Character lastCharacter;

    @Дано("персонаж по имени {string}")
    @Step("Получаем персонажа по имени {name}")
    public void getCharacterByName(String name) {
        morty = rickAndMortyPage.getCharacterByName(name);
        String info = "Персонаж: " + morty.getName() + "\nЛокация: " + morty.getLocation().getName() + "\nРаса: " + morty.getSpecies();
        log.info(info);
        Allure.addAttachment("Персонаж " + name, info);}

    @Когда("получаем персонажа Морти и последний эпизод с ним")
    @Step("Получаем персонажа Морти и последний эпизод с ним")
    public void getMortyLastEpisode() {
        lastEpisodeUrl = rickAndMortyPage.getMortyLastEpisode();
        log.info("Последний эпизод с Морти: {}", lastEpisodeUrl);
        Allure.addAttachment("Последний эпизод с Морти", lastEpisodeUrl);
    }

    @И("получаем последнего персонажа из последнего эпизода")
    @Step("Получаем последнего персонажа из последнего эпизода")
    public void getLastCharacterFromEpisode() {
        lastCharacterUrl = rickAndMortyPage.getLastCharacterFromEpisode(lastEpisodeUrl);
        lastCharacter = rickAndMortyPage.getCharacterByUrl(lastCharacterUrl);
        String info = "Последний персонаж: " + lastCharacter.getName() + "\nЛокация: " + lastCharacter.getLocation().getName() + "\nРаса: " + lastCharacter.getSpecies();
        log.info(info);
        Allure.addAttachment("Последний персонаж", info);}

    @Тогда("сравниваем расу и локацию последнего персонажа с Морти")
    @Step("Раса и локация последнего персонажа совпадают с Морти")
    public void checkSpeciesAndLocation() {
        boolean sameSpecies = lastCharacter.getSpecies().equals(morty.getSpecies());
        boolean sameLocation = lastCharacter.getLocation().getName().equals(morty.getLocation().getName());

        if (sameSpecies && sameLocation) {
            log.info("Раса и локация совпадают.");
            Allure.addAttachment("Результат сравнения: ","раса и локация совпадают.");
        } else {
            log.info("Раса или локация не совпадают.");
            Allure.addAttachment("Результат сравнения: ", "раса или локация не совпадают.");
        }
    }
}
