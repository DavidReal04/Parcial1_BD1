package co.edu.unbosque;

public class Apuestas {

    private int consecutivo;
    private int idEncuentro;
    private int valorApostado;
    private int idUsuario;

    public Apuestas(int consecutivo, int idEncuentro, int valorApostado, int idUsuario) {
        this.consecutivo = consecutivo;
        this.idEncuentro = idEncuentro;
        this.valorApostado = valorApostado;
        this.idUsuario = idUsuario;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getIdEncuentro() {
        return idEncuentro;
    }

    public void setIdEncuentro(int idEncuentro) {
        this.idEncuentro = idEncuentro;
    }

    public int getValorApostado() {
        return valorApostado;
    }

    public void setValorApostado(int valorApostado) {
        this.valorApostado = valorApostado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
