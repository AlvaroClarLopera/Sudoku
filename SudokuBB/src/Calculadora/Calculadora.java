/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import Datos.Soluciones;
import Datos.Tablero;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AndresOverflow
 */
public class Calculadora extends Thread {
    
    private Tablero tablero;
    private Soluciones soluciones;
    boolean solucion_encontrada;
    private int mode;
    private int nSoluciones;
    
    public Calculadora() {
    }

    public Calculadora(Tablero tablero, Soluciones soluciones, int mode) {
        this.tablero = tablero;
        this.soluciones = soluciones;
        this.mode = mode;
    }

    @Override
    public void run() {
        
        nSoluciones = 0;
        solucion_encontrada = false;
        long tiempo_inicial;
        long tiempo_final;
        long tiempo_total;
        
        tiempo_inicial = System.currentTimeMillis();
        switch (mode){
            case 0: sudoku_una(0,0); break;
            case 1: sudoku_todas(0,0); break;
            case 2: sudoku_poda_una(0,0); break;
            case 3: sudoku_poda_todas(0,0); break;
        }
            
        tiempo_final = System.currentTimeMillis();
        tiempo_total = tiempo_final - tiempo_inicial;
        System.out.println("El algoritmo de Backtracking ha terminado en " + (tiempo_total) + " milisegundos");
        System.out.println("Ha encontrado un total de "+nSoluciones+" soluciones."
                + "Haz click en el panel para mostrar las demás soluciones. ");
        soluciones.setMaxNSol(nSoluciones);
        
    }
    
    
  
    public void sudoku_una (int fila, int columna) {
        
        boolean casilla_libre_encontrada = false;
        int fila_actual = fila;
        int columna_actual = columna;
        int i_ramificacion = 1;
        
        
        /*
        Caso base: no nos quedan más casillas por poner, retornamos el tablero que nos han pasado (ya está completo)
        miramos si es un tablero válido, si es un tablero válido retornamos tablero, sino, null.
        */

        if (tablero.getCasillas_puestas() >= tablero.getCasillas_por_poner()) {
//            System.out.println(tablero.toString());
            if (tablero.tablero_valido()) {
                nSoluciones++;
                solucion_encontrada = true; // CON ESTO SE DETIENE CON LA PRIMERA SOLUCION
                return;
            } else {
//                System.out.println("POS NO ESTA BIEN");
                return;
            }
            
        } else {
        /*
            Caso Complejo: nos quedan más casillas por poner,
            En ese caso, miramos cual es la siguiente casilla libre, completamos y llamamos a sudoku, para complete el resto de casillas.
            */
            while (fila_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                columna_actual = 0;
                while (columna_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                    if (tablero.getElement(fila_actual, columna_actual) != 0){
                        columna_actual++;                       
                    }else{
                        casilla_libre_encontrada = true;
                        break;
                    }                       
                }                
                if (!casilla_libre_encontrada)fila_actual++;
            }
            
            tablero.setCasillas_puestas(tablero.getCasillas_puestas() + 1 );
            for (int i = 1; i < tablero.getPOSIBLES_VALORES() + 1 && !solucion_encontrada; i++) {
                tablero.setElement(fila_actual, columna_actual, i);
                sudoku_una(fila_actual, columna_actual);              
                // si es una solución válida, la retornamos,                 
            }
            if (!solucion_encontrada) {
                tablero.setCasillas_puestas(tablero.getCasillas_puestas() - 1 );
                tablero.setElement(fila_actual, columna_actual, 0);
            }
            
            

        }
        return;
    }
    
