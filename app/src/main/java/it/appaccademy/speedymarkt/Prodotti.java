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
    public static int quantitasum;
    onFragmentBtnSelected2 listener;
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
        Button carr=(Button)view.findViewById(R.id.buttoncarrello);
        carr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ongoCarrello();
            }
        });
        WorkerProdotto process = new WorkerProdotto(getContext());
        process.execute(negozio);

        return view;
    }
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        try  {
            listener = (onFragmentBtnSelected2) context;

        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "ciao");
        }

    }

    public interface onFragmentBtnSelected2{
        public void ongoCarrello();
    }
}


    class singleRowProdotto {
        String Nome;
        String Marchio;
        String Prezzo;
        int quantitatot;
        int quantita=0;


        singleRowProdotto(String marchio,String nome,String prezzo,int qu){
            this.Nome=nome;
            this.Marchio=marchio;
            this.Prezzo=prezzo;
            this.quantitatot=qu;
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
            if(quantita<quantitatot){
                quantita++;
            }
        }
        public void removeQuantita(){
            if(quantita!=0){
                quantita--;
            }

        }
        public String toString(){
            return "Nome: "+Nome+" Marchio: "+Marchio+" Prezzo: "+Prezzo+" Quantita: "+quantita;
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
                    if(!Carrello.carrello.contains(list.get(position))){
                        list.get(position).addQuantita();
                        Carrello.carrello.add(list.get(position));
                    }else{
                        Carrello.carrello.remove(list.get(position));
                        list.get(position).addQuantita();
                        Carrello.carrello.add(list.get(position));
                    }
                    qt.setText(String.valueOf(list.get(position).getQuantita()));
                    System.out.println("carrello: "+Carrello.carrello.toString());
                }
            });
            ImageButton rem=(ImageButton)convertView.findViewById(R.id.buttonremove);
            rem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Carrello.carrello.contains(list.get(position))){
                        Carrello.carrello.remove(list.get(position));
                        list.get(position).removeQuantita();
                        if(list.get(position).getQuantita()>0){
                            Carrello.carrello.add(list.get(position));
                        }
                    }
                    qt.setText(String.valueOf(list.get(position).getQuantita()));
                    System.out.println("carrello: "+Carrello.carrello.toString());
                }

            });

            return convertView;
        }




    }

