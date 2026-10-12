#ejercico
def sinpila():
    paginas = ["Home"]
    while True:
        print("1. Ir a otra página")
        print("2. Volver a la página anterior")
        print("3. Salir")
        opcion = input("Elige una opción: ")

        if opcion == "1":
            print("estas en la página:", paginas[-1])
            nueva_pagina = input("Ingresa el nombre de la nueva página: ")
            paginas.append(nueva_pagina)
        elif opcion == "2":
            if len(paginas) > 1:
                paginas.pop()
            else:
                print("No hay páginas anteriores.")
        elif opcion == "3":
            break
        else:
            print("Opción no válida.")
#ejercicio
lista = [4, 2, 7, 1, 3]
salida=[]
def pila():
    for i in lista:
        j =lista[i+1] if i < len(lista) - 1 else -1
        if lista[i] > j:
            salida.append(lista[i])
        else:
            salida.append(-1) 