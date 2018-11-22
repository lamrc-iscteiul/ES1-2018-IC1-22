package Testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import BDA.TwitterStatusesTest;

@RunWith(Suite.class)
@SuiteClasses({ TwitterStatusesTest.class, TwitterGetTweettest.class, TwitterMessageListTest.class })


/**
 * Mostra todos os testes da API Twitter

 * @author Adil
 * 
 *
 */
public class AllTwitterTests {

}
