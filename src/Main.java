import lab5.task1.CachedFraction;
import lab5.task1.Fraction;
import lab5.task2.Cat;
import lab5.task2.CountingMeower;
import lab5.task2.Meower;
import lab5.task2.ProcessUtils;
import lab5.task3.ListUtils;
import lab5.task6.QueueUtils;
import lab5.task7.Point;
import lab5.task7.Polyline;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n        МЕНЮ        ");
            System.out.println("1. Работа с дробями");
            System.out.println("2. Количество мяуканий");
            System.out.println("3. Элементы из L1, отсутствующие в L2");
            System.out.println("4. Анализ результатов экзамена (файл input.txt)");
            System.out.println("5. Символы, встречающиеся только в одном слове");
            System.out.println("6. Разворот очереди (L1 → L2 в обратном порядке)");
            System.out.println("7. Обработка точек");
            System.out.println("8. Группировка людей по номерам");


            System.out.println("0. Выход");
            int choice = InputUtils.readInt("Выберите пункт меню: ");

            switch (choice) {
                case 1 -> workWithFractions();
                case 2 -> workWithCats();
                case 3 -> workWithLists();
                case 4 -> processExamResults();
                case 5 -> findUniqueCharsInWords();
                case 6 -> reverseQueueDemo();
                case 7 -> workWithPoints();
                case 8 -> groupPeopleByNumber();
                case 0 -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Ошибка! Нет такого пункта меню.");
            }
        }
    }

    private static void workWithFractions() {
        System.out.println("\n--- Работа с дробями ---");
        System.out.println("1. Обычная дробь");
        System.out.println("2. Дробь с кэшированием");
        int type = InputUtils.readInt("Выберите тип дроби: ");

        int num = InputUtils.readInt("Введите числитель: ");
        int den = 0;
        while (den == 0) {
            den = InputUtils.readInt("Введите знаменатель (не 0!): ");
            if (den == 0) {
                System.out.println("Знаменатель не может быть нулём!");
            }
        }

        if (type == 1) {
            Fraction f = new Fraction(num, den);
            System.out.println("Создана дробь: " + f);
            System.out.printf("Вещественное значение: %.6f\n", f.getValue());

            System.out.println("\n--- Изменение дроби ---");
            int newNum = InputUtils.readInt("Новый числитель: ");
            int newDen = 0;
            while (newDen == 0) {
                newDen = InputUtils.readInt("Новый знаменатель (не 0!): ");
                if (newDen == 0) {
                    System.out.println("Знаменатель не может быть нулём!");
                }
            }
            f.setNum(newNum);
            f.setDen(newDen);
            System.out.println("Обновлённая дробь: " + f);
            System.out.printf("Новое значение: %.6f\n", f.getValue());

        } else if (type == 2) {
            CachedFraction cf = new CachedFraction(num, den);
            System.out.println("Создана кэшированная дробь: " + cf);
            System.out.printf("Значение (1-й вызов): %.6f\n", cf.getValue());
            System.out.printf("Значение (2-й вызов): %.6f\n", cf.getValue());

            System.out.println("\n--- Изменение дроби ---");
            int newNum = InputUtils.readInt("Новый числитель: ");
            int newDen = 0;
            while (newDen == 0) {
                newDen = InputUtils.readInt("Новый знаменатель (не 0!): ");
                if (newDen == 0) {
                    System.out.println("Знаменатель не может быть нулём!");
                }
            }
            cf.setNum(newNum);
            cf.setDen(newDen);
            System.out.println("Обновлённая дробь: " + cf);
            System.out.printf("Новое значение: %.6f\n", cf.getValue());

        } else {
            System.out.println("Неверный выбор типа дроби.");
        }
    }
    private static void workWithCats() {
        System.out.println("\n--- Задание 2: Количество мяуканий ---");

        System.out.println("Демонстрация задачи 2.5.4:");
        Cat barsik = new Cat("Барсик");
        barsik.meow();
        barsik.meow(3);

        System.out.println("\n--- Подсчёт мяуканий через обёртку ---");
        String name = InputUtils.readString("Введите имя кота: ");
        if (name.isEmpty()) {
            name = "Мурзик";
        }

        Cat cat = new Cat(name);
        CountingMeower countedCat = new CountingMeower(cat);

        System.out.println("Передаём кота в метод processMeowers...");
        ProcessUtils.processMeowers(new Meower[]{countedCat});

        System.out.println("Кот мяукнул " + countedCat.getMeowCount() + " раз(а).");
    }
    private static void workWithLists() {
        System.out.println("\n--- Задание 3: Разность списков (L1 \\ L2) ---");


        List<Integer> L1 = Arrays.asList(1, 2, 3, 2, 4, 5);
        List<Integer> L2 = Arrays.asList(2, 4, 6);
        List<Integer> L = ListUtils.differenceUnique(L1, L2);

        System.out.println("L1: " + L1);
        System.out.println("L2: " + L2);
        System.out.println("L (результат): " + L);

        System.out.println("\n--- Ручной ввод ---");
        System.out.println("Введите элементы L1 (через запятую):");
        String input1 = InputUtils.readString("");
        System.out.println("Введите элементы L2 (через запятую):");
        String input2 = InputUtils.readString("");

        List<String> list1 = parseList(input1);
        List<String> list2 = parseList(input2);
        List<String> result = ListUtils.differenceUnique(list1, list2);

        System.out.println("Ваш L1: " + list1);
        System.out.println("Ваш L2: " + list2);
        System.out.println("Результат L: " + result);
    }

    private static List<String> parseList(String input) {
        if (input.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(
                Arrays.asList(input.split("[,\\s]+"))
        );
    }

    private static void processExamResults() {
        System.out.println("\n--- Задание 4: Анализ результатов экзамена ---");
        System.out.println("Программа будет читать данные из файла 'input.txt'");
        System.out.println("и записывать результат в файл 'output.txt'.");

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {

            int n = Integer.parseInt(reader.readLine().trim());

            long totalScore = 0;
            Map<Integer, Integer> schoolSum = new HashMap<>();
            Map<Integer, Integer> schoolCount = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String[] parts = reader.readLine().split("\\s+");
                int school = Integer.parseInt(parts[2]);
                int score = Integer.parseInt(parts[3]);

                totalScore += score;
                schoolSum.merge(school, score, Integer::sum);
                schoolCount.merge(school, 1, Integer::sum);
            }

            double districtAverage = (double) totalScore / n;
            List<Integer> qualifyingSchools = new ArrayList<>();

            for (int school : schoolSum.keySet()) {
                double schoolAvg = (double) schoolSum.get(school) / schoolCount.get(school);
                if (schoolAvg > districtAverage) {
                    qualifyingSchools.add(school);
                }
            }

            qualifyingSchools.sort(Integer::compareTo);

            for (int i = 0; i < qualifyingSchools.size(); i++) {
                if (i > 0) writer.print(" ");
                writer.print(qualifyingSchools.get(i));
            }

            System.out.println("Обработка завершена. Результат записан в 'output.txt'.");

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл 'input.txt' не найден в корне проекта.");
            System.out.println("Создайте файл 'input.txt' рядом с папкой src и повторите попытку.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при чтении файла или некорректный формат данных.");
            e.printStackTrace();
        }
    }
    private static void findUniqueCharsInWords() {
        System.out.println("\n--- Задание 5: Символы, встречающиеся только в одном слове ---");
        System.out.println("Программа читает текст из файла 'text.txt'.");

        try {
            String content = new String(Files.readAllBytes(Paths.get("text.txt")), "UTF-8");


            String[] words = content.toLowerCase()
                    .split("[^а-яёa-z]+");

            List<String> validWords = new ArrayList<>();
            for (String word : words) {
                if (!word.isEmpty()) {
                    validWords.add(word);
                }
            }

            if (validWords.isEmpty()) {
                System.out.println("В файле не найдено ни одного слова.");
                return;
            }

            Map<Character, Integer> charWordCount = new HashMap<>();

            for (String word : validWords) {
                Set<Character> uniqueChars = new HashSet<>();
                for (char c : word.toCharArray()) {
                    if (isRussianLetter(c)) {
                        uniqueChars.add(c);
                    }
                }
                for (char c : uniqueChars) {
                    charWordCount.put(c, charWordCount.getOrDefault(c, 0) + 1);
                }
            }

            List<Character> result = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry : charWordCount.entrySet()) {
                if (entry.getValue() == 1) {
                    result.add(entry.getKey());
                }
            }

            Collections.sort(result);

            if (result.isEmpty()) {
                System.out.println("Нет символов, встречающихся только в одном слове.");
            } else {
                System.out.print("Символы: ");
                for (int i = 0; i < result.size(); i++) {
                    if (i > 0) System.out.print(", ");
                    System.out.print("'" + result.get(i) + "'");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Убедитесь, что файл 'text.txt' существует и содержит текст.");
        }
    }


    private static boolean isRussianLetter(char c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }
    private static void reverseQueueDemo() {
        System.out.println("\n--- Задание 6: Разворот очереди ---");

        Queue<Integer> L1 = new LinkedList<>();
        L1.offer(1);
        L1.offer(2);
        L1.offer(3);
        L1.offer(4);
        L1.offer(5);

        Queue<Integer> L2 = new LinkedList<>();

        System.out.println("Исходная очередь L1: " + L1);

        QueueUtils.reverseQueue(L1, L2);

        System.out.println("Очередь L1 после переноса: " + L1); // пустая
        System.out.println("Очередь L2 (обратный порядок): " + L2); // [5, 4, 3, 2, 1]
    }

    private static void workWithPoints() {
        System.out.println("\n--- Задание 7: Обработка точек через Stream API ---");

        List<Point> points = Arrays.asList(
                new Point(1, -2),
                new Point(3, 4),
                new Point(1, -2),
                new Point(0, -5),
                new Point(2, 3),
                new Point(0, 5),
                new Point(-1, -1)
        );

        System.out.println("Исходные точки:");
        for (Point p : points) {
            System.out.println("  " + p);
        }

        Polyline polyline = processPoints(points);

        System.out.println("\nРезультат (ломаная):");
        System.out.println(polyline);
    }

    public static Polyline processPoints(List<Point> points) {
        List<Point> result = points.stream()
                .distinct()
                .sorted(Comparator.comparing(Point::getX))
                .map(p -> new Point(p.getX(), Math.abs(p.getY())))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        return new Polyline(result);
    }

    private static void groupPeopleByNumber() {
        System.out.println("\n--- Задание 7.2: Группировка людей по номерам ---");
        System.out.println("Чтение данных из файла 'people.txt'...");

        try {
            List<String> lines = Files.readAllLines(Paths.get("people.txt"));

            Map<Integer, List<String>> result = lines.stream()
                    .filter(line -> line != null && !line.trim().isEmpty())
                    .filter(line -> line.contains(":"))
                    .map(line -> line.split(":", 2))
                    .filter(parts -> parts.length == 2 && !parts[1].trim().isEmpty())
                    .filter(parts -> parts[1].trim().matches("\\d+"))
                    .collect(Collectors.groupingBy(
                            parts -> Integer.parseInt(parts[1].trim()),
                            Collectors.mapping(
                                    parts -> formatName(parts[0].trim()),
                                    Collectors.toList()
                            )
                    ));

            System.out.println("Результат группировки:");
            System.out.println(result);

        } catch (IOException e) {
            System.out.println("Ошибка: файл 'people.txt' не найден.");
        } catch (Exception e) {
            System.out.println("Ошибка при обработке данных: " + e.getMessage());
        }
    }

    private static String formatName(String name) {
        if (name == null || name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();
    }
}