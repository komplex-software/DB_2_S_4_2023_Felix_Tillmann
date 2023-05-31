package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class HatAnsprechpartner {
    int kunde_id;
    int mitarbeiter_id;

    public HatAnsprechpartner(int kunde_id, int mitarbeiter_id) {
        this.kunde_id = kunde_id;
        this.mitarbeiter_id = mitarbeiter_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }

    public void setMitarbeiter_id(int mitarbeiter_id) {
        this.mitarbeiter_id = mitarbeiter_id;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public int getMitarbeiter_id() {
        return mitarbeiter_id;
    }

    public static ArrayList<HatAnsprechpartner> getHatAnsprechpartnerList() {
        return hatAnsprechpartnerList;
    }

    private static ArrayList<HatAnsprechpartner> hatAnsprechpartnerList = new ArrayList<>();
    public static void addHatAnsprechpartner(HatAnsprechpartner hatAnsprechpartner) {
        hatAnsprechpartnerList.add(hatAnsprechpartner);
    }
    public static void printCount() {
        System.out.println("Anzahl HatAnsprechpartner: " + hatAnsprechpartnerList.stream().count());
    }
}
