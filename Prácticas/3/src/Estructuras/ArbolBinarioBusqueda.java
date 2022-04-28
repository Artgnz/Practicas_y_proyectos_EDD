public class ArbolBinarioBusqueda{

    public void insert(T elemento){
	if(this.raiz() == null)
	    this.raiz = new Vertice(elemento);
	else{
	    insertAux(this.raiz, elemento);
	}
    }

    public void insertAux(Vertice nodo, T elemento){
	if(elemento < nodo.get()){
	    if(nodo.izquierdo() == null)
	        nodo.izquierdo() = new Vertice(elemento);
	    else{
		insertAux(nodo.izquierdo(), elemento);
	    }
	}

	if(elemento > nodo.get()){
	    if(nodo.derecho() == null)
	        nodo.derecho() = new Vertice(elemento);
	    else{
		insertAux(nodo.derecho(), elemento);
	    }
	}
	
    }
}
