import java.util.ArrayList;

public class Kolcsonzok {
    Integer kolcsonzo_id;
    String nev;
    String szuletesi_datum;
    ArrayList<Kolcsonzesek> kolcsonzesek;

    public Kolcsonzok(
        Integer kolcsonzo_id,
        String nev,
        String szuletesi_datum,
        ArrayList<Kolcsonzesek> kolcsonzesek
    ) {
        this.kolcsonzo_id = kolcsonzo_id;
        this.nev = nev;
        this.szuletesi_datum = szuletesi_datum;
        this.kolcsonzesek = kolcsonzesek;
    }
}
