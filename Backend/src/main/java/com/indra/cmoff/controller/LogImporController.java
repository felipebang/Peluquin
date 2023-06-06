package com.indra.cmoff.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.indra.cmoff.dto.LogImporDTO;

import com.indra.cmoff.dto.PaginatedFilter;
import com.indra.cmoff.service.ILogImporService;
import com.indra.cmoff.utils.util.Constantes;
import com.indra.cmoff.utils.util.ConstantesRolesMP;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.InputStreamReader;
import com.opencsv.CSVReader;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.OPTIONS })
@RestController
@RequestMapping(path = "/importacion")
public class LogImporController {

	private HashMap<String, Object> jsonResponse = new HashMap<>();
	private String messageResponse;
	private HttpStatus statusResponse;

	@Value("${directory-files-storage}")
	private String fileLocation;

	private final ILogImporService logImporService;

	public LogImporController(ILogImporService logImporService) {
		this.logImporService = logImporService;
	}

	/**
	 * Método que realiza el filtrado de Logs de importación
	 * 
	 * @param filtro,valor por el cual se quiere filtrar
	 * @return Page con la filtracion realizada
	 */
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + "')")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@PostMapping("/buscarl")
	public Page<LogImporDTO> buscarl(@RequestBody PaginatedFilter<LogImporDTO> filtro) {
		return logImporService.filterPaginated(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());
	}

	/**
	 * Método que se encarga de importar archivos al servidor para poblar la BD
	 * 
	 * @param arbol,Archivo   de arbol de conocimientos
	 * @param gestion,Archivo de gestion de conocimientos
	 * @return
	 * @throws IOException
	 */
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@PostMapping("/uploads")
	public Object uploads(@RequestParam("candidatos") MultipartFile candidatos,
			@RequestParam("necesidades") MultipartFile necesidades, @RequestParam("enviocandidato") String arbolf,
			@RequestParam("envionecesidad") String gestionf) throws IOException {
		System.out.println("" + candidatos.getOriginalFilename() + " " + necesidades.getOriginalFilename() + " "
				+ arbolf + "  .." + gestionf);

		try {

			Date fechaActual = new Date();
			if (arbolf.equals(Constantes.ENVIO_ARCHIVO_S)) {
				byte[] bytesA = candidatos.getBytes();
				Path pathA = Paths.get(fileLocation, candidatos.getOriginalFilename());
				Files.write(pathA, bytesA);
				LogImporDTO dto1 = logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
						"error Ejecución", "El número de columnas del registro no coincide con la tabla "));

				// proceso el archivo
				File file = new File(pathA.toString());

				CSVReader reader = new CSVReader(new FileReader(file), '^');

				String QueryInsert = "";

				int cantt = reader.readAll().size();
				// se realiza una nueva instancia para procesar los registros
				CSVReader readerDos = new CSVReader(new FileReader(file), '^');
				String[] record;
				readerDos.readNext();
				while ((record = readerDos.readNext()) != null) {
					record[0] = record[0].replaceAll(",", ".");
					record[0] = record[0].replaceAll("\"", "");
					String[] campos = record[0].replaceAll(",", ".").split(";");

					System.out.println(" cant " + campos.length + " ");
					if (campos.length == 24) {

						campos[18] = campos[18].replaceAll("'", "");
						campos[18] = campos[18].replaceAll(",", "");
						campos[19] = campos[19].replaceAll("'", "");
						campos[19] = campos[19].replaceAll(",", "");
						campos[6] = campos[6].replaceAll(",", ".");

						if (campos[0].equals("")) {
							campos[0] = "null";
						} else {
							campos[0] = "'" + campos[0] + "'";
						}

						campos[5] = campos[5].replaceAll("\"", "");
						campos[6] = campos[6].replaceAll("\"", "");

						if (campos[6].equals("")) {
							campos[6] = "null";
						}
						if (campos[15].equals("")) {
							campos[15] = "null";
						} else {
							campos[15] = "'" + campos[15] + "'";
						}
						if (campos[20].equals("")) {
							campos[20] = "0";
						}

						QueryInsert = "INSERT INTO public.candidatos_tmp(\n"
								+ "cand_fecha, nombre, email, pais, telefono, nivel_estudio, tiempo_experiencia_anios, tecnologia_principal, "
								+ " seniority, estado, tes_t_1, test_2, test_3, test_4, resultados_test, entrevista_tecnica, responsable,"
								+ " ressultados_e_t, calificacion, obsevaciones, cant_proyectos_presentados, sem_pres, sem_ent, fecha_datos)\n"
								+ "VALUES ( " + campos[0] + ", '" + campos[1] + "', '" + campos[2] + "', '" + campos[3]
								+ "', '" + campos[4] + "', '" + campos[5] + "',  " + campos[6] + ", '" + campos[7]
								+ "'," + " '" + campos[8] + "', '" + campos[9] + "', '" + campos[10] + "', '"
								+ campos[11] + "', '" + campos[12] + "', '" + campos[13] + "','" + campos[14] + "', "
								+ campos[15] + ", '" + campos[16] + "', '" + campos[17] + "'," + " '" + campos[18]
								+ "', '" + campos[19] + "','" + campos[20] + "', '" + campos[21] + "', '" + campos[22]
								+ "', '" + campos[23] + "' );\r\n";

						if (campos[23] != "" && campos[22] != "" && campos[21] != "" && campos[20] != ""
								&& campos[1] != "" && campos[3] != "" && campos[7] != "" && campos[8] != ""
								&& campos[9] != "") {
							System.out.println(" QueryInsert..." + QueryInsert);
							logImporService.callCargarCandidatos(QueryInsert, dto1.getId().intValue(), cantt);
						}

						else {

							logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
									"error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
									"" + record[0] + "   "));

						}
					} else {
						logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(), "error en el regis"
								+ "tro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
								"" + record[0] + "   "));

					}

				}

				reader.close();
				if (dto1.getId().intValue() != 0) {

					statusResponse = HttpStatus.OK;

				}

				// llamamos el procedure carga de datos
				logImporService.callCargarMaestraCandidatos(0);

			}
			//// si se ha enviado archivo necesidades
			if (gestionf.equals(Constantes.ENVIO_ARCHIVO_S)) {
				byte[] bytesG = necesidades.getBytes();
				Path pathG = Paths.get(fileLocation, necesidades.getOriginalFilename());
				Files.write(pathG, bytesG);
				int cant = 0;
				LogImporDTO dto2 = logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
						"erro Ejecución", "El número de columnas del registro no coincide con la tabla "));

				// proceso el archivo
				File file = new File(pathG.toString());
				// byte[] bFile = new byte[(int) file.length()];

				CSVReader reader = new CSVReader(new FileReader(file), '^');
				String QueryInsert = "";
				int cantt = reader.readAll().size();
				// se realiza una nueva instancia para procesar los registros
				CSVReader readerDos = new CSVReader(new FileReader(file), '^');
				String[] record = reader.readNext();
				while ((record = readerDos.readNext()) != null) {
					record[0] = record[0].replaceAll(",", ".");
					record[0] = record[0].replaceAll("\"", "");
					record[0] = record[0].replaceAll("'", "");

					String[] campos = record[0].split(";");
					if (campos.length == 35) {

						if (campos[3].equals("")) {
							campos[3] = null;
						} else {
							campos[3] = "'" + campos[3] + "'";
						}

						if (campos[6].equals("")) {
							campos[6] = null;
						} else {
							campos[6] = "'" + campos[6].replaceAll(",", ".") + "'";
						}

						if (campos[12].equals("")) {
							campos[12] = null;
						} else {
							campos[12] = "'" + campos[12] + "'";
						}

						if (campos[21].equals("")) {
							campos[21] = null;
						} else {
							campos[21] = "'" + campos[21] + "'";
						}

						if (campos[22].equals("")) {
							campos[22] = null;
						} else {
							campos[22] = "'" + campos[22] + "'";
						}

						if (campos[23].equals("")) {
							campos[23] = null;
						} else {
							campos[23] = "'" + campos[23] + "'";
						}

						QueryInsert = "INSERT INTO public.necesidad_tmp(\n"
								+ "solicitud, cod_necesidad, pais, fecha_solicitud, proyecto_apertura, proyecto_cierre, "
								+ " descripcion_proyecto, perfil_resumido, tecnologia, rol, contacto_gestion,"
								+ " contacto_tecnico, fecha_requerida, tasa, estado_people, ugr_resuelve, cubre_baja_codigo,"
								+ " cubre_nombre, cubre_baja_rol, cubre_baja_tasa, estado_necesidad, fecha_off, fecha_stop, fecha_reactivacion,"
								+ "propuesto, valido, elegido, vinculado, rechazado, desistio, total, mercado_horizontal,"
								+ "mercado_vertical, cliente, fecha_datos )\n" + "VALUES ( '" + campos[0] + "', '"
								+ campos[1] + "', '" + campos[2] + "', " + campos[3] + ", '" + campos[4] + "', '"
								+ campos[5] + "'," + campos[6] + ", '" + campos[7] + "'," + " '" + campos[8] + "','"
								+ campos[9] + "', '" + campos[10] + "', '" + campos[11] + "', " + campos[12] + ", '"
								+ campos[13] + "','" + campos[14] + "', '" + campos[15] + "', '" + campos[16] + "', '"
								+ campos[17] + "'," + " '" + campos[18] + "', '" + campos[19] + "','" + campos[20]
								+ "', " + campos[21] + ", " + campos[22] + ", " + campos[23] + ", '" + campos[24] + "'"
								+ ", '" + campos[25] + "', '" + campos[26] + "', '" + campos[27] + "' , '" + campos[28]
								+ "'" + ", '" + campos[29] + "', '" + campos[30] + "', '" + campos[31] + "', '"
								+ campos[32] + "', '" + campos[33] + "', '" + campos[34] + "');\r\n";

						if (campos[29] != "" && campos[28] != "" && campos[27] != "" && campos[26] != ""
								&& campos[25] != "" && campos[1] != "" && campos[2] != "" && campos[6] != ""
								&& campos[30] != "" && campos[34] != "") {

							// System.out.println(QueryInsert);
							logImporService.callProcedureCargarNecesidades(QueryInsert, dto2.getId().intValue(), cantt);
						}

						else {

							logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
									"error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
									"" + record[0] + "   "));

						}

					} else {

						logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
								"Error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
								"" + record[0] + "   "));
					}

				}
				reader.close();

				// ------------------------------------
				if (dto2.getId().intValue() != 0) {
					statusResponse = HttpStatus.OK;
					System.out.println(" cant " + cant);
				}

				// llamamos el procedure carga de datos
				logImporService.callCargarMaestraNecesidad(0);

			}

			if (arbolf.equals(Constantes.ENVIO_ARCHIVO_N) && gestionf.equals(Constantes.ENVIO_ARCHIVO_N)) {
				statusResponse = HttpStatus.BAD_REQUEST;
				messageResponse = Constantes.ERROR_SOLICITUD;
			}

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;
			System.err.println("" + e.getMessage() + " " + e.toString());
		}

		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

	/**
	 * Método que se encarga de importar archivos al servidor para poblar
	 * informacion de proyectos y plantiooffshere_tmp en la BD
	 * 
	 * @param proyectos : Archivo de proyectos
	 * @param plantilla : Archivo de plantiooffshere
	 * @return
	 * @throws IOException
	 */
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@PostMapping("/uploads/proyectosPlantilla")
	public Object uploadsProyectosRoles(@RequestParam("proyectos") MultipartFile proyectos,
			@RequestParam("plantillaoffshore") MultipartFile plantillaoffshore,
			@RequestParam("envioproyecto") String envProyectf,
			@RequestParam("envioplantillaoffshore") String envioplanti) throws IOException {
		String cadenaValidar;
		try {

			System.out.println("" + proyectos.getOriginalFilename() + " " + plantillaoffshore.getOriginalFilename()
					+ " " + envProyectf + "  .." + envioplanti);

			Date fechaActual = new Date();
			// formateador.format(ahora),
			// si se ha enviado
			if (envProyectf.equals("s")) {
				byte[] bytesA = proyectos.getBytes();
				Path pathA = Paths.get(fileLocation, proyectos.getOriginalFilename());
				Files.write(pathA, bytesA);
				LogImporDTO dto1 = logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
						"Ejecución", "El número de columnas del archivo no coincide con la tabla"));

				// proceso el archivo
				File file = new File(pathA.toString());
				byte[] bFile = new byte[(int) file.length()];

				FileInputStream fileInputStream = null;
				fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
				fileInputStream.close();

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
				String linea;
				String QueryInsert = "";
				File tempFile = File.createTempFile("mificherotemporal", null);
				BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

				CSVReader reader = new CSVReader(new FileReader(file), '^');
				int cantt = reader.readAll().size();
				int item = 0;
				while ((linea = br.readLine()) != null) {
					cadenaValidar = linea;
					String cadena = linea;
					try {
						item++;
						cadena = cadena.replaceAll("\"", "");
						String[] splitString = cadena.split(";");

						if (splitString[0].equals("")) {
							splitString[0] = "null";
						} else {
							splitString[0] = "'" + splitString[0] + "'";

						}

						if (splitString[10].equals("")) {
							splitString[10] = "null";
						} else {
							splitString[10] = "'" + splitString[10] + "'";

						}
						if (splitString[8].equals("")) {
							splitString[8] = "null";
						} else {
							splitString[8] = "'" + splitString[8] + "'";

						}
						if (splitString[6].equals("")) {
							splitString[6] = "null";
						} else {
							splitString[6] = "'" + splitString[6] + "'";

						}
						QueryInsert = "INSERT INTO public.d_proyectos_tmp(\n"
								+ " mes, cod_delivery, cod_proyecto, nom_proyecto, cod_proyprin, "
								+ "nom_proyprin, cod_proyfactura, nom_proyfactura, cod_operacion, cliente, "
								+ "flag_offshore, flag_eficiencia, mercado_hori, mercado_vert, porc_eficiencia, minsait"
								+ " )" + "VALUES (" + splitString[0] + ", '" + splitString[1] + "', '" + splitString[2]
								+ "', '" + splitString[3] + "', '" + splitString[4] + "', " + "'" + splitString[5]
								+ "', " + splitString[6] + ", '" + splitString[7] + "', " + splitString[8] + ", '"
								+ splitString[9] + "', " + splitString[10] + ", " + "'" + splitString[11] + "', '"
								+ splitString[12] + "', '" + splitString[13] + "', '" + splitString[14] + "','"
								+ splitString[15] + "'" + " );";
						if (item != 1)
							logImporService.callProcedureProyectos(QueryInsert, dto1.getId().intValue(), cantt);

					} catch (Exception e) {

						boolean intIndex = cadena.contains("\"");
						boolean intIndexDos = cadena.contains("'");
						boolean intIndexTres = cadenaValidar.contains(";");

						if (!cadena.equals("") && intIndex) {
							logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
									"error en el registro  comillas dobles o comillas simples", "" + cadena + "   "));
						} else if (!cadena.equals("") && intIndexDos) {
							logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
									"error en el registro  comillas dobles o comillas simples", "" + cadena + "   "));
						} else if (!cadenaValidar.equals("") && cadenaValidar != null && cadenaValidar != ""
								&& intIndexTres) {
							logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
									"error en el registro posible carácter no permitido en el archivo",
									"" + "   " + cadenaValidar));
						}
						statusResponse = HttpStatus.BAD_REQUEST;
						messageResponse = Constantes.ERROR_SOLICITUD;

					}

				}
				out.close();
				br.close();

				if (dto1.getId().intValue() != 0) {

					statusResponse = HttpStatus.OK;
				}

				// llamamos el procedure carga de datos
				logImporService.callCargarMaestraProyectos(0);
			}

			if (envioplanti.equals("s")) {
				byte[] bytesG = plantillaoffshore.getBytes();
				Path pathG = Paths.get(fileLocation, plantillaoffshore.getOriginalFilename());
				Files.write(pathG, bytesG);
				LogImporDTO dto2 = logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
						"Ejecución", "El número de columnas del registro no coincide con la tabla"));
				// proceso el archivo
				File file = new File(pathG.toString());
				CSVReader reader = new CSVReader(new FileReader(file), '^');

				String QueryInsert = "";
				int cantt = reader.readAll().size();
				// se realiza una nueva instancia para procesar los registros
				CSVReader readerDos = new CSVReader(new FileReader(file), '^');
				String[] record = reader.readNext();

				while ((record = readerDos.readNext()) != null) {
					record[0] = record[0].replaceAll(",", ".");
					record[0] = record[0].replaceAll("\"", " ");
					record[0] = record[0].replaceAll("'", " ");
					record[0] = record[0].replaceAll(",", " ");

					String[] campos = record[0].split(";");

					System.out.println(" cant " + campos.length + " ");
					if (campos.length == 21) {
						campos[20] = campos[20].replaceAll(",", "");
						if (campos[0].equals("")) {
							campos[0] = "null";
						} else {
							campos[0] = "'" + campos[0] + "'";

						}
						if (campos[16].equals("")) {
							campos[16] = "0";
						}

						if (campos[10].equals("")) {
							campos[10] = "null";
						} else {
							campos[10] = "'" + campos[10] + "'";

						}
						if (campos[8].equals("")) {
							campos[8] = "null";
						} else {
							campos[8] = "'" + campos[8] + "'";

						}
						if (campos[6].equals("")) {
							campos[6] = "null";
						} else {
							campos[6] = "'" + campos[6] + "'";

						}

						QueryInsert = "INSERT INTO public.h_plantilla_offshore_tmp(\n"
								+ " h_mes, cod_delivery, cod_empleado, nom_empleado, cod_proyecto, nom_proyecto, cod_proyprin, "
								+ "nom_proyprin, cod_proyfactura, nom_proyfactura, cod_operacion, cliente, flag_offshore, flag_eficiencia, "
								+ "mercado_hori, mercado_vert, porc_eficiencia, cod_tarifa, tarifa, perfil, practica_tecnologico"
								+ " )" + "VALUES (" + campos[0] + ", '" + campos[1] + "', '" + campos[2] + "', '"
								+ campos[3] + "', '" + campos[4] + "', " + "'" + campos[5] + "', " + campos[6] + ", '"
								+ campos[7] + "', " + campos[8] + ", '" + campos[9] + "', " + campos[10] + ", " + "'"
								+ campos[11] + "', '" + campos[12] + "', '" + campos[13] + "', '" + campos[14] + "','"
								+ campos[15] + "',  '" + campos[16] + "', '" + campos[17] + "', '" + campos[18] + "', '"
								+ campos[19] + "','" + campos[20] + "'" + " );";
						if (campos[0] != "" && campos[1] != "" && campos[2] != "" && campos[3] != "" && campos[4] != ""
								&& campos[5] != "" && campos[6] != "") {
							System.out.println(" QueryInsert..." + QueryInsert);
							logImporService.callProcedurePlantillaOffshore(QueryInsert, dto2.getId().intValue(), cantt);
						}

						else {

							logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
									"error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
									"" + record[0] + "   "));

						}

					} else {

						logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
								"error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
								"" + record[0] + "   "));

					}

				}

				if (dto2.getId().intValue() != 0) {

					statusResponse = HttpStatus.OK;
				}

				// llamamos el procedure carga de datos
				logImporService.callCargarMaestraPlantillaOffshore(0);
			}

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;
		}
		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

	/**
	 * Método que se encarga de importar archivos al servidor para poblar la BD
	 * 
	 * @param arbol,Archivo de seguimiento
	 * @return
	 * @throws IOException
	 */

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@PostMapping("/Seguimiento")
	public Object uploads(@RequestParam("seguimiento") MultipartFile arbol) throws IOException {
		try {
			byte[] bytesA = arbol.getBytes();
			Path pathA = Paths.get(fileLocation, arbol.getOriginalFilename());
			Files.write(pathA, bytesA);
			Date fechaActual = new Date();
			// formateador.format(ahora),
			LogImporDTO dto1 = logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(), "Ejecución",
					"El número de columnas del registro no coincide con la tabla"));

			// proceso el archivo
			// proceso el archivo
			File file = new File(pathA.toString());
			CSVReader reader = new CSVReader(new FileReader(file), '^');

			String QueryInsert = "";
			int cantt = reader.readAll().size();

			// se realiza una nueva instancia para procesar los registros
			CSVReader readerDos = new CSVReader(new FileReader(file), '^');
			String[] record = readerDos.readNext();

			while ((record = readerDos.readNext()) != null) {
				record[0] = record[0].replaceAll(",", ".");
				record[0] = record[0].replaceAll("\"", "");
				record[0] = record[0].replaceAll("'", "");
				record[0] = record[0].replaceAll(",", "");

				String[] campos = record[0].split(";");
				if (campos.length == 17) {

					if (campos[0].equals("")) {
						campos[0] = "null";
					} else {
						campos[0] = "'" + campos[0] + "'";

					}

					if (campos[8].equals("")) {
						campos[8] = "null";
					} else {
						campos[8] = "'" + campos[8] + "'";

					}
					if (campos[6].equals("")) {
						campos[6] = "null";
					} else {
						campos[6] = "'" + campos[6] + "'";

					}
					if (campos[10].equals("")) {
						campos[10] = "null";
					} else {
						campos[10] = "'" + campos[10] + "'";

					}
					QueryInsert = "INSERT INTO public.seguimiento_tmp(\n"
							+ " fecha_presentacion, cod_necesidad, nombre, estado_cantidato, pais_cantidato, "
							+ "proyecto, fecha_validacion_tecnica, contacto_tecnico, fecha_ingreso, tasa_off, "
							+ "tarifa_off, equipo_computo, usuario_indra, sem_pres, sem_ent_conc, sem_vinc, fecha_datos"
							+ " )" + "VALUES (" + campos[0] + ", '" + campos[1] + "', '" + campos[2] + "', '"
							+ campos[3] + "', '" + campos[4] + "', " + "'" + campos[5] + "', " + campos[6] + ", '"
							+ campos[7] + "', " + campos[8] + ", '" + campos[9] + "', " + campos[10] + ", " + "'"
							+ campos[11] + "', '" + campos[12] + "', '" + campos[13] + "', '" + campos[14] + "','"
							+ campos[15] + "'," + "'" + campos[16] + "' );";

					if (campos[16] != "") {
						logImporService.callProcedureCargueSeguimiento(QueryInsert, dto1.getId().intValue(), cantt);
					}

					else {

						logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
								"error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
								"" + record[0] + "   "));

					}

				} else {

					logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(), ""
							+ "---error en el registro  revisar tabulaciones, campos obligatirios o formato fecha no válida  en el archivo  ",
							"" + record[0] + "   "));
				}

			}

			// llamamos el procedure carga de datos
			logImporService.callCargarMaestraSeguimiento(0);

			if (dto1.getId().intValue() != 0) {
				statusResponse = HttpStatus.OK;
			} else {
				statusResponse = HttpStatus.BAD_REQUEST;
				messageResponse = Constantes.ERROR_SOLICITUD;
			}

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;

		}
		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@PostMapping("/DedicacionPlantillaPTP")
	public Object uploadsDedicacionPlantillaPTP(@RequestParam("PlantillaPTP") MultipartFile arbol) throws IOException {
		try {
			byte[] bytesA = arbol.getBytes();
			Path pathA = Paths.get(fileLocation, arbol.getOriginalFilename());
			Files.write(pathA, bytesA);

			Date fechaActual = new Date();

			LogImporDTO dto1 = logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
					"Error en ejecución", "revisar validar columnas del archivo"));

			// proceso el archivo
			File file = new File(pathA.toString());
			CSVReader reader = new CSVReader(new FileReader(file), '^');

			String QueryInsert = "";

			int cantt = reader.readAll().size();
			// se realiza una nueva instancia para procesar los registros
			CSVReader readerDos = new CSVReader(new FileReader(file), '^');
			String[] record = readerDos.readNext();
			while ((record = readerDos.readNext()) != null) {
				record[0] = record[0].replaceAll(",", ".");
				record[0] = record[0].replaceAll("\"", "");
				record[0] = record[0].replaceAll("'", "");
				record[0] = record[0].replaceAll(",", "");

				String[] campos = record[0].split(";");
				if (campos.length == 40) {
					if (campos[21].equals("")) {
						campos[21] = "'null'";
					} else {
						campos[21] = "'" + campos[21] + "'";

					}

					if (campos[22].equals("")) {
						campos[22] = "'null'";
					} else {
						campos[22] = "'" + campos[22] + "'";

					}
					if (campos[23].equals("")) {
						campos[23] = "'null'";
					} else {
						campos[23] = "'" + campos[23] + "'";

					}
					if (campos[12].equals("") || campos[12] == null) {
						campos[12] = "'null'";
					} else {
						campos[12] = "'" + campos[12] + "'";
					}

					campos[33] = campos[33].replaceAll(",", ".");
					campos[32] = campos[32].replaceAll(",", ".");
					campos[31] = campos[31].replaceAll(",", ".");
					campos[30] = campos[30].replaceAll(",", ".");

					QueryInsert = "INSERT INTO public.dedicacion_plantilla_eyp_tmp(\n"
							+ " pais_empresa, empresa, sociedad_empleado, empleado, nro_empleado, pais_empleado, directo_indirecto, "
							+ "cod_directo_indirecto_, rol, nucleo, cod_nucleo, uo_nivel_12, uo_nivel_10, propio_subcontratado, mercado_empleado,"
							+ " cliente, indicador_dedicaciones_intercompany, proyecto_principal_participante, proyecto_participante, "
							+ "cod_empresa_proyecto_participante, descripcion_proyecto_participante, proyecto_principal, cod_empresa_proyecto_principal,"
							+ " descripcion_proyecto_principal, clase_de_proyecto, cod_clase_proyecto, mercado_global_proyecto, mercado_horizontal_proyecto, "
							+ "mercado_vertical_proyecto, indicadores, horas_dedicadas_mes, horas_dedicadas_ano, ftes_dedicadas_mes, "
							+ "ftes_dedicadas_ano, fecha_datos, mes, pais_sociedad, mercado, entrega, practica_tecnologica) \n"
							+ "VALUES ( '" + campos[0] + "', '" + campos[1] + "', '" + campos[2] + "', '" + campos[3]
							+ "', " + campos[4] + ", '" + campos[5] + "',  '" + campos[6] + "'," + " '" + campos[7]
							+ "'," + " '" + campos[8] + "', '" + campos[9] + "'," + " '" + campos[10] + "', '"
							+ campos[11] + "', " + campos[12] + "," + " '" + campos[13] + "','" + campos[14] + "', '"
							+ campos[15] + "', " + " '" + campos[16] + "', '" + campos[17] + "'," + " '" + campos[18]
							+ "'," + " '" + campos[19] + "','" + campos[20] + "', " + campos[21] + ", " + campos[22]
							+ ", " + campos[23] + "," + " '" + campos[24] + "', '" + campos[25] + "', '" + campos[26]
							+ "', '" + campos[27] + "'," + " '" + campos[28] + "', '" + campos[29] + "', '" + campos[30]
							+ "'," + " '" + campos[31] + "','" + campos[32] + "','" + campos[33] + "'," + " '"
							+ campos[34] + "','" + campos[35] + "'," + " '" + campos[36] + "', '" + campos[37] + "','"
							+ campos[38] + "', '" + campos[39] + "'   );\r\n";

					logImporService.callProcedureCargarDedicacionPlantillaPTP(QueryInsert, dto1.getId().intValue(),
							cantt);

				}

			}

			if (dto1.getId().intValue() != 0) {
				statusResponse = HttpStatus.OK;
				// llamamos el procedure carga de datos
				logImporService.callCargarMaestraDedicacionPlantillaEYP(0);
			} else {
				statusResponse = HttpStatus.BAD_REQUEST;
				messageResponse = Constantes.ERROR_SOLICITUD;
			}

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;

		}

		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

	
	
	/**
	 * Método que se encarga de importar archivos al servidor para poblar
	 * informacion de cambios y movimientos en la BD
	 * 
	 * @param proyectos : Archivo de cambios
	 * @param plantilla : Archivo de movimientos
	 * @return
	 * @throws IOException
	 */
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@PostMapping("/cambiosMovimientos")
	public Object uploadsCambiosMovimientos(@RequestParam("Hcambios") MultipartFile Hcambios,
			@RequestParam("movimientos") MultipartFile movimientos,
			@RequestParam("enviocambio") String enviocambio,
			@RequestParam("enviomovimientos") String enviomovimientos) throws IOException {
	
		try {
			Date fechaActual = new Date();
			System.out.println("" + Hcambios.getOriginalFilename() + " " + movimientos.getOriginalFilename()
					+ " " + enviocambio + "  .." + movimientos);

			// si se ha enviado
			if (enviocambio.equals("s")) {

				byte[] bytesA = Hcambios.getBytes();
				Path pathA = Paths.get(fileLocation, Hcambios.getOriginalFilename());
				Files.write(pathA, bytesA);

				//Date fechaActual = new Date();

				LogImporDTO dto1 = logImporService.save(new LogImporDTO(null, fechaActual, pathA.toString(),
						"Error en ejecución", "revisar validar columnas del archivo"));

				// proceso el archivo
				File file = new File(pathA.toString());
				CSVReader reader = new CSVReader(new FileReader(file), '^');
				String QueryInsert = "";

				int cantt = reader.readAll().size();
				// se realiza una nueva instancia para procesar los registros
				CSVReader readerDos = new CSVReader(new FileReader(file), '^');
				String[] record = readerDos.readNext();
				while ((record = readerDos.readNext()) != null) {
					record[0] = record[0].replaceAll(",", ".");
					record[0] = record[0].replaceAll("\"", "");
					record[0] = record[0].replaceAll("'", "");
					record[0] = record[0].replaceAll(",", "");
					String[] campos = record[0].split(";");
					if (campos.length == 5) {
						campos[2] = campos[2].replaceAll(",", ".");
						campos[3] = campos[3].replaceAll(",", ".");

						QueryInsert = "INSERT INTO public.h_cambios_tmp(\n"
								+ " moneda, cod_moneda, cambio_ultimo_dia_eur, cambio_medio_mensual_eur, fecha_datos)"
								+ " \n" + " VALUES ( '" + campos[0] + "', '" + campos[1] + "', '" + campos[2] + "', '"
								+ campos[3] + "','" + campos[4] + "' );\r\n";

						logImporService.callProcedureCargarCambios(QueryInsert, dto1.getId().intValue(), cantt);

					}

				}

				if (dto1.getId().intValue() != 0) {
					statusResponse = HttpStatus.OK;
					// llamamos el procedure carga de datos
					logImporService.callCargarMaestraCambios(0);
				} else {
					statusResponse = HttpStatus.BAD_REQUEST;
					messageResponse = Constantes.ERROR_SOLICITUD;
				}

				
				
			}

			if (enviomovimientos.equals("s")) {
				byte[] bytesG = movimientos.getBytes();
				Path pathG = Paths.get(fileLocation, movimientos.getOriginalFilename());
				Files.write(pathG, bytesG);
				LogImporDTO dto2 = logImporService.save(new LogImporDTO(null, fechaActual, pathG.toString(),
						"Ejecución", "El número de columnas del registro no coincide con la tabla"));
				// proceso el archivo
				File file = new File(pathG.toString());
				CSVReader reader = new CSVReader(new FileReader(file), '^');

				String QueryInsert = "";

				int cantt = reader.readAll().size();
				// se realiza una nueva instancia para procesar los registros
				CSVReader readerDos = new CSVReader(new FileReader(file), '^');
				String[] record = readerDos.readNext();
				while ((record = readerDos.readNext()) != null) {
					record[0] = record[0].replaceAll(",", ".");
					record[0] = record[0].replaceAll("\"", "");
					record[0] = record[0].replaceAll("'", "");
					record[0] = record[0].replaceAll(",", "");

					
					
					String[] campos = record[0].split(";");
					if (campos.length == 16) {
						campos[3]=	campos[3].replaceAll(" ", "");
						campos[3] = campos[3].replace(" ", "");
						
						if (campos[8].equals("") || campos[8] == null) {
							campos[8] = "null";
						} else {
							campos[8] = "'" + campos[8] + "'";
						}
						
						
						if (campos[12].equals("") || campos[12] == null) {
							campos[12] = "null";
						} else {
							campos[12] = "'" + campos[12] + "'";
						}

						QueryInsert = "INSERT INTO public.h_movimientos_tmp(\n"
								+ " delivery, movimiento, mes_movimiento, cod_empleado, empleado, proyecto, horizontal, "
								+ "mercado_vertical, fecha_incorporacion_proyecto, rol, practica, subpractica, fecha_retiro, motivo_de_la_baja, mercado_vert_agrup,"
								+ " fecha_datos) \n" + "VALUES ( '" + campos[0] + "', '" + campos[1] + "', '" + campos[2]
								+ "', '"+ campos[3]+"', '" + campos[4] + "', '" + campos[5] + "',  '" + campos[6] + "',"
								+ " '" + campos[7] + "'," + " " + campos[8] + ", '" + campos[9] + "'," + " '" + campos[10]
								+ "', '" + campos[11] + "', " + campos[12] + "," + " '" + campos[13] + "','" + campos[14]
								+ "', '" + campos[15] + "'   );\r\n";
						logImporService.callProcedureCargarMovimientos(QueryInsert, dto2.getId().intValue(),
								cantt);

					}

				}

				if (dto2.getId().intValue() != 0) {
					statusResponse = HttpStatus.OK;
					// llamamos el procedure carga de datos
					logImporService.callCargarMaestraMovimientos(0);
				} else {
					statusResponse = HttpStatus.BAD_REQUEST;
					messageResponse = Constantes.ERROR_SOLICITUD;
				}
			}

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;
		}
		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}


}
