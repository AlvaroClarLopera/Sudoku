/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import sudokubb.SudokuBB;

/**
 *
 * @author Usuario
 */
public class CambiarDimension extends JPanel implements ActionListener{
    
    private JTextField numero;
    private SudokuBB prog;
    private String mensaje;
    
    public CambiarDimension(SudokuBB prog, String m, int dim) {
        this.prog = prog;
        mensaje = m;
//        numero = new JTextField(8);
//        numero.setText(Integer.toString(dim));
//        numero.addActionListener(this);
//        this.add(numero);
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, new JLabel("Elige la dimensión del sudoku: "));
        hacerRadios(dim);
    }

    private void hacerRadios(int d){
        ButtonGroup group = new ButtonGroup();
        JPanel botonesrad = new JPanel();
        JLabel imbut;
        botonesrad.setLayout(new BoxLayout(botonesrad, BoxLayout.Y_AXIS));
        for (int i = 0; i < 3; i++) {            
            JRadioButton rbut = new JRadioButton("");
            switch (i){
                case 0: imbut = new JLabel("4x4"); break;
                case 1: imbut = new JLabel("9x9"); break;
                case 2: imbut = new JLabel("16x16"); break;
//                case 4: imbut = new JLabel("Distribución centrada en dos puntos"); break;
                default: imbut = new JLabel("4x4"); break;
            }
//            if (i == 0){
//                imbut = new JLabel("Distribución uniforme");
//            } 
//            else  {
//                imbut = new JLabel("Distribución gaussiana");
//            }

            rbut.setActionCommand(getDistribution(i));
            rbut.addActionListener(this);
            group.add(rbut);
            JPanel pan = new JPanel();
            pan.setLayout(new FlowLayout());
            pan.add(rbut);
            pan.add(imbut);
            botonesrad.add(pan);
            if(d == i) {               
                rbut.setSelected(true); //marcamos el boton de la pieza actual
            }
        }
        this.add(BorderLayout.CENTER, botonesrad);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        prog.notificar(mensaje + e.getActionCommand());
    }
    
    private String getDistribution(int i){
        return Integer.toString(i);
    }
}
