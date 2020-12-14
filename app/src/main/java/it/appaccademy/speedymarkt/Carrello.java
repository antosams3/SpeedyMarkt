package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.UUID;

public class Carrello extends Fragment {
    private onFragmentBtnSelected3 listener;
    TextView prezzo;
    public static String email;
    ListView elencocarrello;
    TextView datisuperm;
    public static String nome, via;
    UUID code;
    String idOrdine;
    public static double prezzotot;
    public static  ArrayList<singleRowProdotto> carrello=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_carrello, container, false);


        if (getArguments() != null) {
            email = getArguments().getString("email");
        }

        if (getArguments() != null) {
           nome = getArguments().getString("nome");
        }
        if (getArguments() != null) {
            via = getArguments().getString("via");
        }

        int tot=0;
        for(int i=0;i<carrello.size();i++){
            tot=tot+carrello.get(i).getQuantita();
        }
        prezzotot=0.0;
        for(int i=0;i<carrello.size();i++){
            prezzotot = prezzotot + ((carrello.get(i).getQuantita()) * Double.parseDouble(carrello.get(i).getPrezzo()));
        }
        System.out.println(prezzotot);
        System.out.println("tot: "+tot);

        String prezzoNonArr = String.valueOf(prezzotot);

        datisuperm = (TextView) view.findViewById(R.id.datisuperm);
        datisuperm.setText("Stai ordinando da: "+nome+", "+via);
        prezzo = (TextView) view.findViewById(R.id.price);
        prezzo.setText((String.format("%.3g",prezzotot)+"€"));

        elencocarrello = (ListView) view.findViewById(R.id.listview_elencocarrello);
        elencocarrello.setAdapter(new customAdapterCarrello(getContext(), Carrello.carrello));

        Button buttonpagamento = view.findViewById(R.id.buttonpagamento);
        buttonpagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al click su "vai al pagamento" viene generato automaticamente l'id dell'ordine
                code = UUID.randomUUID();
                idOrdine = code.toString().substring(0,8);
                listener.ongoPagamento(idOrdine, email, prezzoNonArr, nome, via);
            }
        });

        System.out.println("DATI DA PASSARE A MAIN: "+email+" "+prezzoNonArr);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentBtnSelected3) {
            listener = (onFragmentBtnSelected3) context;
        } else {
            throw new ClassCastException(context.toString() +"ciao");
        }
    }

    public interface onFragmentBtnSelected3{
        public void ongoPagamento(String idOrdine, String email, String prezzoNonArr, String nome, String via);
    }

    class customAdapterCarrello extends BaseAdapter {

        public ArrayList<singleRowProdotto> list;
        Context c;


        public customAdapterCarrello(Context context, ArrayList<singleRowProdotto> L) {
            c = context;
            list = L;
        }


        public ArrayList<singleRowProdotto> getList(){return list;}


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

            TextView nome = (TextView) convertView.findViewById(R.id.sro_idordine);
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



