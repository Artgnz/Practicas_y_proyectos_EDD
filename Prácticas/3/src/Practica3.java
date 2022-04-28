public class Practica3{

    /**
     *Imprime todas las permutaciones de un String dado.
     *@param cadena String a permutar.
     */
    public static void  permutacionesCadena(String cadena){
	if (cadena == null)
	   throw new IllegalArgumentException("");
	else{
	    char[] array = cadena.toCharArray();//Convertimos la cadena a arreglo para tener complejidad constante al momento de permutar.
	    permuta(array, 0, array.length-1);
	}
	
    }
    /**
     *Imprime todas las permutaciones de un arreglo de caracteres dado.
     *@param array Arreglo de caracteres apermutar.
     *@param c Indice a partir del cual se comienza a permutar el arreglo.
     *@param f Indice que indica al ulitmo elemento del arreglo.
     */
    public static void permuta(char[] array, int c, int f){
	if (c == f){
	    for(int i = 0; i<array.length; i++){//Al llegar al final del arreglo, imprimimos la permutacion.
		System.out.print(array[i]);
	    }
	    System.out.print("\n");
	}
	
	else{
	    for(int i = c; i <=f; i++ ){
		swap(array, c, i);//Intercambiamos los caracteres.
		permuta(array, c+1, f);//Permutamos sobre el resto de caracteres restantes hasta llegar al caso base.
		swap(array, c, i);//Intercambiamos los caracteres para volver al orden inicial una vez impresa la permutacions (Backtracking).
	    }
	}
    }
    
    /**
     *Intercambia a dos caracteres de un arreglo dados dos indices.
     *@param array El arreglo de caractes a intercambiar.
     *@param i Primer indice.
     *@param j Segundo indice.
     */
    public static void swap(char[] array, int i, int j){
	char aux = array[i];
	array[i] = array[j];
	array[j] = aux;
    }
    
    /**
     *Metodo main que permite ejecutar los metodos de la clase.
     *@param args Argumentos de la consola.
     */
    public static void main(String[] args){
	String cadena = "ABCD";
	permutacionesCadena(cadena);
    }
}
