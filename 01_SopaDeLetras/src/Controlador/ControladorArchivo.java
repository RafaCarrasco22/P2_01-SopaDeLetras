/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Rafael
 */
public class ControladorArchivo {
    private Gestor gestor;
    
    public void abrirArchivo(File archivo) throws IOException {
        this.gestor = new Gestor(archivo.getPath());   
    }
    
    public Gestor getGestorArchivos(){
        return gestor;
    }
    
    public void generarSalida() throws IOException {
        this.gestor.escribirArchivo();
    }
}