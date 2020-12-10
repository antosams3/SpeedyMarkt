package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Carrello extends Fragment {
    TextView prezzo;
    String email;
    ListView elencocarrello;
    public static  ArrayList<singleRowProdotto> carrello=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_carrello, container, false);
        int tot=0;
        for(int i=0;i<carrello.size();i++){
            tot=tot+carrello.get(i).getQuantita();
        }
        int prezzotot=0;
        for(int i=0;i<carrello.size();i++){
            prezzotot = prezzotot + ((carrello.get(i).getQuantita()) * Integer.parseInt(carrello.get(i).getPrezzo()));
        }
        System.out.println(prezzotot);
        System.out.println("tot: "+tot);

        prezzo = (TextView) view.findViewById(R.id.price);
        prezzo.setText(String.valueOf(prezzotot)+"€");

        elencocarrello = (ListView) view.findViewById(R.id.listview_elencocarrello);
        elencocarrello.setAdapter(new customAdapterCarrello(getContext(), Carrello.carrello));

        return view;
    }

    class customAdapterCarrello extends BaseAdapter {

        ArrayList<singleRowProdotto> list;
        Context c;


        public customAdapterCarrello(Context context, ArrayList<singleRowProdotto> L) {
            c = context;
            list = L;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(c);
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.singlerow_prodotto, parent, false);
            }

            TextView nome = (TextView) convertView.findViewById(R.id.nome);
            TextView marchio = (TextView) convertView.findViewById(R.id.marchio);
            TextView qt = (TextView) convertView.findViewById(R.id.quantita);
            TextView prezzo = (TextView) convertView.findViewById(R.id.prezzo);
            qt.setText(String.valueOf(list.get(position).getQuantita()));
            singleRowProdotto tmp=list.get(position);
            nome.setText(tmp.Nome);
            marchio.setText(tmp.Marchio);
            prezzo.setText((String.valueOf(list.get(position).getPrezzo()))+"€");
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Hai selezionato : " + list.get(position).getNome(), Toast.LENGTH_SHORT).show();

                }
            });
            ImageButton add = (ImageButton) convertView.findViewById(R.id.buttonadd);
            ImageButton rem = (ImageButton) convertView.findViewById(R.id.buttonremove);
            add.setVisibility(View.GONE);
            rem.setVisibility(View.GONE);

            return convertView;
        }
    }
}



