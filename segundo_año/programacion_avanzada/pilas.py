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
salida = [-1] * (len(lista))
for i in range(len(lista)):
    elemento = lista[i]
    j = i + 1
    while j < len(lista):
        if lista[j] > elemento:
            salida[i] = lista[j]
            break
        j=+1
