import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyConn {

    public String resp;

    public MyConn() {
        resp = conn();
        resolveResp(resp);
    }

    public String conn (){
        String resp;
        try {
            URL url = new URL("http://127.0.0.1:8000/api/Kolcsonzok");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            //con.setConnectTimeout(5000);
            //con.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            //System.out.println(in.readLine());
            resp = in.readLine();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
            resp = null;
            
        }
        return resp;
    }

    public void resolveResp (String resp){

        ArrayList<Kolcsonzesek> kolcsonzesekArr = new ArrayList<Kolcsonzesek>();
        ArrayList<Kolcsonzok> kolcsonzokArr = new ArrayList<Kolcsonzok>();

        JSONArray jsonAr = new JSONArray(resp.toString());

        for (int i = 0; i < jsonAr.length(); i++) {
            //System.out.println(jsonAr.getJSONObject(i).getString("nev") + "\n");
            JSONArray kolcsonzesek = jsonAr.getJSONObject(i).getJSONArray("kolcsonzesek");
            for (int j = 0; j < kolcsonzesek.length(); j++) {
                //System.out.println("  "+kolcsonzesek.getJSONObject(j).getString("cim")); 
                Kolcsonzesek kolcsonzes = new Kolcsonzesek(
                    kolcsonzesek.getJSONObject(j).getInt("kolcsonzes_id"),
                    kolcsonzesek.getJSONObject(j).getInt("kolcsonzo_id"), 
                    kolcsonzesek.getJSONObject(j).getString("iro"), 
                    kolcsonzesek.getJSONObject(j).getString("mufaj"), 
                    kolcsonzesek.getJSONObject(j).getString("cim")
                    ); 
                kolcsonzesekArr.add(kolcsonzes);
            }
        }

        ArrayList<Kolcsonzesek> kolcsonzoKolcsozese = new ArrayList<Kolcsonzesek>();

        for (int i = 0; i < jsonAr.length(); i++) {

            for (Kolcsonzesek kolcsonzes : kolcsonzesekArr) {
                if (kolcsonzes.kolcsonzo_id == jsonAr.getJSONObject(i).getInt("kolcsonzo_id")) {
                    //System.out.println(kolcsonzes.kolcsonzes_id + kolcsonzes.cim+jsonAr.getJSONObject(i).getString("nev"));
                    kolcsonzoKolcsozese.add(kolcsonzes);
                };
            }

            for (Kolcsonzesek kolcsonzes : kolcsonzoKolcsozese) {
                System.out.println(kolcsonzes.cim + "  " + kolcsonzes.kolcsonzo_id);
                
            }
            
            System.out.println( "\n ciklus vége \n");

            Kolcsonzok kolcsonzok = new Kolcsonzok(
                jsonAr.getJSONObject(i).getInt("kolcsonzo_id"),
                jsonAr.getJSONObject(i).getString("nev"),
                jsonAr.getJSONObject(i).getString("szuletesi_datum"),
                kolcsonzoKolcsozese
            );
            kolcsonzoKolcsozese.clear();
            kolcsonzokArr.add(kolcsonzok);
        }

        
        // for (Kolcsonzok kolcsonzo : kolcsonzokArr) {
        //         System.out.println(kolcsonzo.nev);
        //         System.out.println(kolcsonzo.kolcsonzesek.size());
        // }

    }
}
