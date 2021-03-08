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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sudokubb.SudokuBB;

/**
 *
 * @author Usuario
 */
public class InsertValue extends JPanel implements ActionListener {
    private JTextField numero;
    private JButton ok;
    private SudokuBB prog;
    private String mensaje;
    private int row;
    private int col;

    public InsertValue(SudokuBB n, int row, int col) {
        this.row = row;
        this.col = col;
        this.setLayout(new BorderLayout());
        prog = n;
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Escribe el número a añadir en esta casilla: "));
        numero = new JTextField(8);
        numero.addActionListener(this);
        this.add(numero);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        prog.notificar("addValue-"+Integer.parseInt(numero.getText())+"-"+row+"-"+col);
    }
    
}
