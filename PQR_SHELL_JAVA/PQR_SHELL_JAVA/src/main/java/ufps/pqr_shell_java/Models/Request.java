package ufps.pqr_shell_java.Models;

import org.json.*;

public class Request {
    
    int id;
    String requestState, requestType, subject, description, respondedAt, userId, createdAt, updatedAt;

    public Request() {
    }

    //Mapeo de Json API a Modelo
    public void requestFromJson(final JSONObject responseAPI){
        //Conversion de string de la respuesta http en JSON con la respuesta de la API
        final JSONObject requestJson = new JSONObject(responseAPI.toString());
    
        //Mapeo del JSON en Modelo
        this.id           = Integer.valueOf(requestJson.get("id").toString());
        this.requestState = requestJson.get("request_state").toString();
        this.requestType  = requestJson.get("request_type").toString();
        this.subject      = requestJson.get("subject").toString();
        this.description  = requestJson.get("description").toString();
        this.respondedAt  = requestJson.get("responded_at").toString();
        this.userId       = requestJson.get("user_id").toString();
        this.createdAt    = requestJson.get("created_at").toString();
        this.updatedAt    = requestJson.get("updated_at").toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestState() {
        return requestState;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(String respondedAt) {
        this.respondedAt = respondedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Request [createdAt=" + createdAt + ", description=" + description + ", id=" + id + ", requestState="
                + requestState + ", requestType=" + requestType + ", respondedAt=" + respondedAt + ", subject="
                + subject + ", updatedAt=" + updatedAt + ", userId=" + userId + "]";
    }
    
}
