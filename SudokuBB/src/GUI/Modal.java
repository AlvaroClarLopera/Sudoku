/*
Clase que implementa los diálogos correspondientes a cada opción (pieza, dimensión)
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

import sudokubb.SudokuBB;



public class Modal extends JDialog implements ActionListener {

    private JButton ok;
    private SudokuBB prog;

    public Modal(SudokuBB p, String m, int d,int mode) {
        prog = p;
        constructA();
        if (mode == 0) this.add(new CambiarDimension(prog, m, d), BorderLayout.CENTER);
        else this.add(new ElegirNPistas(prog, m, d), BorderLayout.CENTER);
        
        constructB();
    }
    

    private void constructA() {
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    private void constructB() {
        ok = new JButton("Ok");
        ok.addActionListener(this);
        this.add(ok, BorderLayout.SOUTH);
        this.pack();
        this.setLocation(300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            this.dispose();
        }
    }
}
