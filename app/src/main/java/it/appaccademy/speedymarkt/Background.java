package it.appaccademy.speedymarkt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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

import static it.appaccademy.speedymarkt.MainActivity.email;


public class Background extends AsyncTask<String, Void, String> {
    Context context;
    String type;
    String user_name;
    String email_reg;
    String email_card;
    AlertDialog alertDialog;
    String data = "";
    String tipo;
    String nome;
    String nomecard, tipocard;



    Background(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        switch (type) {

            /**
             * *****************************
             * LOGICA SCHERMATA INIZIALE
             *******************************
             */

            case "login":
                String login_url = "http://10.0.2.2/login.php";
                try {
                    user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            /**
             ****************************
             * LOGICA REGISTRAZIONE
             ****************************
             */
            case "insert":
                login_url = "http://10.0.2.2/register.php";
                try {
                    nome = params[1];
                    String cognome = params[2];
                    String data = params[3];
                    email_reg = params[4];
                    String password = params[5];
                    String piva = params[6];
                    tipo = params[7];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email_reg, "UTF-8") + "&" + URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(nome, "UTF-8") + "&" + URLEncoder.encode("cognome", "UTF-8") + "=" + URLEncoder.encode(cognome, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" + URLEncoder.encode("dataNasc", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8") + "&" + URLEncoder.encode("piva", "UTF-8") + "=" + URLEncoder.encode(piva, "UTF-8")+ "&" + URLEncoder.encode("tipo", "UTF-8") + "=" + URLEncoder.encode(tipo, "UTF-8");
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
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            /**
             ****************************
             * LOGICA REGISTRAZIONE CARTA
             ****************************
             */

            case "card":
                login_url = "http://10.0.2.2/card.php";
                try {
                    email_card = params[1];
                    String titolare = params[2];
                    String numero = params[3];
                    String scadenza = params[4];
                    String cvv = params[5];
                    tipocard = params[6];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email_card, "UTF-8") + "&" + URLEncoder.encode("titolare", "UTF-8") + "=" + URLEncoder.encode(titolare, "UTF-8") + "&" + URLEncoder.encode("numero", "UTF-8") + "=" + URLEncoder.encode(numero, "UTF-8") + "&" + URLEncoder.encode("scadenza", "UTF-8") + "=" + URLEncoder.encode(scadenza, "UTF-8") + "&" + URLEncoder.encode("cvv", "UTF-8") + "=" + URLEncoder.encode(cvv, "UTF-8");
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
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            /**
             ****************************
             * LOGICA DA TESTARE
             ****************************
             */
            case "tipo":
                login_url = "http://10.0.2.2/login.php";
                try {
                    String user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            /**
             ******************************
             * LOGICA INSERIMENTO PRODOTTO
             ******************************
             */
            case "insert_product":
                login_url = "http://10.0.2.2/insertproduct.php";
                try {
                    String ean = params[1];
                    String marchio = params[2];
                    String nome = params[3];
                    String prezzo = params[4];
                    String quantita = params[5];
                    String idattivita = params[6];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("idattivita", "UTF-8") + "=" + URLEncoder.encode(idattivita, "UTF-8") + "&" + URLEncoder.encode("ean", "UTF-8") + "=" + URLEncoder.encode(ean, "UTF-8") + "&" + URLEncoder.encode("marchio", "UTF-8") + "=" + URLEncoder.encode(marchio, "UTF-8") + "&" + URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(nome, "UTF-8") + "&" + URLEncoder.encode("prezzo", "UTF-8") + "=" + URLEncoder.encode(prezzo, "UTF-8") + "&" + URLEncoder.encode("quantita", "UTF-8") + "=" + URLEncoder.encode(quantita, "UTF-8");
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
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;


            /**
             ******************************
             * LOGICA INSERIMENTO ATTIVITA'
             ******************************
             */
            case "insert_activity":
                login_url = "http://10.0.2.2/housekeeping.php";

                try {
                    String nome = params[1];
                    String via = params[2];
                    String civico = params[3];
                    String cap = params[4];
                    String telefono = params[5];
                    String user_name = params[6];
                    String categoria = params[7];
                    //prendere param da user_name
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(nome, "UTF-8") + "&" + URLEncoder.encode("categoria", "UTF-8") + "=" + URLEncoder.encode(categoria, "UTF-8") + "&" + URLEncoder.encode("via", "UTF-8") + "=" + URLEncoder.encode(via, "UTF-8") + "&" + URLEncoder.encode("civico", "UTF-8") + "=" + URLEncoder.encode(civico, "UTF-8") + "&" + URLEncoder.encode("cap", "UTF-8") + "=" + URLEncoder.encode(cap, "UTF-8") + "&" + URLEncoder.encode("telefono", "UTF-8") + "=" + URLEncoder.encode(telefono, "UTF-8") + "&" + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
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


    /**
     * PRE EXECUTE ********************************************************************************************************************************************
     */

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status:");

    }

    /**
     * POST EXECUTE ********************************************************************************************************************************************
     */

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        super.onPostExecute(result);

        //Accesso a RicercaSupermercati da SchermataIniziale con dato email
        if (result.equals("Accesso eseguito")) {
            Intent intent = new Intent(this.context, MainActivity.class);
            intent.putExtra("email", user_name);
            context.startActivity(intent);
        }

        //Ritorno ad Accesso_admin dopo Inserimento_attivita con dato email
        if (result.equals("Completato")) {
            Intent intent = new Intent(this.context, MainActivity.class);
            intent.putExtra("email", user_name);
            context.startActivity(intent);
        }

        //Ritorno ad Accesso_admin dopo Inserimento_prodotto con dato email
        if (result.equals("Prodotto inserito con successo")) {
            Intent intent = new Intent(this.context, MainActivity.class);
            intent.putExtra("email", user_name);
            context.startActivity(intent);

        }

        //Accesso a Carta terminata una registrazione con passaggio del dato email
        if (result.equals("Utente creato con successo")) {
            Intent intent = new Intent(this.context, Carta.class);
            intent.putExtra("email", email_reg);
            intent.putExtra("tipo", tipo);
            System.out.println("da reg a card "+tipo);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        //Accesso a SchermataIniziale terminata una registrazione carta con passaggio del dato email
        if (result.equals("Carta creata con successo")) {
            Intent intent = new Intent(this.context, MainActivity.class);
            intent.putExtra("email", email_card);
            intent.putExtra("tipo", tipocard);
            System.out.println("da card a main "+tipocard);
            context.startActivity(intent);

        }
    }




    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}