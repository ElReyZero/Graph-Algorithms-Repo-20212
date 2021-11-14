import java.util.*;
@SuppressWarnings("unchecked")
public class Prim
{
    public static void main(String[]args) 
    {
        Grafo grafoEjemplo = new Grafo(7);
        grafoEjemplo.agregarNodo(0,1,3);
        grafoEjemplo.agregarNodo(1,2,5);
        grafoEjemplo.agregarNodo(1,3,7);
        grafoEjemplo.agregarNodo(1,3,7);
        grafoEjemplo.agregarNodo(2,5,7);
        grafoEjemplo.agregarNodo(2,3,9);
        grafoEjemplo.agregarNodo(3,5,4);
        grafoEjemplo.agregarNodo(3,6,3);
        grafoEjemplo.agregarNodo(3,4,4);
        grafoEjemplo.agregarNodo(4,6,7);
        grafoEjemplo.agregarNodo(5,6,2);
        grafoEjemplo.agregarNodo(5,7,5);
        grafoEjemplo.agregarNodo(6,7,2);

        System.out.println(Prim.prim(grafoEjemplo, 1));
    }

    /*static class Arco
    {
        String anterior;
        String siguiente;
        int peso;
        public Arco(String anterior,String siguiente, int peso) 
        {
            this.anterior= anterior;
            this.siguiente= siguiente;
            this.peso= peso;
        }
    }*/

    static public class Grafo
    {
        int vertices;
        LinkedList<Node>[] listaAdyacencia;
        public Grafo(int vertices)
        {
            this.vertices = vertices;
            listaAdyacencia = (LinkedList<Node>[]) new LinkedList<?>[vertices];
            for(int i = 0; i < vertices; i++)
            {
                listaAdyacencia[i] = new LinkedList<Node>();
                listaAdyacencia[i].add( new Node(i,0) );
            }
        }
        public void agregarNodo(int nodoRaizAnterior, int numNodo, int peso)
        {
            this.listaAdyacencia[nodoRaizAnterior].add(new Node(numNodo,peso));
        }
    }


    static public class Node
    {
        int numNodo;
        int peso;
        public Node(int numNodo, int peso) 
        {
            this.numNodo= numNodo;
            this.peso= peso;
        }
    }


    public static LinkedList<Node> prim(Grafo grafo, int nodoInicial)
    {
        LinkedList<Node> result = new LinkedList<Node>();
        result.add(grafo.listaAdyacencia[nodoInicial].getFirst());
        ArrayList<Integer> recorridos = new ArrayList<Integer>();
        while (result.size() < grafo.vertices)
        {
            Integer infinito = Integer.MAX_VALUE;
            int min=infinito;
            Node minNode = new Node(-1,infinito);
            for (Node nodo: result)
            {
                LinkedList<Node> listaNodo = grafo.listaAdyacencia[nodo.numNodo];
                for (Node adyacente:listaNodo)
                {
                    if (adyacente.peso<min && !recorridos.contains(adyacente.numNodo))
                    {

                        min = adyacente.peso;
                        minNode = adyacente;
                    }
                }
            }
            recorridos.add(minNode.numNodo);
            result.add(minNode);
        }
        return result;
    }
}
