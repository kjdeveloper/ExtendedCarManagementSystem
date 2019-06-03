package jankowiak.kamil.mainmenu;

import jankowiak.kamil.mainmenu.menu.MenuService;


public class App
{
    public static void main( String[] args )
    {
        /*DataGeneratorForCarPropertiesService dataGeneratorForCarPropertiesService = new DataGeneratorForCarPropertiesService();
        var jsonFilenameSave = "C:\\Programowanie\\CarPropertiesStatisticsFinal\\persistence\\src\\main\\java\\jankowiak\\kamil\\persistence\\resources\\carPropertiesCarsList.json";
        dataGeneratorForCarPropertiesService.saveToFile(jsonFilenameSave);*/


        final var jsonFilenameFindAll = "C:\\Users\\Admin\\Desktop\\Git\\carPropertiesStatisticsModulesFinal\\persistence\\src\\main\\java\\jankowiak\\kamil\\persistence\\resources\\carPropertiesCarsList.json";
        new MenuService(jsonFilenameFindAll).service();
    }
}
