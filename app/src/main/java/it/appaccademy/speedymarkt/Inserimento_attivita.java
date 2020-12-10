package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class Inserimento_attivita extends Fragment {
    String email;
    EditText EtNome, EtIndirizzo, EtCivico, EtCap, EtTelefono;
    Spinner Sel_Categoria;
    String negozio;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inserimento_attivita, container, false);

        EtNome = (EditText) view.findViewById(R.id.etNome);
        EtIndirizzo = (EditText) view.findViewById(R.id.etIndirizzo);
        EtCivico = (EditText) view.findViewById(R.id.etCivico);
        EtCap = (EditText) view.findViewById(R.id.etCap);
        EtTelefono = (EditText) view.findViewById(R.id.etTelefono);

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

        return view;
    }



}
