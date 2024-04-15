package ciencias.RedesBooleanas;
import java.util.HashMap;

public class GeneracionRed {
    Grafica grafica;
    HashMap<String,Integer> tabla;

    public GeneracionRed(Grafica grafica, HashMap<String,Integer>tabla){
        this.grafica=grafica;
        this.tabla=tabla;
    }

    public void nuevaGeneracion(){
        grafica.modificaValores(tabla);
    }

    public Grafica getGrafica() {
        return grafica;
    }
}
