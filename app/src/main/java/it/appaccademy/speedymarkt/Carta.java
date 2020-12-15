package it.appaccademy.speedymarkt;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Carta extends AppCompatActivity {
    private static final String TAG = "Carta";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDate;
    String email;
    String tipo;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carta);

        //Prelievo del dato email
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
            tipo = extras.getString("tipo");
            System.out.println("prelievo da reg "+tipo);
        }



        //Gestione calendario
        mDisplayDate = findViewById(R.id.scadenza);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Carta.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/yyyy: " + month + "/" + year);
                String date = + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };



    }

    public void checkcarta(View view) {
        String scadenza = ((TextView) findViewById(R.id.scadenza)).getText().toString();
        String titolare = ((EditText) findViewById(R.id.titolare)).getText().toString();
        String numero = ((EditText) findViewById(R.id.numero)).getText().toString();
        String cvv = ((EditText) findViewById(R.id.cvv)).getText().toString();
        //Thread
        Background backgroundWorker = new Background(this);
        backgroundWorker.execute("card", email, titolare, numero, scadenza, cvv, tipo);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

