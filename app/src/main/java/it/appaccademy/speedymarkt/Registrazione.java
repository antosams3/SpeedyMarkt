package it.appaccademy.speedymarkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Registrazione extends AppCompatActivity {
    static boolean cond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);
        Switch s = (Switch)findViewById(R.id.register_switch);
        findViewById(R.id.register_piva).setClickable(false);
        findViewById(R.id.register_piva).setEnabled(false);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

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

        String newnome=((EditText) findViewById(R.id.register_nome)).getText().toString();
        String newcognome=((EditText) findViewById(R.id.register_cognome)).getText().toString();
        String newdata= ((EditText) findViewById(R.id.register_data)).getText().toString();
        String newemail = ((EditText) findViewById(R.id.register_email)).getText().toString();
        String newpassword = ((EditText) findViewById(R.id.register_password1)).getText().toString();
        String newpassword2=((EditText) findViewById(R.id.register_password2)).getText().toString();
        String newpiva=((EditText)findViewById(R.id.register_piva)).getText().toString();
        if(newpiva.equals(null)){
            newpiva="";
        }

        if(newnome.equals("")||newcognome.equals("")||newdata.equals("")||newemail.equals("")||newpassword.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Riempire campi obbligatori! ", Toast.LENGTH_LONG);
            toast.show();
        }else{
            if(!newpassword.equals(newpassword2)){
                Toast toast = Toast.makeText(getApplicationContext(), "Controlla password! ", Toast.LENGTH_LONG);
                toast.show();
            }else{
                Background backgroundWorker = new Background(this);
                backgroundWorker.execute("insert", newnome, newcognome,newdata,newemail,newpassword,newpiva);
                if(cond==true){
                    Intent intent1 = new Intent(this, SchermataIniziale.class);
                    startActivity(intent1);
                    startActivity(intent1);
                }
            }
        }
    }
}