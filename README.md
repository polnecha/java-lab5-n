# Нечаева Полина ИТ-3,4 — Лабораторная работа №5

## Задание 1

### Задача 1. Шаблоны
#### Текст задачи
В	класс	Дробь,	добавить	интерфейс	на	два	метода:	получение	вещественного	значения,	установка
числителя	и	установка	знаменателя.
Сгенерировать	такую	версию	дроби,	которая	будет	кэшировать	вычисление	вещественного
значения.
Если	раннее	в	вашем	варианте	не	было	Дроби,	то	создайте	сущность Дробь	со	следующими
особенностями:

- Имеет	числитель:	целое	число.
- Имеет	знаменатель:	целое	число.
- Дробь	может	быть	создана	с	указанием	числителя	и	знаменателя.
- Может	вернуть	строковое	представление	вида	“числитель/знаменатель”.
- Необходимо корректно обрабатывать отрицательные значения. Учтите, что знаменатель не может
  быть отрицательным.
- Переопределите	метод	сравнения	объектов	по	состоянию	таким	образом,	чтобы	две	дроби
  считались	одинаковыми	тогда,	когда	у	них	одинаковые	значения	числителя	и	знаменателя.
#### Алгоритм решения

```java
// Интерфейс
interface lab5.task1.Fractionable {
    double getValue();
    void setNum(int num);
    void setDen(int den);
}

// Обычная дробь
class lab5.task1.Fraction implements lab5.task1.Fractionable {
    protected int num, den;

    public lab5.task1.Fraction(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator can't be zero");
        if (den < 0) { num = -num; den = -den; }
        this.num = num;
        this.den = den;
    }

    @Override
    public double getValue() {
        return (double) num / den;
    }

    @Override
    public void setNum(int num) { this.num = num; }

    @Override
    public void setDen(int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator can't be zero");
        if (den < 0) { this.num = -this.num; den = -den; }
        this.den = den;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof lab5.task1.Fraction f)) return false;
        return num == f.num && den == f.den;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(num) * 31 + Integer.hashCode(den);
    }
}

// Кэшированная дробь
class lab5.task1.CachedFraction implements lab5.task1.Fractionable {
    private int num, den;
    private Double cache = null;

    public lab5.task1.CachedFraction(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator can't be zero");
        if (den < 0) { num = -num; den = -den; }
        this.num = num;
        this.den = den;
    }

    @Override
    public double getValue() {
        if (cache == null) cache = (double) num / den;
        return cache;
    }

    @Override
    public void setNum(int num) {
        this.num = num;
        cache = null;
    }

    @Override
    public void setDen(int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator can't be zero");
        if (den < 0) { this.num = -this.num; den = -den; }
        this.den = den;
        cache = null;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof lab5.task1.CachedFraction f)) return false;
        return num == f.num && den == f.den;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(num) * 31 + Integer.hashCode(den);
    }
}
```


## Задание 2. Структурные	шаблоны

### Задача 1
#### Текст задачи
Количество мяуканий.  
Необходимо воспользоваться классом Кот и методом принимающим всех мяукающих из задачи 2.5.4.
Необходимо таким образом передать кота в указанный метод, что бы после окончания его работы
узнать сколько раз мяукал кот за время его работы. На рисунке показан пример работы. Перед вызовом
метода создаем кота, отправляем ссылку на кота в метод, после окончания его работы выводим
количество мяуканий на экран. Кота изменять нельзя.

Если	раннее	в	вашем	варианте	не	было	Кота,	то	создайте
1. сущность	Кот,	которая	описывается	следующим	образом:
   -  Имеет	Имя	(строка)
   -  Для	создания	необходимо	указать	имя	кота.
   -  Может	быть	приведен	к	текстовой	форме	вида:	“кот:	Имя”
   - Может	помяукать,	что	приводит	к	выводу	на	экран	следующего	текста:	“Имя:	мяу!”,
   вызвать	мяуканье	можно	без	параметров.
