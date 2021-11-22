package IsBipartite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
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

        Grafo grafo2 = new Grafo(5);
        grafo2.agregarNodo(0,1);
        grafo2.agregarNodo(0,3);
        grafo2.agregarNodo(1,2);
        grafo2.agregarNodo(3,4);
        ArrayList<Integer>[] res = isBipartite(grafo2, grafo2.listaAdyacencia[1].get(0));
        if (res == null)
        {
            System.out.println("El grafo no es Bipartito");
        }
        else
        {
            System.out.println("El grafo si es bipartito");
            String impresion="Los conjuntos disyuntos de vértices son:\n";
            int contador = 1;
            for (ArrayList<Integer> lista:res)
            {
                impresion += "Conjunto " + contador +":\n";
                for (int elemento : lista)
                {
                    impresion += elemento+", ";
                }
                impresion += "\n";
                contador ++;
            }
            System.out.println(impresion);
        }
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
        Node[] descubiertos = new Node[g.vertices]; 
        Node vActual;
        Node vSiguiente;
        LinkedList<Node> p;

        q.add(inicio);
        descubiertos[inicio.numNodo] = inicio;
        blancos.add(inicio.numNodo);
        inicio.setColor("BLANCO");
        while(!q.isEmpty())
        {
            vActual = q.poll();
            p = g.listaAdyacencia[vActual.numNodo];
            ListIterator<Node> it = p.listIterator();
            while(it.hasNext())
            {
                vSiguiente = it.next();
                if(descubiertos[vSiguiente.numNodo] == null)
                {
                    if(vActual.color.equals("BLANCO"))
                    {
                        negros.add(vSiguiente.numNodo);
                        vSiguiente.setColor("NEGRO");
                    }
                    else
                    {
                        blancos.add(vSiguiente.numNodo);
                        vSiguiente.setColor("BLANCO");
                    } 
                    descubiertos[vSiguiente.numNodo] = vSiguiente;
                    q.add(vSiguiente);
                }
                else if(descubiertos[vSiguiente.numNodo].color.equals(vActual.color))
                {
                    return null;
                }
            }
        }
        resultado[0] = blancos;
        resultado[1] = negros;
        return resultado;
    }
}
