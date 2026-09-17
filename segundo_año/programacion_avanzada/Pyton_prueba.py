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

