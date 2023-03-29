import java.util.*;
public class Main {
    public static void main(String[] args) {
        Detail bolt1 = new Bolt("шестиугольник", "сталь", 0.05, 5, "M6");
        Detail bolt2 = new Bolt("квадрат", "карбон", 0.1, 10, "M8");
        Detail bolt3 = new Bolt("шестиугольник", "аллюминий", 0.02, 3, "M4");
        Detail nut1 = new Nut("шестиугольник", "пластик", 0.01, 2, "M3");
        Detail nut2 = new Nut("квадрат", "дерево", 0.02, 4, "M5");
        Detail screw1 = new Screw("шестиугольник", "карбон", 0.03, 3, "M4", 10);
        Detail screw2 = new Screw("квадрат", "сталь", 0.04, 4, "M5", 12);
        Detail screw3 = new Screw("овал", "пластик", 0.02, 2, "M3", 8);
        Detail screw4 = new Screw("овал", "кaкарбон", 0.01, 1, "M2", 6);
        Detail screw5 = new Screw("Шар", "сталь", 0.05, 5, "M6", 15);

        ArrayList<Detail> details = new ArrayList<>();
        details.add(bolt1);
        details.add(bolt2);
        details.add(bolt3);
        details.add(nut1);
        details.add(nut2);
        details.add(screw1);
        details.add(screw2);
        details.add(screw3);
        details.add(screw4);
        details.add(screw5);

        System.out.println("Общее количество деталей: " + details.size() + " шт.\n");

        overallWeight(details);

        findUniqueDetails(details);

    }
    static void overallWeight(ArrayList<Detail> details) {

        Map<String, Double> totalWeightsByShape = new HashMap<>();

        for (Detail detail : details) {
            String shape = detail.getForm();
            double weight = detail.getWeight();


            double currentTotalWeight = totalWeightsByShape.getOrDefault(shape, 0.0);

            totalWeightsByShape.put(shape, currentTotalWeight + weight);
        }

        for (Map.Entry<String, Double> entry : totalWeightsByShape.entrySet()) {
            String form = entry.getKey();
            double totalWeight = entry.getValue();
            System.out.println("Обший вес деталей формы " + form + " равняется: " + totalWeight + " kg");
        }
        System.out.println("\n");
    }

    static void findUniqueDetails(List<Detail> details) {
        List<Detail> uniqueDetails = new ArrayList<>();
        for (Detail detail : details) {
            boolean isUnique = true;
            for (Detail otherDetail : details) {
                if (detail != otherDetail && detail.getForm().equals(otherDetail.getForm())) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueDetails.add(detail);
            }
        }
        for (Detail detail : uniqueDetails) {
            System.out.println("Деталь, которая отличается своей формой: " + detail.getForm());
        }
    }
}
