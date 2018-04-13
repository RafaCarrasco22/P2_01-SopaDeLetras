/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rafael
 */
public class Gestor {
    private Sopita sopa;
    private File inArchivo;
    private File outArchivo;
    private FileReader lector;
    private FileWriter escritor;
    private String[] palabras;
    private Integer avances;
    
    public Gestor(String ruta) throws FileNotFoundException, IOException {
        inArchivo = new File(ruta);
        outArchivo = new File("output.txt");
        lector = new FileReader(inArchivo);
        escritor = new FileWriter(outArchivo);
        sopa = this.enmarcar();
        avances = 0;
    }


    public Sopita getSopa(){
        return sopa;
    }

    public File getiArchivo(){
        return inArchivo;
    }

    public File getoArchivo(){
        return outArchivo;
    }

    public FileReader getLector(){
        return lector;
    }

    public FileWriter getEscritor(){
        return escritor;
    }

    public String[] getPalabras(){
        return palabras;
    }

    public synchronized Integer getProgress() {
        return avances;
    }
    
    private char[][] leerArchivo() throws IOException {
        int c = 0;
        int aux=0;
        int numFilas = 0, numColumnas = 0, numPalabras = 0;
        String str = "";
        this.palabras = null;
        int pos = 0;
        boolean flag = true;
        char [][] matriz = null;
        while (lector.ready()) {
            c = lector.read();
            if (c == 32 || c == 10 || c == 13){
                switch (aux) {
                    case 0:
                        numColumnas = Integer.valueOf(str);
                        break;
                    case 1:
                        numFilas = Integer.valueOf(str);
                        break;
                    case 2:
                        numPalabras = Integer.valueOf(str);
                        break;
                    default:
                        if(flag) {
                            if(this.palabras==null) {
                                this.palabras = new String [numPalabras];
                            }
                            if (!str.equals("")){
                                this.palabras[pos]=str;
                                pos++;
                            }
                            if(pos==numPalabras){
                                flag=false;
                            }
                        } else {
                            if(matriz == null) {
                                matriz = new char[numFilas][numColumnas];
                            }
                            matriz[0][0] = (char)lector.read();
                            for (int k = 1; k < matriz[0].length; k++) {
                                matriz[0][k] = (char)lector.read();
                            }
                            lector.skip(2);
                            for (int k = 1; k < matriz.length; k++) {
                                for (int l = 0; l < matriz[k].length; l++) {
                                    matriz[k][l] = (char)lector.read();
                                }
                                lector.skip(2);
                            }
                        }   break;
                }
                str="";
                aux++;
            } else {
                str = str+(char)c;
            }
        }
        lector.close();
        return matriz;
    }
    
    public void escribirArchivo() throws IOException {
        String[] resultado = this.sopa.buscarPalabra(palabras);
        this.escritor.write(this.sopa.getErrores().toString());
        this.escritor.write("\r\n");
        for (String resultado1 : resultado) {
            this.escritor.write(resultado1);
            this.escritor.write("\r\n");
            this.avances++;
        }
        escritor.close();
    }
    
    private Sopita enmarcar() throws IOException {
        char [][] matriz = leerArchivo();
        Character [][] matrizResultado = new Character [(matriz.length+2)][(matriz[0].length+2)];
        for (int i = 0; i < matrizResultado.length; i++) {
            for (int j = 0; j < matrizResultado[i].length; j++) {
                if (i == 0 || i == (matrizResultado.length-1) || j == 0 || j == (matrizResultado[i].length-1)) {
                    matrizResultado[i][j] = '0';
                } else {
                    matrizResultado[i][j] = matriz[i-1][j-1];
                }
            }
        }
        Sopita soup = new Sopita(matrizResultado);
        return soup;
    }

}
