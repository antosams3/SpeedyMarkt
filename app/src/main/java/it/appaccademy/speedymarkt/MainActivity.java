package it.appaccademy.speedymarkt;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Ricerca_supermercati.onFragmentBtnSelected,Prodotti.onFragmentBtnSelected2, Carrello.onFragmentBtnSelected3, Pagamento.onFragmentBtnSelected3, Pagamento.onFragmenBtnSelected4{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    public static NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String negozio;
    public static ArrayList<singleRow> lista;
    public static String email;
    String negozio_sel, nome, via,id,esitoInsert,tipo,id_ordine;
    public static int  carrello_count_number=0;
    public static TextView carrello_counter;
    String cond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // prelievo dati
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
            tipo = extras.getString("tipo");
            negozio_sel = extras.getString("negozio_sel");
            nome = extras.getString("nome");
            via = extras.getString("via");
            cond= extras.getString("from");
            id=extras.getString("id");
            id_ordine=extras.getString("id_ordine");
        }


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //carica il fragment di default
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new Ricerca_supermercati());
        fragmentTransaction.commit();




        if(id_ordine!=null){
            OrdineSelezionato fragmenti = new OrdineSelezionato();
            Bundle args = new Bundle();
            args.putString("id_ordine", id_ordine);
            fragmenti.setArguments(args);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragmenti);
            fragmentTransaction.commit();
        }


        if(negozio_sel!=null&&cond.equals("FALSE")){
            Prodotti fragmenti = new Prodotti();
            Bundle args = new Bundle();
            args.putString("email", email);
            args.putString("negozio_sel",negozio_sel);
            args.putString("nome",nome);
            args.putString("via",via);
            fragmenti.setArguments(args);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragmenti);
            fragmentTransaction.commit();
        }
        if(negozio_sel!=null&&cond.equals("TRUE")){
            nuovoProdotto fragmenti = new nuovoProdotto();
            Bundle args = new Bundle();
            args.putString("email", email);
            args.putString("negozio_sel",negozio_sel);
            args.putString("nome",nome);
            args.putString("id",id);
            fragmenti.setArguments(args);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragmenti);
            fragmentTransaction.commit();
        }


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if(item.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new Ricerca_supermercati());
            fragmentTransaction.commit();

        }
        if(item.getItemId() == R.id.profilo){
            Profilo fragment = new Profilo();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragment);
            fragmentTransaction.commit();


        }

        if(item.getItemId() == R.id.carrello) {
            if (carrello_count_number != 0) {
                Carrello fragment = new Carrello();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, fragment);
                fragmentTransaction.commit();
            } else Toast.makeText(this, "Devi prima selezionare dei prodotti!", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId() == R.id.notifiche){
            Pagamento fragment = new Pagamento();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragment);
            fragmentTransaction.commit();
        }

        if(item.getItemId() == R.id.esci){
            Intent intent = new Intent(this, SchermataIniziale.class);
            startActivity(intent);


        }

        if(item.getItemId() == R.id.inserisci_attivita){
            Inserimento_attivita fragment = new Inserimento_attivita();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragment);
            fragmentTransaction.commit();

        }

        if(item.getItemId() == R.id.inserisci_prodotto){
            Inserimento_prodotti fragment = new Inserimento_prodotti();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragment);
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.visualizza_ordini){

            ElencoOrdini.spinnerArray.clear();
            ElencoOrdini fragment = new ElencoOrdini();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragment);
            fragmentTransaction.commit();
        }
        return true;
    }

    @Override
    public void onButtonSelcted(String s) {
        ElencoSupermercati fragmento = new ElencoSupermercati();
        Bundle args = new Bundle();
        args.putString("negozio",s);
        fragmento.setArguments(args);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragmento);
        fragmentTransaction.commit();
    }
    @Override
    public void ongoCarrello(String nome, String via) {
        Carrello fragmento = new Carrello();
        Bundle args = new Bundle();
        args.putString("email", email);
        args.putString("nome", nome);
        args.putString("via", via);
        fragmento.setArguments(args);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragmento);
        fragmentTransaction.commit();

    }


    public static void  Show_Counter(int carrcount_number) {
    if(carrcount_number>0) {
       carrello_counter.setText(carrcount_number+"");
    } else {
        carrello_counter.setText("");
    }


    }


    @Override
    public void ongoPagamento(String idOrdine, String email, String prezzoNonArr, String nome, String via) {
        System.out.println("DATI DA IN MAIN DA PASSARE AL BACKGROUNDWORKER MAIN: "+email+" "+prezzoNonArr);
        Pagamento fragmento = new Pagamento();
        Bundle args = new Bundle();
        args.putString("idOrdine", idOrdine);
        args.putString("email", email);
        args.putString("prezzoNonArr", prezzoNonArr);
        args.putString("nome", nome);
        args.putString("via", via);
        fragmento.setArguments(args);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragmento);
        fragmentTransaction.commit();
    }

    @Override
    public void ongoAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("SpeedyMarkt:");
        alertDialog.setMessage("Grazie per aver testato la beta di SpeedyMarkt!\n\nPresto nuovi aggiornamenti.");
        alertDialog.show();
    }

    @Override
    public void ongoCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0804742983"));
        startActivity(intent);
    }
}