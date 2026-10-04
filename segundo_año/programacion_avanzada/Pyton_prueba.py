#ejercicio 1
def ej1():
    nombre = input("Ingrese su nombre: ")
    edad = int(input("Ingrese su edad: "))
    print(f"hola {nombre}, dentro de 10 años tendras {edad + 10} años")
#ejercicio 2
def ej2():
    num1 = int(input("Ingrese una temperatura en grados Celsisus"))
    print(f"{num1} en farenheit es : {num1 * 9/5 + 32}")
#ejercicio 3
def ej3():
    nota = int(input("Ingrese una nota :"))
    if 7 >= nota >= 5:
        print("bien")
    elif 10 >= nota > 7:
        print("sobresaliente")
    else:
        print("mal")
#ejercicio 4
def ej4():
    año = int(input("ingrese un año: "))
    if (año % 100 == 0 and año % 400 == 0) or (año % 4 == 0 and año % 100 != 0):
        print(f"{año} es un año bisiesto")
    else:
        print(f"{año} no es un año bisiesto")
#ejercicio 5
def ej5():
    num1 = int(input("Ingrese un numero: "))
    while num1 != -1:
        num1 -= 1
        print(f"el numero es {num1}")

    for n in range(num1 + 1):
        print(num1-n)
#ejercicio 6
def ej6():
    nombre = input("pon un nombre:")
    nombre1 = input("pon otro nombre:")

    lista = [nombre, nombre1]
    for n in lista:
        nombre3 = input("pregunta si esta en la lista:")
        while nombre3 == nombre or nombre3 == nombre1:
            print("esta en la lista")
        else:
            print("no esta en la lista")

#ejercicio 7
def ej7():
    lista2 = []
    lista = [4,2,4,7,2,9.4]
    for n in lista:
        palabra = input("escribe un numero:")
        if palabra in lista:
            print("esta en la lista")
        else:
            print("no esta en la lista")
#ejercicio 8
lista = [7,1,5,8,4,10]
numero = lista[0]
numero2 = lista[0]

def ej8():   
    for n in range(len(lista)):
        if numero2 < lista[n]:
            numero = numero2
            numero2 = lista[n]
        if numero < lista[n]:
            numero2 = lista[n]
print("el numero mayor es: ", numero2)
print("el segundo mayor es : " ,numero)

#ejercicio 9
def ej9():
    frase ="hola soy Aitor"
    frase2 = input("dame un insulto para Aitor:")

    print("".join(frase2.split()[::-1]))
#ejercicio 10
def es_pare(n):
    if n == 0 :
        return True
    elif n == 1:
        return False
    else:
        return es_pare(n-2)
#ejercicio 11
def mayor():
    lista = [2,4,5,7,10,27,1,7,9,40,210,30]
    NumeroMayor = lista[0]
    for n in lista:
        if n > NumeroMayor:
            NumeroMayor = n

    print(f"el numero mayor es: {NumeroMayor}")
def mayor2(lista):
    if len(lista) == 1:
        return lista[0]
    else:
        primero = lista[0]
        sublista = lista[1:]
        mayor_sub = mayor2(sublista)

        if primero > mayor_sub:
            return primero
        else:
            return mayor_sub

def suma_recursiva(lista):
    if len(lista) == 0:
        return 0
    else:
        primero = lista[0]
        sublista = lista[1:]

        return primero + suma_recursiva(sublista)
#ejercicio 12
def read():
    with open(f"texto/archivo.txt", "r") as archivo:
        contenido = archivo.read()
        print(contenido)

def write():
    with open(f"texto/archivo.txt", "w") as archivo:
        archivo.write("hola soy Ruben Chicano Hernandez")

def append():
    with open(f"texto/archivo.txt", "a") as archivo:
        archivo.write("\nHola soy Ruben Chicano Hernandez")

#ejercicio 13
x = "texto.txt" 

with open(x, "a") as archivo:
    archivo.write("Hola soy Ruben Chicano Hernandez")

def lineas():
    with open("texto.txt", "r") as archivo:
        suma = 0
        for linea in archivo:
            suma += 1

def palabras():
    with open("texto.txt", "r") as archivo:
        suma = 0
        for linea in archivo:
            palabras = linea.split()
            suma += len(palabras)
def caracteres():
    with open("texto.txt", "r") as archivo:
        suma = 0
        for linea in archivo:
            suma += len(linea)

#ejer 14
def ej14():
    with open("texto.txt", "r") as archivo:
        contenido = archivo.read()
        palabras = contenido.split()
        a = 0
        for palabra in palabras:
            if len(palabra) < 4:
                a += 1
                palabras.remove(palabra)
#sudoku

