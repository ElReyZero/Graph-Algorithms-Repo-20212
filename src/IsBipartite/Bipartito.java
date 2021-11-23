package IsBipartite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Bipartito 
{
    
    /** 
     * @param args
     */
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
                g = new Grafo(Integer.parseInt(dataStr[0]), Integer.parseInt(dataStr[1]));
                contador++;
                for(int i = 2; i<dataStr.length; i+=2) 
                {
                    g.agregarArco(Integer.parseInt(dataStr[i]), Integer.parseInt(dataStr[i+1]));
                }
                ArrayList<Integer>[] res = isBipartite(g, g.listaAdyacencia[1].get(0));
                String impresion="---------------------------------------------\nGrafo Caso #: " + contador;
                if (res == null)
                {
                    System.out.println(impresion);
                    System.out.println("Respuesta del algoritmo: false");
                }
                else
                {
                    impresion += "\nRespuesta del algoritmo: true\nLos conjuntos disyuntos de vértices son:\n";
                    int contador2 = 1;
                    for (ArrayList<Integer> lista:res)
                    {
                        impresion += "Conjunto " + contador2 +":\n";
                        for (int elemento : lista)
                        {
                            impresion += elemento+", ";
                        }
                        impresion += "\n";
                        contador2 ++;
                    }
                    System.out.println(impresion);
                }
                line = br.readLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    static public class Grafo
    {
        int vertices;
        int pesoTotal;
        int arcos;
        LinkedList<Node>[] listaAdyacencia;

        /**
         * Constructor del grafo
         * @param vertices Cantidad de vertices del grafo
         * @param listaAdyacencia Es la lista que contiene los nodos del grafo
         */
        public Grafo(int vertices, int arcos)
        {
            this.vertices = vertices;
            this.arcos = arcos;
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
        public void agregarArco(int nodoRaizAnterior, int numNodo)
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

    
    /** 
     * @param g Grafo a analizar
     * @param inicio nodo de inicio del algoritmo
     * @return ArrayList<Integer>[]
     */
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