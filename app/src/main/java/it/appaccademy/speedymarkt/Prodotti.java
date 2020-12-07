package it.appaccademy.speedymarkt;

import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Prodotti extends Fragment {
    String negozio;
    public static ListView elenco;
    public static ArrayList<singleRowProdotto> vettore;
    Button add;
    public int tot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_prodotti, container, false);
        tot=0;
        vettore = new ArrayList<singleRowProdotto>();
        elenco = (ListView) view.findViewById(R.id.listview_elencoprodotti);
        if (getArguments() != null) {
            negozio = getArguments().getString("negozio_sel");
        }
        WorkerProdotto process = new WorkerProdotto(getContext());
        process.execute(negozio);

        return view;
    }
}


    class singleRowProdotto {
        String Nome;
        String Marchio;
        String Prezzo;
        int quantita=0;


        singleRowProdotto(String marchio,String nome,String prezzo){
            this.Nome=nome;
            this.Marchio=marchio;
            this.Prezzo=prezzo;

        }

        public String getNome(){
            return Nome;
        }
        public int getQuantita(){
            return quantita;
        }
        public String getMarchio(){
            return Marchio;
        }
        public void addQuantita(){
            quantita++;
        }
        public void removeQuantita(){
            if(quantita!=0){
                quantita--;
            }

        }
        public String toString(){
            return "Nome: "+Nome+" Marchio: "+Marchio+" Prezzo: "+Prezzo;
        }
    }


    class customAdapterProdotto extends BaseAdapter {

        ArrayList<singleRowProdotto> list;
        Context c;




        public customAdapterProdotto(Context context,ArrayList<singleRowProdotto> L) {
            c=context;
            list=L;
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
            LayoutInflater layoutInflater= LayoutInflater.from(c);
            if(convertView==null){
                convertView=layoutInflater.inflate(R.layout.singlerow_prodotto,parent,false);
            }

            TextView nome=(TextView)convertView.findViewById(R.id.nome);
            TextView marchio=(TextView)convertView.findViewById(R.id.marchio);
            TextView qt=(TextView) convertView.findViewById(R.id.quantita);
            qt.setText(String.valueOf(list.get(position).getQuantita()));
            singleRowProdotto tmp=list.get(position);
            nome.setText(tmp.Nome);
            marchio.setText(tmp.Marchio);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"Hai selezionato : "+list.get(position).getNome(),Toast.LENGTH_SHORT).show();
                    //Intent i=new Intent(c,MainActivity.class);
                    //i.putExtra("negozio_sel",list.get(position).getNome());
                    //c.startActivity(i);
                }
            });
            ImageButton add=(ImageButton)convertView.findViewById(R.id.buttonadd);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).addQuantita();
                    qt.setText(String.valueOf(list.get(position).getQuantita()));
                }
            });
            ImageButton rem=(ImageButton)convertView.findViewById(R.id.buttonremove);
            rem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).removeQuantita();
                    qt.setText(String.valueOf(list.get(position).getQuantita()));
                }
            });

            return convertView;
        }




    }

