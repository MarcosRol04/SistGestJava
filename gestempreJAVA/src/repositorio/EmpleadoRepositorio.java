package repositorio;

import dominio.Empleado;
import util.FicheroUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoRepositorio
        implements IRepositorioExtend<Empleado, String> {

    private final String RUTA = "empleados.txt";
    private List<Empleado> empleados;

    public EmpleadoRepositorio() {
        cargarEmpleados();
    }

    // ================= CARGA Y GUARDADO =================

    private void cargarEmpleados() {
        empleados = new ArrayList<>();
        List<String> lineas = FicheroUtil.leerFichero(RUTA);

        for (String linea : lineas) {
            String[] d = linea.split(";");

            Empleado e = new Empleado(
                    d[0],              // id
                    d[1],              // departamento
                    d[2],              // nombre
                    d[3],              // apellido
                    d[4],              // email
                    Double.parseDouble(d[5]) // salario
            );

            e.setActivo(Boolean.parseBoolean(d[6]));
            e.ficharHoras(Double.parseDouble(d[7]));

            empleados.add(e);
        }
    }

    private void guardarEmpleados() {
        List<String> lineas = new ArrayList<>();

        for (Empleado e : empleados) {
            String linea =
                    e.getIdEmpleado() + ";" +
                            e.getDepartamento() + ";" +
                            e.getNombre() + ";" +
                            e.getApellido() + ";" +
                            e.getEmail() + ";" +
                            e.getSalario() + ";" +
                            e.isActivo() + ";" +
                            e.getHorasDia() + ";" +
                            e.getHorasExtra();

            lineas.add(linea);
        }

        FicheroUtil.escribirFichero(RUTA, lineas);
    }

    // ================= CRUD =================

    @Override
    public long count() {
        return empleados.size();
    }

    @Override
    public boolean existsById(String id) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Empleado findById(String id) {
        for (Empleado e : empleados) {
            if (e.getIdEmpleado().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Optional<Empleado> findByIdOptional(String id) {
        return Optional.ofNullable(findById(id));
    }

    @Override
    public Iterable<Empleado> findAll() {
        return empleados;
    }

    @Override
    public List<Empleado> findAllToList() {
        return empleados;
    }

    @Override
    public Empleado save(Empleado entity) {

        Empleado existente = findById(entity.getIdEmpleado());
        if (existente != null) {
            empleados.remove(existente);
        }

        empleados.add(entity);
        guardarEmpleados();
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Empleado e = findById(id);
        if (e != null) {
            empleados.remove(e);
            guardarEmpleados();
        }
    }

    @Override
    public void deleteAll() {
        empleados.clear();
        guardarEmpleados();
    }

    // 🔹 MÉTODO PROPIO
    public List<Empleado> findEmpleadosActivos() {
        List<Empleado> activos = new ArrayList<>();

        for (Empleado e : empleados) {
            if (e.isActivo()) {
                activos.add(e);
            }
        }
        return activos;
    }
}
