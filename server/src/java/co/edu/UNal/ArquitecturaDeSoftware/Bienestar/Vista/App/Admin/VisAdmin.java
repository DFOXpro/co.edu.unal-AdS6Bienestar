package co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.App.Admin;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.App.Usuario.VisUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author awake
 */
public class VisAdmin extends VisUsuario {

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		if (null != request.getParameter("tipo"))
			switch (request.getParameter("tipo")) {
//CRUD USUARIO
				case "Usuario": {
					CRUDUsuarios.leerUsuarioId(request, response);
					break;
				} case "usuarios": {
					CRUDUsuarios.leerUsuariosMultiplesId(request, response);
					break;
				} case "numUsuarios": {
					CRUDUsuarios.obtenerTotalUsuarios(request, response);
					break;
				} case "crearUsuario": {
					CRUDUsuarios.crearUsuario(request, response);
					break;
				} case "editarUsuario": {
					CRUDUsuarios.editarUsuario(request, response);
					break;
				} case "eliminarUsuario": {
					CRUDUsuarios.eliminarUsuario(request, response);
					break;
				}

//CRUD CONVOCATORIA
				case "convocatoria": {
					leerConvocatoria(request, response);
					break;
				} case "/evento/convocatorias": {
					leerMultiplesConvocatorias(request, response);
					break;
				} case "numConvocatorias": {
					obtenerTotalConvocatorias(request, response);
					break;
				} case "inscritosConvocatoria": {
					obtenerInscritosConv(request, response);
					break;
				} case "eliminarUsuarioConv": {
					quitarUsuarioConvocatoria(request, response);
					break;
				} case "regUsuarioConv": {
					registrarUsuarioConvocatoria(request, response);
					break;
				} case "crearConvocatoria": {
					CUDEventos.crearConvocatoria(request, response);
					break;
				} case "editarconvocatorias": {
					CUDEventos.actualizarConvocatoria(request, response);
					break;
				} case "eliminarconvocatorias": {
					CUDEventos.eliminarConvocatoria(request, response);
					break;
				} case "usuariosEnConvocatoria": {
					CUDEventos.consultarUsuariosEnConvocatoriaId(request, response);
					break;
				}

//CRUD TALLER
				case "taller": {//Lee solo uno
					leerTaller(request, response);
					break;
				} case "/evento/talleres": {//Lee muchos
					leerMultiplesTalleres(request, response);
					break;
				} case "numTalleres": {//
					obtenerTotalTalleres(request, response);
					break;
				} case "inscritosTaller": {
					obtenerInscritosTaller(request, response);
					break;
				} case "crearTaller": {
					CUDEventos.crearTaller(request, response);
					break;
				} case "editartalleres": {
					CUDEventos.actualizarTaller(request, response);
					break;
				} case "eliminartalleres": {
					CUDEventos.eliminarTaller(request, response);
					break;
					//Relaciones
				} case "usuariosEnTaller": {
					CUDEventos.consultarUsuariosEnTallerId(request, response);
					break;
				} case "eliminarUsuarioTaller": {
					quitarUsuarioTaller(request, response);
					break;
				} case "docentesEnTaller": {
					CUDEventos.consultarDocentesEnTallerId(request, response);
					break;
				} case "regUsuarioTaller": {
					registrarUsuarioTaller(request, response);
					break;
				} case "regDocente": {
					registrarDocenteTaller(request, response);
					break;
				} case "regDocenteDocumento": {
					registrarATallerDocenteByDoc(request, response);
					break;
				} default:
					System.err.print("tipo de request invalido: " + request.getParameter("tipo"));
			}
		else System.err.print("VisAdmin.doPost: request inválido");
	}

	/**
	 * Returns a short description of the servlet
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Vista de Cuenta.Admin";
	}
}