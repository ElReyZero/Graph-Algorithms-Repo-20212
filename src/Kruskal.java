import java.util.ArrayList;

public class Kruskal 
{
    
    class Node
    {
        int numNodo;
    }

    class Arco
    {
        int anterior;
        int siguiente;
        int peso;

        public Arco(int anterior, int siguiente, int peso)
        {
            this.anterior = anterior;
            this.siguiente = siguiente;
            this.peso = peso;
        }

        public ArrayList<Arco> ordenarArcos()
        {
            ArrayList<Arco> resultado = new ArrayList<Arco>();
            return resultado;
        }
    }
    
    class Grafo
    {
        int vertices;
        int arcos;
        int[][] matAdj;
        ArrayList<Integer> listaDeNodos = new ArrayList<Integer>();
        ArrayList<Arco> listaArcos = new ArrayList<Arco>();
        public Grafo(int tamano)
        {
            this.vertices = tamano;
            this.matAdj = new int[tamano][tamano];

            for (int i = 0; i < tamano; i++) 
            {
                listaDeNodos.add(i);
            }
        }

        public void agregarArco(int nodoInicio, int nodoFin, int peso)
        {
            this.matAdj[nodoInicio][nodoFin] = peso;
            this.matAdj[nodoFin][nodoInicio] = peso;
            this.arcos += 1;
            this.listaArcos.add(new Arco(nodoInicio, nodoFin, peso));
        }
    }

    
    /** 
     * @param grafo
     */
    public void kruskal(Grafo grafo)
    {
        int[][] matriz = grafo.matAdj;
        ArrayList<Integer> tamano = grafo.listaDeNodos;
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; i++)
            {
                if (matriz[i][j] !=0)
                {

                } 
            }
            visitados.add(i);
        }
    }
}
