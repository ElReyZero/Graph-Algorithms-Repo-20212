package Kruskal;

import java.util.ArrayList;
import java.util.HashSet;

public class Kruskal 
{
    public static void main(String[] args)
    {
        Grafo grafoEjemplo = new Grafo(6);
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
        Grafo result = kruskal(grafoEjemplo);   
        for (int i =0;i<result.vertices;i++)
        {
            String impresion="";
            for(int j =0;j<result.vertices;j++)
            {
                impresion += result.matAdj[i][j]+", ";
            }
            System.out.println(impresion);
        }
        System.out.println(result.peso);
    }
    
    public static void mergesort(int A[],int izq, int der){
        if (izq < der){
                int m=(izq+der)/2;
                mergesort(A,izq, m);
                mergesort(A,m+1, der);                                                                                
                merge(A,izq, m, der);                                                                                 
        }
    }
    public static void merge(int A[],int izq, int m, int der){
        int i, j, k;
        int [] B = new int[A.length]; //array auxiliar
        for (i=izq; i<=der; i++)      //copia ambas mitades en el array auxiliar                                       
             B[i]=A[i];
     
        i=izq; j=m+1; k=izq;
          
        while (i<=m && j<=der) //copia el siguiente elemento más grande                                      
               if (B[i]<=B[j])
                   A[k++]=B[i++];
               else
                   A[k++]=B[j++];
             
        while (i<=m)         //copia los elementos que quedan de la
              A[k++]=B[i++]; //primera mitad (si los hay)
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


    static public class Grafo
    {
        int vertices;
        int arcos;
        int[][] matAdj;
        int peso;
        //ArrayList<Integer> listaDeNodos = new ArrayList<Integer>();
        //ArrayList<Arco> listaArcos = new ArrayList<Arco>();
        public Grafo(int tamano)
        {
            this.vertices = tamano;
            this.matAdj = new int[tamano][tamano];
            this.peso = 0;
            this.arcos = 0;
            /*
            for (int i = 0; i < tamano; i++) 
            {
                listaDeNodos.add(i);
            }
            */
        }

        public void agregarArco(int nodoInicio, int nodoFin, int peso)
        {
            this.matAdj[nodoInicio][nodoFin] = peso;
            this.matAdj[nodoFin][nodoInicio] = peso;
            this.arcos += 1;
            this.peso += peso;
            //this.listaArcos.add(new Arco(nodoInicio, nodoFin, peso));
        }
        public void removerArco(int nodoInicio,int nodoFin, int peso)
        {
            this.matAdj[nodoInicio][nodoFin] = 0;
            this.matAdj[nodoFin][nodoInicio] = 0;
            this.arcos -= 1;
            this.peso -= peso;
        }
        /*
        public void ordenarArcos()
        {
            mergesort(pesos,0,arcos-1);
        }
        */
    }

    
    /** 
     * @param grafo
     */
    public static Grafo kruskal(Grafo grafo)
    {
        Grafo result = new Grafo(grafo.vertices);
        int[][] matriz = grafo.matAdj;
        int pesos[] = new int[grafo.arcos];
        int i = 0;
        int j = 0;
        int k =0;
        while (i<grafo.vertices)
        {
            if (matriz[i][j] != 0)
            {
                pesos[k] = matriz[i][j];
                k+=1;
            }
            if (j==grafo.vertices-1)
            {
                i+=1;
                j=i;
            }
            else if (j<grafo.vertices-1)
            {
                j+=1;
            }
        }
        mergesort(pesos,0,grafo.arcos-1);
        for (int p:pesos) 
        {
            i=0;
            j=0;
            while (i<grafo.vertices)
            {
                if (matriz[i][j] == p )
                {
                    result.agregarArco(i,j,p);
                    if (!ciclo(result))
                    {
                        break;
                    }
                    else
                    {
                        result.removerArco(i,j, p);
                    }   
                }
                if (j==grafo.vertices-1)
                {
                    i+=1;
                    j = i;
                }
                else if (j<grafo.vertices-1)
                {
                    j+=1;
                }
            }
        }
        return result;
    }
   
    
    public static boolean ciclo(Grafo graph)
    {

        HashSet<Integer> visited = new HashSet<Integer>();
        int numVertices = graph.vertices;

        for (int i = 0; i < numVertices; i++)
        {
            if (visited.contains(i))
            {
                continue;
            }

            if (dfs(i, Integer.MIN_VALUE, visited, numVertices, graph.matAdj))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean dfs(int vertex, int parent, HashSet<Integer> visited, int numVertices, int[][] adjMatrix)
    {
        visited.add(vertex);

        for (int i = 0; i < numVertices; i++)
        {
            // skip if i is parent (this is allowed in undirected graphs)
            if (i == parent)
            {
                continue;
            }

            // skip if not vertex in matrix
            if (adjMatrix[vertex][i]==0)
            {
                continue;
            }

            // cycle if already visited
            if (visited.contains(i))
            {
                return true;
            }

            if (dfs(i, vertex, visited, numVertices, adjMatrix))
            {
                return true;
            }
        }

        return false;
    }
}
