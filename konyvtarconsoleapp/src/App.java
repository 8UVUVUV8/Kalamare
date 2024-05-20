import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;

//import org.mariadb.jdbc.Connection;

public class App {
    public static void main(String[] args) throws Exception {

        String [] params = {"Kolcsonsesek.csv", "Kolcsonzok.csv"};

        App app = new App();

        int rowNum = app.MyFileReader(params);
        System.out.println("Sikeresen beolvasot sorok száma: "+rowNum);
    }


    public int MyFileReader(String [] params){
        //System.out.println(params[0]);

        int rowCounter = 0;
        ArrayList<Kolcsonzesek> kolcsonzesekArray = new ArrayList<Kolcsonzesek>();

        try {
            File myObj = new File(params[0]);
            Scanner myReader = new Scanner(myObj, "utf-8");
            myReader.nextLine();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              //System.out.println(data);
              rowCounter = rowCounter + 1;
              String[] arrOfStr = data.split(";");
              Kolcsonzesek kolcsonzesek = new Kolcsonzesek(Integer.parseInt(arrOfStr[0]), arrOfStr[1], arrOfStr[2], arrOfStr[3]);
                kolcsonzesekArray.add(kolcsonzesek);

            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

          System.out.println("kövi csv");
          ArrayList<Kolcsonzok> kolcsonzokArray = new ArrayList<Kolcsonzok>();

          try {
            //System.out.println( params[1]);
            File myObj = new File(params[1]);
            Scanner myReader = new Scanner(myObj, "utf-8");
            myReader.nextLine();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              rowCounter = rowCounter + 1;
              String[] arrOfStr = data.split(";");
                Kolcsonzok kolcsonzok = new Kolcsonzok(arrOfStr[0], arrOfStr[1]);
                //System.out.println(kolcsonzok.name);
              kolcsonzokArray.add(kolcsonzok);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

          uploadData(kolcsonzesekArray, kolcsonzokArray);
          return rowCounter;
    }


    public void uploadData(ArrayList<Kolcsonzesek> kolcsonzesekArray, ArrayList<Kolcsonzok> kolcsonzokArray){


        String outMessage = "";
        Connection conn = null;
        String url = "jdbc:mariadb://localhost:3306/konyvtar";
        try {
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println("Conection error");
            e.printStackTrace();
        }

        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery("ALTER TABLE kolcsonzok AUTO_INCREMENT = 1;");
            for (Kolcsonzok kolcsonzo : kolcsonzokArray) {
                String sql = "INSERT INTO kolcsonzok (nev, szuletesi_datum) VALUES ('"+kolcsonzo.name+"','"+kolcsonzo.birthdate+"');"; 
                stmt.executeQuery(sql);
            }
            outMessage = "kolcsonzok sikeresen feltöltve";

        } catch (SQLException e) {
            System.out.println("Nem sikerült végrahajtani az sql parancsot 1");
            e.printStackTrace();
        }

        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery("ALTER TABLE kolcsonzesek AUTO_INCREMENT = 1;");
            for (Kolcsonzesek kolcsonzesek : kolcsonzesekArray) {
                String sql = "INSERT INTO kolcsonzesek (kolcsonzo_id, iro, mufaj, cim) VALUES ("+kolcsonzesek.kolcsonzoId+",'"+kolcsonzesek.iro+"','"+kolcsonzesek.mufaj+"','"+kolcsonzesek.cim+"');"; 
                stmt.executeQuery(sql);
            }
            outMessage = "kolcsonzok és kolcsonzesek sikeresen feltöltve";

        } catch (SQLException e) {
            System.out.println("Nem sikerült végrahajtani az sql parancsot 2");
            e.printStackTrace();
        }

        System.out.println(outMessage);
    };
}
