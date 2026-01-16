package repositorio;

import dominio.Incidencia;
import util.FicheroUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncidenciaRepositorio
        implements IRepositorioExtend<Incidencia, String> {

    private final String RUTA = "incidencias.dat";
    private List<Incidencia> incidencias;

    public IncidenciaRepositorio() {
        incidencias = FicheroUtil.leerFichero(RUTA);
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
        FicheroUtil.escribirFichero(RUTA, incidencias);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Incidencia i = findById(id);
        if (i != null) {
            incidencias.remove(i);
            FicheroUtil.escribirFichero(RUTA, incidencias);
        }
    }

    @Override
    public void deleteAll() {
        incidencias.clear();
        FicheroUtil.escribirFichero(RUTA, incidencias);
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
