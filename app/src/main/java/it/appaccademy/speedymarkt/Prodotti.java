package it.appaccademy.speedymarkt;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static it.appaccademy.speedymarkt.MainActivity.Show_Counter;
import static it.appaccademy.speedymarkt.MainActivity.carrello_count_number;
import static it.appaccademy.speedymarkt.MainActivity.carrello_counter;
import static it.appaccademy.speedymarkt.MainActivity.navigationView;

public class Prodotti extends Fragment {
    String negozio;
    public static int quantitasum;
    onFragmentBtnSelected2 listener;
    static Button carr;
    public static ListView elenco;
    public static ArrayList<singleRowProdotto> vettore;
    public static ImageView escl;
    Button add;
    String nome, via;
    public int tot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.elenco_prodotti, container, false);
        MainActivity.carrello_count_number = 0;
        escl = (ImageView) view.findViewById(R.id.escl);
        tot=0;
        vettore = new ArrayList<singleRowProdotto>();
        elenco = (ListView) view.findViewById(R.id.listview_elencoprodotti);
        if (getArguments() != null) {
            negozio = getArguments().getString("negozio_sel");
        }
        if (getArguments() != null) {
            nome = getArguments().getString("nome");
        }
        if (getArguments() != null) {
            via = getArguments().getString("via");
        }

        carr=(Button)view.findViewById(R.id.buttonpaga1);
        carr.setVisibility(GONE);
        carr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ongoCarrello(nome, via);
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
        public void ongoCarrello(String nome, String via);
    }
}


    class singleRowProdotto {
        String Nome;
        String Marchio;
        String Prezzo;
        String Ean;
        int quantitatot;
        int quantita=0;


        singleRowProdotto(String ean, String marchio,String nome,String prezzo,int qu){
            this.Ean = ean;
            this.Nome=nome;
            this.Marchio=marchio;
            this.Prezzo=prezzo;
            this.quantitatot=qu;
        }

        public String getEan() {return Ean;}
        public String getPrezzo() {return Prezzo;}
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
            return "Ean: "+Ean+" Nome: "+Nome+" Marchio: "+Marchio+" Prezzo: "+Prezzo+" Quantita: "+quantita;
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
            TextView prezzo = (TextView) convertView.findViewById(R.id.prezzo);
            qt.setText(String.valueOf(list.get(position).getQuantita()));
            singleRowProdotto tmp=list.get(position);
            nome.setText(tmp.Nome);
            marchio.setText(tmp.Marchio);
            prezzo.setText((String.valueOf(list.get(position).getPrezzo()))+"€");
            if (list.get(position).quantitatot <= 5) {
                nome.setTextColor(Color.parseColor("#D40000"));
                marchio.setTextColor(Color.parseColor("#D40000"));
                prezzo.setTextColor(Color.parseColor("#D40000"));
                qt.setTextColor(Color.parseColor("#D40000"));
            } else {
                nome.setTextColor(Color.parseColor("#000000"));
                marchio.setTextColor(Color.parseColor("#000000"));
                prezzo.setTextColor(Color.parseColor("#000000"));
                qt.setTextColor(Color.parseColor("#000000"));
            }
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
                    if (!Carrello.carrello.contains(list.get(position))) {
                        list.get(position).addQuantita();
                        Carrello.carrello.add(list.get(position));
                    } else {
                        Carrello.carrello.remove(list.get(position));
                        list.get(position).addQuantita();
                        Carrello.carrello.add(list.get(position));
                    }

                    qt.setText(String.valueOf(list.get(position).getQuantita()));
                    System.out.println("carrello: " + Carrello.carrello.toString());

                    carrello_count_number++;
                    LayoutInflater li = LayoutInflater.from(v.getContext());
                    carrello_counter = (TextView) li.inflate(R.layout.counter_carrello, null);
                    navigationView.getMenu().findItem(R.id.carrello).setActionView(carrello_counter);
                    Show_Counter(carrello_count_number);

                        if (carrello_count_number > 0 && Prodotti.escl.getVisibility() == GONE) {
                        Prodotti.escl.setVisibility(VISIBLE);
                        Prodotti.carr.setVisibility(VISIBLE);
                        Animation slideinleft1 = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_left);
                        Animation slideinright = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_right);
                        Prodotti.carr.startAnimation(slideinleft1);
                        Prodotti.escl.startAnimation(slideinright);

                    }
                }

            });
            ImageButton rem=(ImageButton)convertView.findViewById(R.id.buttonremove);
            rem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Carrello.carrello.contains(list.get(position))){
                        Carrello.carrello.remove(list.get(position));
                        list.get(position).removeQuantita();
                        carrello_count_number--;
                        LayoutInflater li = LayoutInflater.from(v.getContext());
                        carrello_counter = (TextView)li.inflate(R.layout.counter_carrello,null);
                        navigationView.getMenu().findItem(R.id.carrello).setActionView(carrello_counter);
                        Show_Counter(carrello_count_number);
                        if (carrello_count_number > 0 && Prodotti.escl.getVisibility() == GONE) {
                            Prodotti.escl.setVisibility(VISIBLE);
                        }
                        if (carrello_count_number == 0){
                            Prodotti.escl.setVisibility(GONE);
                            Prodotti.carr.setVisibility(GONE);
                            Animation slideoutleft1 = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_left);
                            Animation slideoutright = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_right);
                            Prodotti.carr.startAnimation(slideoutleft1);
                            Prodotti.escl.startAnimation(slideoutright);
                        }
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

