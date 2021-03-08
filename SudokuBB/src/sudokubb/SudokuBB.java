/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokubb;

import Calculadora.Calculadora;
import Datos.Soluciones;
import Datos.Tablero;
import GUI.Gui;
import GUI.Modal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AndresOverflow
 */
public class SudokuBB {

    /**
     * @param args the command line arguments
     */
    public Gui gui;
    public Tablero tablero;
    public Soluciones soluciones;
    private int NXJUAN;
    private int dim;
    private int pistas;
    public static void main(String[] args) {
        new SudokuBB().init();
        // TODO code application logic here
    }

    private void init() {
        dim = 4;
        pistas = 0;
        tablero = new Tablero(4,0);
        soluciones = new Soluciones(500);
        gui = construirInterfaz();
        gui.visualizar();
        
                
    }
    
    private Gui construirInterfaz() { 
        Gui gui = new Gui("Recorrido de una pieza", 600, 600, tablero,soluciones, this);
        gui.ponOpcion("imagenes/elijedim.png", "barra:elige-dim",
                "Elige la dimensión del tablero", "");
        gui.ponOpcion("imagenes/pistas.png", "barra:elige-pistas",
                "Elige la cantidad de pistas antes de resolver", "");
        gui.ponOpcion("imagenes/una.png", "barra:calcular-una",
                "Obtiene la primera solución sin aplicar poda", "");
        gui.ponOpcion("imagenes/todas.png", "barra:calcular-todas",
                "Obtiene todas las soluciones sin aplicar poda", "");
        gui.ponOpcion("imagenes/poda_una.png", "barra:calcular-poda-una",
                "Obtiene la primera solución aplicando poda", "");
        gui.ponOpcion("imagenes/poda_todas.png", "barra:calcular-poda-todas",
                "Obtiene todas las soluciones aplicando poda", "");
      
        return gui;
    }
    
    public void notificar(String s) {
        if (s.contentEquals("barra:calcular-una")) { 
            Calculadora calculadora = new Calculadora(tablero,soluciones,0);
            System.out.println("Calculando una solución sin poda ...");
            calculadora.start();
            try {
                calculadora.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SudokuBB.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            gui.repintar(0);
        } else if (s.contentEquals("barra:calcular-todas")){ 
            Calculadora calculadora = new Calculadora(tablero,soluciones,1);
            System.out.println("Calculando múltiples soluciones sin poda ...");
            calculadora.start();
            try {
                calculadora.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SudokuBB.class.getName()).log(Level.SEVERE, null, ex);
            }

            gui.repintar(1);
        } else if (s.contentEquals("barra:calcular-poda-una")){ 
            Calculadora calculadora = new Calculadora(tablero,soluciones,2);
            System.out.println("Calculando una solución con poda ...");
            calculadora.start();
            try {
                calculadora.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SudokuBB.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.repintar(0);
        }else if (s.contentEquals("barra:calcular-poda-todas")){ 
            Calculadora calculadora = new Calculadora(tablero,soluciones,3);
            System.out.println("Calculando múltiples soluciones con poda ...");
            calculadora.start();
            try {
                calculadora.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SudokuBB.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.repintar(1);
        } else if (s.contentEquals("barra:elige-dim")) {
            Modal ventNivel = new Modal(this, "modal:dimension-",((int) Math.sqrt(tablero.getDIMENSION()))-2,0);
            ventNivel.setVisible(true);
        } else if (s.startsWith("modal:dimension-")) {
            String aux = s.substring(s.indexOf("-") + 1);
//            tablero = new Tablero(Integer.parseInt(aux));
            dim = Integer.parseInt(aux); // COMPROBAR PISTAS POR SI ACASO
            dim = (int) Math.pow(dim+2,2);
            tablero = new Tablero(dim,pistas); 
            soluciones = new Soluciones(500);
            gui.setDatos(tablero,soluciones);
            gui.repintar(0);
        } else if (s.contentEquals("barra:elige-pistas")) {
            Modal ventNivel = new Modal(this, "modal:pistas-",pistas,1);
            ventNivel.setVisible(true);
        } else if (s.startsWith("modal:pistas-")) {
            String aux = s.substring(s.indexOf("-") + 1);
//            tablero = new Tablero(Integer.parseInt(aux));
            pistas = Integer.parseInt(aux);
            if (pistas > dim*dim/2) pistas = dim*dim/2;
            tablero = new Tablero(dim,pistas);
            soluciones = new Soluciones(500);
            gui.setDatos(tablero,soluciones);
            gui.repintar(0);
        } 
    }
}
