package it.appaccademy.speedymarkt;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Pagamento extends Fragment {
    String idOrdine;

    String prezzoNonArr;
    String nome;
    String via;
    private Pagamento.onFragmentBtnSelected3 listener;
    private Pagamento.onFragmenBtnSelected4 listener2;
    boolean cond=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagamento, container, false);

        if (getArguments() != null) {
            idOrdine = getArguments().getString("idOrdine");

        }


        if (getArguments() != null) {
            prezzoNonArr = getArguments().getString("prezzoNonArr");
        }

        if (getArguments() != null) {
           nome = getArguments().getString("nome");
        }

        if (getArguments() != null) {
            via = getArguments().getString("via");
        }

        ImageView qrImage = view.findViewById(R.id.qrPlaceHolder);
        String data = idOrdine;
        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
        Bitmap qrBits = qrgEncoder.getBitmap();
        qrImage.setImageBitmap(qrBits);

        WorkerCarrello process = new WorkerCarrello(getContext());
        process.execute(idOrdine, MainActivity.email, prezzoNonArr);

        //Gestione tamarrate
        Animation slideinleft1 = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left);
        TextView textviewa1= (TextView) view.findViewById(R.id.textViewa1);
        TextView textviewa2= (TextView) view.findViewById(R.id.textViewa2);
        TextView textviewa3= (TextView) view.findViewById(R.id.textViewa3);
        TextView textviewa4= (TextView) view.findViewById(R.id.textViewa4);

        textviewa1.startAnimation(slideinleft1);
        textviewa2.startAnimation(slideinleft1);
        textviewa3.startAnimation(slideinleft1);
        textviewa4.startAnimation(slideinleft1);

        Animation slideinleft2 = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left2);
        TextView textviewb1= (TextView) view.findViewById(R.id.textViewb1);
        TextView textviewb2= (TextView) view.findViewById(R.id.textViewb2);
        TextView textviewb3= (TextView) view.findViewById(R.id.textViewb3);
        TextView textviewb4= (TextView) view.findViewById(R.id.textViewb4);

        textviewb1.startAnimation(slideinleft2);
        textviewb2.startAnimation(slideinleft2);
        textviewb3.startAnimation(slideinleft2);
        textviewb4.startAnimation(slideinleft2);

        Animation slideinleft3 = AnimationUtils.loadAnimation(this.getContext(), R.anim.slide_in_left3);
        TextView textviewc1= (TextView) view.findViewById(R.id.textViewc1);
        TextView textviewc2= (TextView) view.findViewById(R.id.textViewc2);
        TextView textviewc3= (TextView) view.findViewById(R.id.textViewc3);
        TextView textviewc4= (TextView) view.findViewById(R.id.textViewx4);

        textviewc1.startAnimation(slideinleft3);
        textviewc2.startAnimation(slideinleft3);
        textviewc3.startAnimation(slideinleft3);
        textviewc4.startAnimation(slideinleft3);

        TextView textviewprezzo = view.findViewById(R.id.price2);
        textviewprezzo.setText((String.format("%.3g",Carrello.prezzotot)+"€"));
        Button buttonpaga1 = view.findViewById(R.id.buttonpaga1);
        ImageButton buttoncall = view.findViewById(R.id.buttoncall);
        buttonpaga1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ongoAlert();
            }
        });
        buttoncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener2.ongoCall();
            }
        });

return view;    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Pagamento.onFragmentBtnSelected3) {
            listener = (Pagamento.onFragmentBtnSelected3) context;
        }

        if (context instanceof Pagamento.onFragmenBtnSelected4){
            listener2 = (Pagamento.onFragmenBtnSelected4) context;
        }

        else {
            throw new ClassCastException(context.toString() +"ciao");
        }
    }

    public interface onFragmentBtnSelected3{
        public void ongoAlert();
    }

    public interface onFragmenBtnSelected4{
        public void ongoCall();
    }
}
