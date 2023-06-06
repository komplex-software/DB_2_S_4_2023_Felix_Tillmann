package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Anrede {
    int id;

    public static void clearList() {
        anredeList.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnredewort(String anredewort) {
        this.anredewort = anredewort;
    }

    public String getAnredewort() {
        return anredewort;
    }

    public static ArrayList<Anrede> getAnredeList() {
        return anredeList;
    }

    String anredewort;
    public Anrede(int id, String anredewort) {
        this.id = id;
        this.anredewort = anredewort;
    }
    private static ArrayList<Anrede> anredeList = new ArrayList<>();
    public static void addAnrede(Anrede anrede) {
        anredeList.add(anrede);
    }
    public static void printCount() {
        System.out.println("Anzahl Anrede: " + anredeList.stream().count());
    }

    @Override
    public String toString() {
        return anredewort;
    }
}
