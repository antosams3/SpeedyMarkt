package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

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
import java.util.ArrayList;

import static it.appaccademy.speedymarkt.ElencoSupermercati.vettore;


public class Lavoratore extends AsyncTask<String, Void, Void> {
    Context context;
    String data = "";
    String email = "";
    String negozio;
    String nome;
    String via;
    String civico;
    singleRow ogg;
    ArrayList<singleRow> elenco;

   


    Lavoratore(Context ctx) {
        context = ctx;
    }
    
    @Override
    protected Void doInBackground(String... params) {
     try {
        negozio=params[0];
        URL url = new URL("http://10.0.2.2/negozio.php");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        String post_data = URLEncoder.encode("negozio", "UTF-8") + "=" + URLEncoder.encode(negozio, "UTF-8");
        bufferedWriter.write(post_data);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        InputStream inputStream = httpURLConnection.getInputStream();
        inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while(line != null){
        line = bufferedReader.readLine();
        data = data + line;
        }

        JSONArray JA = new JSONArray(data);
        elenco = new ArrayList<>();
        for(int i = 0; i < JA.length(); i++){
        JSONObject JO = (JSONObject) JA.get(i);
            nome = (String) JO.get("nome");
            via = (String) JO.get("via");
            civico = (String) JO.get("civico");
            ogg=new singleRow(nome,via,civico);
            elenco.add(ogg);
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

        for (int i=0; i < elenco.size(); i++){
            vettore.add(elenco.get(i));
        }

        ElencoSupermercati.elenco.setAdapter(new customAdapter(this.context, vettore));
    }
}