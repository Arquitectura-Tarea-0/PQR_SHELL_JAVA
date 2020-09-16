package ufps.pqr_shell_java.Models;

import org.json.*;

public class User {
    
    //Atributos mapeados de la API
    private int id;
    private String name, passwordDigest, role, email, token, createdAt, updatedAt;

    //Constructuro vacio
    public User() {
    }

    //Mapeo de Json API a Modelo
    public void userFromJson(final JSONObject responseAPI, String token){
        //Conversion de string de la respuesta http en JSON con la respuesta de la API
        final JSONObject userJson    = new JSONObject(responseAPI.toString());

        //Mapeo del JSON en Modelo
        this.id             = Integer.valueOf(userJson.get("id").toString());
        this.name           = userJson.get("name").toString();
        this.passwordDigest = userJson.get("password_digest").toString();
        this.role           = userJson.get("role").toString();
        this.email          = userJson.get("email").toString();
        this.createdAt      = userJson.get("created_at").toString();
        this.updatedAt      = userJson.get("updated_at").toString();
        this.token          = token;

    }

    //Metodo para confirmar el login del usuario
    public boolean confirmLogin(){
        return this.token != null && !this.token.isEmpty();
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(final String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "User [createdAt=" + createdAt + ", email=" + email + ", name=" + name + ", passwordDigest="
                + passwordDigest + ", role=" + role + ", token=" + token + ", updatedAt=" + updatedAt + "]";
    }
    
}
