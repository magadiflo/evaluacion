import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Yincana {

    private int totalEquipos;
    private boolean esImpar;
    private String enfrentamientos[][];
    private String salida[][];
    private int filaSalida = 0;
    private List<String> lista;

    public Yincana(String listaParticipantes[]) {
        this.configuraciones(listaParticipantes);
        this.generarMatrizEnfrentamientos();

        this.generarSalida(this.totalEquipos / 2);
        this.filaSalida++;

        for (int i = 0; i < this.lista.size() - 2; i++) {
            this.lista = this.rotar(this.lista);
            this.generarMatrizEnfrentamientos();
            this.generarSalida(this.totalEquipos / 2);
            this.filaSalida++;
        }

        this.imprimirSalida();
    }

    private void configuraciones(String listaParticipantes[]) {
        this.esImpar = listaParticipantes.length % 2 != 0;
        this.totalEquipos = this.esImpar ? listaParticipantes.length + 1 : listaParticipantes.length;
        this.enfrentamientos = new String[this.totalEquipos / 2][2];

        this.lista = new ArrayList<>(Arrays.asList(listaParticipantes));
        if (this.esImpar) {
            this.lista.add("Descansa");
        }

        this.salida = new String[this.totalEquipos - 1][this.totalEquipos / 2];
    }

    private void generarMatrizEnfrentamientos() {
        for (int i = 0; i < this.enfrentamientos.length; i++) {
            for (int j = 0; j < this.enfrentamientos[i].length; j++) {
                if (j == 0) {
                    this.enfrentamientos[i][j] = this.lista.get(i);
                } else {
                    this.enfrentamientos[i][j] = this.lista.get(this.lista.size() - j - i);
                }
            }
        }
    }

    private List<String> rotar(List<String> lista) {
        String temp = lista.get(lista.size() - 1);
        int index = lista.size() - 1;
        for (int i = 1; i < lista.size(); i++) {
            lista.set(index--, lista.get(index));
        }
        lista.set(1, temp);
        return lista;
    }


    private void generarSalida(int columnas) {
        String cadena = "";
        int k = 0;
        for (int i = 0; i < this.enfrentamientos.length; i++) {
            for (int j = 0; j < this.enfrentamientos[i].length; j++) {
                cadena += this.enfrentamientos[i][j] + ",";
            }
            cadena = cadena.substring(0, cadena.length() - 1);
            //cadena += "\n";
            if (k < columnas) {
                this.salida[this.filaSalida][k] = cadena;
                cadena = "";
                k++;
            }
        }
    }

    private void imprimirSalida() {
        String cadena = "";
        if (this.esImpar) {
            for (int i = 0; i < this.salida.length; i++) {
                cadena += "[";
                for (int j = 0; j < this.salida[i].length; j++) {
                    cadena += "[" + this.salida[i][j] + "],";
                }
                cadena = cadena.substring(0, cadena.length() - 1);
                cadena += "]\n";
            }
        } else {
            for (int j = 0; j < this.salida[0].length; j++) {
                cadena += "[";
                for (int i = 0; i < this.salida.length; i++) {
                    cadena += "[" + this.salida[i][j] + "],";
                }
                cadena = cadena.substring(0, cadena.length() - 1);
                cadena += "]\n";
            }
        }
        System.out.println(cadena);
    }
}
