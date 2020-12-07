package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Profilo extends Fragment {
    public static TextView TvAnagNome;
    public static TextView TvAnagCognome;
    public static TextView TvAnagEmail;
    public static TextView TvAnagDatanasc;
    public static TextView TvAnagCarta;
    public static TextView TvAnagScadenza;
    public static TextView TvAnagTitolare;
    static String email;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilo, container, false);

        TvAnagNome = view.findViewById(R.id.tvAnagNome);
        TvAnagCognome = view.findViewById(R.id.tvAnagCognome);
        TvAnagDatanasc = view.findViewById(R.id.tvAnagDatanasc);
        TvAnagEmail = view.findViewById(R.id.tvAnagEmail);
        TvAnagTitolare = view.findViewById(R.id.tvAnagTitolare);
        TvAnagCarta =  view.findViewById(R.id.tvAnagCarta);
        TvAnagScadenza =  view.findViewById(R.id.tvAnagScadenza);

        //Thread
        BackgroundJSON process = new BackgroundJSON();
        process.execute(MainActivity.email);
        return view;
    }
}