
package MODELO;
public class Historial {
    private int historialId;
    private String fecha;
    private String nombre;
    private String apellidos;
    private double peso;
    private double IMC;
    private double consumo_calorias;
    private double quema_calorias;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getConsumo_calorias() {
        return consumo_calorias;
    }

    public void setConsumo_calorias(double consumo_calorias) {
        this.consumo_calorias = consumo_calorias;
    }

    public double getQuema_calorias() {
        return quema_calorias;
    }

    public void setQuema_calorias(double quema_calorias) {
        this.quema_calorias = quema_calorias;
    }

    public int getHistorialId() {
        return historialId;
    }

    public void setHistorialId(int historialId) {
        this.historialId = historialId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
