package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class nuovoProdotto extends Fragment {

    String idnegozio;
    Button butt;
    String ean,marchio,prodotto,prezzo,quantita;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inserimento_prodotti, container, false);
        if (getArguments() != null) {
            idnegozio = getArguments().getString("id");
        }

        butt=(Button)view.findViewById(R.id.buttonaggiungi);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ean=((TextView)view.findViewById(R.id.etEan)).getText().toString();
                marchio=((TextView)view.findViewById(R.id.etMarchio)).getText().toString();
                prodotto=((TextView)view.findViewById(R.id.etProdotto)).getText().toString();
                prezzo=((TextView)view.findViewById(R.id.etPrezzo)).getText().toString();
                quantita=((TextView)view.findViewById(R.id.etQuantita)).getText().toString();
                Background b=new Background(getContext());
                b.execute("insert_product",ean,marchio,prodotto,prezzo,quantita,idnegozio);
            }
        });

        return view;
    }

}
