package repositorio;

import dominio.Administrador;
import util.FicheroUtil;

import java.util.List;
import java.util.Optional;

public class AdministradorRepositorio
        implements IRepositorioExtend<Administrador, String> {

    private final String RUTA = "administradores.dat";
    private List<Administrador> administradores;

    public AdministradorRepositorio() {
        administradores = FicheroUtil.leerFichero(RUTA);
    }

    @Override
    public long count() {
        return administradores.size();
    }

    @Override
    public boolean existsById(String id) {
        for (Administrador a : administradores) {
            if (a.getIdEmpleado().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Administrador findById(String id) {
        for (Administrador a : administradores) {
            if (a.getIdEmpleado().equals(id)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public Optional<Administrador> findByIdOptional(String id) {
        return Optional.ofNullable(findById(id));
    }

    @Override
    public Iterable<Administrador> findAll() {
        return administradores;
    }

    @Override
    public List<Administrador> findAllToList() {
        return administradores;
    }

    @Override
    public Administrador save(Administrador entity) {

        Administrador a = findById(entity.getIdEmpleado());
        if (a != null) {
            administradores.remove(a);
        }

        administradores.add(entity);
        FicheroUtil.escribirFichero(RUTA, administradores);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Administrador a = findById(id);
        if (a != null) {
            administradores.remove(a);
            FicheroUtil.escribirFichero(RUTA, administradores);
        }
    }

    @Override
    public void deleteAll() {
        administradores.clear();
        FicheroUtil.escribirFichero(RUTA, administradores);
    }

    // 🔹 MÉTODO PROPIO
    public boolean hayAdministradores() {
        return administradores.size() > 0;
    }
}
