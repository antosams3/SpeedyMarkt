package it.appaccademy.speedymarkt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class ElencoOrdini extends Fragment {
    public static ArrayList<singleRowOrdine> vettoreordine;
    public static ListView elencordini;
    public static ArrayList<String> spinnerArray=new ArrayList<>();
    public static ArrayList<singleRow> elencoatt=new ArrayList<>();
    public static String selected;
    Spinner Sel_Attivita;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_ordini, container, false);
        vettoreordine=new ArrayList<singleRowOrdine>();
        elencordini=(ListView)view.findViewById(R.id.listview_elencordini);

        //Gestione spinner

        spinnerArray.add("Tutte le attività"); // vedere come gestire

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sel_Attivita = (Spinner) view.findViewById(R.id.spinnerattivita);
        Sel_Attivita.setAdapter(adapter);


        Sel_Attivita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selected=Sel_Attivita.getSelectedItem().toString();
                for(int i=0;i<elencoatt.size();i++){
                    if(elencoatt.get(i).getNome().equals(selected)){
                        selected=elencoatt.get(i).getId();
                    }
                }
                vettoreordine.clear();
                if(!selected.equals("Tutte le attività")){
                    Lavoratore process = new Lavoratore(getContext());
                    process.execute("elenco_ordini",selected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("niente ");
            }

        });

        Lavoratore riempispinner= new Lavoratore(getContext());
        riempispinner.execute("elencosupermercati",MainActivity.email,"TRUE");
        return view;
    }
}
 class singleRowOrdine{
    String idordine;
    String emailutente;
    String data2;
    String ora2;

    public singleRowOrdine(String idordine, String emailutente, String data2, String ora2){
        this.idordine = idordine;
        this.emailutente =emailutente;
        this.data2 =data2;
        this.ora2 =ora2;
    }
    public String getIdOrdine(){
        return idordine;
    }
    public String getemail(){
        return emailutente;
    }
    public String getData2(){return data2;}
    public String getOra2(){return ora2;}

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
            convertView=layoutInflater.inflate(R.layout.singlerow_ordine,parent,false);
        }
        TextView id=convertView.findViewById(R.id.sro_idordine);
        TextView email=convertView.findViewById(R.id.sro_utente);
        singleRowOrdine tmp=list.get(position);
        id.setText("ID: "+tmp.idordine);
        email.setText("UTENTE: "+tmp.emailutente+"       DATA: "+tmp.data2+" ORA: "+tmp.ora2);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Hai selezionato ordine : "+position,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(c,MainActivity.class);
                i.putExtra("id_ordine",list.get(position).getIdOrdine());
                i.putExtra("email",MainActivity.email);
                c.startActivity(i);


            }
        });

        return convertView;
    }

}
