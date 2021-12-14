package com.example.worldcinemaprob;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API_Auth_Register extends  API_Constant_values {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String responseCode;

    API_Auth_Register (String email, String password, String firstName, String lastName) {
        this.email     = email;
        this.password  = password;
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public void start(URL url) {
        new API_Auth_Register_Thread().execute(url);
    }

    public URL generateURL() {
        URL url = null;

        Uri uri = Uri.parse(API_URL_BASE + API_AUTH_REGISTER).buildUpon().build();

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private String generateJSON () {
        String result = null;

        try {
            result = new JSONObject().put("email", email).put("password", password).put("firstName", firstName)
                    .put("lastName", lastName).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private int sendPostToServer(URL url) throws IOException {
        String message = generateJSON();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bw.write(message);

        return connection.getResponseCode();
    }

    public String getResponseCode() {
        return responseCode;
    }

    class API_Auth_Register_Thread extends AsyncTask <URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            int responseCode = 0;

            try {
                responseCode = sendPostToServer(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Integer.toString(responseCode);
        }

        @Override
        protected void onPostExecute(String response) {
            System.out.println(response);
            responseCode = response;
        }
    }
}
