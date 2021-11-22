# Algoritmo de Prim usando Listas de Adyacencia
Desarrollado para la clase de Diseño y Análisis de Algoritmos 2021-2
Por: 
* Juan Andrés Romero C - 202013449
* Luccas Rojas - 201923052

# Uso:
1. Abrir una terminal del sistema donde se encuentra el archivo Prim.java
2. Guardar un archivo .in con el siguiente formato:
numVertices nodoIni1,nodoFin1,peso1 nodoIni2,nodoFin2,peso2 ... nodoInin,nodoFinn,peson

- Donde numVertices es el número de nodos que tiene el grafo, los nodoIni son nodo de inicio, los nodoFin son los nodo destino y el peso es el peso del arco que los conecta.
- Nota: La implementación trabaja con un arco no dirigido y se encarga de agregar arco de ida y vuelta a la lista de adyacencias, por lo cual solo es necesario ingresar cada arco en una sola dirección.
- Nota 2: El grafo tiene los nodos definidos de 0 hasta n, de forma que el número del nodo a agregar indica el índice del nombre del nodo. Es decir, en el input es necesario que se introduzcan únicamente nodos en orden desde 0 hasta n.

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

3. Ejecutar el comando `javac Prim.java -d .`
4. Ejecutar el comando `java Prim.Prim < nombreEntrada.in > nombreSalida.out`

# Salida
Grafo Caso #: 1

Nodo inicial: 0 
Nodo destino: 2 - Peso: 1

Nodo inicial: 1 
Nodo destino: 2 - Peso: 5
Nodo destino: 4 - Peso: 3

Nodo inicial: 2 
Nodo destino: 0 - Peso: 1
Nodo destino: 5 - Peso: 4
Nodo destino: 1 - Peso: 5

Nodo inicial: 3 
Nodo destino: 5 - Peso: 2

Nodo inicial: 4 
Nodo destino: 1 - Peso: 3

Nodo inicial: 5 
Nodo destino: 2 - Peso: 4
Nodo destino: 3 - Peso: 2

Peso total: 15

# Ejemplos de uso
En los archivos prim.in y prim.out se pueden ver ejemplos concretos del programa