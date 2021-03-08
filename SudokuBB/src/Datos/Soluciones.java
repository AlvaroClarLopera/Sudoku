/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author AndresOverflow
 */
public class Soluciones {
    private Tablero[] soluciones;
    private int solActual;
    private int maxNSol;
    
    public Soluciones(int sol){
        soluciones = new Tablero[sol];
        solActual = 0;
    }
    
    public Tablero getSolucion(int sol){
        return soluciones[sol];
    }
    
    public void setSolucion(Tablero tab, int sol) {
        Tablero t = Tablero.copia(tab);
        soluciones[sol] = t;
    }
    
    public void setMaxNSol(int max){
        maxNSol = max;
    }

    public int getMaxNSol() {
        return maxNSol;
    }
       
    
    public Tablero getSolucionActual(){
        return soluciones[solActual];
    }
    
    public Tablero nextSolucion(){
        Tablero solucion;
        solActual++;
        if (solActual == maxNSol) solActual = 0;
        solucion = soluciones[solActual];
        return solucion;
    }

    public int getSolActual() {
        return solActual;
    }
    
    
}
