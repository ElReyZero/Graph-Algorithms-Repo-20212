# Algoritmo para determinar si un Grafo de lista de Adyacencias es Bipartito o no
Desarrollado para la clase de Diseño y Análisis de Algoritmos 2021-2
Por: 
* Juan Andrés Romero C - 202013449
* Luccas Rojas - 201923052

# Uso:
1. Abrir una terminal del sistema donde se encuentra el archivo Bipartito.java
2. Guardar un archivo .in con el siguiente formato:
numVertices numArcos nodoIni1 nodoFin1 peso1 nodoIni2 nodoFin2 peso2 ... nodoInin nodoFinn

- Donde numVertices es el número de nodos que tiene el grafo, numArcos es el número total de arcos del grafo, los nodoIni son nodo de inicio, los nodoFin son los nodo destino y el peso es el peso del arco que los conecta.
- Nota: La implementación trabaja con un arco no dirigido y se encarga de agregar arco de ida y vuelta a la lista de adyacencias, por lo cual solo es necesario ingresar cada arco en una sola dirección.
- Nota 2: El grafo tiene los nodos definidos de 0 hasta n, de forma que el número del nodo a agregar indica el índice del nombre del nodo. Es decir, en el input es necesario que se introduzcan únicamente nodos en orden desde 0 hasta n.

Ejemplo:
7 12 0 1 0 2 0 3 1 4 1 2 2 4 2 5 2 3 3 5 4 6 4 5 5 6

7 sería el número de nodos del grafo, 12 el número de arcos y éste mismo tendría los arcos
0,1
0,2
0,3
1,4
1,2
2,3
2,5
2,4
3,5
4,5
4,6
5,6

Nota: Se toma un caso por línea, es decir, cada línea del archivo .in comprende un único problema para resolver. (Mirar archivo de ejemplo bipartito.in)

3. Ejecutar el comando `javac Bipartito.java -d .`
4. Ejecutar el comando `java IsBipartite.Bipartito < nombreEntrada.in > nombreSalida.out`

# Salida
La respuesta del algotitmo es si es o no bipartito, y en caso de que si lo sea, devuelve los conjuntos disyuntos del grafo
---------------------------------------------
Grafo Caso #: 1
Respuesta del algoritmo: false
---------------------------------------------
Grafo Caso #: 2
Respuesta del algoritmo: true
Los conjuntos disyuntos de vértices son:
Conjunto 1:
0, 2, 4, 
Conjunto 2:
1, 3, 

# Ejemplos de uso
En los archivos bipartito.in y bipartito.out se pueden ver ejemplos concretos del programa