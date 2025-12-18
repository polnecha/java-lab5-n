package lab5.task2;

public class CountingMeower implements Meower {
    private final Meower original;
    private int meowCount = 0;

    public CountingMeower(Meower original) {
        this.original = original;
    }

    @Override
    public void meow() {
        meowCount++;
        original.meow();
    }

    public int getMeowCount() {
        return meowCount;
    }
}