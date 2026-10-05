TAMANO = 4      # el tablero es de 4x4
CUADRADO = 2    # los minicuadrados son de 2x2

def leer_sudoku(ruta):
    """Lee el fichero y devuelve el tablero como una lista de listas"""
    tablero = []
    with open(ruta, "r", encoding="utf-8") as fichero:
        for linea in fichero:
            linea = linea.strip()
            if linea == "":
                continue
            # "|1|0|3|4|" -> ["1", "0", "3", "4"]
            partes = linea.strip("|").split("|")
            fila = []
            for numero in partes:
                fila.append(int(numero))
            tablero.append(fila)
    return tablero


def guardar_sudoku(ruta, tablero):
    """Escribe el tablero en el fichero con el mismo formato |x|x|x|x|"""
    with open(ruta, "w", encoding="utf-8") as fichero:
        for fila in tablero:
            linea = "|"
            for numero in fila:
                linea += str(numero) + "|"
            fichero.write(linea + "\n")


def mostrar_tablero(tablero):
    for fila in tablero:
        print(fila)
    print()


def numeros_de_fila(tablero, f):
    """Devuelve un set con los números que ya hay en la fila f."""
    numeros = set()
    for c in range(TAMANO):
        if tablero[f][c] != 0:
            numeros.add(tablero[f][c])
    return numeros


def numeros_de_columna(tablero, c):
    """Devuelve un set con los números que ya hay en la columna c."""
    numeros = set()
    for f in range(TAMANO):
        if tablero[f][c] != 0:
            numeros.add(tablero[f][c])
    return numeros


def numeros_de_cuadrado(tablero, f, c):
    """Devuelve un set con los números del minicuadrado de la casilla (f, c)."""
    numeros = set()
    # esquina superior izquierda del minicuadrado
    fila_inicio = (f // CUADRADO) * CUADRADO
    col_inicio = (c // CUADRADO) * CUADRADO
    for i in range(fila_inicio, fila_inicio + CUADRADO):
        for j in range(col_inicio, col_inicio + CUADRADO):
            if tablero[i][j] != 0:
                numeros.add(tablero[i][j])
    return numeros


def calcular_candidatos(tablero):
    """
    Devuelve un diccionario {(fila, columna): {candidatos}}
    solo para las casillas vacías (las que tienen 0).
    """
    todos = set(range(1, TAMANO + 1))   # {1, 2, 3, 4}
    candidatos = {}
    for f in range(TAMANO):
        for c in range(TAMANO):
            if tablero[f][c] == 0:
                usados = (numeros_de_fila(tablero, f)
                          | numeros_de_columna(tablero, c)
                          | numeros_de_cuadrado(tablero, f, c))
                candidatos[(f, c)] = todos - usados
    return candidatos


def resolver(tablero):
    """
    Va rellenando las casillas que solo tienen un candidato.
    Repite hasta que no quede ninguna casilla así.
    Devuelve True si el sudoku queda completo.
    """
    hay_cambios = True
    while hay_cambios:
        hay_cambios = False
        candidatos = calcular_candidatos(tablero)
        print("Candidatos:", candidatos)

        for casilla, posibles in candidatos.items():
            if len(posibles) == 1:
                f, c = casilla
                numero = list(posibles)[0]
                # comprobamos que sigue siendo válido (otra casilla
                # rellenada en esta misma vuelta podría haberlo cambiado)
                if (numero not in numeros_de_fila(tablero, f)
                        and numero not in numeros_de_columna(tablero, c)
                        and numero not in numeros_de_cuadrado(tablero, f, c)):
                    tablero[f][c] = numero
                    print("Casilla", casilla, "->", numero)
                    hay_cambios = True

    for fila in tablero:
        if 0 in fila:
            return False
    return True

# Programa principal 
tablero = leer_sudoku("sudoku.txt")
print("Sudoku inicial:")
mostrar_tablero(tablero)

completo = resolver(tablero)

print()
if completo:
    print("Sudoku resuelto:")
else:
    print("No se ha podido completar sin probar valores. Resultado parcial:")
mostrar_tablero(tablero)

guardar_sudoku("solución.txt", tablero)
print("Solución guardada en solución.txt")
