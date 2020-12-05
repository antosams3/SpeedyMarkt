package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Profilo extends Fragment {
    public static TextView TvAnagNome;
    public static TextView TvAnagCognome;
    public static TextView TvAnagEmail;
    public static TextView TvAnagDatanasc;
    public static TextView TvAnagCarta;
    public static TextView TvAnagScadenza;
    static String email;
    static String stringona;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilo, container, false);

        TvAnagNome = view.findViewById(R.id.tvAnagNome);
        TvAnagCognome = view.findViewById(R.id.tvAnagCognome);
        TvAnagDatanasc = view.findViewById(R.id.tvAnagDatanasc);
        TvAnagEmail = view.findViewById(R.id.tvAnagEmail);
        TvAnagCarta =  view.findViewById(R.id.tvAnagCarta);
        TvAnagScadenza =  view.findViewById(R.id.tvAnagScadenza);

        if (getArguments() != null){
            email = getArguments().getString("email");
        }

        //Thread
        BackgroundJSON process = new BackgroundJSON();
        process.execute(email);
        return view;
    }



    /**Passaggio dato email a Ricercasupermercati
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email a Accesso_admin
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email a Carrello
    public void goCarrello(View view) {
        Intent intent = new Intent(this, Carrello.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Logout
    public void logout(View view) {
        Intent intent = new Intent(this, SchermataIniziale.class);
        startActivity(intent);
    }*/
}