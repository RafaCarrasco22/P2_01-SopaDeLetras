/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Rafael
 */
public class Sopita {
    Character [][] sopa;
    private Integer errores;
    
    public Sopita(Character [][] sopa){
        this.sopa = sopa;
        this.errores = 0;
    }
    
    public String buscarPalabra(String word){
        char inicio = word.charAt(0);
        char segundo = word.charAt(1);
        String resultado = String.format("%s (0,0) Error", word);
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if(sopa[i][j]==inicio){
                    if(sopa[i+1][j]==segundo){//Abajo
                        if(buscarAbajo(word,i,j)){
                            return String.format("%s (%d,%d) Vertical, Abajo", word,j,i);
                        }
                        
                    }
                    
                    
                    if(sopa[i-1][j]==segundo){//Arriba
                        if(buscarArriba(word,i,j)){
                            return String.format("%s (%d,%d) Vertical, Arriba", word,j,i);
                        }
                    }
                    
                    
                    if(sopa[i][j+1]==segundo){//Derecha
                        if(buscarDerecha(word,i,j)){
                            return String.format("%s (%d,%d) Horizontal, Derecha", word,j,i);
                        }
                    }
                    
                    
                    if(sopa[i][j-1]==segundo){//Izquierda
                        if(buscarIzquierda(word,i,j)){
                            return String.format("%s (%d,%d) Horizontal, Izquierda", word,j,i);
                        }
                    }
                    
                    
                    if(sopa[i-1][j+1]==segundo){//Diagonal derecha-arriba
                        if(buscarDerechaArriba(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal, Derecha, Arriba", word,j,i);
                        }
                    }
                    
                    
                    if(sopa[i+1][j+1]==segundo){//Diagonal derecha-abajo
                        if(buscarDerechaAbajo(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal, Derecha, Abajo", word,j,i);
                        }
                    }
                    
                    
                    if(sopa[i-1][j-1]==segundo){//Diagonal izquierda-arriba
                        if(buscarIzquierdaArriba(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal, Izquierda, Arriba", word,j,i);
                        }
                    }
                    
                    
                    if(sopa[i+1][j-1]==segundo){//Diagonal izquierda-abajo
                        if(buscarIzquierdaAbajo(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal, Izquierda, Abajo", word,j,i);
                        }
                    }
                    
                    
                }
            }
        }
        return resultado;
    }
    
    public String[] buscarPalabra(String[] palabras) {
        String[] resultado = new String[palabras.length];
        String error= null;
        for (int i = 0; i < palabras.length; i++) {
            error = String.format("%s (0,0) ERROR", palabras[i]);
            resultado[i] = this.buscarPalabra(palabras[i]);
            if (resultado[i].compareTo(error)==0) {
                this.errores++;
            }
        }
        return resultado;
    }
    
    private boolean buscarDerecha(String palabra, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = columna+2; i < sopa[fila].length ; i++) {
            letra=palabra.charAt(posicion);
            if (letra==sopa[fila][i]) {
                if(posicion==palabra.length()-1){
                    break;
                }
                posicion++;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    private boolean buscarIzquierda(String palabra, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = columna-2; i >= 0 ; i--) {
            letra=palabra.charAt(posicion);
            if (letra==sopa[fila][i]) {
                if(posicion==palabra.length()-1){
                    break;
                }
                posicion++;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    private boolean buscarAbajo(String palabra, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = fila+2; i < sopa.length; i++) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][columna])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarArriba(String palabra, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = fila-2; i >= 0; i--) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][columna])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarDerechaAbajo(String palabra, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        int j = columna+2;
        for (int i = fila+2; i < sopa.length; i++,j++) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarDerechaArriba (String palabra, int fila, int columna) {
        int posicion=2;
        char letra = ' ';
        int j = columna+2;
        for (int i = fila-2; i >= 0; i--,j++) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarIzquierdaArriba (String palabra, int fila, int columna) {
        int posicion=2;
        char letra = ' ';
        int j = columna-2;
        for (int i = fila-2; i >= 0; i--,j--) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarIzquierdaAbajo (String palabra, int fila, int columna) {
        int posicion=2;
        char letra = ' ';
        int j = columna-2;
        for (int i = fila+2; i < sopa.length; i++,j--) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }

    public Integer getErrores() {
        return errores;
    }
}
