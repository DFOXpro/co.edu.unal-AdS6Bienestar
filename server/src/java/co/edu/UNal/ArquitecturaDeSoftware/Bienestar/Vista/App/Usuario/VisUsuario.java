package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.App.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author awake
 */
public class VisUsuario extends Read {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DO NOTHING
		System.out.print("Warning!: acceso por get: " + request);
		response.sendError(401);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (null != request.getParameter("tipo")) {
			switch (request.getParameter("tipo")) {
				case "convocatoria": {
					leerConvocatoria(request, response);
					break;
				} case "taller": {
					leerTaller(request, response);
					break;
				} case "/evento/convocatorias": {
					leerMultiplesConvocatorias(request, response);
					break;
				} case "numConvocatorias": {
					obtenerTotalConvocatorias(request, response);
					break;
				}
				case "inscritosConvocatoria": {
					obtenerInscritosConv(request, response);
					break;
				}
				case "/evento/talleres": {
					leerMultiplesTalleres(request, response);
					break;
				}
				case "numTalleres": {
					obtenerTotalTalleres(request, response);
					break;
				}
				case "inscritosTaller": {
					obtenerInscritosTaller(request, response);
					break;
				}
				case "regUsuarioTaller": {
					registrarUsuarioTaller(request, response);
					break;
				}
				case "regUsuarioConv": {
					registrarUsuarioConvocatoria(request, response);
					break;
				}
				case "regDocente": {
					registrarDocenteTaller(request, response);
					break;
				}
				case "regDocenteDocumento": {
					registrarATallerDocenteByDoc(request, response);
					break;
				}
				case "eliminarUsuarioTaller": {
					quitarUsuarioTaller(request, response);
					break;
				}
				case "eleminarUsuarioConv": {
					quitarUsuarioConvocatoria(request, response);
					break;
				}
				default:
					System.err.print("tipo de request invalido: " + request.getParameter("tipo"));
			}
		} else {
			System.err.print("VisUsuario.doPost: request inválido");
		}
	}

	@Override
	public String getServletInfo() {
		return "Vista de Cuenta.Usuario";
	}
}