import java.util.*;
@SuppressWarnings("unchecked")
public class Prim
{
    
    
    /** 
     * @param String[]args
     */
    public static void main(String[]args) 
    {
        Grafo grafoEjemplo = new Grafo(6);
        grafoEjemplo.agregarNodo(0,1,6);
        grafoEjemplo.agregarNodo(0,2,1);
        grafoEjemplo.agregarNodo(0,3,5);
        grafoEjemplo.agregarNodo(1,2,5);
        grafoEjemplo.agregarNodo(1,4,3);
        grafoEjemplo.agregarNodo(2,3,5);
        grafoEjemplo.agregarNodo(2,5,4);
        grafoEjemplo.agregarNodo(2,4,6);
        grafoEjemplo.agregarNodo(3,5,2);
        grafoEjemplo.agregarNodo(4,5,6);
        ///grafoEjemplo.agregarNodo(4,5,2);
        ///grafoEjemplo.agregarNodo(5,6,2);

        Grafo res = (Prim.prim(grafoEjemplo, 0));
        String impresion="";
        for (int i = 0; i < res.vertices; i++)
        {
            LinkedList<Node> n = res.listaAdyacencia[i];
            impresion += "\n\nNodo inicial: "+i+" ";
            for (Node nodo:n)
            {
                impresion += ("\nNodo destino: " + nodo.numNodo + " - Peso: "+nodo.peso);
            }
        }
        System.out.println(impresion);
        System.out.println("\nPeso total: "+res.pesoTotal);
        
    }
    
    static public class Grafo
    {
        int vertices;
        int pesoTotal;
        LinkedList<Node>[] listaAdyacencia;

        /**
         * 
         * @param vertices
         */
        public Grafo(int vertices)
        {
            this.vertices = vertices;
            listaAdyacencia = (LinkedList<Node>[]) new LinkedList<?>[vertices];


            for(int i = 0; i < vertices; i++)
            {
                listaAdyacencia[i] = new LinkedList<Node>();
            }

        }

        /**
         * 
         * @param nodoRaizAnterior
         * @param numNodo
         * @param peso
         */
        public void agregarNodo(int nodoRaizAnterior, int numNodo, int peso)
        {
            this.listaAdyacencia[nodoRaizAnterior].add(new Node(numNodo,peso));
            this.listaAdyacencia[numNodo].add(new Node(nodoRaizAnterior,peso));
            this.pesoTotal += peso;
        }
    }

    static public class Node
    {
        int numNodo;
        int peso;

        /**
         * 
         * @param numNodo
         * @param peso
         */
        public Node(int numNodo, int peso) 
        {
            this.numNodo= numNodo;
            this.peso= peso;
        }
    }


    
    /** 
     * @param grafo
     * @param nodoInicial
     * @return Grafo
     */
    public static Grafo prim(Grafo grafo, int nodoInicial)
    {
        Grafo result = new Grafo(grafo.vertices);
        ArrayList<Integer> recorridos = new ArrayList<Integer>();
        recorridos.add(nodoInicial);
        while (recorridos.size() < grafo.vertices)
        {
            Integer infinito = Integer.MAX_VALUE;
            int min=infinito;
            Node minNode = new Node(-1,infinito);
            int i = 0; 
            for (int j : recorridos)
            {
                LinkedList<Node> listaNodo = grafo.listaAdyacencia[j];
                for (Node adyacente:listaNodo)
                {
                    if (adyacente.peso<min && !recorridos.contains(adyacente.numNodo))
                    {
                        i=j;
                        min = adyacente.peso;
                        minNode = adyacente;
                    }
                }
            }
            recorridos.add(minNode.numNodo);
            result.agregarNodo(i, minNode.numNodo, min);
        }
        return result;
    }

}
