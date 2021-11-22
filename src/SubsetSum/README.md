# Problema de Subconjunto Suma
Desarrollado para el Parcial 2 de Diseño y Análisis de Algoritmos 2021-2
Por: Juan Andrés Romero C - 202013449

# Uso: 
1. Abrir una terminal del sistema en el directorio donde se encuentra el archivo SubconjuntoSuma.py
2. Guardar un archivo .in con el siguiente formato:
SumaDeseada N1 N2 N3 N4 ... Nn

- Donde SumaDeseada es la suma a buscar y N1 hasta Nn son los números elementos del arreglo en donde buscar. 

Ejemplo: 
16 0 2 3 15 89 1

16 sería la suma a buscar y el arreglo: [16,0,2,3,15,89,1]
Nota: Se toma un caso por línea, es decir, cada línea del archivo .in comprende un único problema para resolver. (Mirar archivo de ejemplo Z.in)

3. Ejecutar el comando `python SubconjuntoSuma.py < nombreEntrada.in > nombreSalida.out`

# Salida:
Un ejemplo de la salida del problema se ve así:

Caso: 1
La suma introducida es: 16
El arreglo introducido fue: [1, 3, 5, 10]

 * El resultado del algoritmo fue: True
 * La matriz que devuelve el programa es:
[[True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False], [True, True, False, False, False, False, False, False, False, False, False, False, False, False, False, False, False], [True, True, False, True, True, False, False, False, False, False, False, False, False, False, False, False, False], [True, True, False, True, True, True, True, False, True, True, False, False, False, False, False, False, False], [True, True, False, True, True, True, True, False, True, True, True, True, False, True, True, True, True]]
 * El backtracking del problema es (indices del arreglo original): [3, 2, 0]
 * Los números que suman 16 son 10 5 1 

# Ejemplos de uso
En los archivos Z.in y z.out se pueden ver ejemplos concretos del programa