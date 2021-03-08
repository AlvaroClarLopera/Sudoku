/*
Clase que construye la GUI
 */
package GUI;


import Datos.Soluciones;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Datos.Tablero;
import java.awt.Color;
import sudokubb.SudokuBB;



public class Gui implements ActionListener {

    private JFrame vent;
    private JToolBar barra;
    private JPanel contenedor;
    private Tablero tab;
    private Soluciones soluciones;
    private PanelCentral central;
    private SudokuBB prog;
    private int x;
    private int y;

    public Gui(String s, int x, int y, Tablero tab, Soluciones soluciones, SudokuBB prog) {
        this.x = x;
        this.y = y;
        this.tab = tab;
        this.soluciones = soluciones;
        this.prog = prog;
        vent = new JFrame(s);
        vent.setPreferredSize(new Dimension(x, y));
        crear();
    }

    public void crear() {
        contenedor = new JPanel();
        contenedor.setLayout(new BorderLayout());
        barra = new JToolBar();
        barra.setBackground(new Color(108,160,220));
        contenedor.add(BorderLayout.PAGE_START, barra);
        barra.addSeparator(new Dimension(x/2-250, 0));
        central = new PanelCentral(tab,soluciones);
        contenedor.addMouseListener(central);
        contenedor.add(BorderLayout.CENTER, central);
        vent.add(contenedor);
    }

    
    public void ponOpcion(String s1, String s2, String s3, String s4) {
        JButton bot = null;
        bot = makeNavigationButton(s1, s2, s3, s4);
        barra.add(bot);
    }

    public void visualizar() {
        vent.pack();
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vent.setVisible(true);
    }

    protected JButton makeNavigationButton(String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {
        String imgLocation = "/" + imageName;
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        URL imageURL = getClass().getResource(imgLocation);
        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }
        button.setText(altText);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String aux = e.toString();
        aux = aux.substring(aux.indexOf(",cmd=")+5,aux.indexOf(",when="));
        prog.notificar(aux);
    }
    
    public void repintar(int mode) {
        central.repintar(mode);
    }
    
    public void repaint() {
        if (vent != null) {
            vent.paint(vent.getGraphics());
        }
    }
    
    public void setDatos(Tablero tab,Soluciones soluciones) {
        this.tab = tab;
        this.soluciones = soluciones;
        central.setDatos(this.tab,this.soluciones);
    }
}
