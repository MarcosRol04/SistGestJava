package repositorio;

import dominio.Empleado;
import util.FicheroUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoRepositorio
        implements IRepositorioExtend<Empleado, String> {

    private final String RUTA = "empleados.dat";
    private List<Empleado> empleados;

    public EmpleadoRepositorio() {
        empleados = FicheroUtil.leerFichero(RUTA);
    }

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
        FicheroUtil.escribirFichero(RUTA, empleados);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Empleado e = findById(id);
        if (e != null) {
            empleados.remove(e);
            FicheroUtil.escribirFichero(RUTA, empleados);
        }
    }

    @Override
    public void deleteAll() {
        empleados.clear();
        FicheroUtil.escribirFichero(RUTA, empleados);
    }

    // 🔹 MÉTODO PROPIO (OBLIGATORIO)
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
