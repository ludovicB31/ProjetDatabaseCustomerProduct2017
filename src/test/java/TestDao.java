/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.mavenprojetaab.DAOprojet;
import com.mycompany.mavenprojetaab.DataSourceFactory;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lblanc01
 */
public class TestDao {
    private DAOprojet  myDAO; // L'objet à tester
	private DataSource myDataSource; // La source de données à utiliser
        
        
    public TestDao() {
        myDataSource = DataSourceFactory.getDataSource();
		myDAO = new DAOprojet(myDataSource);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
          myDataSource = DataSourceFactory.getDataSource();
		myDAO = new DAOprojet(myDataSource);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
	/**
	 * Test of findCustomer method, of class SimpleDataAccessObject.
	 * @throws simplejdbc.DAOException
	 */
	@Test
	public void testFindClient() throws SQLException  {
		int customedID = 25;
                String Email="www.wrencomp.example.com";
		boolean result = myDAO.ClientExist(Email, customedID);
		assertEquals(true,result);
	}
         
	@Test
	public void testNotClient() throws SQLException  {
		int customedID = 100;
                String Email="www.notexisting.com";
		boolean result = myDAO.ClientExist(Email, customedID);
		assertEquals(false,result);
	}
        
        @Test
	public void testCategorieList() throws SQLException  {
		
	List<String> result = myDAO.allCats();
		assertEquals(6,result.size());
	}
        
         @Test
	public void testProdByCat() throws SQLException  {
		
	List<String> result = myDAO.ProdsByCat("Books");
		assertEquals(5,result.size());
	}
        
         @Test
	public void testProdIDbyDesc() throws SQLException  {
		
	int result;
        result=myDAO.getProductIDByDesc("10Gb Ram");
		assertEquals(980030,result);
	}
        
//        @Test
//	public void testOrderNumGenerator() throws SQLException  {
//		
//	int result;
//        result=myDAO.OrderNumGenerator();
//		assertEquals(30298007+1,result);
//	}
}

