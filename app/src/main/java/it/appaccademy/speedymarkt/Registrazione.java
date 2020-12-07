package it.appaccademy.speedymarkt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Registrazione extends AppCompatActivity {
    private static final String TAG = "Registrazione";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);

        //Gestione calendario
        mDisplayDate = (TextView) findViewById(R.id.register_data);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Registrazione.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet (DatePicker datePicker, int year, int month, int day){
                month = month+1;
                Log.d(TAG, "onDateSet: yyyy/mm/dd: " + year + "/" + month + "/" + day);
                String date = + year + "/" + month + "/" + day;
                mDisplayDate.setText(date);
            }
        };

        //Gestione dello switch
        Switch s = (Switch) findViewById(R.id.register_switch);
        findViewById(R.id.register_piva).setClickable(false);
        findViewById(R.id.register_piva).setEnabled(false);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    findViewById(R.id.register_piva).setClickable(true);
                    findViewById(R.id.register_piva).setEnabled(true);
                } else {
                    findViewById(R.id.register_piva).setClickable(false);
                    findViewById(R.id.register_piva).setEnabled(false);
                }

            }
        });

    }


    public void controlla(View view) {

        String newnome = ((EditText) findViewById(R.id.register_nome)).getText().toString();
        String newcognome = ((EditText) findViewById(R.id.titolare)).getText().toString();
        String newdata = ((TextView) findViewById(R.id.register_data)).getText().toString();
        String newemail = ((EditText) findViewById(R.id.register_email)).getText().toString();
        String newpassword = ((EditText) findViewById(R.id.register_password1)).getText().toString();
        String newpassword2 = ((EditText) findViewById(R.id.register_password2)).getText().toString();
        String newpiva = ((EditText) findViewById(R.id.register_piva)).getText().toString();

        //Check su campi inseriti

        if (newnome.equals("") || newcognome.equals("") || newdata.equals("") || newemail.equals("") || newpassword.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Riempire campi obbligatori! ", Toast.LENGTH_LONG);
            toast.show();

        } else {

            if (!newpassword.equals(newpassword2)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Controlla password! ", Toast.LENGTH_LONG);
                toast.show();

            } else {


                Background backgroundWorker = new Background(this);
                backgroundWorker.execute("insert", newnome, newcognome, newdata, newemail, newpassword, newpiva);

            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}