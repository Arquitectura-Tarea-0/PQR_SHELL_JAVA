package ufps.pqr_shell_java;

import java.util.ArrayList;
import java.util.Scanner;
import ufps.pqr_shell_java.Models.*;
import ufps.pqr_shell_java.Providers.*;

public class Main {
       
    //Incialización de objetos para el manejo del modelo de datos
    static User user       = new User();
    static Note note       = new Note();
    static Request request = new Request();
    static ArrayList<Request> userRequests = new ArrayList<Request>();

    //Inicialización de objetos para el consumo de servicios 
    static UserProvider userProvider       = new UserProvider();
    static NoteProvider noteProvider       = new NoteProvider();
    static RequestProvider requestProvider = new RequestProvider();

    static Boolean validacion;
    static int accion;

    public static boolean verificar(boolean validacion){
        if(validacion){
            System.out.println("¡Error, ha ingresado datos inválidos! \nIntentelo nuevamente:");
        }else{
            validacion = true;
        }
        return validacion;
    }
    
    public static void main(final String[] args) {

        final Scanner sc = new Scanner(System.in);
        accion = 0;
        validacion = false;
        
        System.out.println("¡Bienvenido a Peticiones, Quejas y Reclamos (PQR)! \n\n ¿Qué desea hacer? "
                + "\n\n 1) Iniciar sesión. \n 2) Salir. \n\nIngrese el número respectivo: ");
        
        //Verificar si el dato ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        } while(accion != 1 && accion != 2);
        
        validacion = false;

        if(accion == 1){
            login();
            userRequests = requestProvider.getUserRequests(user.getToken());
            menuInicial();
        }else{
            exit();
        }
        
        sc.close();

    }

    public static void exit(){
        System.out.println("¡Hasta Luego!");
    }

    public static void login(){
        Scanner sc = new Scanner(System.in);

        do{
            validacion = verificar(validacion);
            System.out.println("Correo:");
            user.setEmail(sc.next());
            System.out.println("Clave:");
            user.setPasswordDigest(sc.next());
            user = userProvider.login(user);
        }while(!user.confirmLogin());

        validacion = false;
    }

    public static void menuInicial(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\nBienvenido al inicio de PQR\n");
        System.out.println("-----------------------------------------------\n");
        System.out.println("PQRs creadas en el sistema por el usuario\n");
        for(int i=0; i< userRequests.size(); i++){
            System.out.println("Descripción de PQR: " + userRequests.get(i).getDescription() +
                                " Radicada el " + userRequests.get(i).getCreatedAt());
        }
        System.out.println("\n-----------------------------------------------");
        System.out.println("\n¿Qué desea hacer?" +
                            "\n\n 1) Filtrar PQRs. " + 
                            "\n 2) Seleccionar PQR. " +
                            "\n 3) Crear una PQR. " +
                            "\n 4) Cerrar Sesión. " +
                            "\n\n Ingrese el respectivo número:"
                        );
            
        //Validar si el número ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        } while(accion < 1 || accion > 4);
            
        validacion = false;
        switch (accion) {
            case 1:
                filtrarPQRs();
                break;
            case 2:
                seleccionarPQR();
                break;                
            case 3:
                crearPQR();
                break;
            case 4:
                exit();
                break;
        }
    }
                
    public static void filtrarPQRs(){
        Scanner sc = new Scanner(System.in);

        //Preguntar cuál filtro aplicar
        System.out.println("\n¿Cuál filtro desea aplicar? " +
                            "\n\n 1) Estado" + 
                            "\n 2) Tipo " +
                            "\n 3) Volver al menu inicial" +
                            "\n\n Ingrese el respectivo número:"
                        );
                
        //Validar si el número ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        }while(accion < 1 || accion > 3);
        
        validacion = false;

        switch (accion) {
            case 1:
                filtrarPorTipo();
                break;
            case 2:
                filtrarPorEstado();
                break;        
            default:
                menuInicial();
                break;
        }
    }   

    private static void filtrarPorEstado() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Request> filterRequest = new ArrayList<Request>();

        System.out.println("\n¿Cuál filtro desea aplicar? " +
                            "\n\n 1) Creada" + 
                            "\n 2) En proceso " +
                            "\n 3) Solucionada " +
                            "\n 4) Cerrada " +
                            "\n 5) Volver al menu inicial" +
                            "\n\n Ingrese el respectivo número:"
                        );

        //Validar si el número ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        }while(accion < 1 || accion > 5);

        validacion = false;

        switch (accion) {
            case 1:
                filterRequest = requestProvider.getAllRequestsByState(0, user.getToken());
                imprimirFiltro(filterRequest);
                break;
            case 2:
                filterRequest = requestProvider.getAllRequestsByState(1, user.getToken());
                imprimirFiltro(filterRequest);
                break;   
            case 3:
                filterRequest = requestProvider.getAllRequestsByState(2, user.getToken());
                imprimirFiltro(filterRequest);
                break;   
            case 4:
                filterRequest = requestProvider.getAllRequestsByState(3, user.getToken());
                imprimirFiltro(filterRequest);
                break;    
            default:
                menuInicial();
                break;
        }

