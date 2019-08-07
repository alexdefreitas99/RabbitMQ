import java.io.Serializable;

public class Venda {

    private int id;
    private String nomeDaFila;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDaFila() {
        return nomeDaFila;
    }

    public void setNomeDaFila(String nomeDaFila) {
        this.nomeDaFila = nomeDaFila;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", nomeDaFila='" + nomeDaFila + '\'' +
                '}';
    }
}