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
public interface ConjuntoADT<T> {

    public void inserta(T elem);

    public boolean borra(T elem);

    public int size();

    public boolean isEmpty();

    public boolean contains(T elem);

    public Conjunto<T> union(Conjunto<T> otroCon) throws Exception;

    public Conjunto<T> interseccion(Conjunto<T> otroCon) throws Exception;

    public Conjunto<T> diferencia(Conjunto<T> otroCon) throws Exception;

    public Conjunto<T> diferenciaSimetrica(Conjunto<T> otroCon) throws Exception;

    public String toString();

}