2. интерфейс	Мяуканье: разработайте метод, который принимает набор объектов способных
   мяукать и вызывает мяуканье у каждого объекта. Мяукающие объекты должны иметь метод со
   следующей сигнатурой: public void meow();


#### Алгоритм решения
```java
// Интерфейс "мяукающего"
interface lab5.task2.Meower {
    void meow();
}

// Класс Кот
class lab5.task2.Cat implements lab5.task2.Meower {
    private final String name;
    public lab5.task2.Cat(String name) {
        this.name = name;
    }
    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }
    // Метод из задачи 2.5.4 (опционально)
    public void meow(int n) {
        if (n <= 0) return;
        System.out.print(name + ": ");
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print("мяу");
        }
        System.out.println("!");
    }
    @Override
    public String toString() {
        return "кот: " + name;
    }
}

// Обёртка со счётчиком (структурный шаблон Decorator)
class lab5.task2.CountingMeower implements lab5.task2.Meower {
    private final lab5.task2.Meower original;
    private int count = 0;
    public lab5.task2.CountingMeower(lab5.task2.Meower original) {
        this.original = original;
    }
    @Override
    public void meow() {
        count++;
        original.meow();
    }
    public int getMeowCount() {
        return count;
    }
}

// Внешний метод, который "обрабатывает" мяукающих
class lab5.task2.ProcessUtils {
    public static void processMeowers(lab5.task2.Meower[] meowers) {
        for (lab5.task2.Meower m : meowers) {
            m.meow();
        }
    }
}

// Демонстрация
class Main {
    public static void main(String[] args) {
        // Демо задачи 2.5.4
        lab5.task2.Cat barsik = new lab5.task2.Cat("Барсик");
        barsik.meow();    // 1 раз
        barsik.meow(3);   // 3 раза

        // Подсчёт мяуканий
        lab5.task2.Cat cat = new lab5.task2.Cat("Мурзик");
        lab5.task2.CountingMeower counted = new lab5.task2.CountingMeower(cat);
        lab5.task2.ProcessUtils.processMeowers(new lab5.task2.Meower[]{counted});
        System.out.println("Кот мяукнул " + counted.getMeowCount() + " раз(а).");
    }
}
```

## Задание 3. Список

### Задача 8
#### Текст задачи
Составить	программу,	которая	формирует	список	L,	включив	в	него	по	одному	разу	элементы,
которые	входят	в	список	L1,	но	не	входят	в	список	L2.

#### Алгоритм решения
```java
import java.util.*;

public class ListDifference {
    // Формирует список L: элементы из L1, которых нет в L2 (без дубликатов, порядок как в L1)
    public static <T> List<T> getUniqueDifference(List<T> L1, List<T> L2) {
        Set<T> setL2 = new HashSet<>(L2);
        Set<T> seen = new HashSet<>();
        List<T> L = new ArrayList<>();
        for (T item : L1) {
            if (!setL2.contains(item) && seen.add(item)) {
                L.add(item);
            }
        }
        return L;
    }

    // Демонстрация
    public static void main(String[] args) {
        List<Integer> L1 = Arrays.asList(1, 2, 3, 2, 4, 5);
        List<Integer> L2 = Arrays.asList(2, 4, 6);
        List<Integer> L = getUniqueDifference(L1, L2);
        System.out.println("L1: " + L1);
        System.out.println("L2: " + L2);
        System.out.println("L (результат): " + L); // [1, 3, 5]
    }
}
```



## Задание 4. Мап

### Задача 9
#### Текст задачи
Имеется	список	учеников	разных	школ,	сдававших	экзамен	по	информатике,	с	указанием	их
фамилии,	имени,	школы	и	набранного	балла.	Напишите	программу,	которая	будет	определять
номера	школ,	в	которых	средний	балл	выше,	чем	средний	по	району.	Известно,	что	информатику
сдавали	не	менее	5	учеников.		
На	вход	программе	в	первой	строке	подается	количество	учеников	списке	N.	В	каждой	из
последующих	N	строк	находится	информация	в	следующем	формате:
<Фамилия><Имя><Школа><Балл>, где	<Фамилия>	–	строка,	состоящая	не	более,	чем	из	20	символов	без	пробелов,	<Имя>–	строка,
состоящая	не	более,	чем	из	20	символов	без	пробелов,	<Школа>	–	целое	число	от	1	до	99,	<Балл>	–
целое	число	от	1	до	100.
Пример	входной	строки: Иванов	Сергей	50	87
Пример	выходных	данных,	когда	найдено	три	школы: 50	87	23
Пример	вывода	в	том	случае,	когда	найдена	одна	школа: 18

