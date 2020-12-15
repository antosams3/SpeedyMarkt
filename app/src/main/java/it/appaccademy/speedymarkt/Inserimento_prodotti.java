package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Inserimento_prodotti extends Fragment {


    public static ListView elenco;
    public static ArrayList<singleRow> vettore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ip_seleziona_supermercato, container, false);



        vettore=new ArrayList<singleRow>();
        elenco=(ListView)view.findViewById(R.id.listview_elencosupermercati);


        Lavoratore process = new Lavoratore(getContext());
        System.out.println(MainActivity.email);
        process.execute("elencosupermercati",MainActivity.email,"FALSE");
        // inserisci FALSE causa riciclo di visualizza ordini che passa TRUE
        return view;
    }

}
