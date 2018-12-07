package JUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EmailFolderTest.class, EmailMessageTest.class, EmailUserPasswordTest.class })

/**
 * Mostra todos os testes da API Email

 * @author Adil
 * 
 */

public class AllEmailTests {

}
