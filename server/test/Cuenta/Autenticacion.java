/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlRegistro;
import co.edu.UNal.ArquitecturaDeSoftware.Bienestar.Control.Cuentas.CtrlAutenticacion;
import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dfoxpro
 */
public class Autenticacion {
	
        
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
    
	public Autenticacion() {
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
            System.out.println("Se da inicio a las pruebas unitarias para el módulo de Autenticación");
	}
	
	@AfterClass
	public static void tearDownClass() {
            System.out.println("Se concluyen las pruebas unitarias para el módulo de Autenticación");
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
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void verificarLogin() {
            System.out.println("Creación de usuario aleatorio para verificar registro");
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
            if(r.equals("isExitoso")){
//                ArrayList v = CtrlAutenticacion.autenticar(correo, contrasena, Cifrar.get);
//                assertEquals("isExitoso", p.get(0));  
            }
            
        }
        

}
