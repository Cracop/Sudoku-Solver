  package sudoku;
//To do list: hacer una funcion que saque la casilla con la menor posibilidad de casillas posibles
//MRV Heuristic

/**
 *
 * @author Rodrigo Alejandro Barrera Manjarrez
 */
public class Sudoku {

    Conjunto renglones[], columnas[], cuadrante[][], intocables;
    float stepsTaken = 0;
    int[][] grid
            = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

    public Sudoku() {
        renglones = new Conjunto[9];
        for (int i = 0; i < renglones.length; i++) {
            renglones[i] = new Conjunto();
            llenarConjunto(renglones[i]);
        }
        columnas = new Conjunto[9];
        for (int i = 0; i < columnas.length; i++) {
            columnas[i] = new Conjunto();
            llenarConjunto(columnas[i]);
        }
        cuadrante = new Conjunto[3][3];
        for (int k = 0; k < cuadrante.length; k++) {
            for (int j = 0; j < cuadrante[0].length; j++) {
                cuadrante[k][j] = new Conjunto();
                llenarConjunto(cuadrante[k][j]);
            }
        }
        intocables = new Conjunto();
        

    }

    public Sudoku(String prueba) {

        stringToGrid(prueba);
        renglones = new Conjunto[9];
        for (int i = 0; i < renglones.length; i++) {
            renglones[i] = new Conjunto();
            llenarConjunto(renglones[i]);
        }
        columnas = new Conjunto[9];
        for (int i = 0; i < columnas.length; i++) {
            columnas[i] = new Conjunto();
            llenarConjunto(columnas[i]);
        }
        cuadrante = new Conjunto[3][3];
        for (int k = 0; k < cuadrante.length; k++) {
            for (int j = 0; j < cuadrante[0].length; j++) {
                cuadrante[k][j] = new Conjunto();
                llenarConjunto(cuadrante[k][j]);
            }
        }
        intocables = new Conjunto();
        sacarIntocables();

    }

    /**
     * Metodo que llena la matriz usando un String
     *
     * @param str el sudoku prellenado como un solo String
     */
    public void stringToGrid(String str) {
        String var;
        int num;
        for (int i = 0; i < str.length(); i++) {
            var = str.charAt(i) + "";
            try {
                num = Integer.parseInt(var);
            } catch (Exception e) {
                num = 0;
            }
            int row = i / 9, column = i % 9;
            grid[row][column] = num;
        }
    }

    /**
     * Metodo que transforma la matriz en un solo String
     *
     * @return un el sudoku como un solo String
     */
    public String gridToString() {
        String resp = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                resp += grid[i][j];
            }
        }
        return resp;
    }

    /**
     * Metodo que saca las casillas que no se pueden usar.
     */
    public void sacarIntocables() {
        intocables(0);
    }

    /**
     * Metodo que saca las casillas que no se pueden usar.
     *
     * @param orden la casilla en la que estoy
     */
    private void intocables(int orden) {
        int row = orden / 9, column = orden % 9;
        if (orden > 80) {
            return;
        } else {
            if (grid[row][column] != 0) {
                intocables.inserta(orden);
                eliminaNumero(orden, grid[row][column]);
            }
            intocables(orden + 1);
        }
    }

    /**
     * Metodo que borra de los conjuntos correspontientes el numero indicado
     *
     * @param orden la casilla en la que estoy
     * @param num el numero que quiero borrar
     */
    public void eliminaNumero(int orden, int num) {
        int row = orden / 9, column = orden % 9;
        int cuadrantRow = row / 3, cuadrantColumn = column / 3;
        renglones[row].borra(num);
        columnas[column].borra(num);
        cuadrante[cuadrantRow][cuadrantColumn].borra(num);

    }

    /**
     * Metodo que agrega a los conjunto correspondientes el numero indicado
     *
     * @param orden la casilla en la que estoy
     * @param num el numero que quiero agregar
     */
    public void agregaNumero(int orden, int num) {
        int row = orden / 9, column = orden % 9;
        int cuadrantRow = row / 3, cuadrantColumn = column / 3;
        renglones[row].inserta(num);
        columnas[column].inserta(num);
        cuadrante[cuadrantRow][cuadrantColumn].inserta(num);

    }

    /**
     * Metodo public para llamar al metodo recursivo
     */
    public void solve() {
        solve(0);
    }

    /**
     * Metodo recursivo que resuelve el sudoku realizando backtracking y
     * conjuntos de posibilidad, mientras se tengan numeros que poner va a
     * seguir intentando, si no hay más y no se ha resuelto el sudoku, se
     * regresa a la casilla anterior.
     *
     * @param orden la casilla en la que me encuentro renglon cuadrante =
     * renglon/3 Columna cuadrante = columna/3 renglon = orden/9 columna =
     * orden%9
     */
    public boolean solve(int orden) {
        stepsTaken++;
        boolean done = false;
        int row = orden / 9, column = orden % 9;
        if(stepsTaken>10000){
            return false;
        }
        if (orden > 80) {
            done = true;
            return true;
        }
        if (!intocables.contains(orden)) {
            Conjunto posibilidades = sacarPosibilidades(orden);
            posibilidades.iterator();
            Iterador it = posibilidades.iterator;
            while (it.hasNext()) {
                int num = (int) it.next();
                grid[row][column] = num;
                eliminaNumero(orden, num);
                posibilidades.borra(num);
                done = solve(orden + 1);

                if (done) {
                    return true;
                }

                grid[row][column] = 0;
                agregaNumero(orden, num);

            }

            return false;

        } else {
            done = solve(orden + 1);
            if (done) {
                return true;
            }

        }

        return done;
    }

    /**
     * Realiza la intersección de los conjuntos de posibilidad para sacar los
     * numeros que puedo poner en esa casilla
     *
     * @param orden casilla en la que estoy
     * @return el conjunto de numeros que puedo poner en esa casilla
     */
    public Conjunto sacarPosibilidades(int orden) {
        int row = orden / 9, column = orden % 9;
        int cuadrantRow = row / 3, cuadrantColumn = column / 3;
        Conjunto posibilidades = interseccion(renglones[row], columnas[column]);
        posibilidades = interseccion(posibilidades, cuadrante[cuadrantRow][cuadrantColumn]);

        return posibilidades;
    }

    /**
     * Realiza la intersección de dos conjuntos dados
     *
     * @param c1 conjunto 1
     * @param c2 conjunto 2
     * @return interseccion de esos dos conjuntos
     */
    public Conjunto interseccion(Conjunto c1, Conjunto c2) {
        Conjunto resp = new Conjunto();
        NodoConjunto aux = c1.head;
        while (aux.getSig() != null) {
            if (c2.contains(aux.getSig().getElem())) {
                resp.inserta(aux.getSig().getElem());
            }
            aux = aux.getSig();
        }
        return resp;
    }

    /**
     * Llena el conjunto dado con numeros del 1 al 9
     *
     * @param con el conjunto a llenar
     */
    public void llenarConjunto(Conjunto con) {
        for (int i = 1; i < 10; i++) {
            con.inserta(i);
        }
    }

    /**
     * Imprime la matriz como un Sudoku
     *
     * @return Regresa el Sudoku impreso
     */
    public String toString() {
        String result = "\n";
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                result += grid[row][column] + "";
                if (column == 2 || column == 5) {
                    result += " ";
                }
            }
            result += "\n";
            if (row == 2 || row == 5) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GUISudoku gs = new GUISudoku();
        NewGUI ng = new NewGUI();
        ng.setVisible(true);

    }

}
