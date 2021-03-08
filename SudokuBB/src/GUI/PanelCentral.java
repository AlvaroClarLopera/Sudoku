/*
Clase que actua de panel en el que se pinta el tablero NxN
 */
package GUI;

import Datos.Soluciones;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Datos.Tablero;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PanelCentral extends JPanel implements MouseListener{

    private Tablero tab;
    private Tablero solucionActual;
    private Soluciones soluciones;
    private boolean todas;
    
    public PanelCentral(Tablero tab,Soluciones sol) {
        this.tab = tab;
        this.soluciones = sol;
    }
    
    
    public void repintar(int mode){
        todas = (mode != 0);
        repaint();
    }

    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
    @Override
    public void paint(Graphics g) {
        
        
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(0, 0, 0));
        
        //Pintamos el tablero
//        g.setColor(new Color(0, 0, 0));
        if (!todas){
            int dim = tab.getDIMENSION();
            g.setFont(new Font("Arial", Font.BOLD, (320/dim)));
            //calculamos el ancho y alto de la casilla
            int ancho = this.getWidth() / dim;
            int alto = this.getHeight() / dim;
            int offset_ancho = 200/dim;
            int offset_altura = 160/dim;
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    g2d.setStroke(new BasicStroke(1));
                    g.drawRect(j * ancho, i * alto, ancho, alto);
                    if (tab.getElement(i,j) != 0) g.drawString(String.valueOf(tab.getElement(i, j)), j * ancho + offset_ancho, i * alto + alto - offset_altura); 
                    if (i % Math.sqrt(tab.getDIMENSION()) == 0 && j % Math.sqrt(tab.getDIMENSION()) == 0){
                        g2d.setStroke(new BasicStroke(6));
                        if ((int) Math.sqrt(tab.getDIMENSION()) == 1){
                            g2d.drawRect(j * ancho, i * alto, ancho, alto);
                        }
                        else g2d.drawRect(j * ancho, i * alto, (int) Math.sqrt(tab.getDIMENSION()) * ancho, (int) Math.sqrt(tab.getDIMENSION())  * alto);
                    }
                }
            }
        }else if (todas && soluciones.getMaxNSol() > 0) {     
            System.out.println("Nº de solución actual: "+(soluciones.getSolActual()+1));
            int dim = soluciones.getSolucionActual().getDIMENSION();
            solucionActual = soluciones.getSolucionActual();
            g.setFont(new Font("Arial", Font.BOLD, (320/dim)));
            //calculamos el ancho y alto de la casilla
            int ancho = this.getWidth() / dim;
            int alto = this.getHeight() / dim;
            int offset_ancho = 200/dim;
            int offset_altura = 160/dim;
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    g2d.setStroke(new BasicStroke(1));
                    g.drawRect(j * ancho, i * alto, ancho, alto);
                    if (solucionActual.getElement(i,j) != 0) g.drawString(String.valueOf(solucionActual.getElement(i, j)), j * ancho + offset_ancho, i * alto + alto - offset_altura); 
                    if (i % Math.sqrt(solucionActual.getDIMENSION()) == 0 && j % Math.sqrt(solucionActual.getDIMENSION()) == 0){
                        g2d.setStroke(new BasicStroke(6));
                        if ((int) Math.sqrt(solucionActual.getDIMENSION()) == 1){
                            g2d.drawRect(j * ancho, i * alto, ancho, alto);
                        }
                        else g2d.drawRect(j * ancho, i * alto, (int) Math.sqrt(solucionActual.getDIMENSION()) * ancho, (int) Math.sqrt(solucionActual.getDIMENSION())  * alto);
                    }
                }
            }
        } else if (soluciones.getMaxNSol() == 0){
            int dim = tab.getDIMENSION();
            g.setFont(new Font("Arial", Font.BOLD, (320/dim)));
            //calculamos el ancho y alto de la casilla
            int ancho = this.getWidth() / dim;
            int alto = this.getHeight() / dim;
            int offset_ancho = 200/dim;
            int offset_altura = 160/dim;
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    g2d.setStroke(new BasicStroke(1));
                    g.drawRect(j * ancho, i * alto, ancho, alto);
                    if (tab.getElement(i,j) != 0) g.drawString(String.valueOf(tab.getElement(i, j)), j * ancho + offset_ancho, i * alto + alto - offset_altura); 
                    if (i % Math.sqrt(tab.getDIMENSION()) == 0 && j % Math.sqrt(tab.getDIMENSION()) == 0){
                        g2d.setStroke(new BasicStroke(6));
                        if ((int) Math.sqrt(tab.getDIMENSION()) == 1){
                            g2d.drawRect(j * ancho, i * alto, ancho, alto);
                        }
                        else g2d.drawRect(j * ancho, i * alto, (int) Math.sqrt(tab.getDIMENSION()) * ancho, (int) Math.sqrt(tab.getDIMENSION())  * alto);
                    }
                }
            }
        }
        
    }
    
    public void setDatos(Tablero tab, Soluciones soluciones) {
        this.tab = tab;
        this.soluciones = soluciones;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if (todas) {
            solucionActual = soluciones.nextSolucion();
            repintar(1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
