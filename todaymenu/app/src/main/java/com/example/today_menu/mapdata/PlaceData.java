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

public class PlaceData {
    String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?category_group_code=FD6";
    String auth = "6dd936323c9993cfe5d691918c46476a";
    String str, receiveMsg;
    String resultURL;

    public ArrayList<Place> getData(int i, String currentAdd, String keyword) {
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
                    if(keyword.equals("")) {
                        resultURL = apiUrl + "&query=" + currentAdd + "&page=" + i;
                    } else {
                        resultURL = apiUrl + "&query=" + currentAdd + " " + keyword + "&page=" + i;
                    }

                    URL url = new URL(resultURL);
                    HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
                    myConnection.setRequestProperty("Authorization", "KakaoAK " + auth);

                    Log.d("TestKakaoData", String.valueOf(url));


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
                        String totalArray = jsonObject.getJSONObject("meta").optString("total_count");
                        Log.d("totalArray", String.valueOf(totalArray));

                        for (int j = 0; j < jsonArray.length(); j++) {
                            Place Place = new Place();
                            JSONObject obj = jsonArray.getJSONObject(j);
                            Place.setName(obj.optString("place_name"));
                            Log.d("TestKakaoData", "place_name : " + Place.getName());
                            Place.setAddress(obj.optString("address_name"));
                            Log.d("TestKakaoData", "address_name : " + Place.getAddress());
                            Place.setRoadAddress(obj.optString("road_address_name"));
                            Log.d("TestKakaoData", "road_address_name : " + Place.getRoadAddress());
                            Place.setLongitude(obj.optDouble("x"));
                            Log.d("TestKakaoData", "x : " + Place.getLongitude());
                            Place.setLatitude(obj.optDouble("y"));
                            Log.d("TestKakaoData", "y : " + Place.getLatitude());

                            dataArr.add(j,Place);
//                            Log.d("testArray", String.valueOf(dataArr.size()));
//                            Log.d("testArray", dataArr.toString());
                        }
                    } else {
                        Log.d("TestKakaoData", "통신 결과 : " + myConnection.getResponseCode() + "에러");
//                    }
                    }
                    Log.d("testArray", dataArr.toString());
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

        return dataArr;
    }
}