#### Алгоритм решения
```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine().trim());
        
        long totalScore = 0;
        Map<Integer, Integer> schoolSum = new HashMap<>();
        Map<Integer, Integer> schoolCount = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String[] p = reader.readLine().split("\\s+");
            int school = Integer.parseInt(p[2]);
            int score = Integer.parseInt(p[3]);
            
            totalScore += score;
            schoolSum.merge(school, score, Integer::sum);
            schoolCount.merge(school, 1, Integer::sum);
        }
        
        double avg = (double) totalScore / n;
        List<Integer> res = new ArrayList<>();
        
        for (int school : schoolSum.keySet()) {
            double sAvg = (double) schoolSum.get(school) / schoolCount.get(school);
            if (sAvg > avg) res.add(school);
        }
        
        Collections.sort(res);
        PrintWriter out = new PrintWriter("output.txt");
        for (int i = 0; i < res.size(); i++) {
            if (i > 0) out.print(" ");
            out.print(res.get(i));
        }
        out.close();
    }
}
```



## Задание 5. Сет

### Задача 10
#### Текст задачи
Файл	содержит	текст	на	русском	языке.	Какие	символы	встречаются	в	одном	и	только	в	одном
слове?

#### Алгоритм решения
```java
import java.io.*;
import java.util.*;

public class UniqueCharFinder {
    public static void main(String[] args) throws Exception {
        String text = new String(Files.readAllBytes(Paths.get("text.txt")), "UTF-8");
        String[] words = text.toLowerCase().split("[^а-яё]+");
        
        Map<Character, Integer> charCount = new HashMap<>();
        
        for (String word : words) {
            if (word.isEmpty()) continue;
            Set<Character> unique = new HashSet<>();
            for (char c : word.toCharArray()) {
                if (c >= 'а' && c <= 'я' || c == 'ё') {
                    unique.add(c);
                }
            }
            for (char c : unique) {
                charCount.merge(c, 1, Integer::sum);
            }
        }
        
        List<Character> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : charCount.entrySet()) {
            if (e.getValue() == 1) result.add(e.getKey());
        }
        Collections.sort(result);
        
        PrintWriter out = new PrintWriter("output.txt", "UTF-8");
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) out.print(" ");
            out.print(result.get(i));
        }
        out.close();
    }
}
```

## Задание 6. Очередь

### Задача 5
#### Текст задачи
Переписать	элементы	из	очереди	L1	в	очередь	L2	в	обратном	порядке.


#### Алгоритм решения
```java

```

## Задание 7. Стрим

### Задача 1
#### Текст задачи
Необходимо	написать	стрим:
Дан	набор	объектов	типа	lab5.task7.Point,	необходимо	взять	все	lab5.task7.Point	в	разных	координатах,	убрать	с
одинаковыми	X,Y,	отсортировать	по	X,	отрицательные	Y	сделать	положительными	и	собрать	это
все	в	ломаную	(объект	типа	lab5.task7.Polyline)
Если	раннее	в	вашем	варианте	не	было	задание	с	классом	lab5.task7.Point	и	lab5.task7.Polyline,	то	написать	их:
1. класс	lab5.task7.Point:
    - Координата	Х:	число.			
    - Координата	Y:	число.
    - Может	возвращать	текстовое	представление	вида	“{X;Y}”.
