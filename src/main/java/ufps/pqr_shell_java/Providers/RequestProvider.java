package ufps.pqr_shell_java.Providers;

import java.util.*;
import org.json.*;
import ufps.pqr_shell_java.Models.Request;

public class RequestProvider extends HttpRequest {
    
    /**
     * Retorna las request asociadas a un usuario, aunque solo lo hace para
     * el usuario logeado usando el token de ese usuario
    */
    public ArrayList<Request> getUserRequests(String userToken){
        ArrayList<Request> request = new ArrayList<Request>();
        String respuesta = "";

        try{
            respuesta = peticionHttpGetWithHeader(super.url + "requests/user_requests", userToken);
        }catch (final Exception e){
            e.printStackTrace();
        }

        JSONObject responseAPI = new JSONObject(respuesta);

        if (responseAPI.has("request")){
            JSONArray arrayRequest = new JSONArray(responseAPI.get("request").toString());
            for (int i = 0; i < arrayRequest.length(); i++) {
                JSONObject requestJson = new JSONObject(arrayRequest.get(i).toString());
                Request temp = new Request();
                temp.requestFromJson(requestJson);
                System.out.println(temp.toString());
                request.add(temp);
            }
        }

        return request;
    }

    /**
     * Retorna todas las request almacenadas en la base de datos, el unico parametro
     * solicitado es el token del usuario logeado, cualquier usuario puede de igual
     * forma general la consulta
    */
    public ArrayList<Request> getAllRequests(String userToken){
        ArrayList<Request> request = new ArrayList<Request>();
        String respuesta = "";

        try{
            respuesta = peticionHttpGetWithHeader(super.url + "requests/general_requests", userToken);
        }catch (final Exception e){
            e.printStackTrace();
        }

        JSONObject responseAPI = new JSONObject(respuesta);

        if (responseAPI.has("request")){
            JSONArray arrayRequest = new JSONArray(responseAPI.get("request").toString());
            for (int i = 0; i < arrayRequest.length(); i++) {
                JSONObject requestJson = new JSONObject(arrayRequest.get(i).toString());
                Request temp = new Request();
                temp.requestFromJson(requestJson);
                System.out.println(temp.toString());
                request.add(temp);
            }
        }
        System.out.println("----" + request.size() + "---");
        return request;
    }

    /**
     * Retorna todas las request almacenadas en la base de datos, aplicando un criterio
     * de filtro basado en el estado del request, los valores aceptados para aplicar el 
     * filtro son los siguientes:
     * 
     * Para obtener las request en estado "settled" usar el 0
     * Para obtener las request en estado "in_progress" usar el 1
     * Para obtener las request en estado "solved" usar el 2
     * Para obtener las request en estado "closed" usar el 3
     * En caso de enviar un valor que no este dentro del rango retornará todas las request
     * 
     * Adicionalmente debe proporcionar el token del usuario logeado
    */
    public ArrayList<Request> getAllRequestsByState(int requestState, String userToken){
        ArrayList<Request> request = new ArrayList<Request>();
        String respuesta = "";

        try{
            if(requestState>=0 && requestState<=3)
                respuesta = peticionHttpGetWithHeader(super.url + "requests/general_requests?request_state=" + requestState, userToken);
            else
                respuesta = peticionHttpGetWithHeader(super.url + "requests/general_requests", userToken);
        }catch (final Exception e){
            e.printStackTrace();
        }

        JSONObject responseAPI = new JSONObject(respuesta);

        if (responseAPI.has("request")){
            JSONArray arrayRequest = new JSONArray(responseAPI.get("request").toString());
            for (int i = 0; i < arrayRequest.length(); i++) {
                JSONObject requestJson = new JSONObject(arrayRequest.get(i).toString());
                Request temp = new Request();
                temp.requestFromJson(requestJson);
                System.out.println(temp.toString());
                request.add(temp);
            }
        }
        System.out.println("----" + request.size() + "---");
        return request;
    }

