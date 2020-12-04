package it.appaccademy.speedymarkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Ricerca_supermercati.onFragmentBtnSelected {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String negozio;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        //Prelievo del dato email
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }
    }

    //Ricerca supermercato
   /** public void selezionaNegozio(View view) {
        negozio=((TextView)findViewById(R.id.ricercanegozio_input)).getText().toString();
        Intent intent = new Intent(this, ElencoSupermercati.class);
        intent.putExtra("negozio", negozio);
        startActivity(intent);
    }*/

    /** //Accesso a profilo
    public void visitaProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Accesso a pannello admin
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Accesso a carrello
    public void goCarrello(View view) {
        Intent intent = new Intent(this, Carrello.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }*/


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
            Bundle args = new Bundle();
            args.putString("email", email);
            fragment.setArguments(args);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, fragment);
            fragmentTransaction.commit();


        }

        if(item.getItemId() == R.id.carrello){

        }

        if(item.getItemId() == R.id.notifiche){

        }

        if(item.getItemId() == R.id.esci){
            Intent intent = new Intent(this, SchermataIniziale.class);
            startActivity(intent);

            
        }

        if(item.getItemId() == R.id.inserisci_attivita){

        }

        if(item.getItemId() == R.id.inserisci_prodotto){

        }
        return true;
    }

    @Override
    public void onButtonSelcted() {
        System.out.println("Sono qui 2 "+negozio);
        ElencoSupermercati fragmento = new ElencoSupermercati();
        Bundle args = new Bundle();
        args.putString("negozio", "Rocco");
        fragmento.setArguments(args);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragmento);
        fragmentTransaction.commit();

    }
    public void hitler(String v){
        System.out.println("hitler " + negozio);
        negozio=v;
    }
}