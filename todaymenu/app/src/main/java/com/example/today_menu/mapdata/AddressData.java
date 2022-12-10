package com.example.today_menu.mapdata;

import android.util.Log;

import com.example.today_menu.mapdata.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class AddressData {
    String apiUrl = "https://dapi.kakao.com/v2/local/geo/coord2address?";
    String auth = "6dd936323c9993cfe5d691918c46476a";
    String str, receiveMsg;
    String firstAdd, secondAdd, thirdAdd;

    public String getData(int addressNo, double x, double y) {
        //return과 관련된 부분
        ArrayList<Place> dataArr = new ArrayList<Place>();
        Place Place = new Place();


        //네트워킹 작업은 메인스레드에서 처리하면 안된다. 따로 스레드를 만들어 처리하자
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
//                    for(int i=1; i<45; i++) {
//                        ArrayList<Place> dataArr = new ArrayList<Place>();

//                        String fullURL = apiUrl + "&page=" + i;
                    URL url = new URL(apiUrl+ "x=" + x + "&y=" + y);
                    HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
                    myConnection.setRequestProperty("Authorization", "KakaoAK " + auth);

//                    Log.d("TestKakaoData", String.valueOf(myConnection.getResponseCode()));

                    if(myConnection.getResponseCode()==200) {
//                        InputStream is = myConnection.getInputStream();
//                        InputStreamReader response = new InputStreamReader(is, "utf-8");
//                        JsonReader jsonReader = new JsonReader(response);
                        InputStreamReader tmp = new InputStreamReader(myConnection.getInputStream(), "utf-8");
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuffer buffer = new StringBuffer();
                        while ((str = reader.readLine()) != null) {
                            buffer.append(str);
                        }
                        receiveMsg = buffer.toString();
//                        Log.d("TestkakaoData", "receiveMsg : " + receiveMsg);

                        reader.close();

                        JSONObject jsonObject = new JSONObject(receiveMsg);
                        JSONArray jsonArray = jsonObject.getJSONArray("documents");

                        JSONObject addressArray = jsonArray.getJSONObject(0).getJSONObject("address");
                        Log.d("ApiAddress", addressArray.toString());

                        firstAdd = String.valueOf(addressArray.get("region_1depth_name"));
                        secondAdd = String.valueOf(addressArray.get("region_2depth_name"));
                        thirdAdd = String.valueOf(addressArray.get("region_3depth_name"));


                    } else {
                        Log.d("TestKakaoData", "통신 결과 : " + myConnection.getResponseCode() + "에러");
//                    }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        try {
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(addressNo==1) {
            return firstAdd;
        } else if(addressNo==2) {
            return secondAdd;
        } else if(addressNo==3) {
            return thirdAdd;
        } else return " ";

    }
}
