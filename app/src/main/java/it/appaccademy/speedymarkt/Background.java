package it.appaccademy.speedymarkt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import javax.net.ssl.HttpsURLConnection;

public class Background extends AsyncTask<String,Void,String> {
    Context context;
    String type;
    String user_name;
    AlertDialog alertDialog;
    String data = "";
    String TvNome ="";
    String TvCognome = "";
    String TvData = "";
    String TvEmail = "";
    Background(Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        type = params[0];

        /**
         * *****************************
         * LOGICA SCHERMATA INIZIALE
         *******************************
         */

        switch (type){
            case "login":
                String login_url = "http://10.0.2.2/login.php";
                try {
                    user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result ="";
                    String line = "";
                    while((line = bufferedReader.readLine())!= null) {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            /**
             *
             *
             */
            case "insert":
                login_url = "http://10.0.2.2/register.php";
                try { //ordine nel database email nome cognome password datanascita  piva
                    String nome = params[1];
                    String cognome = params[2];
                    String data = params[3];
                    String email = params[4];
                    String password = params[5];
                    String piva= params[6];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"+URLEncoder.encode("nome", "UTF-8")+"="+URLEncoder.encode(nome, "UTF-8")+"&"+URLEncoder.encode("cognome", "UTF-8")+"="+URLEncoder.encode(cognome, "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"+URLEncoder.encode("dataNasc", "UTF-8")+"="+URLEncoder.encode(data, "UTF-8")+"&"+URLEncoder.encode("piva", "UTF-8")+"="+URLEncoder.encode(piva, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result ="";
                    String line = "";
                    while((line = bufferedReader.readLine())!= null) {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "tipo":
                login_url = "http://10.0.2.2/login.php";
                try {
                    String user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result ="";
                    String line = "";
                    while((line = bufferedReader.readLine())!= null) {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "profilo":
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

                break;
                case "insert_product":
                    login_url = "http://10.0.2.2/insertproduct.php";
                        try {
                            String ean = params[1];
                            String marchio = params[2];
                            String nome = params[3];
                            String prezzo = params[4];
                            String quantita = params[5];
                            URL url = new URL(login_url);
                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                            String post_data = URLEncoder.encode("//idattivita", "UTF-8")+"="+URLEncoder.encode(//idattivita, "UTF-8")+"&"+URLEncoder.encode("ean", "UTF-8")+"="+URLEncoder.encode(ean, "UTF-8")+"&"+URLEncoder.encode("marchio", "UTF-8")+"="+URLEncoder.encode(marchio, "UTF-8")+"&"+URLEncoder.encode("nome", "UTF-8")+"="+URLEncoder.encode(nome, "UTF-8")+"&"+URLEncoder.encode("prezzo", "UTF-8")+"="+URLEncoder.encode(prezzo, "UTF-8")+"&"+URLEncoder.encode("quantita", "UTF-8")+"="+URLEncoder.encode(quantita, "UTF-8");
                                    bufferedWriter.write(post_data);
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            outputStream.close();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                            String result ="";
                            String line = "";
                            while((line = bufferedReader.readLine())!= null) {
                                result+=line;
                            }
                            bufferedReader.close();
                            inputStream.close();
                            httpURLConnection.disconnect();
                            return result;
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    break;

                        case "insert_activity":
                login_url = "http://10.0.2.2/housekeeping.php";

                    try {
                        String nome = params[1];
                        String via = params[2];
                        String civico = params[3];
                        String cap = params[4];
                        String telefono = params[5];
                        String email=params[6];
                        String categoria= params[7];
                        //prendere param da user_name
                        URL url = new URL(login_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        //aggiungi il param user_name
                        String post_data = URLEncoder.encode("nome", "UTF-8")+"="+URLEncoder.encode(nome, "UTF-8")+"&"+URLEncoder.encode("categoria", "UTF-8")+"="+URLEncoder.encode(categoria, "UTF-8")+"&"+URLEncoder.encode("via", "UTF-8")+"="+URLEncoder.encode(via, "UTF-8")+"&"+URLEncoder.encode("civico", "UTF-8")+"="+URLEncoder.encode(civico, "UTF-8")+"&"+URLEncoder.encode("cap", "UTF-8")+"="+URLEncoder.encode(cap, "UTF-8")+"&"+URLEncoder.encode("telefono", "UTF-8")+"="+URLEncoder.encode(telefono, "UTF-8")+"&"+URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String result ="";
                        String line = "";
                        while((line = bufferedReader.readLine())!= null) {
                            result+=line;
                        }
                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();
                        return result;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                break;

        }


        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Stato login:");
        }

    @Override
    protected void onPostExecute(String result) {
        case "login"
           alertDialog.setMessage(result);
            alertDialog.show();
            super.onPostExecute(result);
            if(result.equals("Accesso eseguito")) {
            Intent intent = new Intent(this.context, RicercaSupermercati.class);
            intent.putExtra("email", user_name);
            context.startActivity(intent);

            }
        }




    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}