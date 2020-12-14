package it.appaccademy.speedymarkt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        //Gestione tamarrate
        Animation slideinleft1 = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left);
        TextView textview19= (TextView) view.findViewById(R.id.textView19);
        TextView textview44= (TextView) view.findViewById(R.id.textView44);
        TextView textview43= (TextView) view.findViewById(R.id.textView43);
        TextView textview47= (TextView) view.findViewById(R.id.textView47);
        TextView textview46= (TextView) view.findViewById(R.id.textView46);
        textview19.startAnimation(slideinleft1);
        textview44.startAnimation(slideinleft1);
        textview43.startAnimation(slideinleft1);
        textview47.startAnimation(slideinleft1);
        textview46.startAnimation(slideinleft1);
        Animation slideinleft2 = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left2);
        TextView textview21= (TextView) view.findViewById(R.id.textView21);
        TextView textview24= (TextView) view.findViewById(R.id.textView24);
        TextView textview27= (TextView) view.findViewById(R.id.textView27);
        TextView textview26= (TextView) view.findViewById(R.id.textView26);
        TextView textview23= (TextView) view.findViewById(R.id.textView23);
        TextView textview25= (TextView) view.findViewById(R.id.textView25);
        textview21.startAnimation(slideinleft2);
        textview24.startAnimation(slideinleft2);
        textview27.startAnimation(slideinleft2);
        textview26.startAnimation(slideinleft2);
        textview23.startAnimation(slideinleft2);
        textview25.startAnimation(slideinleft2);
        Animation slideinleft3 = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left3);
        TextView textview22= (TextView) view.findViewById(R.id.textView22);
        TextView textview42= (TextView) view.findViewById(R.id.textView42);
        TextView textview40= (TextView) view.findViewById(R.id.textView40);
        TextView textview41= (TextView) view.findViewById(R.id.textView41);
        TextView textview38= (TextView) view.findViewById(R.id.textView38);
        textview22.startAnimation(slideinleft3);
        textview42.startAnimation(slideinleft3);
        textview40.startAnimation(slideinleft3);
        textview41.startAnimation(slideinleft3);
        textview38.startAnimation(slideinleft3);

        inserimentosup=((EditText)view.findViewById(R.id.cerca));
        cerca=view.findViewById(R.id.button2);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
