package ciencias.RedesBooleanas;
import java.util.*;

public class Grafica {

    private class Iterador implements Iterator{

        private Iterator<Nodo> listaNodos;
        public Iterador(){
            listaNodos=(nodos.keySet()).iterator();
        }
        @Override
        public boolean hasNext() {
            return listaNodos.hasNext();
        }

        @Override
        public Object next() {
            return listaNodos.next().valor;
        }
    }

    private class Nodo{
        int valor;
        int posicion;
        ArrayList<Nodo> vecinos;

        public Nodo(int valor, int posicion) {
            vecinos=new ArrayList<>();
            this.valor = valor;
            this.posicion = posicion;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }

        public ArrayList<Nodo> getVecinos() {
            return vecinos;
        }

        public void setVecinos(ArrayList<Nodo> vecinos) {
            this.vecinos = vecinos;
        }

        @Override
        public String toString() {
            String str="";
            for(Nodo vecino : vecinos){
                str+=vecino.posicion+",";
            }
            return "Nodo{" +
                    "valor=" + valor +
                    ", posicion=" + posicion +
                    ", vecinos= "+ str+
                    "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Nodo)) return false;
            Nodo nodo = (Nodo) o;
            return posicion == nodo.posicion;
        }

        @Override
        public int hashCode() {
            return Objects.hash(posicion);
        }
    }

    HashMap<Nodo,Nodo> nodos;

    public Grafica(){
        Random random = new Random();
        nodos=new HashMap<>();
        for(int i=0;i<20;i++){
            Nodo nodo=generarNodo(i);
            nodos.put(nodo,nodo);
        }
        for (Nodo nodo : nodos.keySet()){
            Nodo vecino1=nodos.get(new Nodo(0,random.nextInt(20)));
            Nodo vecino2=nodos.get(new Nodo(0,random.nextInt(20)));
            Nodo vecino3=nodos.get(new Nodo(0,random.nextInt(20)));
            conectarNodos(nodo,nodos.get(vecino1));
            conectarNodos(nodo,nodos.get(vecino2));
            conectarNodos(nodo,nodos.get(vecino3));
        }

    }

    public Grafica(ArrayList<Integer>valores){
        Random random = new Random();
        nodos=new HashMap<>();
        for(int i=0;i<20;i++){
            Nodo nodo=new Nodo(valores.get(i),i);
            nodos.put(nodo,nodo);
        }
        for (Nodo nodo : nodos.keySet()){
            Nodo vecino1=nodos.get(new Nodo(0,random.nextInt(20)));
            Nodo vecino2=nodos.get(new Nodo(0,random.nextInt(20)));
            Nodo vecino3=nodos.get(new Nodo(0,random.nextInt(20)));
            conectarNodos(nodo,nodos.get(vecino1));
            conectarNodos(nodo,nodos.get(vecino2));
            conectarNodos(nodo,nodos.get(vecino3));
        }
    }

    public void conectarNodos(Nodo nodo1, Nodo nodo2){
        ArrayList<Nodo> vecinos1= nodo1.getVecinos();
        ArrayList<Nodo> vecinos2= nodo2.getVecinos();
        if(nodo1.getVecinos().size()<3) {
            vecinos1.add(nodo2);
        }
    }
    public Nodo generarNodo(int posicion){
        Random random = new Random();
        int valor = random.nextInt(2);
        return new Nodo(valor,posicion);
    }

    public HashMap<Integer,Integer> getValores() {
        HashMap<Integer, Integer> valores=new HashMap<>();
        for(Nodo nodo: nodos.keySet())
            valores.put(nodo.posicion,nodo.valor);
        return  valores;
    }

    public HashMap<Nodo, Nodo> getNodos() {
        return nodos;
    }

    public void modificaValores(HashMap<String , Integer> tabla){
        HashMap<Integer,Integer> valores= getValores();
        for(Nodo nodo: nodos.keySet()){
            String valor="";
            for(Nodo vecino: nodo.vecinos){
                valor+=valores.get(vecino.posicion);
            }
            Integer nuevoVal= tabla.get(valor);
            nodo.setValor(nuevoVal);
        }
    }

    public Iterator iterator(){
        return new Iterador();
    }

    @Override
    public String toString() {
        String str="";
        for(Nodo nodo: nodos.keySet()) {
            String vecinos = "";
            for(Nodo vecino: nodo.vecinos)
                vecinos+=vecino.posicion+ " ";
            str += nodo.posicion + " " +vecinos+ "\n";
        }
        return str;
    }
}
