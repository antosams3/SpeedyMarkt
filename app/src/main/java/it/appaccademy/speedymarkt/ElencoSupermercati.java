package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ElencoSupermercati extends Fragment {

    ListView elenco;
    static ArrayList<singleRow> vettore;
    String negozio;
    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_supermercati, container, false);

        if (getArguments() != null){
            negozio = getArguments().getString("negozio");
        }

        vettore=new ArrayList<>();
        System.out.println("Sono qui 3 " + negozio);
        BackgroundJSON b=new BackgroundJSON();
        b.execute("elenco",negozio);//bisognerà passare variabile negozio nel background
        elenco=(ListView)view.findViewById(R.id.listview_elencosupermercati);
        elenco.setAdapter(new customAdapter(getActivity(), vettore));


    return view;
    }
}


class singleRow {
     String Nome;
     String Via;
     String Civico;


    singleRow(String nome,String via,String civico){
        this.Nome=nome;
        this.Via=via+" n°"+civico;

    }

    public String getNome(){
        return Nome;
    }
    public String getVia(){
        return Via;
    }
    public String toString(){
        return Nome+" "+Via;
    }
}

class customAdapter extends BaseAdapter {
    ArrayList<singleRow> list;
    Context c;

    public customAdapter(Context context,ArrayList<singleRow> L) {
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
            convertView=layoutInflater.inflate(R.layout.singlerow,parent,false);
        }
        //View row=layoutInflater.inflate(R.layout.singlerow,parent,false);
        TextView nome=(TextView)convertView.findViewById(R.id.nome);
        TextView via=(TextView)convertView.findViewById(R.id.via);
        singleRow tmp=list.get(position);
        nome.setText(tmp.Nome);
        via.setText(tmp.Via);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Hai selezionato elemento"+position,Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


}
