package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ElencoSupermercati extends AppCompatActivity {

    ListView elenco;
    static ArrayList<singleRow> vettore;
    String negozio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elenco_supermercati);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            negozio = extras.getString("negozio");
        }
        vettore=new ArrayList<>();

        Background b=new Background(this);
        b.execute(); //bisognerà passare variabile negozio nel background
        elenco=(ListView)findViewById(R.id.listview_elencosupermercati);
        elenco.setAdapter(new customAdapter(this,vettore));
    }
}


class singleRow {
    String Nome;
    String Via;


    singleRow(String categoria, String nome,String via,String civico){
        this.Nome=nome;
        this.Via=via;
        this.Via=via+" n°"+civico;

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

        return convertView;
    }
}