        retornar();
    }

    private static void filtrarPorTipo() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Request> filterRequest = new ArrayList<Request>();

        System.out.println("\n¿Cuál filtro desea aplicar? " +
                            "\n\n 1) Petición" + 
                            "\n 2) Queja " +
                            "\n 3) Reclamo" +
                            "\n 4) Volver al menu inicial" +
                            "\n\n Ingrese el respectivo número:"
                        );

        //Validar si el número ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        }while(accion < 1 || accion > 4);

        validacion = false;

        switch (accion) {
            case 1:
                filterRequest = requestProvider.getAllRequestsByType(0, user.getToken());
                imprimirFiltro(filterRequest);
                break;
            case 2:
                filterRequest = requestProvider.getAllRequestsByType(1, user.getToken());
                imprimirFiltro(filterRequest);
                break;   
            case 3:
                filterRequest = requestProvider.getAllRequestsByType(2, user.getToken());
                imprimirFiltro(filterRequest);
                break;        
            default:
                menuInicial();
                break;
        }

        retornar();

    }

    private static void imprimirFiltro(ArrayList<Request> filterRequest){
        int count=1;
        System.out.println("\n-----------------------------------------------\n");

        if(filterRequest.size()==0)
            System.out.println("NO HAY REQUEST BAJO EL CRITERIO");

        for(int i=0; i<filterRequest.size(); i++){
            if(Integer.parseInt(filterRequest.get(i).getUserId()) == user.getId()){
                System.out.println(count + ") Tema: "   + filterRequest.get(i).getSubject() + 
                                    "\nDescripción: "     + filterRequest.get(i).getDescription() + 
                                    "\ntipo: "            + filterRequest.get(i).getRequestType() +  
                                    "\nestado: "          + filterRequest.get(i).getRequestState() + 
                                    "\nRadicada el: "     + filterRequest.get(i).getCreatedAt() + 
                                    "\nUsuario: "         + filterRequest.get(i).getUserId() 
                                );
                count++;
                System.out.println();
            }
        }

    }

    public static void seleccionarPQR() {
        Scanner sc = new Scanner(System.in);

        System.out.println("El listado de PQRs es el siguiente:\n\n");
        for(int i=0; i< userRequests.size(); i++){
            System.out.println((int) (i+1) + ") Descripción de PQR: " + userRequests.get(i).getDescription());
        }

        System.out.println("\n\nIngrese el número relacionado a la solicitud que desea consultar:");
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        } while(accion < 1 || accion > userRequests.size());

        validacion = false;
        System.out.println("\n-----------------------------------------------");
        System.out.println("\nLa PQR seleccionada es: \n\n" +
                            "\nTema: "          + userRequests.get(accion-1).getSubject() +
                            "\nDescripcion: "   + userRequests.get(accion-1).getDescription() +
                            "\nEstado: "        + userRequests.get(accion-1).getRequestState() +
                            "\nTipo: "          + userRequests.get(accion-1).getRequestType() +
                            "\nRadicada el: "   + userRequests.get(accion-1).getCreatedAt() 
                        );

        retornar();
    }

    public static void crearPQR(){
        Scanner sc = new Scanner(System.in);
        Request temp = new Request();
        String type [] = {"request", "complain", "claim"};

        System.out.println("\n\nPara crear una PQR complete el siguiente formulario:");

        System.out.println("\n¿Qué tipo de solicitud es?" +
                            "\n\n 1) Peticióm" + 
                            "\n 2) Queja " +
                            "\n 3) Reclamo " +
                            "\n\n Ingrese el respectivo número:"
                        );
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        } while(accion < 1 || accion > 3);

        temp.setRequestType(type[accion-1]);
        temp.setRequestState("settled");
        validacion = false;
        String karen;
        sc.nextLine();

        System.out.println("Ingrese el tema y oprima enter:");
        karen = sc.nextLine();
        temp.setSubject(karen);

        System.out.println("Ingrese la descipción y oprima enter:");
        karen = sc.nextLine();
        temp.setDescription(karen);

        temp = requestProvider.createRequest(temp, user.getToken());

        System.out.println("\n\nLa PQR creada es: \n\n" +
                            "\nTema: "          + temp.getSubject() +
                            "\nDescripcion: "   + temp.getDescription() +
                            "\nEstado: "        + temp.getRequestState() +
                            "\nTipo: "          + temp.getRequestType() +
                            "\nRadicada el: "   + temp.getCreatedAt() 
                        );

        retornar();   
    }

    public static void retornar(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n-----------------------------------------------");
        System.out.println("\n¿Qué desea hacer?" +
                            "\n\n 1) Volver al menu inicial" + 
                            "\n 2) Cerrar sesión " +
                            "\n\n Ingrese el respectivo número:"
                        );
            
        //Validar si el número ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = Integer.parseInt(sc.next());
        } while(accion < 1 || accion > 2);
                                
        validacion = false;

        if(accion==1)
            menuInicial();
        else
            exit();
    }
    
}
