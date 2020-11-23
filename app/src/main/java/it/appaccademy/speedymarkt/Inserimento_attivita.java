package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class Inserimento_attivita extends AppCompatActivity {
    String email="";
    String result="";
    EditText EtNome, EtIndirizzo, EtCivico, EtCap, EtTelefono;
    Spinner Sel_Categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento_attivita);
        EtNome = (EditText) findViewById(R.id.etNome);
        EtIndirizzo = (EditText) findViewById(R.id.etIndirizzo);
        EtCivico = (EditText) findViewById(R.id.etCivico);
        EtCap = (EditText) findViewById(R.id.etCap);
        EtTelefono = (EditText) findViewById(R.id.etTelefono);

        List<String> spinnerArray =  new ArrayList<String>();
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
        System.out.println(categoria);
        Background backgroundWorker = new Background(this);
        backgroundWorker.execute("insert_attivita",nome,indirizzo,civico,cap,telefono,email,categoria);
    }
}
