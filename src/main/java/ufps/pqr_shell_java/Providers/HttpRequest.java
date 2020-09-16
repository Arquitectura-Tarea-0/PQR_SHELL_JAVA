package ufps.pqr_shell_java.Providers;

import java.net.*;
import java.util.*;
import java.io.*;

public class HttpRequest {

	final String url = "https://pqr-api-rails.herokuapp.com/";
    
	public String peticionHttpGet(String urlParaVisitar) throws Exception {
		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
        String linea;
        
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
        }
        
		rd.close();
		return resultado.toString();
	}

	public String peticionHttpPost(String urlParaVisitar, Map<String, Object> params) throws Exception {
		StringBuilder resultado = new StringBuilder();
		StringBuilder body = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (body.length() != 0)
				body.append('&');
			body.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			body.append('=');
			body.append(URLEncoder.encode(String.valueOf(param.getValue()),
					"UTF-8"));
		}
		byte[] resultadoBytes = body.toString().getBytes("UTF-8");

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("POST");
		conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conexion.setRequestProperty("Content-Length",String.valueOf(resultadoBytes.length));
		conexion.setDoOutput(true);
		conexion.getOutputStream().write(resultadoBytes);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
		String linea;
		
        
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
        }
        
		rd.close();
		return resultado.toString();
	}

	public String peticionHttpGetWithHeader(String urlParaVisitar, String token) throws Exception {
		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		conexion.setRequestProperty("Authorization", token);

		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
        String linea;
        
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
        }
        
		rd.close();
		return resultado.toString();
	}

	public String peticionHttpPostWithHeader(String urlParaVisitar, Map<String, Object> params, String token) throws Exception {
		StringBuilder resultado = new StringBuilder();
		StringBuilder body = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (body.length() != 0)
				body.append('&');
			body.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			body.append('=');
			body.append(URLEncoder.encode(String.valueOf(param.getValue()),
					"UTF-8"));
		}
		byte[] resultadoBytes = body.toString().getBytes("UTF-8");

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("POST");
		conexion.setRequestProperty("Authorization", token);
		conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conexion.setRequestProperty("Content-Length",String.valueOf(resultadoBytes.length));
		conexion.setDoOutput(true);
		conexion.getOutputStream().write(resultadoBytes);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
		String linea;
		
        
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
        }
        
		rd.close();
		return resultado.toString();
	}

	public String peticionHttpPutWithHeader(String urlParaVisitar, Map<String, Object> params, String token) throws Exception {
		StringBuilder resultado = new StringBuilder();
		StringBuilder body = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (body.length() != 0)
				body.append('&');
			body.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			body.append('=');
			body.append(URLEncoder.encode(String.valueOf(param.getValue()),
					"UTF-8"));
		}
		byte[] resultadoBytes = body.toString().getBytes("UTF-8");

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("PUT");
		conexion.setRequestProperty("Authorization", token);
		conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conexion.setRequestProperty("Content-Length",String.valueOf(resultadoBytes.length));
		conexion.setDoOutput(true);
		conexion.getOutputStream().write(resultadoBytes);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
		String linea;
		
        
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
        }
        
		rd.close();
		return resultado.toString();
	}


}
