package com.app.cleartv;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dell on 3/8/2018.
 */

public class DataFeeder {
    public static JSONArray District() {

        String districtJson = "[" +
                "{" +
                "\"id\": 1," +
                "\"district\": \"Jhapa\"" +
                "}," +
                "{" +
                "\"id\": 2," +
                "\"district\": \"Ilam\"" +
                "}," +
                "{" +
                "\"id\": 3," +
                "\"district\": \"Panchthar\"" +
                "}," +
                "{" +
                "\"id\": 4," +
                "\"district\": \"Taplejung\"" +
                "}," +
                "{" +
                "\"id\": 5," +
                "\"district\": \"Morang\"" +
                "}," +
                "{" +
                "\"id\": 6," +
                "\"district\": \"Sunsari\"" +
                "}," +
                "{" +
                "\"id\": 7," +
                "\"district\": \"Bhojpur\"" +
                "}," +
                "{" +
                "\"id\": 8," +
                "\"district\": \"Dhankuta\"" +
                "}," +
                "{" +
                "\"id\": 9," +
                "\"district\": \"Terhathum\"" +
                "}," +
                "{" +
                "\"id\": 10," +
                "\"district\": \"Sankhuwasabha\"" +
                "}," +
                "{" +
                "\"id\": 11," +
                "\"district\": \"Saptari\"" +
                "}," +
                "{" +
                "\"id\": 12," +
                "\"district\": \"Siraha\"" +
                "}," +
                "{" +
                "\"id\": 13," +
                "\"district\": \"Udayapur\"" +
                "}," +
                "{" +
                "\"id\": 14," +
                "\"district\": \"Khotang\"" +
                "}," +
                "{" +
                "\"id\": 15," +
                "\"district\": \"Okhaldhunga\"" +
                "}," +
                "{" +
                "\"id\": 16," +
                "\"district\": \"Solukhumbu\"" +
                "}," +
                "{" +
                "\"id\": 17," +
                "\"district\": \"Dhanusa\"" +
                "}," +
                "{" +
                "\"id\": 18," +
                "\"district\": \"Mahottari\"" +
                "}," +
                "{" +
                "\"id\": 19," +
                "\"district\": \"Sarlahi\"" +
                "}," +
                "{" +
                "\"id\": 20," +
                "\"district\": \"Sindhuli\"" +
                "}," +
                "{" +
                "\"id\": 21," +
                "\"district\": \"Ramechhap\"" +
                "}," +
                "{" +
                "\"id\": 22," +
                "\"district\": \"Dolakha\"" +
                "}," +
                "{" +
                "\"id\": 23," +
                "\"district\": \"Bhaktapur\"" +
                "}," +
                "{" +
                "\"id\": 24," +
                "\"district\": \"Dhading\"" +
                "}," +
                "{" +
                "\"id\": 25," +
                "\"district\": \"Kathmandu\"" +
                "}," +
                "{" +
                "\"id\": 26," +
                "\"district\": \"Kavrepalanchok\"" +
                "}," +
                "{" +
                "\"id\": 27," +
                "\"district\": \"Lalitpur\"" +
                "}," +
                "{" +
                "\"id\": 28," +
                "\"district\": \"Nuwakot\"" +
                "}," +
                "{" +
                "\"id\": 29," +
                "\"district\": \"Rasuwa\"" +
                "}," +
                "{" +
                "\"id\": 30," +
                "\"district\": \"Sindhupalchok\"" +
                "}," +
                "{" +
                "\"id\": 31," +
                "\"district\": \"Bara\"" +
                "}," +
                "{" +
                "\"id\": 32," +
                "\"district\": \"Parsa\"" +
                "}," +
                "{" +
                "\"id\": 33," +
                "\"district\": \"Rautahat\"" +
                "}," +
                "{" +
                "\"id\": 34," +
                "\"district\": \"Chitwan\"" +
                "}," +
                "{" +
                "\"id\": 35," +
                "\"district\": \"Makwanpur\"" +
                "}," +
                "{" +
                "\"id\": 36," +
                "\"district\": \"Gorkha\"" +
                "}," +
                "{" +
                "\"id\": 37," +
                "\"district\": \"Kaski\"" +
                "}," +
                "{" +
                "\"id\": 38," +
                "\"district\": \"Lamjung\"" +
                "}," +
                "{" +
                "\"id\": 39," +
                "\"district\": \"Syangja\"" +
                "}," +
                "{" +
                "\"id\": 40," +
                "\"district\": \"Tanahun\"" +
                "}," +
                "{" +
                "\"id\": 41," +
                "\"district\": \"Manang\"" +
                "}," +
                "{" +
                "\"id\": 42," +
                "\"district\": \"Kapilvastu\"" +
                "}," +
                "{" +
                "\"id\": 43," +
                "\"district\": \"Nawalpur\"" +
                "}," +
                "{" +
                "\"id\": 44," +
                "\"district\": \"Parasi\"" +
                "}," +
                "{" +
                "\"id\": 45," +
                "\"district\": \"Rupandehi\"" +
                "}," +
                "{" +
                "\"id\": 46," +
                "\"district\": \"Arghakhanchi\"" +
                "}," +
                "{" +
                "\"id\": 47," +
                "\"district\": \"Gulmi\"" +
                "}," +
                "{" +
                "\"id\": 48," +
                "\"district\": \"Palpa\"" +
                "}," +
                "{" +
                "\"id\": 49," +
                "\"district\": \"Baglung\"" +
                "}," +
                "{" +
                "\"id\": 50," +
                "\"district\": \"Myagdi\"" +
                "}," +
                "{" +
                "\"id\": 51," +
                "\"district\": \"Parbat\"" +
                "}," +
                "{" +
                "\"id\": 52," +
                "\"district\": \"Mustang\"" +
                "}," +
                "{" +
                "\"id\": 53," +
                "\"district\": \"Dang Deukhuri\"" +
                "}," +
                "{" +
                "\"id\": 54," +
                "\"district\": \"Pyuthan\"" +
                "}," +
                "{" +
                "\"id\": 55," +
                "\"district\": \"Rolpa\"" +
                "}," +
                "{" +
                "\"id\": 56," +
                "\"district\": \"Eastern Rukum\"" +
                "}," +
                "{" +
                "\"id\": 57," +
                "\"district\": \"Western Rukum\"" +
                "}," +
                "{" +
                "\"id\": 58," +
                "\"district\": \"Salyan\"" +
                "}," +
                "{" +
                "\"id\": 59," +
                "\"district\": \"Dolpa\"" +
                "}," +
                "{" +
                "\"id\": 60," +
                "\"district\": \"Humla\"" +
                "}," +
                "{" +
                "\"id\": 61," +
                "\"district\": \"Jumla\"" +
                "}," +
                "{" +
                "\"id\": 62," +
                "\"district\": \"Kalikot\"" +
                "}," +
                "{" +
                "\"id\": 63," +
                "\"district\": \"Mugu\"" +
                "}," +
                "{" +
                "\"id\": 64," +
                "\"district\": \"Banke\"" +
                "}," +
                "{" +
                "\"id\": 65," +
                "\"district\": \"Bardiya\"" +
                "}," +
                "{" +
                "\"id\": 66," +
                "\"district\": \"Surkhet\"" +
                "}," +
                "{" +
                "\"id\": 67," +
                "\"district\": \"Jajarkot\"" +
                "}," +
                "{" +
                "\"id\": 68," +
                "\"district\": \"Dailekh\"" +
                "}," +
                "{" +
                "\"id\": 69," +
                "\"district\": \"Kailali\"" +
                "}," +
                "{" +
                "\"id\": 70," +
                "\"district\": \"Achham\"" +
                "}," +
                "{" +
                "\"id\": 71," +
                "\"district\": \"Doti\"" +
                "}," +
                "{" +
                "\"id\": 72," +
                "\"district\": \"Bajhang\"" +
                "}," +
                "{" +
                "\"id\": 73," +
                "\"district\": \"Bajura\"" +
                "}," +
                "{" +
                "\"id\": 74," +
                "\"district\": \"Kanchanpur\"" +
                "}," +
                "{" +
                "\"id\": 75," +
                "\"district\": \"Dadeldhura\"" +
                "}," +
                "{" +
                "\"id\": 76," +
                "\"district\": \"Baitadi\"" +
                "}," +
                "{" +
                "\"id\": 77," +
                "\"district\": \"Darchula\"" +
                "}" +
                "]";

        JSONArray ja = new JSONArray();
        try {
            ja = new JSONArray(districtJson);
        } catch (JSONException e) {
            System.out.println("Issue here:");
            e.printStackTrace();
            System.out.println("+++++++++++++++++++++");
        } finally {
            return ja;
        }
    }
}
