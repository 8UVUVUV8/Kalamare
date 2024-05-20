public class Kolcsonzesek {
    Integer kolcsonzes_id;
	Integer kolcsonzo_id ;
	String iro;
	String mufaj;
    String cim;

    public Kolcsonzesek(
        Integer kolcsonzes_id,
        Integer kolcsonzo_id ,
        String iro,
        String mufaj,
        String cim
    ) {
        this.kolcsonzes_id = kolcsonzes_id;
        this.kolcsonzo_id  =kolcsonzo_id;
        this.iro =iro;
        this.mufaj =mufaj;
        this.cim =cim;
    }
}
