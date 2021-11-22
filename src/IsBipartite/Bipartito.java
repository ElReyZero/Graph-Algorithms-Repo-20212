package IsBipartite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Bipartito 
{

    public static void main(String[] args)
    {
        Grafo grafoEjemplo = new Grafo(7);
        grafoEjemplo.agregarNodo(0,1);
        grafoEjemplo.agregarNodo(0,2);
        grafoEjemplo.agregarNodo(0,3);
        grafoEjemplo.agregarNodo(1,4);
        grafoEjemplo.agregarNodo(1,2);
        grafoEjemplo.agregarNodo(2,4);
        grafoEjemplo.agregarNodo(2,5);
        grafoEjemplo.agregarNodo(2,3);
        grafoEjemplo.agregarNodo(3,5);
        grafoEjemplo.agregarNodo(4,6);
        grafoEjemplo.agregarNodo(4,5);
        grafoEjemplo.agregarNodo(5,6);

        ArrayList<Integer>[] res = isBipartite(grafoEjemplo, grafoEjemplo.listaAdyacencia[1].get(0));
        System.out.println(res);
    }


    static public class Grafo
    {
        int vertices;
        int pesoTotal;
        LinkedList<Node>[] listaAdyacencia;

        /**
         * Constructor del grafo
         * @param vertices Cantidad de vertices del grafo
         * @param listaAdyacencia Es la lista que contiene los nodos del grafo
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
         * Metodo para agregar un nodo y un arco al grafo
         * Se necesita el valor del nodo a agregar, el valor del nodo anterior y el peso del arco entre ellos
         * @param nodoRaizAnterior Valor del nodo anterior
         * @param numNodo Valor del nodo a insertar
         * @param peso Peso del arco entre ambos nodos
         */
        public void agregarNodo(int nodoRaizAnterior, int numNodo)
        {
            this.listaAdyacencia[nodoRaizAnterior].add(new Node(numNodo));
            this.listaAdyacencia[numNodo].add(new Node(nodoRaizAnterior));
        }
    }

    static public class Node
    {
        int numNodo;
        String color;
        /**
         * Constructor de un nodo
         * @param numNodo Valor del nodo
         * @param peso Peso del arco entre el nodo y el nodo raiz dado por su posicion en la lista del grafo
         */
        public Node(int numNodo) 
        {
            this.numNodo= numNodo;
        }

        public void setColor(String color)
        {
            this.color= color;
        }
    }

    public static ArrayList<Integer>[] isBipartite(Grafo g, Node inicio)
    {
        ArrayList<Integer>[] resultado = (ArrayList<Integer>[]) new ArrayList<?>[2];
        Queue<Node> q = new LinkedList<Node>();
        ArrayList<Integer> blancos = new ArrayList<Integer>();
        ArrayList<Integer> negros = new ArrayList<Integer>();
        boolean[] descubiertos = new boolean[g.vertices]; 
        Node[] parents = new Node[g.vertices]; 
        Node vActual;
        Node vSiguiente;
        LinkedList<Node> p;

        q.add(inicio);
        descubiertos[inicio.numNodo] = true;

        while(!q.isEmpty())
        {
            vActual = q.poll();
            blancos.add(vActual.numNodo);
            vActual.setColor("BLANCO");
            p = g.listaAdyacencia[vActual.numNodo];
            Iterator<Node> it = p.listIterator();
            while(it.hasNext())
            {
                vSiguiente = it.next();
                if(!descubiertos[vSiguiente.numNodo])
                {
                    q.add(vSiguiente);
                    descubiertos[vSiguiente.numNodo] = true;
                    negros.add(vSiguiente.numNodo);
                    vSiguiente.setColor("NEGRO");
                    parents[vSiguiente.numNodo] = vActual;
                }
                else
                {
                    if (parents[vSiguiente.numNodo] == null)
                    {
                        continue;
                    }
                    else if(vSiguiente.color.equals(parents[vSiguiente.numNodo].color))
                    {
                        return null;
                    }
                }
            }
        }
        return resultado;
    }
}