    public void sudoku_todas (int fila, int columna) {
        
        boolean casilla_libre_encontrada = false;
        int fila_actual = fila;
        int columna_actual = columna;
        int i_ramificacion = 1;
        
//        boolean solucion_encontrada = false;
        
        /*
        Caso base: no nos quedan más casillas por poner, retornamos el tablero que nos han pasado (ya está completo)
        miramos si es un tablero válido, si es un tablero válido retornamos tablero, sino, null.
        */
//        System.out.println("CUANTAS HE PUESTO YA: "+tablero.getCasillas_puestas());
//        System.out.println("CUANTAS HE DE PONER REALMENTE: "+tablero.getCasillas_por_poner());
        if (tablero.getCasillas_puestas() >= tablero.getCasillas_por_poner()) {
//            System.out.println(tablero.toString());
            
            if (tablero.tablero_valido()) {
                soluciones.setSolucion(tablero, nSoluciones);
//                System.out.println(tablero);
                nSoluciones++;
                return;
            } else {
//                System.out.println("POS NO ESTA BIEN");
                return;
            }
            
        } else {
        /*
            Caso Complejo: nos quedan más casillas por poner,
            En ese caso, miramos cual es la siguiente casilla libre, completamos y llamamos a sudoku, para complete el resto de casillas.
            */
            //calcular fila y columna para la siguiente casilla.
//            System.out.println(fila_actual);
//            System.out.println(columna_actual);
//            System.out.println("AAAAAAH");
            while (fila_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                columna_actual = 0;
                while (columna_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                    if (tablero.getElement(fila_actual, columna_actual) != 0){
                        columna_actual++;                       
                    }else{
                        casilla_libre_encontrada = true;
                        break;
                    }                       
                }                
                if (!casilla_libre_encontrada)fila_actual++;
            }
            
            tablero.setCasillas_puestas(tablero.getCasillas_puestas() + 1 );
//            System.out.println("Fila dispo: "+fila_actual);
//            System.out.println("Columna dispo: "+columna_actual);

            // ESTE CALCULA SOLO LA PRIMERA
//            for (int i = 1; i < Tablero.getPOSIBLES_VALORES() + 1 && !solucion_encontrada; i++) {
//                tablero.setElement(fila_actual, columna_actual, i);
//                sudoku(fila_actual, columna_actual);              
//                // si es una solución válida, la retornamos,                 
//            }
            for (int i = 1; i < tablero.getPOSIBLES_VALORES() + 1 ; i++) {
                tablero.setElement(fila_actual, columna_actual, i);
                sudoku_todas(fila_actual, columna_actual);              
                // si es una solución válida, la retornamos,                 
            }
            if (nSoluciones < 500) {
                tablero.setCasillas_puestas(tablero.getCasillas_puestas() - 1 );
                tablero.setElement(fila_actual, columna_actual, 0);
            }
            
            

        }
        return;
    }
    
    
    //TODO Función sudoku con poda
    
    
    
     public void sudoku_poda_una (int fila, int columna) {
        
        boolean casilla_libre_encontrada = false;
        int fila_actual = fila;
        int columna_actual = columna;
        int i_ramificacion = 1;
        
//        boolean solucion_encontrada = false;
        
        /*
        Caso base: no nos quedan más casillas por poner, retornamos el tablero que nos han pasado (ya está completo)
        miramos si es un tablero válido, si es un tablero válido retornamos tablero, sino, null.
        */
//        System.out.println("CUANTAS HE PUESTO YA: "+tablero.getCasillas_puestas());
//        System.out.println("CUANTAS HE DE PONER REALMENTE: "+tablero.getCasillas_por_poner());
        if (tablero.getCasillas_puestas() >= tablero.getCasillas_por_poner()) {
            if (tablero.tablero_valido()) {
                nSoluciones++;
                solucion_encontrada = true; // CON ESTO SE DETIENE CON LA PRIMERA SOLUCION
                return;
            } else {
//                System.out.println("POS NO ESTA BIEN");
                return;
            }
            
        } else {
        /*
            Caso Complejo: nos quedan más casillas por poner,
            En ese caso, miramos cual es la siguiente casilla libre, completamos y llamamos a sudoku, para complete el resto de casillas.
            */
            //calcular fila y columna para la siguiente casilla.
//            System.out.println(fila_actual);
//            System.out.println(columna_actual);
//            System.out.println("AAAAAAH");
            while (fila_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                columna_actual = 0;
                while (columna_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                    if (tablero.getElement(fila_actual, columna_actual) != 0){
                        columna_actual++;                       
                    }else{
                        casilla_libre_encontrada = true;
                        break;
                    }                       
                }                
                if (!casilla_libre_encontrada)fila_actual++;
            }
            
            tablero.setCasillas_puestas(tablero.getCasillas_puestas() + 1 );
//            System.out.println("Fila dispo: "+fila_actual);
//            System.out.println("Columna dispo: "+columna_actual);

            // ESTE CALCULA SOLO LA PRIMERA
            for (int i = 1; i < tablero.getPOSIBLES_VALORES() + 1 && !solucion_encontrada; i++) {
                tablero.setElement(fila_actual, columna_actual, i);
                if (tablero.comprobarElemento(fila_actual, columna_actual))sudoku_poda_una(fila_actual, columna_actual);              
                // si es una solución válida, la retornamos,                 
            }

            if (!solucion_encontrada) {
                tablero.setCasillas_puestas(tablero.getCasillas_puestas() - 1 );
                tablero.setElement(fila_actual, columna_actual, 0);
            }
            
            

        }
        return;
    }
    
