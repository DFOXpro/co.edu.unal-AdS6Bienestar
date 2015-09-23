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
            System.out.println("Se da inicio a las pruebas unitarias para el módulo de Autenticación");
	}
	
	@AfterClass
	public static void tearDownClass() {
            System.out.println("Se concluyen las pruebas unitarias para el módulo de Autenticación");
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
	public void hello() {
            System.out.println("yugh");
        }
        
        @Test
	public void hello2() {
            System.out.println("yugh");
        }

}
