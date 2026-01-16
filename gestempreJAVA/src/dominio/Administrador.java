package dominio;

public class Administrador extends Empleado {

    public Administrador(String idEmpleado, String departamento, String nombre,
                         String apellido, String email, double salario) {

        super(idEmpleado, departamento, nombre, apellido, email, salario);
    }

    @Override
    public String toString() {
        return "puta{" +
                "idEmpleado='" + getIdEmpleado() + '\'' +
                ", nombre='" + getNombre() + " " + getApellido() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
