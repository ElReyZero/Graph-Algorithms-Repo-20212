import java.util.*;
public class Prim
{
    public static void main(String[]args) 
    {
        Grafo grafoEjemplo = new Grafo(0,null);

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

    public class Grafo
    {
        int vertices;
        LinkedList<Node>[] listaAdyacencia;
        public Grafo(int vertices,ArrayList<Integer> listaNodos)
        {
            this.vertices = vertices;
            
            for(int i = 0; i < vertices; i++)
            {
                listaAdyacencia[i] = new LinkedList<Node>();
                listaAdyacencia[i].add( new Node(listaNodos.get(i),0) );
            }
        }
        public void agregarNodo(int valor, int peso, int nodoRaiz)
        {
            this.listaAdyacencia[nodoRaiz].add(new Node(valor,peso));
            this.vertices= this.vertices+1;
        }
    }


    public class Node
    {
        int value;
        int peso;
        public Node(int value, int peso) 
        {
            this.value= value;
            this.peso= peso;
        }
    }


    public LinkedList<Node> prim(Grafo grafo, int nodoInicial)
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
                LinkedList<Node> listaNodo = grafo.listaAdyacencia[nodo.value];
                for (Node adyacente:listaNodo)
                {
                    if (adyacente.peso<min && !recorridos.contains(adyacente.value))
                    {

                        min = adyacente.peso;
                        minNode = adyacente;
                    }
                }
            }
            recorridos.add(minNode.value);
            result.add(minNode);
        }
        return result;
    }
}
