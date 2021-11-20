package Kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Kruskal 
{
    public static void main(String[] args)
    {
        try
        (
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
        )
        {
            Grafo g;
                String line = br.readLine();
                int contador = 0;
                while(line!=null && line.length()>0 && !"0".equals(line))
                {
                    final String[] dataStr = line.split(" ");
                    g = new Grafo(Integer.parseInt(dataStr[0]));
                    contador++;
                    for (int i = 1; i<dataStr.length; i++)
                    {
                        final String[] nodoStr = dataStr[i].split(",");
                        g.agregarArco(Integer.parseInt(nodoStr[0]), Integer.parseInt(nodoStr[1]), Integer.parseInt(nodoStr[2]));
                    }
                    Grafo res = kruskal(g);
                    String impresion="---------------------------------------------\nGrafo Caso #: " + contador+"\n";
                    impresion += "Matriz del grafo resultante: ";
                    System.out.println(impresion);
                    for (int i =0;i<res.vertices;i++)
                    {
                        String impresionMatriz = "";
                        for(int j =0;j<res.vertices;j++)
                        {
                            impresionMatriz += res.matAdj[i][j]+", ";
                        }
                        System.out.println(impresionMatriz);
                    }
                    System.out.println("El peso del grafo resultante es: " + res.peso);
                    line = br.readLine();
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

    static public class Grafo
    {
        int vertices;
        int arcos;
        int[][] matAdj;
        int peso;
        public Grafo(int tamano)
        {
            this.vertices = tamano;
            this.matAdj = new int[tamano][tamano];
            this.peso = 0;
            this.arcos = 0;
        }

        public void agregarArco(int nodoInicio, int nodoFin, int peso)
        {
            this.matAdj[nodoInicio][nodoFin] = peso;
            this.matAdj[nodoFin][nodoInicio] = peso;
            this.arcos += 1;
            this.peso += peso;
        }
        public void removerArco(int nodoInicio,int nodoFin, int peso)
        {
            this.matAdj[nodoInicio][nodoFin] = 0;
            this.matAdj[nodoFin][nodoInicio] = 0;
            this.arcos -= 1;
            this.peso -= peso;
        }
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
                if (matriz[i][j] == p && result.matAdj[i][j] == 0)
                {
                    result.agregarArco(i,j,p);
                    if (!ciclo(result))
                    {
                        break;
                    }
                    else
                    {
                        result.removerArco(i,j,p);
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
