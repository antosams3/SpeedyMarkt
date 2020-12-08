package it.appaccademy.speedymarkt;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundJSON extends AsyncTask<String, Void, Void> {
    String data = "";
    String TvNome = "";
    String TvCognome = "";
    String TvData = "";
    String TvEmail = "";
    String email = "";
    String TvTitolare ="";
    String TvCarta ="";
    String TvScadenza ="";

    public BackgroundJSON() {
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            email = params[0];
            URL url = new URL("http://25.59.197.115/profilo.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                TvEmail = (String) JO.get("email") + "\n";
                TvNome = (String) JO.get("nome") + "\n";
                TvCognome = (String) JO.get("cognome") + "\n";
                TvData = (String) JO.get("dataNasc") + "\n";
                TvTitolare = (String) JO.get("titolare")+"\n";
                TvCarta = (String) JO.get("numero") + "\n";
                TvScadenza = (String) JO.get("scadenza") + "\n";


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Profilo.email = email;
        Profilo.TvAnagNome.setText(this.TvNome);
        Profilo.TvAnagCognome.setText(this.TvCognome);
        Profilo.TvAnagDatanasc.setText(this.TvData);
        Profilo.TvAnagEmail.setText(this.TvEmail);
        Profilo.TvAnagTitolare.setText(this.TvTitolare);
        Profilo.TvAnagScadenza.setText(this.TvScadenza);
        Profilo.TvAnagCarta.setText("**** **** **** " + this.TvCarta.substring(12));


    }
}

