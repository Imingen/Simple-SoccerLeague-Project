package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 *
 */
public class SportsTeamFormTests {
	
	SportsTeamForm STF; 
	SportsTeamForm STF2; 

	private static final int fiveGames = 5;
	private static final int threeGames = 3;
	private static final int zeroGames = 0;
	
	@Before
	public void setup(){
		STF = new SportsTeamForm();
	}
	
	@Test
	public void testAddResultToForm(){
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		String last1 = STF.toString().substring(4);
		assertEquals(last1.charAt(0), WLD.DRAW.getChar());
	}
	
	@Test
	public void testAddResultToFullForm(){
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		String last1 = STF.toString().substring(4);
		assertEquals(last1.charAt(0), WLD.DRAW.getChar());
		
		STF.addResultToForm(WLD.WIN);
		String last2 = STF.toString().substring(4);
//		//Not supposed to be equal now that last1 peeked the list BEFORE it was updated
		assertNotEquals(last1, last2 );
	}
	
	@Test
	public void testToStringWithNoMatchesPlayed(){
		String testString = "-----";
		String actualString = STF.toString();
		assertEquals(testString, actualString);
	}
	
	@Test
	public void testWithLessThanFiveData(){
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.WIN);
		String expected = "WLD--";
		String actual = STF.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testWithFiveData(){
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		String expected = "LWWLD";
		String actual = STF.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testWithFiveDataThenUpdate(){
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		String actualFirst = STF.toString();
		STF.addResultToForm(WLD.LOSS);
		String actualSecond = STF.toString();
		assertNotEquals(actualFirst, actualSecond);
	}

	@Test
	public void testWithFiveGames(){
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		assertEquals(fiveGames,  STF.getNumGames());
	}
	
	@Test
	public void testWithMoreThanFiveGames(){
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.DRAW);
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.DRAW);
		assertEquals(fiveGames, STF.getNumGames());
	}
	
	@Test
	public void testWithLessThanFiveGames(){
		STF.addResultToForm(WLD.WIN);
		STF.addResultToForm(WLD.LOSS);
		STF.addResultToForm(WLD.DRAW);
		assertEquals(threeGames, STF.getNumGames());
	}
	
	@Test
	public void testWithZeroGames(){	
		assertEquals(zeroGames, STF.getNumGames());
	}
	
}
