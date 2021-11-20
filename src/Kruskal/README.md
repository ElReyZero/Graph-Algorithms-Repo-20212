# Algoritmo de Kruskal usando Matriz de Adyacencia
Desarrollado para la clase de Diseño y Análisis de Algoritmos 2021-2
Por: 
* Juan Andrés Romero C - 202013449
* Luccas Rojas - 201923052

# Uso:
1. Abrir una terminal del sistema donde se encuentra el archivo Kruskal.java
2. Guardar un archivo .in con el siguiente formato:
numVertices nodoIni1,nodoFin1,peso1 nodoIni2,nodoFin2,peso2 ... nodoInin,nodoFinn,peson

- Donde numVertices es el número de nodos que tiene el grafo, los nodoIni son nodo de inicio, los nodoFin son los nodo destino y el peso es el peso del arco que los conecta.
- Nota: La implementación trabaja con un arco no dirigido y se encarga de agregar arco de ida y vuelta a la matriz de adyacencias, por lo cual solo es necesario ingresar cada arco en una sola dirección.

Ejemplo:
6 0,1,6 0,2,1 0,3,5 1,2,5 1,4,3 2,3,5 2,5,4 2,4,6 3,5,2 4,5,6

6 sería el número de nodos del grafo y éste mismo tendría los arcos
0,1,6
0,2,1
0,3,5
1,2,5
1,4,3
2,3,5
2,5,4
2,4,6
3,5,2
4,5,6

Nota: Se toma un caso por línea, es decir, cada línea del archivo .in comprende un único problema para resolver. (Mirar archivo de ejemplo prim.in)

3. Ejecutar el comando `javac Kruskal.java -d .`
4. Ejecutar el comando `java Kruskal.Kruskal < nombreEntrada.in > nombreSalida.out`

# Salida
Grafo Caso #: 1

Matriz del grafo resultante: 

0, 0, 1, 0, 0, 0, 

0, 0, 5, 0, 3, 0, 

1, 5, 0, 0, 0, 4, 

0, 0, 0, 0, 0, 2, 

0, 3, 0, 0, 0, 0, 

0, 0, 4, 2, 0, 0, 

El peso del grafo resultante es: 15

# Ejemplos de uso
En los archivos kruskal.in y kruskal.out se pueden ver ejemplos concretos del programa