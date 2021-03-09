/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.Random;

/**
 *
 * @author AndresOverflow
 */
public class Tablero {
    private int DIMENSION;
    private int POSIBLES_VALORES;
    
    private int pistas; // cantidad de casillas dadas al principio del sudoku
    private int casillas_totales; // cantidad de casillas totales del sudoku
    private int casillas_por_poner; // cantidad de casillas que hay que poner a lo largo del problema
    private int casillas_puestas = 0; // cantidad de casillas puestas en el BT del sudoku
    
    private int [][] tablero;
    private boolean [][] noeditable;

    public Tablero() {}
    
    public Tablero(Tablero tab){
        this.DIMENSION = tab.DIMENSION;
        this.POSIBLES_VALORES = tab.POSIBLES_VALORES;
        this.casillas_por_poner = tab.casillas_por_poner;
        this.casillas_puestas = tab.casillas_puestas;
        this.casillas_totales = tab.casillas_totales;
        this.pistas = tab.pistas;
        this.noeditable = new boolean[this.DIMENSION][this.DIMENSION];
        this.tablero = new int [this.DIMENSION][this.DIMENSION];
        for (int i = 0; i < this.DIMENSION; i++) {
            for (int j = 0; j < this.DIMENSION; j++) {
                this.tablero[i][j] = tab.tablero[i][j];      
            }            
        }
    }
    
    public Tablero(int dim,int pistas) { 
         // cantidad de casillas totales del sudoku
        DIMENSION = dim;
        casillas_totales = DIMENSION * DIMENSION;
        POSIBLES_VALORES = DIMENSION;
        tablero = new int[DIMENSION][DIMENSION];
        noeditable = new boolean [DIMENSION][DIMENSION];
        for (int[] u: tablero) {
            for (int elem: u) {             
                elem = 0;
            }
        }
        this.pistas = pistas;
        casillas_por_poner = casillas_totales - this.pistas;
        this.rellenar_con_pistas();
        
        casillas_por_poner = casillas_totales - pistas;
    }
    
    public static Tablero copia(Tablero tab){
        return new Tablero(tab);
    }

    @Override
    public String toString() {
        String result = "";
        for (int[] u: tablero) {
            for (int elem: u) {
                result +=" " + elem + " ";
            }
            result += "\n";
        }
                
        return result;
    }
    
    
    
        public boolean comprobarElemento(int fila, int columna) {
        
        if (comprobarFila(fila, columna) == false) return false;
        if (comprobarColumna(fila, columna) == false) return false;
        if (comprobarCuadrante(fila, columna) == false) return false;
        return true;
        
        
    }
        
    
    // O(n)
    public boolean comprobarFila(int row, int column) {
        
        boolean valid_row = true;
        int index = 0;
        while (index < DIMENSION && valid_row) {
            if (tablero[row][index] == tablero[row][column]) {
                if (index != column) valid_row = false;
            }
            index++;
        }
                
        return valid_row;
        
        
    }
    
    
    public boolean comprobarColumna(int row, int column) {
        
        boolean valid_column = true;
        int index = 0;
        while (index < DIMENSION && valid_column) {
            if (tablero[index][column] == tablero[row][column]) {
                if (index != row) valid_column = false;
            }
            index++;
        }
                
        return valid_column;
        
        
    }
    
    
    
