import java.util.*;
public class Main {
    public static void main(String[] args) {
        DetailManager detailManager = new DetailManager();

        // Добавляем несколько деталей
        detailManager.addDetail(new Bolt("шестиугольник", "сталь", 0.05, 5, "M6"));
        detailManager.addDetail(new Bolt("квадрат", "карбон", 0.1, 10, "M8"));
        detailManager.addDetail(new Bolt("шестиугольник", "аллюминий", 0.02, 3, "M4"));
        detailManager.addDetail(new Nut("шестиугольник", "пластик", 0.01, 2, "M3"));
        detailManager.addDetail(new Nut("квадрат", "дерево", 0.02, 4, "M5"));
        detailManager.addDetail(new Screw("шестиугольник", "карбон", 0.03, 3, "M4", 10));
        detailManager.addDetail(new Screw("квадрат", "сталь", 0.04, 4, "M5", 12));
        detailManager.addDetail(new Screw("овал", "пластик", 0.02, 2, "M3", 8));
        detailManager.addDetail(new Screw("овал", "кaкарбон", 0.01, 1, "M2", 6));
        detailManager.addDetail(new Screw("Шар", "сталь", 0.05, 5, "M6", 15));

        // Выводим общее количество деталей
        System.out.println("Общее количество деталей: " + detailManager.getDetails().size() + " шт.\n");

        // Выводим общий вес деталей каждой формы
        detailManager.overallWeight();

        // Находим уникальные детали по форме
        detailManager.findUniqueDetails();

        // Удаляем первую деталь
        detailManager.removeDetail(detailManager.getDetails().get(0));

        // Выводим общее количество деталей после удаления
        System.out.println("Количество деталей после удаления: " + detailManager.getDetails().size() + " шт.\n");

        // Выводим общий вес деталей каждой формы после удаления
        detailManager.overallWeight();

        // Находим уникальные детали по форме после удаления
        detailManager.findUniqueDetails();

        // Обновляем первую деталь
        Detail oldDetail = detailManager.getDetails().get(0);
        Detail newDetail = new Bolt("круг", "аллюминий", 0.03, 3, "M4");
        detailManager.updateDetail(oldDetail, newDetail);

        // Выводим общее количество деталей после обновления
        System.out.println("Количество деталей после обновления: " + detailManager.getDetails().size() + " шт.\n");

        // Выводим общий вес деталей каждой формы после обновления
        detailManager.overallWeight();

        // Находим уникальные детали по форме после обновления
        detailManager.findUniqueDetails();
    }
}