     public void sudoku_poda_todas (int fila, int columna) {
        
        boolean casilla_libre_encontrada = false;
        int fila_actual = fila;
        int columna_actual = columna;
        int i_ramificacion = 1;
        
//        boolean solucion_encontrada = false;
        
        /*
        Caso base: no nos quedan más casillas por poner, retornamos el tablero que nos han pasado (ya está completo)
        miramos si es un tablero válido, si es un tablero válido retornamos tablero, sino, null.
        */
//        System.out.println("CUANTAS HE PUESTO YA: "+tablero.getCasillas_puestas());
//        System.out.println("CUANTAS HE DE PONER REALMENTE: "+tablero.getCasillas_por_poner());
        if (tablero.getCasillas_puestas() >= tablero.getCasillas_por_poner()) {
            try {
                //            System.out.println(tablero.toString());
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (tablero.tablero_valido()) {
                soluciones.setSolucion(tablero, nSoluciones);
                nSoluciones++;
                return;
            } else {
                return;
            }
            
        } else {
        /*
            Caso Complejo: nos quedan más casillas por poner,
            En ese caso, miramos cual es la siguiente casilla libre, completamos y llamamos a sudoku, para complete el resto de casillas.
            */
            //calcular fila y columna para la siguiente casilla.
//            System.out.println(fila_actual);
//            System.out.println(columna_actual);
//            System.out.println("AAAAAAH");
            while (fila_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                columna_actual = 0;
                while (columna_actual < tablero.getDIMENSION() && !casilla_libre_encontrada) {
                    if (tablero.getElement(fila_actual, columna_actual) != 0){
                        columna_actual++;                       
                    }else{
                        casilla_libre_encontrada = true;
                        break;
                    }                       
                }                
                if (!casilla_libre_encontrada)fila_actual++;
            }
            
            tablero.setCasillas_puestas(tablero.getCasillas_puestas() + 1 );
//            System.out.println("Fila dispo: "+fila_actual);
//            System.out.println("Columna dispo: "+columna_actual);

            // ESTE CALCULA SOLO LA PRIMERA
//            for (int i = 1; i < tablero.getPOSIBLES_VALORES() + 1 && !solucion_encontrada; i++) {
//                tablero.setElement(fila_actual, columna_actual, i);
//                if (tablero.comprobarElemento(fila_actual, columna_actual))sudoku_poda_una(fila_actual, columna_actual);              
//                // si es una solución válida, la retornamos,                 
//            }
            for (int i = 1; i < tablero.getPOSIBLES_VALORES() + 1 ; i++) {
                tablero.setElement(fila_actual, columna_actual, i);
                if (tablero.comprobarElemento(fila_actual, columna_actual))sudoku_poda_todas(fila_actual, columna_actual);        
                // si es una solución válida, la retornamos,                 
            }
            if (nSoluciones < 500) {
                tablero.setCasillas_puestas(tablero.getCasillas_puestas() - 1 );
                tablero.setElement(fila_actual, columna_actual, 0);
            }
            
            

        }
        return;
    }
    
}
