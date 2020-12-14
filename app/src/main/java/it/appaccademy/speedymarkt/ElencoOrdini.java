package it.appaccademy.speedymarkt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class ElencoOrdini extends Fragment {
    public static ArrayList<singleRowOrdine> vettoreordine;
    ListView elencordini;
    Spinner Sel_Attivita;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_ordini, container, false);
        vettoreordine=new ArrayList<singleRowOrdine>();
        elencordini=(ListView)view.findViewById(R.id.listview_elencordini);

        //Gestione spinner
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Supermercato");
        spinnerArray.add("Elettronica");
        spinnerArray.add("Alimentari");
        spinnerArray.add("Altro");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sel_Attivita = (Spinner) view.findViewById(R.id.spinner1);
        Sel_Attivita.setAdapter(adapter);


        Lavoratore process = new Lavoratore(getContext());
        process.execute();
        return view;
    }
}
 class singleRowOrdine{
    String idordine;
    String emailutente;

    public singleRowOrdine(String r,String s){
        idordine=r;
        emailutente=s;
    }
    public String getIdOrdine(){
        return idordine;
    }
    public String getemail(){
        return emailutente;
    }

}
class customAdapterOrdine extends BaseAdapter {

    ArrayList<singleRowOrdine> list;
    EventListener listener;
    Context c;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    public customAdapterOrdine(Context context,ArrayList<singleRowOrdine> L) {
        c=context;
        list=L;
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

        TextView id=(TextView)convertView.findViewById(R.id.sro_idordine);
        TextView email=(TextView)convertView.findViewById(R.id.sro_utente);
        singleRowOrdine tmp=list.get(position);
        id.setText(tmp.idordine);
        email.setText(tmp.emailutente);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Hai selezionato ordine : "+list.get(position).getIdOrdine(),Toast.LENGTH_SHORT).show();



            }
        });

        return convertView;
    }

}
