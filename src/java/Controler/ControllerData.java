/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author craitson.mayer
 */
public class ControllerData {

    /**
     * Funcao para montar select com os 6 meses para frente e 6 meses para tras
     */
     public ArrayList<String> pega6MeseFrente6MeseAtras() {

        ArrayList<String> mesAnoFormatado = new ArrayList();
        Calendar calendar = Calendar.getInstance();

        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        int anoAtras = calendar.get(Calendar.YEAR);
        int anoFrente = calendar.get(Calendar.YEAR);
        int auxMesAnt = calendar.get(Calendar.MONTH);
        int auxMesFrent = calendar.get(Calendar.MONTH) + 2;

        String mesFrent = "";

        String auxMesAno1 = "";
        String auxMesAno2 = "";
        String auxMesAno3 = "";
        String auxMesAno4 = "";
        String auxMesAno5 = "";
        String auxMesAno6 = "";

        //monta 6 meses atras, e tambem adiciona o 0 na frente ed mes se for menor que 9
        for (int i = 0; i < 6; i++) {
            if (auxMesAnt == 0) {
                auxMesAnt = 12;
                anoAtras--;
            }
            if (i == 0) {

                if (auxMesAnt == 1 || auxMesAnt == 2 || auxMesAnt == 3 || auxMesAnt == 4 || auxMesAnt == 5 || auxMesAnt == 6 || auxMesAnt == 7 || auxMesAnt == 8 || auxMesAnt == 9) {
                    auxMesAno1 = "0" + auxMesAnt + "-" + anoAtras;
                } else {
                    auxMesAno1 = auxMesAnt + "-" + anoAtras;
                }
            } else if (i == 1) {
                if (auxMesAnt == 1 || auxMesAnt == 2 || auxMesAnt == 3 || auxMesAnt == 4 || auxMesAnt == 5 || auxMesAnt == 6 || auxMesAnt == 7 || auxMesAnt == 8 || auxMesAnt == 9) {
                    auxMesAno2 = "0" + auxMesAnt + "-" + anoAtras;
                } else {
                    auxMesAno2 = auxMesAnt + "-" + anoAtras;
                }
            } else if (i == 2) {
                if (auxMesAnt == 1 || auxMesAnt == 2 || auxMesAnt == 3 || auxMesAnt == 4 || auxMesAnt == 5 || auxMesAnt == 6 || auxMesAnt == 7 || auxMesAnt == 8 || auxMesAnt == 9) {
                    auxMesAno3 = "0" + auxMesAnt + "-" + anoAtras;
                } else {
                    auxMesAno3 = auxMesAnt + "-" + anoAtras;
                }
            } else if (i == 3) {

                if (auxMesAnt == 1 || auxMesAnt == 2 || auxMesAnt == 3 || auxMesAnt == 4 || auxMesAnt == 5 || auxMesAnt == 6 || auxMesAnt == 7 || auxMesAnt == 8 || auxMesAnt == 9) {
                    auxMesAno4 = "0" + auxMesAnt + "-" + anoAtras;
                } else {
                    auxMesAno4 = auxMesAnt + "-" + anoAtras;
                }
            } else if (i == 4) {

                if (auxMesAnt == 1 || auxMesAnt == 2 || auxMesAnt == 3 || auxMesAnt == 4 || auxMesAnt == 5 || auxMesAnt == 6 || auxMesAnt == 7 || auxMesAnt == 8 || auxMesAnt == 9) {
                    auxMesAno5 = "0" + auxMesAnt + "-" + anoAtras;
                } else {
                    auxMesAno5 = auxMesAnt + "-" + anoAtras;
                }
            } else if (i == 5) {
                if (auxMesAnt == 1 || auxMesAnt == 2 || auxMesAnt == 3 || auxMesAnt == 4 || auxMesAnt == 5 || auxMesAnt == 6 || auxMesAnt == 7 || auxMesAnt == 8 || auxMesAnt == 9) {
                    auxMesAno6 = "0" + auxMesAnt + "-" + anoAtras;
                } else {
                    auxMesAno6 = auxMesAnt + "-" + anoAtras;
                }
            }
            auxMesAnt--;
        }

        //Adiciona ao array 6 meses atras
        mesAnoFormatado.add(auxMesAno6);
        mesAnoFormatado.add(auxMesAno5);
        mesAnoFormatado.add(auxMesAno4);
        mesAnoFormatado.add(auxMesAno3);
        mesAnoFormatado.add(auxMesAno2);
        mesAnoFormatado.add(auxMesAno1);

        //Adiciona ao array mes atual, e adiciona o zero ao mes se for menor que 9.
        if (mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9) {

            mesAnoFormatado.add("0" + mes + "-" + ano);
        } else {
            mesAnoFormatado.add(mes + "-" + ano);
        }

        //monta 6 meses a frente
        for (int i = 0; i < 6; i++) {
            if (auxMesFrent == 13) {
                auxMesFrent = 1;
                anoFrente++;
            }

            //Coloca o zero na frente dos mese a frente
            if (auxMesFrent == 1 || auxMesFrent == 2 || auxMesFrent == 3 || auxMesFrent == 4 || auxMesFrent == 5 || auxMesFrent == 6 || auxMesFrent == 7 || auxMesFrent == 8 || auxMesFrent == 9) {
                mesFrent = "0" + auxMesFrent;
            } else {
                mesFrent = "" + auxMesFrent;
            }
            //Adiciona ao array 6 meses para frente
            mesAnoFormatado.add(mesFrent + "-" + anoFrente);
            auxMesFrent++;
        }

        return mesAnoFormatado;
    }

    /**
     * Ex: yyyy-MM-ss HH:mm:ss
     */
    public String pegaDataHora() {

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = new Date();
        String data = df1.format(dt1);

        return data;
    }

    /**
     * Pega data e hora completa Ex: yyyy-MM-ss HH:mm:ss.SSS
     */
    public String pegaDataHoraCompleta() {

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dt1 = new Date();
        String data = df1.format(dt1);

        return data;
    }

}
