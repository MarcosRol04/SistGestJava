package main;

import dominio.Administrador;
import dominio.Empleado;
import dominio.Incidencia;
import repositorio.AdministradorRepositorio;
import repositorio.EmpleadoRepositorio;
import repositorio.IncidenciaRepositorio;
import servicio.ControlHorarioService;


import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmpleadoRepositorio empleadoRepo = new EmpleadoRepositorio();
        AdministradorRepositorio adminRepo = new AdministradorRepositorio();
        IncidenciaRepositorio incidenciaRepo = new IncidenciaRepositorio();
        ControlHorarioService servicio = new ControlHorarioService();

        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN ===");
            System.out.println("1. Entrar como Administrador");
            System.out.println("2. Entrar como Empleado");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    menuAdministrador(sc, empleadoRepo, adminRepo, incidenciaRepo);
                    break;

                case 2:
                    menuEmpleado(sc, empleadoRepo, servicio);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);

        sc.close();
    }

    // ================= MENÚ ADMINISTRADOR =================

    private static void menuAdministrador(Scanner sc,
                                          EmpleadoRepositorio empleadoRepo,
                                          AdministradorRepositorio adminRepo,
                                          IncidenciaRepositorio incidenciaRepo) {

        int opcion;

        do {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Alta empleado");
            System.out.println("2. Alta administrador");
            System.out.println("3. Listar empleados");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Ver incidencias");
            System.out.println("6. Resolver incidencia");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    altaEmpleado(sc, empleadoRepo);
                    break;

                case 2:
                    altaAdministrador(sc, adminRepo);
                    break;

                case 3:
                    listarEmpleados(empleadoRepo);
                    break;

                case 4:
                    eliminarEmpleado(sc, empleadoRepo);
                    break;

                case 5:
                    listarIncidencias(incidenciaRepo);
                    break;

                case 6:
                    resolverIncidencia(sc, incidenciaRepo);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);
    }

    // ================= MENÚ EMPLEADO =================

    private static void menuEmpleado(Scanner sc,
                                     EmpleadoRepositorio empleadoRepo,
                                     ControlHorarioService servicio) {

        System.out.print("Introduce tu ID de empleado: ");
        String id = sc.nextLine();

        Empleado e = empleadoRepo.findById(id);

        if (e == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        int opcion;

        do {
            System.out.println("\n--- MENÚ EMPLEADO ---");
            System.out.println("1. Fichar horas");
            System.out.println("2. Ver horas extra");
            System.out.println("3. Descargar nómina");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Horas trabajadas hoy: ");
                    double horas = sc.nextDouble();
                    sc.nextLine();
                    servicio.ficharHorasEmpleado(id, horas);
                    break;

                case 2:
                    System.out.println("Horas extra: " + e.getHorasExtra());
                    break;

                case 3:
                    servicio.descargarNomina(e, true);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);
    }

    // ================= FUNCIONES AUXILIARES =================

    private static void altaEmpleado(Scanner sc, EmpleadoRepositorio repo) {

        System.out.print("ID empleado: ");
        String id = sc.nextLine();

        System.out.print("Departamento: ");
        String dep = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Salario: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        Empleado e = new Empleado(id, dep, nombre, apellido, email, salario);
        repo.save(e);

        System.out.println("Empleado creado correctamente");
    }

    private static void altaAdministrador(Scanner sc, AdministradorRepositorio repo) {

        System.out.print("ID administrador: ");
        String id = sc.nextLine();

        System.out.print("Departamento: ");
        String dep = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Salario: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        Administrador a = new Administrador(id, dep, nombre, apellido, email, salario);
        repo.save(a);

        System.out.println("Administrador creado correctamente");
    }

    private static void listarEmpleados(EmpleadoRepositorio repo) {

        Iterable<Empleado> empleados = repo.findAll();

        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    private static void eliminarEmpleado(Scanner sc, EmpleadoRepositorio repo) {

        System.out.print("ID empleado a eliminar: ");
        String id = sc.nextLine();

        repo.deleteById(id);
        System.out.println("Empleado eliminado");
    }

    private static void listarIncidencias(IncidenciaRepositorio repo) {

        List<Incidencia> incidencias = repo.findAllToList();

        for (Incidencia i : incidencias) {
            System.out.println(i);
        }
    }

    private static void resolverIncidencia(Scanner sc, IncidenciaRepositorio repo) {

        System.out.print("ID incidencia: ");
        String id = sc.nextLine();

        Incidencia i = repo.findById(id);

        if (i == null) {
            System.out.println("Incidencia no encontrada");
            return;
        }

        System.out.print("Resolución: ");
        String res = sc.nextLine();

        i.resolver(res);
        repo.save(i);

        System.out.println("Incidencia resuelta");
    }
}