    public boolean comprobarCuadrante(int row, int column) {
        
        boolean valid_quadrant = true;
        int index = 0;
        
        // conseguimos la posición izquierda y superior del cuadrante al que pertenece el elemento a comprobar
        int columna_inicial;
        int columna_actual;
        int fila_inicial;
        int fila_actual;
        columna_inicial = getColumnaIzquierdaCuadrante(column);
        fila_inicial = getFilaSuperiorCuadrante(row);
        
        //System.out.println(fila_inicial);
        //System.out.println(columna_inicial);
        
        fila_actual = fila_inicial;
        columna_actual = columna_inicial;
        
        
        while (fila_actual < fila_inicial+ Math.sqrt(DIMENSION) && valid_quadrant) {
            while (columna_actual < columna_inicial + Math.sqrt(DIMENSION) && valid_quadrant) {
                
                if (tablero[fila_actual][columna_actual] == tablero[row][column]) {
                    if (fila_actual != row || columna_actual != column) valid_quadrant = false;
                }
                
                
                columna_actual++;
            }
            columna_actual = columna_inicial;
            fila_actual++;
            
        }
        // iteramos desde la posicion inicial, en fila hasta que columna inicial + raiz de dimensión y siga siendo válido el cuadrante
            // iteramos desde la posicion inicial, en columna hasta fila 
        
       
                
        return valid_quadrant;
        
        
    }
    
    
    public int getColumnaIzquierdaCuadrante(int column) {
        
        int columna_izquierda = column;
        
        while (columna_izquierda % (int)Math.sqrt(DIMENSION) != 0) {
            columna_izquierda--;
        }
        return columna_izquierda;
    }
    
    public int getFilaSuperiorCuadrante(int row) {
        
        int fila_superior = row;
        
        while (fila_superior % (int)Math.sqrt(DIMENSION) != 0) {
            fila_superior--;
        }
        return fila_superior;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    

    public int getElement(int row, int column) {
        return tablero[row][column];
    }
    
    public void setElement(int row, int column, int value) {
        tablero[row][column] = value;
    }
    
    public void addPista(){
        this.pistas++;
        this.casillas_por_poner--;
        
    }
    
    
    //TODO Comprobar que esta función va bien.
    
    public boolean tablero_valido(){
       
        for (int i = 0; i < DIMENSION; i++) {
             for (int j = 0; j < DIMENSION; j++) {
//                 System.out.println(tablero.getElement(i, j));
                if (!this.comprobarElemento(i, j)) return false;               
            }
        }
        
        return true;
    }

    public int getCasillas_totales() {
        return casillas_totales;
    }

    public void setCasillas_totales(int casillas_totales) {
        this.casillas_totales = casillas_totales;
    }

    public int getCasillas_por_poner() {
        return casillas_por_poner;
    }

    public void setCasillas_por_poner(int casillas_por_poner) {
        this.casillas_por_poner = casillas_por_poner;
    }

    public int getCasillas_puestas() {
        return casillas_puestas;
    }

    public void setCasillas_puestas(int casillas_puestas) {
        this.casillas_puestas = casillas_puestas;
    }

    public int getPOSIBLES_VALORES() {
        return POSIBLES_VALORES;
    }
    
    public int getDIMENSION() {
        return DIMENSION;
    }
    
    public boolean editable(int row, int col){
        return !noeditable[row][col];
    }

    private void rellenar_con_pistas() {
        int pistas_a_colocar = pistas;
        Random random = new Random();
        int random_fila;
        int random_columna;
        int random_numero;
        int pistas_colocadas = 0;
        
        // mientras nos queden pistas por colorcar
        while (pistas_colocadas < pistas_a_colocar) {
            
            random_fila = random.nextInt(DIMENSION);
            random_columna = random.nextInt(DIMENSION);
            while (tablero[random_fila][random_columna] != 0) {
                random_fila = random.nextInt(DIMENSION);
                random_columna = random.nextInt(DIMENSION);
            }
            
            //mientras no encontremos una casilla libre
            random_numero = random.nextInt(DIMENSION) + 1;
            tablero[random_fila][random_columna] = random_numero;
            noeditable[random_fila][random_columna] = true;
            while (!(this.comprobarElemento(random_fila, random_columna))) {
                random_numero = random.nextInt(DIMENSION) + 1;
                tablero[random_fila][random_columna] = random_numero;
                
            }       
            pistas_colocadas++;
        }
                  
        
    }
    
    
    


    
    
    
    
    
}
