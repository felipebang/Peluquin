package com.indra.cmoff.utils.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.types.dsl.StringExpression;

public final class Util {

	private static final String GUION = "-";
	private static final String NAMESPACE = "1b671a64-40d5-491e-99b0-da01ff1f3341";
	private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

	public static final String obtenerCodigoArchivo(String campo) {
		String codigo = null;
		if (campo != null) {
			if (campo.contains(GUION)) {
				codigo = campo.substring(0, campo.indexOf(GUION)).trim();
			} else {
				codigo = campo.trim();
			}
		}
		return codigo;
	}

	public static final String toStringPKDto(Object... ids) {
		return StringUtils.join(ids, GUION);
	}

	public static final int calcularEdad(Date fechaNacimiento) {
		Instant instant = Instant.ofEpochMilli(fechaNacimiento.getTime());
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, DEFAULT_ZONE_ID);
		LocalDate localDate = localDateTime.toLocalDate();
		Period period = Period.between(localDate, LocalDate.now());
		return period.getYears();
	}

	public static  final String concatenerCamposPorSeparador(String separador,Object... ids) {
		return StringUtils.join(ids, separador);
	}
	
	public static final Boolean toBoolean(String cadena) {
		String activo = "Activo";
		if (cadena != null) {
			Boolean equals = cadena.equalsIgnoreCase(activo);
			return equals;
		} else {
			return false;
		}
	}
	
	public static final UUID generateType3UUID (String cadena) {
		try {
			if (cadena != null && !StringUtils.isEmpty(cadena)) {
				String campo = NAMESPACE + cadena;
				byte[] bytes = campo.getBytes("UTF-8");
				UUID uuid = UUID.nameUUIDFromBytes(bytes);
				return uuid;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que retorna valor vacío para cuando la expression enviada como parametro retorna valor null
	 * */
	public static final StringExpression emptyIfNull(StringExpression expression) {
		return expression.coalesce("").as("");
	}
	
	/**
	 * Convert of LocalDate to Date
	 * */
	public static final Date convertLocalDateToDate (LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(DEFAULT_ZONE_ID).toInstant());
	}
	
	/**
	 * Método para generar hash
	 */
	public static final String generateHash(byte[] bytes, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte[] dg = md.digest(bytes);
		return DatatypeConverter.printHexBinary(dg).toUpperCase();
	}
	
	/**
	 * Método para obtener imagen base 64
	 */
	public static final String obtenerImagenBase64(String recurso) throws IOException {
		InputStream inputStream = Util.class.getResourceAsStream(recurso);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] bytes = new byte[16384];
		while ((nRead = inputStream.read(bytes, 0, bytes.length)) != -1) {
		  buffer.write(bytes, 0, nRead);
		}
		String imgDataAsBase64 = new String(Base64.getEncoder().encode(buffer.toByteArray()));
		return "data:image/png;base64," + imgDataAsBase64;
	}

	/**
	 * Método para validar fecha
	 */
	 public static boolean validateJavaDate(String strDate) {
	        /* Check if date is 'null' */
	        if (strDate.trim().equals("")) {
	            return false;
	        } /* Date is not 'null' */ else {
	            /*
		     * Set preferred date format,
		     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
	            SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
	            sdfrmt.setLenient(false);
	            /* Create Date object
		     * parse the string into date 
	             */
	            try {
	                Date javaDate = sdfrmt.parse(strDate);
	                // System.out.println(strDate+" is valid date format");
	            } /* Date format is invalid */ catch (ParseException e) {
	                //  System.out.println(strDate+" is Invalid Date format");
	                return false;
	            }
	            /* Return true if date format is valid */
	            return true;
	        }
	    }

}
