package dominio;

import java.io.Serializable;
import java.time.LocalDate;

public class Incidencia implements Serializable {

    private String idIncidencia;
    private String idEmpleado;
    private LocalDate fecha;
    private String motivo;
    private boolean revisada;
    private String resolucion;

    public Incidencia(String idIncidencia, String idEmpleado, String motivo) {
        this.idIncidencia = idIncidencia;
        this.idEmpleado = idEmpleado;
        this.motivo = motivo;
        this.fecha = LocalDate.now();
        this.revisada = false;
        this.resolucion = "";
    }

    // GETTERS
    public String getIdIncidencia() {
        return idIncidencia;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public boolean isRevisada() {
        return revisada;
    }

    public String getResolucion() {
        return resolucion;
    }

    /**
     * Lógica de negocio:
     * Un administrador revisa la incidencia
     */
    public void resolver(String resolucion) {
        this.revisada = true;
        this.resolucion = resolucion;
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "idIncidencia='" + idIncidencia + '\'' +
                ", idEmpleado='" + idEmpleado + '\'' +
                ", puta=" + fecha +
                ", motivo='" + motivo + '\'' +
                ", revisada=" + revisada +
                ", resolucion='" + resolucion + '\'' +
                '}';
    }
}
