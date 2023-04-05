

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**

 Класс для управления списком деталей.
 */
public class DetailManager {

    private List<Detail> details;

    /**

     Конструктор класса DetailManager, инициализирующий пустой список деталей.
     */
    public DetailManager() {
        details = new ArrayList<>();
    }
    /**

     Метод для добавления детали в список деталей.
     @param detail - деталь, которую нужно добавить.
     */
    public void addDetail(Detail detail) {
        details.add(detail);
    }
    /**

     Метод для удаления детали из списка деталей.
     @param detail - деталь, которую нужно удалить.
     */
    public void removeDetail(Detail detail) {
        details.remove(detail);
    }
    /**
     * Метод для изменения детали в списке деталей.
     * @param oldDetail - деталь, которую нужно изменить.
     * @param newDetail - новая деталь, которой нужно заменить старую.
     * @return true, если удалось изменить деталь, false - в противном случае.
     */
    public boolean updateDetail(Detail oldDetail, Detail newDetail) {
        int index = details.indexOf(oldDetail);
        if (index != -1) {
            details.set(index, newDetail);
            return true;
        }
        return false;
    }

    /**

     Метод для получения списка всех деталей.
     @return список всех деталей.
     */
    public List<Detail> getDetails() {
        return details;
    }
    /**

     Метод для вывода общего веса деталей каждой формы.

     Выводится вес каждой формы и суммарный вес всех деталей этой формы.
     */
    public void overallWeight() {
        Map<String, Double> totalWeightsByShape = details.stream()
                .collect(Collectors.groupingBy(Detail::getForm, Collectors.summingDouble(Detail::getWeight)));

        totalWeightsByShape.forEach((form, totalWeight) -> {
            System.out.println("Общий вес деталей формы " + form + " равняется: " + totalWeight + " kg");
        });

        System.out.println("\n");
    }

    /**

     Метод для вывода деталей, которые отличаются своей формой от всех остальных деталей.
     */
    public void findUniqueDetails() {
        List<Detail> uniqueDetails = details.stream()
                .filter(detail -> details.stream()
                        .filter(otherDetail -> !detail.equals(otherDetail))
                        .noneMatch(otherDetail -> detail.getForm().equals(otherDetail.getForm())))
                .collect(Collectors.toList());

        uniqueDetails.forEach(detail ->
                System.out.println("Деталь, которая отличается своей формой: " + detail.getForm()));
    }
}
