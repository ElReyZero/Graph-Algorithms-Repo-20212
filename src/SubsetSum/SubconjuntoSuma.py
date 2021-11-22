'''
Problema de Subconjunto Suma
Desarrollado Por: Juan Andrés Romero C - 202013449

'''

import sys

def numObjetivo(l:list, s:int)-> tuple:
    matriz = [[False for x in range(s+1)] for z in range(len(l)+1)]
    i = 0
    j = 0

    while i< len(l)+1:
        if j == 0:
            matriz[i][j] = True
        elif i>0 and j>0 and l[i-1] > j:
            matriz[i][j] = matriz[i-1][j]
        elif i>0 and j>0 and l[i-1] <= j:
            matriz[i][j] = (matriz[i-1][j-l[i-1]] or matriz[i-1][j])
            
        if j<s:
            j+=1
        elif j == s:
            j = 0
            i += 1
    return (matriz, matriz[len(matriz)-1][len(matriz[0])-1])

def backtracking(mat:list, lista: list, peso:int)->list:
    respuesta = []
    filas = len(mat) - 1

    while peso>0:
        if mat[filas][peso] != mat[filas-1][peso]:
           respuesta.append(filas-1)
           peso -= lista[filas-1]
        else:
            filas-=1
    return respuesta


def correcto(arreglo:list, objetivo:int) -> tuple:
    mat, booleano = numObjetivo(arreglo, objetivo)
    if booleano:
        resultado = backtracking(mat, arreglo, objetivo)
    else:
        resultado = []
    return booleano, mat, resultado

def imprimir(res:bool, mat:list, back:list, original:list, suma:int):
    print(" * El resultado del algoritmo fue: " + str(res))
    print(" * La matriz que devuelve el programa es:\n" + str(mat))
    if res:
        print(" * El backtracking del problema es (indices del arreglo original): " + str(back))
        sol = ""
        if suma != 0:
            for i in back:
                sol += str(original[i]) + " "
            print(f" * Los números que suman {suma} son " +sol)
        else:
            print(f" * Como la suma introducida es {suma}, dentro de la respuesta no se incluyen números del arreglo")
    else:
        print(" * No se encontró ninguna suma de elementos que den el valor introducido. Por consiguiente, no hay backtracking")


if __name__ == "__main__":
    contador = 0
    for caso in sys.stdin:
        contador += 1
        detalles = caso.strip("\n").strip().split(" ")
        arreglo = []
        for i in range(len(detalles)):
            if i == 0:
                suma = int(detalles[i])
            else:
                arreglo.append(int(detalles[i]))
        print()
        print("Caso: " + str(contador))
        print("La suma introducida es: "+str(suma))
        print("El arreglo introducido fue: " + str(arreglo)+"\n")
        if arreglo.__len__ == 0 and suma != 0:
            imprimir(False, [[]], [], arreglo, suma)
        else:
            respuesta, matriz, back = correcto(arreglo, suma)
            imprimir(respuesta, matriz, back, arreglo, suma)