2. класс	lab5.task7.Line	(Линия),	расположенная	на	двумерной	плоскости,	которая	описывается:
    - Координата	начала:	Точка
    - Координата	конца:	Точка
    - Может	возвращать	текстовое	представление	вида	“Линия	от	{X1;Y1}	до	{X2;Y2}”
3. класс	lab5.task7.Polyline	(Ломаная),	которая	будет	представлять	собой	ломаную	линию.	Ломаная
   линия	представляет	собой	набор	следующих	характеристик:			
    - Имеет	массив	Точек,	через	которые	линия	проходит.
    - Может	быть	приведена	к	строковой	форме	вида	“Линия	[Т1,T2,…,TN]”,	где	TN	–	это
   результат	приведения	к	строке	Точки	с	номером	N


#### Алгоритм решения
```java
import java.util.*;
import java.util.stream.Collectors;

// Класс lab5.task7.Point
class lab5.task7.Point {
    double x, y;
    lab5.task7.Point(double x, double y) { this.x = x; this.y = y; }
    @Override public String toString() { return "{" + x + ";" + y + "}"; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof lab5.task7.Point p)) return false;
        return Double.compare(p.x, x) == 0 && Double.compare(p.y, y) == 0;
    }
    @Override public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y);
    }
}

// Класс lab5.task7.Line (требуется по условию)
class lab5.task7.Line {
    lab5.task7.Point start, end;
    lab5.task7.Line(lab5.task7.Point start, lab5.task7.Point end) { this.start = start; this.end = end; }
    @Override public String toString() {
        return "Линия от " + start + " до " + end;
    }
}

// Класс lab5.task7.Polyline
class lab5.task7.Polyline {
    List<lab5.task7.Point> points;
    lab5.task7.Polyline(List<lab5.task7.Point> points) { this.points = points; }
    @Override public String toString() {
        return "Линия " + points.toString();
    }
}

// Демонстрация стрима
class Main {
    public static void main(String[] args) {
        List<lab5.task7.Point> input = Arrays.asList(
            new lab5.task7.Point(1, -2),
            new lab5.task7.Point(3, 4),
            new lab5.task7.Point(1, -2),
            new lab5.task7.Point(0, -5),
            new lab5.task7.Point(-1, -1)
        );

        lab5.task7.Polyline result = new lab5.task7.Polyline(
            input.stream()
                .distinct()
                .sorted(Comparator.comparing(p -> p.x))
                .map(p -> new lab5.task7.Point(p.x, Math.abs(p.y)))
                .collect(Collectors.toList())
        );

        System.out.println(result);
        // Вывод: Линия [{-1.0;1.0}, {0.0;5.0}, {1.0;2.0}, {3.0;4.0}]
    }
}
```

### Задача 2
#### Текст задачи
Дан	текстовый	файл	со	строками,	содержащими	имя	человека	и	его	номер	в	следующей	форме:
  - Вася:5
  -Петя:3
  -Аня:5.
Номера	людей	могут	повторяться.	У	каких-то	людей	может	не	быть	номера.
Необходимо	написать	стрим	выполняющую	следующее: читаются	все	люди	из	файла,	все	имена	приводится	к	нижнему	регистру,	но	с	первой	буквой	в
верхнем	регистре,	убираем	из	перечня	всех	людей	без	номеров,	а	имена	оставшихся	группируются
по	их	номеру:
[5:[Вася,	Аня],	3:[Петя]]

#### Алгоритм решения
```java
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class GroupPeople {
    public static void main(String[] args) throws Exception {
        Map<Integer, List<String>> result = Files.lines(Paths.get("people.txt"))
            .filter(line -> line.contains(":"))
            .map(line -> line.split(":", 2))
            .filter(parts -> parts.length == 2 && !parts[1].trim().isEmpty() && parts[1].trim().matches("\\d+"))
            .collect(Collectors.groupingBy(
                parts -> Integer.parseInt(parts[1].trim()),
                Collectors.mapping(
                    parts -> toProperCase(parts[0].trim()),
                    Collectors.toList()
                )
            ));
        System.out.println(result);
    }

    private static String toProperCase(String name) {
        if (name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
```
