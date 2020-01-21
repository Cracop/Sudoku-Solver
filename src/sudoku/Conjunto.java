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
public class Conjunto<T> implements ConjuntoADT {

    NodoConjunto head;
    Iterador iterator;

    public Conjunto(T elem) {

        head = new NodoConjunto(null);
        inserta(elem);
        

    }

    public Conjunto() {
        head = new NodoConjunto(null);
        head.setSig(null);
        
    }

    @Override
    public void inserta(Object elem) {
        NodoConjunto nuevo = new NodoConjunto(elem);
        NodoConjunto anterior, aux;
        if (!contains(elem)) {
            anterior = head;
            aux = head.getSig();

            while (aux != null) {
                anterior = aux;
                aux = aux.getSig();
            }
            if (aux == null) {
                anterior.setSig(nuevo);
            }
        }

    }

    @Override
    public boolean borra(Object elem) {
        boolean resp = false;
        NodoConjunto<T> anterior, aux;
        if (contains(elem)) {
            anterior = head;
            aux = head.getSig();
            while (aux != null && !aux.getElem().equals(elem)) {
                anterior = aux;
                aux = aux.getSig();
            }
            if (aux != null) {
                anterior.setSig(aux.getSig());
                resp = true;
            }

        }
        return resp;
    }

    @Override
    public int size() {
        int cont = 0;
        NodoConjunto aux = head.getSig();
        while (aux != null) {
            cont++;
            aux = aux.getSig();
        }

        return cont;
    }

    @Override
    public boolean isEmpty() {
        return head.getSig() == null;
    }

    @Override
    public boolean contains(Object elem) {
        boolean flag = false;
        NodoConjunto aux = head.getSig();
        while (!flag && aux != null) {
            if (aux.getElem().equals(elem)) {
                flag = true;
            }
            aux = aux.getSig();
        }
        return flag;
    }

    public String toString() {
        String resp = "";
        resp += "[";
        NodoConjunto<T> aux = head.getSig();
        while (aux != null) {
            resp += aux.getElem() + ",";
            aux = aux.getSig();
        }
        resp += "]";
        return resp;
    }

    @Override
    public Conjunto union(Conjunto otroCon) throws Exception {
        Conjunto<T> resp = new Conjunto();
        NodoConjunto<T> aux = otroCon.head.getSig();
        if (otroCon == null) {
            throw new Exception("Conjunto nulo");
        }
        while (aux != null) {
            if (!contains(aux.getElem())) {
                resp.inserta(aux.getElem());
            }
            aux = aux.getSig();
        }
        aux = head.getSig();
        while (aux != null) {
            resp.inserta(aux.getElem());
            aux = aux.getSig();
        }

        return resp;
    }

    @Override
    public Conjunto interseccion(Conjunto otroCon) throws Exception {
       Conjunto<T> resp = new Conjunto();
        NodoConjunto<T> aux = otroCon.head.getSig();
        if (otroCon == null) {
            throw new Exception("Conjunto nulo");
        }
        while (aux != null) {
            if (contains(aux.getElem())) {
                resp.inserta(aux.getElem());
            }
            aux = aux.getSig();
        }
       return resp; 
    }


    @Override
    public Conjunto diferencia(Conjunto otroCon) throws Exception {
         Conjunto resp = new Conjunto();
        NodoConjunto aux = head.getSig();
        if (otroCon == null) {
            throw new Exception("Conjunto nulo");
        }
        while (aux != null) {
            if (!otroCon.contains(aux.getElem())) {
                resp.inserta(aux.getElem());
            }
            aux = aux.getSig();
        }
       return resp; 
    }
    
    public Conjunto diferenciaSimetrica(Conjunto otroCon) throws Exception{
        Conjunto resp= new Conjunto();
        NodoConjunto aux = head.getSig();
        if (otroCon == null) {
            throw new Exception("Conjunto nulo");
        }
        while (aux != null) {
            if (!otroCon.contains(aux.getElem())) {
                resp.inserta(aux.getElem());
            }
            aux = aux.getSig();
        }
        aux = otroCon.head.getSig();
         while (aux != null) {
            if (!contains(aux.getElem())) {
                resp.inserta(aux.getElem());
            }
            aux = aux.getSig();
        }
         return resp;
    }
    
    public Iterador iterator(){
        Iterador iterator= new Iterador(head);
        this.iterator=iterator;
        return iterator;
    }

}
