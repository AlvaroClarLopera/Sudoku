/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sudokubb.SudokuBB;

/**
 *
 * @author Usuario
 */
public class ElegirNPistas extends JPanel implements ActionListener {
    private JTextField numero;
    private SudokuBB prog;
    private String mensaje;
    
     public ElegirNPistas(SudokuBB n, String m, int nPuntos) {
        prog = n;
        mensaje = m;
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Elige la cantidad de pistas antes de resolver: "));
        numero = new JTextField(8);
        numero.setText(Integer.toString(nPuntos));
        numero.addActionListener(this);
        this.add(numero);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == numero) {
            prog.notificar(mensaje+Integer.parseInt(numero.getText()));
        }
    }
}
