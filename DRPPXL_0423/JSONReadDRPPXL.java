import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadDRPPXL {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("orarendDRPPXL.json")) {

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONObject root = (JSONObject) jsonObject.get("DRPPXL_orarend");
            JSONArray orarend = (JSONArray) root.get("ora");

            System.out.println("DRPPXL órarend 2025-26_IV. \n");

            for (int i = 0; i < orarend.size(); i++) {

                JSONObject ora = (JSONObject) orarend.get(i);
                JSONObject time = (JSONObject) ora.get("idopont");

                System.out.println("Tárgy: " + ora.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + " " + time.get("tol") + " - " + time.get("ig"));
                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}