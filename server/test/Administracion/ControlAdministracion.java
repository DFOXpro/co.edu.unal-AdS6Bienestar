/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;

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
public class ControlAdministracion {
	
        private ArrayList testList = new ArrayList();;

    
	public ControlAdministracion() {
            
	}
	
        
	@BeforeClass
	public static void setUpClass() {
            System.out.println("Se da inicio a las pruebas unitarias para el módulo de Administración");
	}
	
	@AfterClass
	public static void tearDownClass() {
            System.out.println("Se concluyen las pruebas unitarias para el módulo de Administración");
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

    // TODO add test methods here.
	// Crear usuario
	//
	@Test
	public void hello() {
            System.out.println("yugh");
        }
        
	// Editar usuario
	//        
        @Test
	public void hello2() {
            System.out.println("yugh");
        }

        // Consultar usuario
	//        
        @Test
	public void hello3() {
            System.out.println("yugh");
        }
        
        
        // Eliminar usuario
	//        
        @Test
	public void hello4() {
            System.out.println("yugh");
        }
        
        // Crear Convocatoria
	//        
        @Test
	public void crearConvocatoria() {
            System.out.println("yugh");
        }
        
                // Editar Convocatoria
	//        
        @Test
	public void editarConvocatoria() {
            System.out.println("yugh");
        }
        
                // Eliminar Convocatoria
	//        
        @Test
	public void eliminarConvocatoria() {
            System.out.println("yugh");
        }
        
        // Crear Taller
	//        
        @Test
	public void crearTaller() {
            System.out.println("yugh");
        }
        // Editar Taller
	//        
        @Test
	public void editarTaller() {
            System.out.println("yugh");
        }
        
        // Eliminar Taller
	//        
        @Test
	public void eliminarTaller() {
            System.out.println("yugh");
        }

}
