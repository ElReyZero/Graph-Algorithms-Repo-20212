package Kruskal;

import java.util.ArrayList;

public class Kruskal 
{
    public static void main(String[] args)
    {
        Grafo grafoEjemplo = new Grafo(5);
        grafoEjemplo.agregarArco(0,1,6);
        grafoEjemplo.agregarArco(0,2,1);
        grafoEjemplo.agregarArco(0,3,5);
        grafoEjemplo.agregarArco(1,2,5);
        grafoEjemplo.agregarArco(1,4,3);
        grafoEjemplo.agregarArco(2,3,5);
        grafoEjemplo.agregarArco(2,5,4);
        grafoEjemplo.agregarArco(2,4,6);
        grafoEjemplo.agregarArco(3,5,2);
        grafoEjemplo.agregarArco(4,5,6);
        mergesort(grafoEjemplo.listaArcos,0,grafoEjemplo.listaArcos.size());
        
    }
    
    static class Arco
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

    }


    /**
     * Copiamos el algorimto merge sort de internet par organizar los arcos al comienzo
     * @param A
     * @param izq
     * @param der
     */

    public static void mergesort(ArrayList<Arco> arcos,int izq, int der){
        if (izq < der){
                int m=(izq+der)/2;
                mergesort(arcos,izq, m);
                mergesort(arcos,m+1, der);                                                                                
                merge(arcos,izq, m, der);                                                                                 
        }
    }

    public static void merge(ArrayList<Arco> arcos,int izq, int m, int der){
        int i, j, k;
        ArrayList<Arco> aux = arcos; //array auxiliar
        //for (i=izq; i<=der; i++)      copia ambas mitades en el array auxiliar                                       
             //B[i]=A[i];
             ///arcos.remove(i);
             ///arcos.add(i,new Arco(1,2,3));


        i=izq; j=m+1; k=izq;
        while (i<=m && j<=der) //copia el siguiente elemento más grande                                      
               //if (B[i]<=B[j])
               //A[k++]=B[i++];
               if (aux.get(i).peso<=aux.get(j).peso)
               {
                   arcos.remove(k++);
                   arcos.add(k++,aux.get(i++));
               }
               //else
                   //A[k++]=B[j++];
                else
                {
                    arcos.remove(k++);
                    arcos.add(k++,aux.get(j++));
                }
             
        while (i<=m)         //copia los elementos que quedan de la
        {
            arcos.remove(k++);
            arcos.add(k++,aux.get(i++));
        }
              //A[k++]=B[i++]; //primera mitad (si los hay)
     }
    
    static public class Grafo
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
        
        public ArrayList<Arco> ordenarArcos()
        {
            ArrayList<Arco> resultado = mergesort(listaArcos,0);
            return resultado;
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
