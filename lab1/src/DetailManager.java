

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
    public void updateDetail(Detail oldDetail, Detail newDetail) {
        int index = details.indexOf(oldDetail);
        details.set(index, newDetail);
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
    public String overallWeight() {
        Map<String, Double> totalWeightsByShape = details.stream()
                .collect(Collectors.groupingBy(Detail::getForm, Collectors.summingDouble(Detail::getWeight)));

        for (Map.Entry<String, Double> entry : totalWeightsByShape.entrySet()) {
            String form = entry.getKey();
            Double totalWeight = entry.getValue();
            String result = "Общий вес деталей формы " + form + " равняется: " + totalWeight + " kg";
            return result;
        }

        return null;
    }

    /**

     Метод для вывода деталей, которые отличаются своей формой от всех остальных деталей.
     */
    public List<Detail> findUniqueDetails() {
        return details.stream()
                .filter(detail -> details.stream()
                        .filter(otherDetail -> !detail.equals(otherDetail))
                        .noneMatch(otherDetail -> detail.getForm().equals(otherDetail.getForm())))
                .collect(Collectors.toList());
    }
}
