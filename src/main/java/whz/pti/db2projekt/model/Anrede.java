package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Anrede {
    int id;
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
}
