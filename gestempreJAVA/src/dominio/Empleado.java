package dominio;

import java.io.Serializable;

public class Empleado implements Serializable {

    private String idEmpleado;
    private String departamento;
    private String nombre;
    private String apellido;
    private String email;
    private double salario;
    private boolean activo;

    // Control horario
    private double horasDia;
    private double horasExtra;

    public Empleado(String idEmpleado, String departamento, String nombre,
                    String apellido, String email, double salario) {

        this.idEmpleado = idEmpleado;
        this.departamento = departamento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.salario = salario;
        this.activo = true;
        this.horasDia = 0;
        this.horasExtra = 0;
    }

    // GETTERS
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public double getSalario() {
        return salario;
    }

    public boolean isActivo() {
        return activo;
    }

    public double getHorasDia() {
        return horasDia;
    }

    public double getHorasExtra() {
        return horasExtra;
    }

    // SETTERS
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Lógica de negocio:
     * El empleado ficha horas.
     * Si supera 8 horas, se consideran horas extra.
     */
    public void ficharHoras(double horas) {
        this.horasDia = horas;

        if (horas > 8) {
            this.horasExtra = horas - 8;
        } else {
            this.horasExtra = 0;
        }
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado='" + idEmpleado + '\'' +
                ", departamento='" + departamento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", salario=" + salario +
                ", activo=" + activo +
                ", horasDia=" + horasDia +
                ", horasExtra=" + horasExtra +
                '}';
    }
}
