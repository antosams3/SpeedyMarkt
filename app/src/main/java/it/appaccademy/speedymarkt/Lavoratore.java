package it.appaccademy.speedymarkt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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


public class Lavoratore extends AsyncTask<String, Void, Void> {
    Context context;
    String data = "";
    String email;
    String negozio;
    String nome;
    String via;
    String id;
    String civico;
    String cap;
    String città;
    singleRow ogg;
    singleRowOrdine ord;
    String idattivita;
    String idordine;
    String emailutente;
    ArrayList<singleRow> elenco;
    ArrayList<singleRowOrdine> elencoordini;
    String menu;
    boolean cond=false;
   


    Lavoratore(Context ctx) {
        context = ctx;
    }
    
    @Override
    protected Void doInBackground(String... params) {
        menu=params[0];
        switch(menu){
            case "elencosupermercati":
            try {
                email=params[1];
                if(params[2].equals("TRUE")){
                    cond=true;
                }
                URL url = new URL("http://10.0.2.2/ip_elencosupermercati.php");
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
                while(line != null){
                    line = bufferedReader.readLine();
                    if(line!=null) {
                        data = data + line;
                    }
                }
                if(!(data.equals("Nessun risultato"))) {
                    JSONArray JA = new JSONArray(data);
                    elenco = new ArrayList<>();
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        nome = (String) JO.get("nome");
                        via = (String) JO.get("via");
                        civico = (String) JO.get("civico");
                        id = (String) JO.get("id");
                        cap = (String) JO.get("cap");
                        città = (String) JO.get("città");
                        ogg = new singleRow(nome, via, civico, id, cap, città);
                        elenco.add(ogg);
                        //dataParsed = dataParsed + singleParsed;
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        break;
            case "elenco_attivita":
            try {
                negozio=params[1];
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
                    if(line!=null) {
                        data = data + line;
                    }
                }
                if(!(data.equals("Nessun risultato"))) {
                    JSONArray JA = new JSONArray(data);
                    elenco = new ArrayList<>();
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        nome = (String) JO.get("nome");
                        via = (String) JO.get("via");
                        civico = (String) JO.get("civico");
                        id = (String) JO.get("id");
                        cap = (String) JO.get("cap");
                        città = (String) JO.get("città");
                        ogg = new singleRow(nome, via, civico, id, cap, città);
                        elenco.add(ogg);
                        System.out.println(ogg.toString());
                        //dataParsed = dataParsed + singleParsed;
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            break;
            case "elenco_ordini":
                try {
                    idattivita=params[1];
                    URL url = new URL("http://10.0.2.2/elenco_ordini.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("idattivita", "UTF-8") + "=" + URLEncoder.encode(idattivita, "UTF-8");
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
                        if(line!=null) {
                            data = data + line;
                        }
                    }
                    if(!(data.equals("Nessun risultato"))) {
                        JSONArray JA = new JSONArray(data);

                        elencoordini = new ArrayList<>();
                        for (int i = 0; i < JA.length(); i++) {
                            JSONObject JO = (JSONObject) JA.get(i);
                            idordine = (String) JO.get("idOrdine");
                            emailutente = (String) JO.get("emailUtente");
                            ord = new singleRowOrdine(idordine, emailutente);
                            elencoordini.add(ord);
                            //dataParsed = dataParsed + singleParsed;
                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

        return null;

}

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        switch(menu){
            case "elenco_ordini":
                ElencoOrdini.vettoreordine.clear();
            if(!data.equals("Nessun risultato")){

                for (int i=0; i < elencoordini.size(); i++){
                    ElencoOrdini.vettoreordine.add(elencoordini.get(i));
                }
                ElencoOrdini.elencordini.setAdapter(new customAdapterOrdine(this.context,ElencoOrdini.vettoreordine));
            }else{
                Toast.makeText(this.context, "Nessun ordine da visualizzare", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(this.context, MainActivity.class);
                //context.startActivity(intent);
                ElencoOrdini.elencordini.setAdapter(new customAdapterOrdine(this.context,ElencoOrdini.vettoreordine));
            }
            break;

            case "elenco_attivita":
                if (data.equals("Nessun risultato")) {
                    Toast.makeText(this.context, data, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this.context, MainActivity.class);
                    context.startActivity(intent);
                } else {


                    ElencoSupermercati.vettore.addAll(elenco);
                    ElencoSupermercati.elenco.setAdapter(new customAdapter(this.context, ElencoSupermercati.vettore,"FALSE"));

                }

                break;
            case "elencosupermercati":
                if (data.equals("Nessun risultato")) {
                    Toast.makeText(this.context, data, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this.context, MainActivity.class);
                    context.startActivity(intent);
                } else {

                        if(cond==true){
                            for (int i=0; i < elenco.size(); i++){
                                ElencoOrdini.elencoatt.add(elenco.get(i));
                                ElencoOrdini.spinnerArray.add(elenco.get(i).getNome());
                            }
                        }else{
                            Inserimento_prodotti.vettore.addAll(elenco);
                            Inserimento_prodotti.elenco.setAdapter(new customAdapter(this.context, Inserimento_prodotti.vettore, "TRUE"));
                        }

                    }

                break;

        }


    }
}