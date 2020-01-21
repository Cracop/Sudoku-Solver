/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Rodrigo Alejandro Barrera Manjarrez
 */
public class NodoConjunto<T> {

    private T elem;
    private NodoConjunto<T> sig;

    public NodoConjunto(T elem) {
        this.elem = elem;
        this.sig = null;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoConjunto<T> getSig() {
        return sig;
    }

    public void setSig(NodoConjunto<T> sig) {
        this.sig = sig;
    }
}
