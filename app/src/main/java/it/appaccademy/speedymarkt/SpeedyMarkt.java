package it.appaccademy.speedymarkt;

import android.app.Application;

public class SpeedyMarkt extends Application {

    private String email;
    private String tipo;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
