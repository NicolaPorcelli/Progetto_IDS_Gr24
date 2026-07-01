package controller;

import entity.RegistroUtenze;

public class ControllerUtenze {

    public static boolean registrazioneUtente(String nome, String cognome, String email, String password, String ruolo) {
        RegistroUtenze registro = new RegistroUtenze();
        return registro.registraUtente(nome, cognome, email, password, ruolo);
    }

    public static String accessoUtente(String email, String password) {
        RegistroUtenze registro = new RegistroUtenze();
        return registro.autentica(email, password);
    }
}