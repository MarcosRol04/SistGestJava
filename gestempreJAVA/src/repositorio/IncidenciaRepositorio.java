package repositorio;

import dominio.Incidencia;
import util.FicheroUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncidenciaRepositorio
        implements IRepositorioExtend<Incidencia, String> {

    private final String RUTA = "incidencias.txt";
    private List<Incidencia> incidencias;

    public IncidenciaRepositorio() {
        cargarIncidencias();
    }

    private void cargarIncidencias() {
        incidencias = new ArrayList<>();
        List<String> lineas = FicheroUtil.leerFichero(RUTA);

        for (String linea : lineas) {
            String[] d = linea.split(";");

            Incidencia i = new Incidencia(
                    d[0], // id incidencia
                    d[1], // id empleado
                    d[3]  // motivo
            );

            if (d[2] != null && !d[2].isEmpty()) {
                i = new Incidencia(d[0], d[1], d[3]);
            }

            if (Boolean.parseBoolean(d[4])) {
                i.resolver(d[5]);
            }

            incidencias.add(i);
        }
    }

    private void guardarIncidencias() {
        List<String> lineas = new ArrayList<>();

        for (Incidencia i : incidencias) {
            String linea =
                    i.getIdIncidencia() + ";" +
                            i.getIdEmpleado() + ";" +
                            i.getFecha() + ";" +
                            i.getMotivo() + ";" +
                            i.isRevisada() + ";" +
                            i.getResolucion();

            lineas.add(linea);
        }

        FicheroUtil.escribirFichero(RUTA, lineas);
    }

    @Override
    public long count() {
        return incidencias.size();
    }

    @Override
    public boolean existsById(String id) {
        for (Incidencia i : incidencias) {
            if (i.getIdIncidencia().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Incidencia findById(String id) {
        for (Incidencia i : incidencias) {
            if (i.getIdIncidencia().equals(id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Optional<Incidencia> findByIdOptional(String id) {
        return Optional.ofNullable(findById(id));
    }

    @Override
    public Iterable<Incidencia> findAll() {
        return incidencias;
    }

    @Override
    public List<Incidencia> findAllToList() {
        return incidencias;
    }

    @Override
    public Incidencia save(Incidencia entity) {
        incidencias.add(entity);
        guardarIncidencias();
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Incidencia i = findById(id);
        if (i != null) {
            incidencias.remove(i);
            guardarIncidencias();
        }
    }

    @Override
    public void deleteAll() {
        incidencias.clear();
        guardarIncidencias();
    }

    // 🔹 MÉTODO PROPIO
    public List<Incidencia> findIncidenciasNoRevisadas() {
        List<Incidencia> pendientes = new ArrayList<>();

        for (Incidencia i : incidencias) {
            if (!i.isRevisada()) {
                pendientes.add(i);
            }
        }
        return pendientes;
    }
}
