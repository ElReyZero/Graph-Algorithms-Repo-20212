package Prim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
@SuppressWarnings("unchecked")
public class Prim
{
    /** 
     * Metodo main del programa
     * @param String[]args
     */
    public static void main(String[]args) 
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
                        g.agregarNodo(Integer.parseInt(nodoStr[0]), Integer.parseInt(nodoStr[1]), Integer.parseInt(nodoStr[2]));
                    }
                    Grafo res = (Prim.prim(g, 0));
                    String impresion="---------------------------------------------\nGrafo Caso #: " + contador;
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
         * Constructor de un nodo
         * @param numNodo Valor del nodo
         * @param peso Peso del arco entre el nodo y el nodo raiz dado por su posicion en la lista del grafo
         */
        public Node(int numNodo, int peso) 
        {
            this.numNodo= numNodo;
            this.peso= peso;
        }
    }


    
    /** 
     * Algoritmo de PRIM para hallar el MST de un grafo 
     * @param grafo Grafo sobre el cual se va a realizar el algoritmo
     * @param nodoInicial El valor del nodo inicial para tomar como primer subgrafo
     * @return  Grafo de recubrimiento minimo o MST
     */
    public static Grafo prim(Grafo grafo, int nodoInicial)
    {
        Grafo result = new Grafo(grafo.vertices);
        ArrayList<Integer> recorridos = new ArrayList<Integer>();///Lista que nos facilita saber si un nodo ya fue recorrido por el MST
        recorridos.add(nodoInicial);
        while (recorridos.size() < grafo.vertices)
        /**
         * Esta operacion se encarga de añadir nodos al subgrafo result hasta que todos los nodos del grafo inicial se encuentren en result
         */
        {
            Integer infinito = Integer.MAX_VALUE;
            int min=infinito;
            Node minNode = new Node(-1,infinito); ///Se crea un nodo con peso infinito para que cualquier nodo sea tomado al comenzar el bucle
            int i = 0; 
            for (int j : recorridos)
            /**
             * Se recorren los nodos del subgrafo para poder revisar el peso de sus arcos
             */
            {
                LinkedList<Node> listaNodo = grafo.listaAdyacencia[j];
                for (Node adyacente:listaNodo)
                {
                    /**
                     * Se recorren los arcos de un nodo para hallar cual es el arco minimo que conecta a los subgrafos
                     */
                    if (adyacente.peso<min && !recorridos.contains(adyacente.numNodo))
                    /**
                     * Se compara con lo que venia siendo el arco minimo y si es menor y el nodo que conecta no ha sido recorrido, se modifica el candidato para menor arco
                     */
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
