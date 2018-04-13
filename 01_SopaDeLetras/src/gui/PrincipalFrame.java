/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controlador.ControladorArchivo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Rafael
 */
public class PrincipalFrame extends JFrame {
    private ControladorArchivo controlador;
    
    public PrincipalFrame() {
        super("Sopa de letras (SOLVER)");
        this.controlador = new ControladorArchivo();
        super.setSize(330, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        
        JPanel pnlBoton = new JPanel();
        pnlBoton.add(this.creaBotones());
        
        Imagen pnlImagen = new Imagen("/images/unnamed.png");
        pnlImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(PrincipalFrame.this,"Hola Profe :)");
            }
        });
        pnlBoton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        super.add(pnlBoton,BorderLayout.CENTER);
        
        super.add(pnlImagen, BorderLayout.PAGE_END);
        
        
        super.pack();
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
    
    
    
    
    
    private JPanel creaBotones() {
        
        JPanel pnl = new JPanel();
        JButton btnElegir = new JButton("Selecciona archivo");
        JButton btnResultado = new JButton("Obtener Resultado");
       
        
        
        JFileChooser fileChooser = new JFileChooser();
        pnl.setLayout(new FlowLayout());
        btnResultado.setEnabled(false);
        
        
        btnElegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int returnVal = fileChooser.showOpenDialog(pnl);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        controlador.abrirArchivo(fileChooser.getSelectedFile());
                        btnResultado.setEnabled(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(PrincipalFrame.this,"Ocurrio un error al abrir el archivo");
                    }
            }
            }
        });
        
        
        btnResultado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.generarSalida();
                    JOptionPane.showMessageDialog(PrincipalFrame.this,String.format("El archivo se generó con éxito en %s",controlador.getGestorArchivos().getoArchivo().getPath()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(PrincipalFrame.this,"Error inesperado");
                }
            }
        });
        
        pnl.add(btnElegir);
        pnl.add(btnResultado);
        
        return pnl;
    }
}
