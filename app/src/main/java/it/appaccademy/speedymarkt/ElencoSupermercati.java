package it.appaccademy.speedymarkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class ElencoSupermercati extends Fragment  {

    public static ListView elenco;
    public static ArrayList<singleRow> vettore;
    public String negozio;
    public String email;
    public static String Nome;
    public static String Via;
    public static String Civico;
    public static String Città;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_supermercati, container, false);
        MainActivity.carrello_count_number = 0;
        Carrello.carrello.clear();
        vettore=new ArrayList<singleRow>();
        elenco=(ListView)view.findViewById(R.id.listview_elencosupermercati);


        if (getArguments() != null){
            negozio = getArguments().getString("negozio");
        }

        Lavoratore process = new Lavoratore(getContext());
        process.execute("elenco_attivita",negozio);
    return view;
    }
}


class singleRow {
     String Nome;
     String Via;
     String Civico;
     String Id;
     String Cap;
     String Città;


    singleRow(String nome,String via,String civico,String id, String cap, String città){
        this.Nome=nome;
        this.Civico=civico;
        this.Cap=cap;
        this.Città=città;
        this.Via=via+" n°"+civico+", "+cap+", "+città;
        this.Id=id;

    }
    public String getId() {
        return Id;
    }
    public String getCap() {
        return Cap;
    }
    public String getNome(){
        return Nome;
    }
    public String getVia(){
        return Via;
    }
    public String getCivico(){return Civico;}
    public String getCittà(){return Città;}
    public String toString(){
        return Nome+" "+Via;
    }
}


class customAdapter extends BaseAdapter {

    ArrayList<singleRow> list;
    EventListener listener;
    Context c;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String from="FALSE";

    public customAdapter(Context context,ArrayList<singleRow> L,String from) {
        c=context;
        list=L;
        this.from=from;
    }
    public interface EventListener {
        void onEvent(int data);
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
            convertView=layoutInflater.inflate(R.layout.singlerow,parent,false);
        }

        TextView nome=(TextView)convertView.findViewById(R.id.sro_idordine);
        TextView via=(TextView)convertView.findViewById(R.id.sro_utente);
        singleRow tmp=list.get(position);
        nome.setText(tmp.Nome);
        via.setText(tmp.Via);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Hai selezionato : "+list.get(position).getNome(),Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(c,MainActivity.class);
                    i.putExtra("negozio_sel",list.get(position).getId());
                    i.putExtra("nome", list.get(position).getNome());
                    i.putExtra("via", list.get(position).getVia());
                    //i.putExtra("email",MainActivity.email);
                    i.putExtra("from",from);
                    i.putExtra("id",list.get(position).getId());
                    c.startActivity(i);

            }
        });

        return convertView;
    }




}
