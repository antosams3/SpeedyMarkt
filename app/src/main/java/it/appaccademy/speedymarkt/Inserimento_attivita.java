package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class Inserimento_attivita extends Fragment {
    String email;
    String EtNome, EtIndirizzo, EtCivico, EtCap, EtTelefono;
    Spinner Sel_Categoria;
    String negozio;
    Button buttatt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inserimento_attivita, container, false);

        //Gestione spinner
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Supermercato");
        spinnerArray.add("Elettronica");
        spinnerArray.add("Alimentari");
        spinnerArray.add("Altro");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sel_Categoria = (Spinner) view.findViewById(R.id.spinner1);
        Sel_Categoria.setAdapter(adapter);

        buttatt=(Button)view.findViewById(R.id.etButton);
        buttatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EtNome = ((EditText) view.findViewById(R.id.etNome)).getText().toString();
                EtIndirizzo =( (EditText) view.findViewById(R.id.etIndirizzo)).getText().toString();
                EtCivico = ((EditText) view.findViewById(R.id.etCivico)).getText().toString();
                EtCap = ((EditText) view.findViewById(R.id.etCap)).getText().toString();
                EtTelefono = ((EditText) view.findViewById(R.id.etTelefono)).getText().toString();
                //System.out.println(EtNome+" "+EtIndirizzo+" "+MainActivity.email+" "+Sel_Categoria.getSelectedItem().toString());
                Background b= new Background(getContext());
                b.execute("insert_activity",EtNome,EtIndirizzo,EtCivico,EtCap,EtTelefono,MainActivity.email,Sel_Categoria.getSelectedItem().toString());
            }
        });
        return view;
    }



}
