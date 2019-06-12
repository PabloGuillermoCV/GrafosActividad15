package Graphs;

import java.util.Vector;

public class Conjunto<V> {

	 //Instance
    private Vector<V> CE;
    private int cantElem;
    private Comparator<V> comp;
    //constructor
    public Conjunto(int max, Comparator<V> c){
        CE = new Vector<V>(max);
        cantElem = 0;
        comp = c;
    }
    //comandos
    public void insertar(V e){
        if(!(pertenece(e))){
            CE.add(e);
            cantElem++;
        }
    }
    //consultas
    public boolean pertenece(V e){
        boolean esta = false;
        for(int i = 0; i < cantElem && !(esta); i++){
            esta = CE.get(i).equals(e);
        }
        return esta;
    }
    private boolean pertenece(Conjunto<V> c){
        boolean inc = false;
        int menor = 0;
        if(this.cardinalidad() < c.cardinalidad())
            menor = this.cardinalidad();
        else
            menor = c.cardinalidad();
        for(int i = 0; i < menor && inc; i++){
           inc = busquedaBinaria(0,cantElem,CE.get(i));
        }
        return inc;
    }
    public boolean vacio(){
        return cantElem == 0;
    }
    public boolean incluido(Conjunto<V> c){
       //busco el conjunto de menor cardinalidad
        boolean inc = false;
        int menor = 0;
        if(this.cardinalidad() < c.cardinalidad())
            menor = this.cardinalidad();
        else
            menor = c.cardinalidad();
        for(int i = 0; i < menor && inc; i++){
           inc = busquedaBinaria(0,cantElem,CE.get(i));
        }
        return inc;
    }
    private boolean busquedaBinaria(int ini, int fin, V e){
        boolean existe;
        if(ini > fin)
            existe = false;
        else{
            int mitad = (ini + fin)/2;
            if(CE.get(mitad).equals(e))
                existe = true;
            else{
                if(comp.compare(CE.get(mitad), e) < 0)
                    existe = busquedaBinaria(ini, mitad-1,e);
                else
                    existe = busquedaBinaria(mitad+1,fin,e);
            }
        }
        return existe;
    }
    public boolean equals(Conjunto<V> c){
        boolean igual = true;
        if(cardinalidad() == c.cardinalidad()){
            for(int i = 0; i < cantElem && igual; i++){
                igual = CE.get(i).equals(c);
            }
        }
        else
            igual = false;
        return igual;
    }
    public boolean disjuntos(Conjunto<V> e){
        return this.interseccion(e).vacio();
    }
    public Conjunto<V> union(Conjunto<V> e){
        Conjunto<V> u = new Conjunto<V>(cardinalidad()+e.cardinalidad(), comp);
        for(int i = 0; i < cantElem; i++){
            u.insertar(CE.get(i));
        }
        for(int i = 0; i < e.cardinalidad(); i++){
            u.insertar(e.obtenerElemento(i));
        }
        return u;
    }
    public V obtenerElemento(int pos){
        return CE.get(pos);
    }
    public Conjunto<V> interseccion(Conjunto e){
        int menor = 0;
        if(cardinalidad() <= e.cardinalidad()){
            menor = cardinalidad();
        }
        else{
            menor = e.cardinalidad();
        }
        Conjunto<V> I = new Conjunto<V>(menor,comp);
        for(int i = 0; i < cantElem; i++){
            if(busquedaBinaria(0,cantElem,CE.get(i)))
                I.insertar(CE.get(i));
        }
        return I;
    }
    public Conjunto<V> diferencia(Conjunto<V> e){
        Conjunto<V> D = null;
        if( this.cardinalidad() <= e.cardinalidad() && this.incluido(e)){
            D = new Conjunto<V>(e.cardinalidad()-cardinalidad(),comp);
            for( int i = cantElem; i <e.cardinalidad(); i++){
                D.insertar(e.obtenerElemento(i));
            }
        }
        else{
            if(cardinalidad() >= e.cardinalidad() && e.incluido(this)){
                D = new Conjunto<V>(cardinalidad()-e.cardinalidad(),comp);
                for(int i = e.cardinalidad(); i < cantElem; i++){
                   D.insertar(CE.get(i)); 
                }
            }
            else{
                if(this.disjuntos(e)){
                    D = new Conjunto<V>(cardinalidad()+e.cardinalidad(),comp);
                    int l = 0; //lector para el Conjunto en si
                    int i = 0; //lector para el conjunto e
                    while(l < cantElem && i < e.cardinalidad()){
                        D.insertar(CE.get(l));
                        D.insertar(e.obtenerElemento(i));
                        l++;
                        i++;
                    }
                    while(l < cantElem){
                        D.insertar(CE.get(l));
                        l++;
                    }
                    while(i < e.cardinalidad()){
                        D.insertar(e.obtenerElemento(i));
                        i++;
                    }
                }   
            }
        }
        return D;    
    }
    public int cardinalidad(){
        return cantElem;
    }
}
