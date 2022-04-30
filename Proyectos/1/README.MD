#Integrantes
1. Arturo González Peñaloza
2. Emilio Arsenio Raudry Rico

#Estructura del Proyecto
```
El proyecto consiste principalmente de cuatro partes:
```

1. Interfaz. Esta solo consta de una clase, Interfaz.java, es la encargada de iniciar el juego y de validar los valores de entrada que ingrese el usuario.
2. Elementos. Esta formada por las clases encargadas de representar aquellos objetos que son usados en el transcurso del juego. La clase Carta.java se encarga de representar cada una de las cartas usadas en el juego. Baraja.java representa al mazo de 60 cartas usadas durante el juego. Jugador.java representa a cada jugador que participará en el juego Wizard.
3. Logica. Esta parte consta de los elementos funcionales del juego. Truco.java se encarga de la creación y el desarrollo de un truco del juego, estableciendo los ganadores de cada uno. Ronda.java consta de un cierta cantidad de trucos, también se encarga de establecer el puntaje de cada jugador. Juego.java se encarga del desarrollo general del juego, establece y desarrolla el numero de rondas al igual que el ganador de todo el juego.

4. Estructuras. Esta constituida por aquellas estructuras de datos que desarrollamos en las clases de laboratorio.

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

#Inconvenientes
Tuvimos pequeños inconvenientes con la actualización de nuestro repositorio local con respecto al remoto, no obstante logramos resolverlo. De igual manera tuvimos un ligero problema con una de las clases de Estructuras, ya que en la definición de ciertos métodos no contemplaba ciertos casos que terminaron por parar la ejecución de nuestro programa.
