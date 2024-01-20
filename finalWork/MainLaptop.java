package finalWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainLaptop {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("Lenovo", 8, 512, "Windows", "Silver");
        Laptop laptop2 = new Laptop("Dell", 16, 1024, "Windows", "Black");
        Laptop laptop3 = new Laptop("HP", 8, 256, "Linux", "Silver");
        Laptop laptop4 = new Laptop("Apple", 16, 512, "macOS", "Space Gray");
        Laptop laptop5 = new Laptop("Acer", 4, 512, "Windows", "Black");

        Set<Laptop> laptops = new HashSet<>(Arrays.asList(laptop1, laptop2, laptop3, laptop4, laptop5));

        // System.out.println(laptops);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите критерии фильтрации:");
        System.out.println("1. Фильтр по ОЗУ");
        System.out.println("2. Фильтр по объему ЖД");
        System.out.println("3. Фильтр по операционной системе");
        System.out.println("4. Фильтр по цвету");

        Map<Integer, Object> filters = new HashMap<>();

        while(true){
        System.out.print("Выберите критерий (или введите 0 для завершения): ");
        int filterType = scanner.nextInt();
        if (filterType == 0) {
            break;
        }

        Object filterValue;

        switch (filterType) {
            case 1:
                System.out.println("Выберите минимальное значение ОЗУ (в ГБ):");
                System.out.println("1. 4 ГБ");
                System.out.println("2. 8 ГБ");
                System.out.println("3. 16 ГБ");
                int ramChoice = scanner.nextInt();
                switch (ramChoice) {
                    case 1:
                        filterValue = 4;
                        break;
                    case 2:
                        filterValue = 8;
                        break;
                    case 3:
                        filterValue = 16;
                        break;
                    default:
                        System.out.println("Некорректный выбор значения.");
                        continue;
                }
                break;
            case 2:
                System.out.println("Выберите минимальный объем ЖД (в ГБ):");
                System.out.println("1. 256 ГБ");
                System.out.println("2. 512 ГБ");
                System.out.println("3. 1024 ГБ");
                int storageChoice = scanner.nextInt();
                switch (storageChoice) {
                    case 1:
                        filterValue = 256;
                        break;
                    case 2:
                        filterValue = 512;
                        break;
                    case 3:
                        filterValue = 1024;
                        break;
                    default:
                        System.out.println("Некорректный выбор значения.");
                        continue;
                }
                break;
            case 3:
                System.out.println("Выберите операционную систему:");
                System.out.println("1. Windows");
                System.out.println("2. macOS");
                System.out.println("3. Linux");
                int osChoice = scanner.nextInt();
                switch (osChoice) {
                    case 1:
                        filterValue = "Windows";
                        break;
                    case 2:
                        filterValue = "macOS";
                        break;
                    case 3:
                        filterValue = "Linux";
                        break;
                    default:
                        System.out.println("Некорректный выбор значения.");
                        continue;
                }
                break;
            case 4:
                System.out.println("Выберите цвет:");
                System.out.println("1. Black");
                System.out.println("2. Silver");
                System.out.println("3. Space Gray");
                int colorChoice = scanner.nextInt();
                switch (colorChoice) {
                    case 1:
                        filterValue = "Black";
                        break;
                    case 2:
                        filterValue = "Silver";
                        break;
                    case 3:
                        filterValue = "Space Gray";
                        break;
                    default:
                        System.out.println("Некорректный выбор значения.");
                        continue;
                }
                break;
            default:
                System.out.println("Некорректный выбор критерия.");
                continue;
        }

        filters.put(filterType, filterValue);
    }

    List<Laptop> filteredLaptops = filterLaptops(laptops, filters);

    System.out.println("Результаты фильтрации:");

    if(filteredLaptops.isEmpty())
    {
        System.out.println("Нет ноутбуков, удовлетворяющих заданным критериям.");
    }else
    {
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}

    public static List<Laptop> filterLaptops(Set<Laptop> laptops, Map<Integer, Object> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean isMatched = true;

            for (Map.Entry<Integer, Object> entry : filters.entrySet()) {
                int filterType = entry.getKey();
                Object filterValue = entry.getValue();

                switch (filterType) {
                    case 1: // Фильтрация по ОЗУ
                        int minRam = (int) filterValue;
                        if (laptop.getRam() < minRam) {
                            isMatched = false;
                        }
                        break;
                    case 2: // Фильтрация по объему ЖД
                        int minStorage = (int) filterValue;
                        if (laptop.getStorage() < minStorage) {
                            isMatched = false;
                        }
                        break;
                    case 3: // Фильтрация по операционной системе
                        String os = (String) filterValue;
                        if (!laptop.getOs().equalsIgnoreCase(os)) {
                            isMatched = false;
                        }
                        break;
                    case 4: // Фильтрация по цвету
                        String color = (String) filterValue;
                        if (!laptop.getColor().equalsIgnoreCase(color)) {
                            isMatched = false;
                        }
                        break;
                    default:
                        System.out.println("Некорректный выбор критерия.");
                        return filteredLaptops;
                }
            }
            if (isMatched) {
                filteredLaptops.add(laptop);
            }
        }
        return filteredLaptops;
    }    
}