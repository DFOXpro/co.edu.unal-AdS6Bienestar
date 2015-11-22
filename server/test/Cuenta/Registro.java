/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlRegistro;
import java.util.ArrayList;
import java.lang.String;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Vista.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author clmahechap
 * Prueba automática de registro de usuarios a la aplicación.
 * Las pruebas se realizan con datos aleatorios generados automaticamente 
 * por las funciones definidas a continuación.
 */
public class Registro {
	
        private CtrlRegistro reg = new CtrlRegistro();
        private Random r = new Random();
        private ArrayList<String> rolOpt = new ArrayList<>();
        private ArrayList<String> tDocOpt = new ArrayList<>();
        
        String nombre;
        String apellido;
        String tDocumento;
        String documento;
        String correo;
        String contrasena;
        char rol;
            
        private static final String NUMBERS = "1234567890.";
        private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890._-@+/\\()[]:'#$!¡%?{}";
        private static final String EMAIL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#$%&'*+-/=?^_`{|}~.";
        private static final String CHAR_LIST_NAME = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
     
        
        public Registro() {
            rolOpt.add("A");
            rolOpt.add("P");
            rolOpt.add("E");
            
            tDocOpt.add("CC");
            tDocOpt.add("PP");
            tDocOpt.add("TI");
	}
        
        private int getRandomNumber(int p) {
            String usedChart="";
            if(p==1){
                usedChart=CHAR_LIST;
            }else if (p==2){
                usedChart=NUMBERS;
            }else if (p==4){
                usedChart=EMAIL;
            }else{
                usedChart=CHAR_LIST_NAME;
            }
            int randomInt = 0;
            Random randomGenerator = new Random();
            randomInt = randomGenerator.nextInt(usedChart.length());
            if (randomInt - 1 == -1) {
                return randomInt;
            } else {
                return randomInt - 1;
            }
        }
        

        /**
     * Este método genera un string aleatorio para representar nombres y apellidos
     * posibles ingresados por un usuario. Considera caracteres especiales.
     * @return
     */
    public String generateRandomStringAlpha(int lenght, int p){
        String usedChart="";
        if(p==1){
            usedChart=CHAR_LIST;
        }else if (p==2){
            usedChart=NUMBERS;
        }else if (p==4){
            usedChart=EMAIL;
        }else{
            usedChart=CHAR_LIST_NAME;
        } 
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<lenght; i++){
            int number = getRandomNumber(p);
            char ch = usedChart.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }


	
        
	@BeforeClass
	public static void setUpClass() {
            System.out.println("Se da inicio a las pruebas unitarias para el módulo de Registro");
	}
	
	@AfterClass
	public static void tearDownClass() {
            System.out.println("Se concluyen las pruebas unitarias para el módulo de Registro");
	}
	
	@Before
	public void setUp() {
            nombre =  generateRandomStringAlpha(10, 0) + " "+generateRandomStringAlpha(10, 0);            
            apellido =  generateRandomStringAlpha(10, 0) + " "+generateRandomStringAlpha(10, 0);            
            System.out.println("Nombre:  " + nombre);
            System.out.println("Apellido:  " + apellido);
            
            int randomIndex2 = r.nextInt(tDocOpt.size());
            tDocumento= tDocOpt.get(randomIndex2);
            System.out.println("Tipo documento:  " +tDocumento);
            
            documento= generateRandomStringAlpha(20, 2); 
            System.out.println("Documento:  " +documento);
            
            correo = generateRandomStringAlpha(10, 4) + "@"+generateRandomStringAlpha(3, 0)+"."+generateRandomStringAlpha(3, 0);
            System.out.println("Correo:  " +correo);
            
            contrasena = generateRandomStringAlpha(20, 1); 
            System.out.println("Contraseña:  " + contrasena);
            

            
            int randomIndex = r.nextInt(rolOpt.size());
            this.rol= rolOpt.get(randomIndex).charAt(0);
            System.out.println("Rol:  "+rol);
	}
	
	@After
	public void tearDown() {
	}



    // TODO add test methods here.
	/* Los Documentos de los usuarios se generarán 
        aleatoriamente.*/
	

        /* Prueba 1: Creación de sesión usuario.
        Tipo Usuario - Aleatorio
        Tipo documento - Aleatorio
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionAleatoria() {
            System.out.println("Prueba 1: Creación Exitosa - Todo Aleatorio");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    tDocumento, 
                    documento,
                    correo, 
                    contrasena, 
                    rol);
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }
        
        /* Prueba 2: Creación de sesión usuario.
        Tipo Usuario - Administrador
        Tipo documento - Aleatorio
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionTest2() {
            System.out.println("Prueba 2: Tipo usuario Admin");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    tDocumento, 
                    documento,
                    correo, 
                    contrasena, 
                    'A');
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }
        
                /* Prueba 3: Creación de sesión usuario.
        Tipo Usuario - Estudiante
        Tipo documento - Aleatorio
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionTest3() {
            System.out.println("Prueba 3: Tipo usuario Estudiante");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    tDocumento, 
                    documento,
                    correo, 
                    contrasena, 
                    'E');
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }
        
        /* Prueba 4: Creación de sesión usuario.
        Tipo Usuario - Profesor
        Tipo documento - Aleatorio
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionTest4() {
            System.out.println("Prueba 4: Tipo usuario Profesor");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    tDocumento, 
                    documento,
                    correo, 
                    contrasena, 
                    'P');
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }
        
        /* Prueba 5: Creación de sesión usuario.
        Tipo Usuario - Aleatorio
        Tipo documento - Tarjeta identitad
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionTest5() {
            System.out.println("Prueba 5: Documento es tarjeta de identidad");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    "TI", 
                    "94032602461",
                    correo, 
                    contrasena, 
                    rol);
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }
        
                /* Prueba 6: Creación de sesión usuario.
        Tipo Usuario - Aleatorio
        Tipo documento - Cedula con puntos
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionTest6() {
            System.out.println("Prueba 6: Documento es cedula con puntos");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    "CC", 
                    "1.013.648.467",
                    correo, 
                    contrasena, 
                    rol);
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }
        
                        /* Prueba 7: Creación de sesión usuario.
        Tipo Usuario - Aleatorio
        Tipo documento - Pasaporte
        Correo con caracteres numericos-alfabéticos
        
        */
	@Test
	public void crearSesionTest7() {
            System.out.println("Prueba 7: Documento es Pasaporte");
            //Se generan strings aleatorios para los campos:
            ArrayList r = CtrlRegistro.crearCuenta(
                    nombre,
                    apellido, 
                    "PP", 
                    "JP12345ABC6723",
                    correo, 
                    contrasena, 
                    rol);
            System.out.println(r);
            
            assertEquals("isExitoso", r.get(0));  
        }

}
