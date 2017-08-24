package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;



/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerTeamTests {
	
	SoccerTeam soccerTeam;
	SoccerTeam anotherTeam;
	
	private static final int zeroGoals = 0;
	private static final int oneGoal = 1;
	private static final int twoGoals = 2;
	private static final int threeGoals = 3;
	private static final int fourGoals = 4;
	private static final int fiveGoals = 5;
	
	private static final int negativeGoals = -5;
	private static final int unrealisticNumberOfGoals = 52;
	
	private static final int pointsForWin = 3;
	private static final int pointsForDraw = 1;
	private static final int pointsForLoss = 0;
	
	private static final int soccerTeamScoredGoalsInSeason = 12;
	private static final int soccerTeamConcededGoalsInSeason = 10;
	private static final int soccerTeamMatcheWon = 2;
	private static final int soccerTeamMatchesDrawn = 1;
	private static final int soccerTeamMatchesLost = 2;
	
	private static final String soccerTeamName = "Team";
	private static final String soccerTeamNickName = "T";
	private static final String anotherTeamName = "AbbA";
	private static final String anotherTeamNickName = "ab";
	
	private static final String randomTeamName = "Sculi";
	private static final String randomTeamNickName = "Buli";
	private static final String emptyString = "";
	
	private static final String emptyFormString = "-----";
	private static final String soccerTeamFormString = "WLLDW";
	
	@Before
	public void setup() throws TeamException{
		soccerTeam = new SoccerTeam(soccerTeamName, soccerTeamNickName);
		anotherTeam = new SoccerTeam(anotherTeamName, anotherTeamNickName);
	}
	
	private void soccerTeamPlayMatches() throws TeamException{
		soccerTeam.playMatch(fiveGoals, zeroGoals);
		soccerTeam.playMatch(twoGoals, twoGoals);
		soccerTeam.playMatch(twoGoals, threeGoals);
		soccerTeam.playMatch(oneGoal, fiveGoals);
		soccerTeam.playMatch(twoGoals, zeroGoals);
	}
	
	@Test
	public void testConstructorCheckName() throws TeamException{
		SoccerTeam testTeam = new SoccerTeam(randomTeamName, randomTeamNickName);	
		assertEquals(randomTeamName, testTeam.getOfficialName());
	}
	
	@Test
	public void testConstructorCheckNickname() throws TeamException{
		SoccerTeam testTeam = new SoccerTeam("Sculi", "Buli");
		assertEquals(randomTeamNickName, testTeam.getNickName());
	}
	
	@Test(expected = TeamException.class)
	public void testConstructorWithNoNickname() throws TeamException{
		SoccerTeam testTeam = new SoccerTeam(randomTeamName, emptyString);
		testTeam.getNickName();
	}
	
	@Test(expected = TeamException.class)
	public void testConstructorWithNoName() throws TeamException{
		SoccerTeam testTeam = new SoccerTeam(emptyString, randomTeamNickName);
		testTeam.getOfficialName();
	}
	
	@Test(expected = TeamException.class)
	public void testConstructionWithTwoEmptyStrings() throws TeamException{
			SoccerTeam testTeam = new SoccerTeam(emptyString, emptyString);
			testTeam.getNickName(); testTeam.getOfficialName();
		}

	@Test(expected = TeamException.class)
	public void testWithNegativeGoalsForHomeTeam() throws TeamException{
		soccerTeam.playMatch(negativeGoals, twoGoals);
	}
			
	@Test(expected = TeamException.class)
	public void testWithNegativeGoalsForAwayTeam() throws TeamException{
		soccerTeam.playMatch(fiveGoals, negativeGoals);
	}
	
	@Test(expected = TeamException.class)
	public void testWithMoreThanTwentyGoalsForHomeTeam() throws TeamException{
		soccerTeam.playMatch(unrealisticNumberOfGoals, twoGoals);
	}
	
	@Test(expected = TeamException.class)
	public void testWithMoreThanTwentyGoalsForAwayTeam() throws TeamException{
		soccerTeam.playMatch(twoGoals, unrealisticNumberOfGoals);
	}

	@Test
	public void testResetFormFormIsCorrect() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertEquals(emptyFormString, soccerTeam.getFormString());
	}
	
	@Test
	public void testResetCompetitionPointsIsZero() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertEquals(zeroGoals, soccerTeam.getCompetitionPoints());
	}

	@Test
	public void testsoccerTeamHasMorePoints() throws TeamException{
		soccerTeam.playMatch(threeGoals, zeroGoals);
		anotherTeam.playMatch(zeroGoals, zeroGoals);
		assertTrue(soccerTeam.compareTo(anotherTeam) < 0 );
	}
	
	@Test
	public void testAnotherTeamHasMorePoints() throws TeamException{
		soccerTeam.playMatch(zeroGoals, fiveGoals);
		anotherTeam.playMatch(oneGoal, zeroGoals);
		assertTrue(soccerTeam.compareTo(anotherTeam) > 0);
	}
	
	@Test
	public void testTheSameAmountOfPointsButSoccerTeamHasMoreGoals() throws TeamException{
		soccerTeam.playMatch(fiveGoals, twoGoals);
		anotherTeam.playMatch(twoGoals, oneGoal);
		assertTrue(soccerTeam.compareTo(anotherTeam) < 0 );
	}
	
	@Test
	public void testTheSameAmountOfPointsButTeamTwoHasMoreGoals() throws TeamException{
		soccerTeam.playMatch(twoGoals, oneGoal);
		anotherTeam.playMatch(fiveGoals, twoGoals);
		assertTrue(soccerTeam.compareTo(anotherTeam) > 0);
	}
	
	@Test
	public void testSamePointsAndSameNumberOfGoals() throws TeamException{
		soccerTeam.playMatch(fourGoals, zeroGoals);
		anotherTeam.playMatch(fourGoals, zeroGoals);
		assertTrue(soccerTeam.compareTo(anotherTeam) > 0);
	}
	
	@Test
	public void testAllStatsTheSame() throws TeamException{
		SoccerTeam ABB = new SoccerTeam(anotherTeamName, anotherTeamNickName);
		ABB.playMatch(oneGoal, zeroGoals);
		anotherTeam.playMatch(oneGoal, zeroGoals);
		assertEquals(ABB.compareTo(anotherTeam), 0);
	}
	
	@Test
	public void testGetOfficialName(){
		assertEquals(soccerTeam.getOfficialName(), soccerTeamName);
	}
	
	@Test
	public void testGetNickName(){
		assertEquals(soccerTeam.getNickName(), soccerTeamNickName);
	}
	
	@Test
	public void testGetGoalsScoredSeason() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getGoalsScoredSeason(), soccerTeamScoredGoalsInSeason);
	}
	
	@Test
	public void testGetGoalsScoredSeasonAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(soccerTeamScoredGoalsInSeason, soccerTeam.getGoalsScoredSeason());
	}
	
	@Test
	public void testGetGoalsConcededSeason() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getGoalsConcededSeason(), soccerTeamConcededGoalsInSeason);
	}
	
	@Test
	public void testGetGoalsConcededSeasonAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(soccerTeamConcededGoalsInSeason, soccerTeam.getGoalsConcededSeason());
	}
	
	@Test
	public void testGetMatchesWon() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getMatchesWon(), soccerTeamMatcheWon);
	}
	
	@Test
	public void testGetMatchesWonAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(soccerTeamMatcheWon, soccerTeam.getMatchesWon());
	}
	
	@Test
	public void testGetMatchesLost() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getMatchesLost(), soccerTeamMatchesLost);
	}
	
	@Test
	public void testGetMatchesLostAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(soccerTeamMatchesLost, soccerTeam.getMatchesLost());
	}
	
	@Test
	public void testGetMatchesDrawn() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getMatchesDrawn(), soccerTeamMatchesDrawn);
	}
	
	@Test
	public void testGetMatchesDrawnAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(soccerTeamMatchesDrawn, soccerTeam.getMatchesDrawn());
	}
	
	@Test
	public void testGetCompetitionpoints() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getCompetitionPoints(), 
			soccerTeam.getMatchesLost() * pointsForWin + soccerTeam.getMatchesDrawn() * pointsForDraw );
	}
	
	@Test
	public void testGetCompetitionPointsAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		int pointsBeforeReset = soccerTeam.getMatchesLost() * pointsForWin + 
				soccerTeam.getMatchesDrawn() * pointsForDraw;
		
		soccerTeam.resetStats();
		assertNotEquals(pointsBeforeReset, soccerTeam.getCompetitionPoints());
	}
	
	@Test
	public void testGetGoalDifference() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getGoalDifference(), soccerTeam.getGoalsScoredSeason() - soccerTeam.getGoalsConcededSeason());
	}
	
	@Test
	public void testGetGoalDifferenceAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(oneGoal, soccerTeam.getGoalDifference());
	}
	
	@Test
	public void testGetFormString() throws TeamException{
		soccerTeamPlayMatches();
		assertEquals(soccerTeam.getFormString(), soccerTeamFormString);
	}
	
	@Test
	public void testGetFormStringAfterReset() throws TeamException{
		soccerTeamPlayMatches();
		soccerTeam.resetStats();
		assertNotEquals(soccerTeamFormString, soccerTeam.getFormString());
	}
}	

























