    /**
     * Retorna todas las request almacenadas en la base de datos, aplicando un criterio
     * de filtro basado en el tipo del request, los valores aceptados para aplicar el 
     * filtro son los siguientes:
     * 
     * Para obtener las request del tipo "request" usar el 0
     * Para obtener las request del tipo "complain" usar el 1
     * Para obtener las request del tipo "claim" usar el 2
     * En caso de enviar un valor que no este dentro del rango retornará todas las request
     * 
     * Adicionalmente debe proporcionar el token del usuario logeado
    */
    public ArrayList<Request> getAllRequestsByType(int requestType, String userToken){
        ArrayList<Request> request = new ArrayList<Request>();
        String respuesta = "";
        String filtro [] = {"request", "complain", "claim"};

        try{
            if(requestType>=0 && requestType<=2)
                respuesta = peticionHttpGetWithHeader(super.url + "requests/general_requests?request_type=" + filtro[requestType], userToken);
            else
                respuesta = peticionHttpGetWithHeader(super.url + "requests/general_requests", userToken);
        }catch (final Exception e){
            e.printStackTrace();
        }

        JSONObject responseAPI = new JSONObject(respuesta);

        if (responseAPI.has("request")){
            JSONArray arrayRequest = new JSONArray(responseAPI.get("request").toString());
            for (int i = 0; i < arrayRequest.length(); i++) {
                JSONObject requestJson = new JSONObject(arrayRequest.get(i).toString());
                Request temp = new Request();
                temp.requestFromJson(requestJson);
                System.out.println(temp.toString());
                request.add(temp);
            }
        }
        System.out.println("----" + request.size() + "---");
        return request;
    }

    /**
     * Metodo para la creación de un request en el sistema, recuerde que este es el objeto
     * que permite el manejos de PQRs, tenga presente lo siguiente:
     * 
     * Los valores aceptados para la propeidad requestType son: "request", "complain" 
     * o "claim".
     * 
     * Los valores aceptados para la propiedad requestState son: "settled", "in_progress", 
     * "solved" o "closed".
     * 
     * También se solicita que esten seteadas las propiedades description y subject.
     * 
     * El no enviar estos datos correctamente puede ocasionar un error en el manejo de datos
     * que podría ocasionar la perdida de los mismos.
     * 
     * Adicionalmente debe proporcionar el token del usuario logeado
    */
    public Request createRequest(Request request, String userToken){
        Request newRequest = new Request();
        String respuesta = "";
		Map<String, Object> params = new LinkedHashMap<>();

		// Parametros enviados por POST
		params.put("request_state", request.getRequestState());
		params.put("request_type", 	request.getRequestType());
		params.put("subject", 	    request.getSubject());
		params.put("description", 	request.getDescription());

        try{
            respuesta = peticionHttpPostWithHeader(super.url + "requests/create", params, userToken);
        }catch (final Exception e){
            e.printStackTrace();
        }

        JSONObject responseAPI = new JSONObject(respuesta);

        if (responseAPI.has("request")){
            newRequest.requestFromJson(new JSONObject(responseAPI.get("request").toString()));
        }
        System.out.println(newRequest.toString());
        return newRequest;
    }

    /**
     * Metodo para la actualización de un request en el sistema, el metodo solo permite 
     * actualizar el estado del request para lo cual tenga en cuenta lo siguiente:
     * 
     * Los valores aceptados para la propiedad requestState son: "settled", "in_progress", 
     * "solved" o "closed".
     * 
     * En caso de enviar un valor que no este dentro del rango no se actualizará, debe enviar
     * del mismo modo el request que desea actualizar, adicionalmente debe proporcionar el 
     * token del usuario logeado
    */
    public Request updateRequest(Request request, String userToken){
        Request upRequest = new Request();
        String respuesta = "";
		Map<String, Object> params = new LinkedHashMap<>();
        ArrayList<String> filtro = new ArrayList<String>();
        filtro.add("settled");
        filtro.add("in_progress");
        filtro.add("solved");
        filtro.add("closed");

		// Parametros enviados por POST
		params.put("request_state", request.getRequestState());
        int id = request.getId();

        System.out.println(request.toString());
        System.out.println("///// " + id + " //////");

        try{
            if(id >= 0 && filtro.contains(request.getRequestState()))
                respuesta = peticionHttpPutWithHeader(super.url + "requests/update/" + id, params, userToken);
        }catch (final Exception e){
            e.printStackTrace();
        }

        JSONObject responseAPI = new JSONObject(respuesta);

        if (responseAPI.has("request")){
            upRequest.requestFromJson(new JSONObject(responseAPI.get("request").toString()));
        }

        System.out.println(upRequest.toString());
        return upRequest;
    }



}
