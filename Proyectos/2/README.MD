# Integrantes
1. Arturo González Peñaloza 319091193
2. Emilio Arsenio Raudry Rico 318289276

# Estructura del Proyecto

El proyecto consiste principalmente de tres partes:
1. Interfaz. Este paquete solo consta de una clase, Interfaz.java, que es la encargada de iniciar el juego y proveer métodos para que el usuario ingrese entradas válidas.

2. Logica. Esta parte consta de los elementos funcionales del juego:
   a. EncerradoPieza.java. Es una enumeración que se encarga de representar a una pieza del juego.
   b. EncerradoTablero.java. Es la encargada de representar el tablero del juego. Provee métodos para mover fichas y verificar que los movimientos sean válidos.
   c. Juego.java. Se encarga del desarrollo general del juego, establece y desarrolla las rondas.
   d. Las clases relacionadas con minimax.

3. Estructuras. Está constituida por aquellas estructuras de datos que desarrollamos en las clases de laboratorio.

# Cómo compilar
    Desde el directorio donde se encuentra este README, ejecutar.
```
    javac -d . @sources.txt

```

# Cómo ejecutar
    Desde el directorio donde se encuentra este README, ejecutar.
```
    java edd.src.Interfaz.Interfaz

```

# Inconvenientes

Tuvimos dificultades al momento de implementar detalles como la entrada de una configuración inicial personalizada. Además, nos resultó complicado verificar que los movimientos que el usuario introdujera fueran válidos. De igual forma, la implementación del árbol de minimax fue complicada.
