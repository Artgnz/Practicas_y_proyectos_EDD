# Pasos para compilar
1. En caso de agregar archivos .java, ejecutar el siguiente comando:
```
    find . -name "*.java" > sources.txt
```
2. Compilar:
```
    javac -d . @sources.txt
```
# Cómo ejecutar
```
    java edd.src.Interfaz.Interfaz
```
