/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.*;

/**
 *
 * @author Rodrigo Alejandro Barrera Manjarrez
 */
public class Iterador implements Iterator {

    
    NodoConjunto current;

    public Iterador(NodoConjunto start) {
       current=start;
       
    }

    @Override
    public boolean hasNext() {
        return current.getSig()!=null;
    }

    @Override
    public Object next() {
        Object resp;
        if(hasNext()){
            resp=current.getSig().getElem();
            current=current.getSig();
        }else{
            resp=null;
        }  
       return resp;
    }

}
