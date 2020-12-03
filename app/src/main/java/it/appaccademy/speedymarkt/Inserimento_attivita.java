package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class Inserimento_attivita extends AppCompatActivity {
    String email;
    EditText EtNome, EtIndirizzo, EtCivico, EtCap, EtTelefono;
    Spinner Sel_Categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento_attivita);

        //Prelievo del dato email
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }

        EtNome = (EditText) findViewById(R.id.etNome);
        EtIndirizzo = (EditText) findViewById(R.id.etIndirizzo);
        EtCivico = (EditText) findViewById(R.id.etCivico);
        EtCap = (EditText) findViewById(R.id.etCap);
        EtTelefono = (EditText) findViewById(R.id.etTelefono);

        //Gestione spinner
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Supermercato");
        spinnerArray.add("Elettronica");
        spinnerArray.add("Alimentari");
        spinnerArray.add("Altro");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sel_Categoria = (Spinner) findViewById(R.id.spinner1);
        Sel_Categoria.setAdapter(adapter);

    }

    public void aggiungi(View view) {
        String nome = EtNome.getText().toString();
        String indirizzo = EtIndirizzo.getText().toString();
        String civico = EtCivico.getText().toString();
        String cap = EtCap.getText().toString();
        String telefono = EtTelefono.getText().toString();
        String categoria = Sel_Categoria.getSelectedItem().toString();

        //Thread
        Background backgroundWorker = new Background(this);
        backgroundWorker.execute("insert_activity", nome, indirizzo, civico, cap, telefono, email, categoria);
    }

    //Passaggio dato email a Ricercasupermercati
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email a Profilo
    public void visitaProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email ad Accesso_admin
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email a Carrello
    public void goCarrello(View view) {
        Intent intent = new Intent(this, Carrello.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
