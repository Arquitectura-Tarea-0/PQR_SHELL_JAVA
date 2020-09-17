package ufps.pqr_shell_java;

import java.util.Scanner;
import ufps.pqr_shell_java.Models.*;
import ufps.pqr_shell_java.Providers.*;

public class Main {
    
    public static boolean verificar(boolean validacion){
        if(validacion){
            System.out.println("¡Error, ha ingresado datos inválidos! \nIntentelo nuevamente:");
        } else{
            validacion = true;
        }
        return validacion;
    }
    
    public static void main(String[] args){
        
        User user       = new User();
        Note note       = new Note();
        Request request = new Request();
        UserProvider userProvider       = new UserProvider();
        NoteProvider noteProvider       = new NoteProvider();
        RequestProvider requestProvider = new RequestProvider();

        Scanner sc = new Scanner(System.in);
        int accion = 0;
        boolean validacion = false;
        
        System.out.println("¡Bienvenido a Peticiones, Quejas y Reclamos (PQR)! \n ¿Qué desea hacer? "
                + "\n 1) Iniciar sesión. \n 2) Salir. \nIngrese el número respectivo: ");
        
        //Verificar si el dato ingresado es válido
        do{
            validacion = verificar(validacion);
            accion = sc.nextInt();
        } while(accion != 1 && accion != 2);
        
        validacion = false;
        
        if(accion == 2){
            //Salir
            System.out.println("¡Hasta Luego!");
        }else{

            do{
                validacion = verificar(validacion);
                System.out.println("Correo:");
                user.setEmail(sc.next());
                System.out.println("Clave:");
                user.setPasswordDigest(sc.next());
                user = userProvider.login(user);
            }while(!user.confirmLogin());
            
            Request temp = new Request();
            temp.setSubject("No he recibido clases este semestre");
            temp.setDescription("Desde las 12:30 p.m. mi conexión a internet esta fallando por eso no me he podido conectar a las clases");
            temp.setRequestType("complain");
            temp.setRequestState("settled");

            /*
            System.out.println("Bienvenido al inicio de PQR");
            System.out.println("-----------------------------------------------");
            //System.out.println(solicitudes.consultarSolicitudes(usuario.getCorreo()));
            System.out.println("-----------------------------------------------");
            System.out.println("¿Qué desea hacer? \n 1) Filtrar PQR. \n 2) Seleccionar solicitud. "
                    + "\n 3) Crear una PQR. \n 4) Cerrar Sesión. \n Ingrese el respectivo número:");
            
            //Validar si el número ingresado es válido
            do{
                validacion = verificar(validacion);
                accion = sc.nextInt();
            } while(accion < 1 || accion > 4);
            
            validacion = false;
            /*
            if(accion == 1){
                //Preguntar cuál filtro aplicar
                System.out.println("¿Cuál filtro desea aplicar? \n 1) Estado. \n 2) Tipo \n "
                        + "3) Fecha de respuesta. \n 4) Fecha de Creación.");
                
                //Validar si el número ingresado es válido
                do{
                    validacion = verificar(validacion);
                    accion = sc.nextInt();
                } while(accion < 1 || accion > 4);
                
                validacion = false;
                String filtro = "";
                
                if(accion == 1){
                    //Preguntar por cuál estado filtrar
                    System.out.println("¿Cuál estado desea aplicar? \n Radicada. \n En proceso. "
                            + "\n Resulta. \n Cerrada. \nIngrese el respectivo estado exactamente como se muestra:");
                } else if(accion == 2){
                    //Preguntar por cuál tipo filtrar
                    System.out.println("¿Cuál tipo desea aplicar? \n - Petición. \n - Queja. "
                            + "\n - Reclamo. \nIngrese el respectivo tipo exactamente como se muestra:");
                } else{
                    System.out.println("Ingrese la fecha en formato (dd/mm/aaaa):");
                }
                
                filtro = sc.next();
                //Mostrar las solicitudes con el filtro aplicado
                System.out.println(solicitudes.filtrarSolicitudes(accion, filtro));
                
            } else if(accion == 2){
                //Seleccionar solicitud
                int id, tiempo = 0;
                System.out.println("Indique el id de la solicitud que desea seleccionar: ");
                id = sc.nextInt();
                Request solicitudSeleccionada = solicitudes.seleccionarSolicitud(id);
                //Visualizar solicitud seleccionada
                System.out.println(solicitudes.verSolicitud(solicitudSeleccionada));
                
                int indice = 1;
                
                System.out.println("¿Qué desea hacer? \n 1) Salir.");
                if(!solicitudSeleccionada.getEstado().equalsIgnoreCase("Cerrada")){
                    System.out.println(" 2) Agregar anotación.");
                    indice++; //Cantidad de opciones
                    if(solicitudSeleccionada.getEstado().equalsIgnoreCase("Resuelta")){
                        //Calcular el tiempo transcurrido de la solicitud 
                        tiempo = solicitudSeleccionada.calcularTiempoTranscurrido(id);
                        System.out.println("El tiempo transcurrido es: " + tiempo);
                        if(tiempo <= 8){
                            System.out.println(" 3) Regresar el estado de la solicitud a 'En proceso'.");
                            indice++; //Cantidad de opciones
                        }
                    }                    
                }
                System.out.println("Ingrese el número correspondiente:");
                
                //Validar el dato ingresado
                do{
                    validacion = verificar(validacion);
                    accion = sc.nextInt();
                }while(accion < 1 || accion > indice);
                
                validacion = false;
                
                if(accion == 2){
                    //Agregar nota
                    System.out.println("Ingrese la nota que desea agregar: ");
                    String descripcionNota = sc.next();
                    solicitudes.agregarNota(solicitudSeleccionada, usuario, descripcionNota);
                    System.out.println("La nota ha sido creada");
                    //Enviar correo
                } else if(accion == 3){
                    //Cambiar el estado de la solicitud seleccionada a 'en proceso'
                    solicitudSeleccionada.setEstado("En proceso");
                    System.out.println("Estado cambiado.");
                    //Enviar correo
                }
                
            } else if(accion == 3){
                //Crear una PQR
                String tipo, asunto, descripcion;
                System.out.println("Tipos de solicitudes. \n - Petición. \n - Queja. \n - Reclamo."
                        + "\n Ingrese el tipo de solicitud exactamente como se muestra:");
                tipo = sc.next();
                System.out.println("Ingrese el asunto de solicitud:");
                asunto = sc.next();
                System.out.println("Ingrese la descripcion de solicitud:");
                descripcion = sc.next();
                Request nuevaSolicitud = new Request(tipo, "Radicada", asunto, descripcion);
                System.out.println("La solicitud ha sido creada");
                //Enviar correo
            }
            //Salir
            System.out.println("¡Hasta Luego!");*/
        }

        sc.close();
    }
    
}
