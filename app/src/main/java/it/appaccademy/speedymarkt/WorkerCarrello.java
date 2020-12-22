package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.AsyncTask;

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

public class WorkerCarrello extends AsyncTask<String, Void, Void> {
    String login_url;
    Context context;
    String idOrdine;
    String quantita;
    String ean;


    WorkerCarrello(Context ctx) {
        context = ctx;
    }

    @Override
    protected Void doInBackground(String... params) {

        login_url = "http://10.0.2.2/ordine.php";

        try {
            idOrdine = params[0];
            String email = params[1];
            String costoTot = params[2];
            System.out.println("PARAMETRI IN BGWORKER: " + email + " " + costoTot);
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("idOrdine", "UTF-8") + "=" + URLEncoder.encode(idOrdine, "UTF-8") + "&" + URLEncoder.encode("costoTot", "UTF-8") + "=" + URLEncoder.encode(costoTot, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        login_url = "http://10.0.2.2/ordine_prodotto.php";
            try {
                URL url = new URL(login_url);
                for (int i = 0; i < Carrello.carrello.size(); i++) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                String result = "";
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = "";
                    ean = Carrello.carrello.get(i).getEan();
                    quantita = String.valueOf(Carrello.carrello.get(i).getQuantita());
                    post_data = URLEncoder.encode("idOrdine", "UTF-8") + "=" + URLEncoder.encode(idOrdine, "UTF-8") + "&" + URLEncoder.encode("ean", "UTF-8") + "=" + URLEncoder.encode(ean, "UTF-8") + "&" + URLEncoder.encode("quantita", "UTF-8") + "=" + URLEncoder.encode(quantita, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                }


            } catch (
                    MalformedURLException e) {
                e.printStackTrace();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }



        return null;}
}





