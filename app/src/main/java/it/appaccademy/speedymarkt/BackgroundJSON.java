package it.appaccademy.speedymarkt;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class BackgroundJSON extends AsyncTask<Void, Void, Void> {
        String data = "";
        String TvNome ="";
        String TvCognome = "";
        String TvData = "";
        String TvEmail = "";
        public BackgroundJSON() {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.0.2.2/profilo.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray JA = new JSONArray(data);
                for(int i = 0; i < JA.length(); i++){
                    JSONObject JO = (JSONObject) JA.get(i);
                    TvEmail = (String) JO.get("email") +"\n";
                    TvNome = (String) JO.get("nome") +"\n";
                    TvCognome = (String) JO.get("cognome") +"\n";
                    TvData = (String) JO.get("dataNasc") +"\n";


                    //dataParsed = dataParsed + singleParsed;
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

           Profilo.TvAnagNome.setText(this.TvNome);
           Profilo.TvAnagCognome.setText(this.TvCognome);
           Profilo.TvAnagDatanasc.setText(this.TvData);
           Profilo.TvAnagEmail.setText(this.TvEmail);
        }
    }

