import java.util.*;
public class Prim
{
    public static void main(String[]args) 
    {
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

        return result;
    }
}
