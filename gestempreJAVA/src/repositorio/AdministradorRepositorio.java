package repositorio;

import dominio.Administrador;
import util.FicheroUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdministradorRepositorio
        implements IRepositorioExtend<Administrador, String> {

    private final String RUTA = "administradores.txt";
    private List<Administrador> administradores;

    public AdministradorRepositorio() {
        cargarAdministradores();
    }

    private void cargarAdministradores() {
        administradores = new ArrayList<>();
        List<String> lineas = FicheroUtil.leerFichero(RUTA);

        for (String linea : lineas) {
            String[] d = linea.split(";");

            Administrador a = new Administrador(
                    d[0], d[1], d[2], d[3], d[4],
                    Double.parseDouble(d[5])
            );

            a.setActivo(Boolean.parseBoolean(d[6]));
            administradores.add(a);
        }
    }

    private void guardarAdministradores() {
        List<String> lineas = new ArrayList<>();

        for (Administrador a : administradores) {
            String linea =
                    a.getIdEmpleado() + ";" +
                            a.getDepartamento() + ";" +
                            a.getNombre() + ";" +
                            a.getApellido() + ";" +
                            a.getEmail() + ";" +
                            a.getSalario() + ";" +
                            a.isActivo();

            lineas.add(linea);
        }

        FicheroUtil.escribirFichero(RUTA, lineas);
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

        Administrador existente = findById(entity.getIdEmpleado());
        if (existente != null) {
            administradores.remove(existente);
        }

        administradores.add(entity);
        guardarAdministradores();
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Administrador a = findById(id);
        if (a != null) {
            administradores.remove(a);
            guardarAdministradores();
        }
    }

    @Override
    public void deleteAll() {
        administradores.clear();
        guardarAdministradores();
    }

    // 🔹 MÉTODO PROPIO
    public boolean hayAdministradores() {
        return administradores.size() > 0;
    }
}
