package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class OrdineSelezionato extends Fragment {
    String id_ordine;
    public static ListView listviewordine;
    Button b;
    public static ArrayList<singleRowProdotto> vettoreordine=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_carrello, container, false);
        if (getArguments() != null) {
            id_ordine = getArguments().getString("id_ordine");
        }
        listviewordine = (ListView) view.findViewById(R.id.listview_elencocarrello);
        b=(Button)view.findViewById(R.id.buttonpagamento);
        b.setVisibility(view.GONE);
        WorkerProdotto proc=new WorkerProdotto(getContext());
        proc.execute("Ordine",id_ordine);
        return view;
    }


}
