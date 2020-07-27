package com.robi.dev.model;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONArray;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringEscapeUtils;



public class EtsafInfo {

    void EtsafInfo(){

    }

   public  String  etsafAPILogin()  {

        // url is missing?
        //String url = "https://selfsolve.apple.com/wcResults.do";
        //String url = "https://httpbin.org/post";
        String url ="https://etsaf-api.robi.com.bd/oauth/token";
        String result="", access_token = "";
        JSONParser jsonParser = new JSONParser();


        //add reuqest header
        try {

            HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

            httpClient.setRequestMethod("POST");
            //httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            //httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            httpClient.setRequestProperty("Authorization", "Basic  cmNwcy1hcGktY2xpZW50OlhZN2ttem9OemwxMDA=");
            httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpClient.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            httpClient.setRequestProperty("Accept", "*/*");
            httpClient.setRequestProperty("Connection", "keep-alive");

            //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
            //username=john.doe&password=jwtpass&grant_type=password

            String urlParameters = "username=john.doe&password=jwtpass&grant_type=password";

            // Send post request
            httpClient.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            }

            int responseCode = httpClient.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpClient.getInputStream()))) {

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                //print result
                // System.out.println(response.toString());

                result = "["+response+"]";

                Object obj = jsonParser.parse(result);


                JSONArray etsaflogininfo = (JSONArray) obj;

                System.out.println("info :"+etsaflogininfo.get(0));

                JSONObject jo = (JSONObject)(etsaflogininfo.get(0));
                //JSONObject accesstoken = (JSONObject) jo.get("access_token");

                access_token = (String) jo.get("access_token");
                System.out.println("Access token : "+access_token);

            }
        }
        catch (ProtocolException e) {
            e.printStackTrace();
            access_token="";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            access_token="";
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println ("Not Authorized");
            access_token="";
        }
        catch (ParseException e) {
            e.printStackTrace();
            access_token="";
        }

        return  access_token;
    }

    public String etsafAPICustomerInfo(String msisdn) {

        String url = "https://etsaf-api.robi.com.bd/customers/esaf?msisdn="+msisdn;
        String result= "", access_token ="", submit_time="";
        JSONParser jsonParser = new JSONParser();


        access_token = etsafAPILogin();


        try {
            HttpURLConnection httpClient =
                    (HttpURLConnection) new URL(url).openConnection();

            // optional default is GET
            httpClient.setRequestMethod("GET");

            //add request header
            //httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpClient.setRequestProperty("Authorization", "Bearer "+access_token);
        /*httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpClient.setRequestProperty("Accept-Encoding", "gzip, deflate, br");*/
            //  httpClient.setRequestProperty("Accept", "*/*");
            // httpClient.setRequestProperty("Connection", "keep-alive");

            int responseCode = httpClient.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            try(BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpClient.getInputStream()))) {

                StringBuilder response = new StringBuilder();
                String line;

                while((line =in.readLine())!=null)

                {
                    response.append(line);
                }

                result = "["+response+"]";

                //print result
                Object obj = jsonParser.parse(result);


                JSONArray etsafcusinfo = (JSONArray) obj;

                System.out.println("info :"+etsafcusinfo.get(0));

                JSONObject jo = (JSONObject)(etsafcusinfo.get(0));
                //JSONObject submittime = (JSONObject) jo.get("submitTime");

                submit_time = (String) jo.get("submitTime");
                System.out.println("Submit Time : "+submit_time);

                //System.out.println(response.toString());

            }

        }
        catch (ProtocolException e) {
            e.printStackTrace();
            submit_time = "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            submit_time = "";
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println ("Not Authorized");
            submit_time = "";
        }
        catch (ParseException e) {
            e.printStackTrace();
            submit_time = "";
        }

        return  submit_time;
    }

}
