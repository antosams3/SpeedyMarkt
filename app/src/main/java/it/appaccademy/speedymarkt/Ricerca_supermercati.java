package it.appaccademy.speedymarkt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Ricerca_supermercati extends Fragment {
    onFragmentBtnSelected listener;
    EditText inserimentosup;
    Button cerca;
    String supermercato;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ricerca_supermercati, container, false);
        inserimentosup=((EditText)view.findViewById(R.id.cerca));
        cerca=view.findViewById(R.id.button2);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity m1= (MainActivity) getActivity();
                supermercato=inserimentosup.getText().toString();
                listener.onButtonSelcted(supermercato);
            }
        });
        return view;
    }


    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        try  {
            listener = (onFragmentBtnSelected) context;

        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "ciao");
        }

    }

    public interface onFragmentBtnSelected{
        public void onButtonSelcted(String s);
    }


}
