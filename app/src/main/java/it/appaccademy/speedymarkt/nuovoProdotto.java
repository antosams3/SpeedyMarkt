package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class nuovoProdotto extends Fragment {
    String idnegozio;
    Button butt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inserimento_prodotti, container, false);


        if (getArguments() != null) {
            idnegozio = getArguments().getString("id");
        }

        butt=(Button)view.findViewById(R.id.buttonaggiungi);

        System.out.println("negozio selezionato "+idnegozio);

        return view;
    }
}
