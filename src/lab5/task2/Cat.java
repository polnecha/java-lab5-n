package lab5.task2;

public class Cat implements Meower {
    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    // Дополнительный метод из задачи 2.5.4 — мяукать N раз
    public void meow(int n) {
        if (n <= 0) return;
        StringBuilder sb = new StringBuilder(name + ": ");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(" ");
            sb.append("мяу");
        }
        sb.append("!");
        System.out.println(sb);
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }
}