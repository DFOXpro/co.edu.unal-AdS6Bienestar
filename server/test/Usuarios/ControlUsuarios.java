/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Administracion.*;
import Cuenta.*;
import java.util.ArrayList;
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
public class ControlUsuarios {
	
        private ArrayList testList = new ArrayList();;

    
	public ControlUsuarios() {
            
	}
	
        
	@BeforeClass
	public static void setUpClass() {
            System.out.println("Se da inicio a las pruebas unitarias para el módulo de Usuarios");
	}
	
	@AfterClass
	public static void tearDownClass() {
            System.out.println("Se concluyen las pruebas unitarias para el módulo de Usuarios");
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

    // TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void registrarUsuarioTaller() {
            System.out.println("yugh");
        }
        
        @Test
	public void abandonarTallerUsuario() {
            System.out.println("yugh");
        }
	@Test
	public void registrarConvocatoriaUsuario() {
            System.out.println("yugh");
        }
        
        @Test
	public void abandonarConvocatoriaUsuario() {
            System.out.println("yugh");
        }
	@Test
	public void registrarTallerDocente() {
            System.out.println("yugh");
        }
	@Test
	public void registrarTallerDocenteDocumento() {
            System.out.println("yugh");
        }
        
        @Test
	public void abandonarTallerDocente() {
            System.out.println("yugh");
        }
        @Test
	public void obtenerInscritosTallerId() {
            System.out.println("yugh");
        }
        @Test
	public void obtenerInscritosConvocatoriaId() {
            System.out.println("yugh");
        }
        @Test
	public void consultarConvocatoriaId() {
            System.out.println("yugh");
        }
        @Test
	public void consultarTallerId() {
            System.out.println("yugh");
        }

}
