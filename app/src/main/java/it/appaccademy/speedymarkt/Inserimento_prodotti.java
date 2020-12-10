package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Inserimento_prodotti extends Fragment {

    EditText EtEan, EtMarchio, EtNome, EtPrezzo, EtQuantita;
    String negozio, email, nome, via;
    public static ListView elenco;
    public static ArrayList<singleRow> vettore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ip_seleziona_supermercato, container, false);

        EtEan = (EditText) view.findViewById(R.id.etEan);
        EtMarchio = (EditText) view.findViewById(R.id.etMarchio);
        EtNome = (EditText) view.findViewById(R.id.etProdotto);
        EtPrezzo = (EditText) view.findViewById(R.id.etPrezzo);
        EtQuantita = (EditText) view.findViewById(R.id.etQuantita);


        vettore=new ArrayList<singleRow>();
        elenco=(ListView)view.findViewById(R.id.listview_elencosupermercati);




        Lavoratore process = new Lavoratore(getContext());
        process.execute("elencosupermercati",MainActivity.email);
        return view;
    }

}
