package servicio;

import dominio.Empleado;
import dominio.Incidencia;
import repositorio.EmpleadoRepositorio;
import repositorio.IncidenciaRepositorio;

import java.util.UUID;

public class ControlHorarioService {

    private EmpleadoRepositorio empleadoRepo;
    private IncidenciaRepositorio incidenciaRepo;

    public ControlHorarioService() {
        empleadoRepo = new EmpleadoRepositorio();
        incidenciaRepo = new IncidenciaRepositorio();
    }

    /**
     * Un empleado ficha horas en un día.
     * Si supera las 10 horas, se genera una incidencia.
     */
    public void ficharHorasEmpleado(String idEmpleado, double horas) {

        Empleado e = empleadoRepo.findById(idEmpleado);

        if (e == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        if (!e.isActivo()) {
            System.out.println("Empleado no activo");
            return;
        }

        e.ficharHoras(horas);
        empleadoRepo.save(e);

        if (horas > 10) {
            Incidencia i = new Incidencia(
                    UUID.randomUUID().toString(),
                    idEmpleado,
                    "Exceso de horas fichadas: " + horas
            );
            incidenciaRepo.save(i);

            System.out.println("⚠ Incidencia creada automáticamente");
        }
    }

    /**
     * Calcula el importe de las horas extra según nivel de puesto.
     */
    public double calcularPagoHorasExtra(Empleado e, int nivelPuesto) {

        double precioHora;

        if (nivelPuesto == 1) {
            precioHora = 15;
        } else if (nivelPuesto == 2) {
            precioHora = 20;
        } else {
            precioHora = 10;
        }

        return e.getHorasExtra() * precioHora;
    }

    /**
     * Permite descargar la nómina si el empleado está activo.
     */
    public void descargarNomina(Empleado e, boolean nominaPublicada) {

        if (!e.isActivo()) {
            System.out.println("No puede descargar la nómina: empleado inactivo");
            return;
        }

        if (!nominaPublicada) {
            System.out.println("La nómina aún no está publicada");
            return;
        }

        System.out.println("📄 Nómina descargada correctamente");
    }
}
