package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {
	
	SoccerCompetition soccerComp;
	SoccerCompetition soccerComp2;
	SoccerTeam Team1;
	SoccerTeam Team2;
	SoccerTeam Team3;
	SoccerTeam Team4;
	SoccerTeam Team5;
	SoccerTeam Team6;
	SoccerTeam Team7;
	SoccerTeam Team8;
	SoccerTeam Team9;
	
	private static final int noGoals = 0;
	private static final int oneGoal = 1;
	private static final int twoGoals = 2;
	private static final int threeGoals = 3;
	private static final int fourGoals = 4;
	private static final int fiveGoals = 5;	
	
	private static final int numTeams = 3;
	private static final int numTeamsInLeagueIsZero = 0;
	private static final int numLeaguesInSoccerComp2 = 3;
	private static final int numLeaguesInSoccerComp1 = 1;
	
	
	private static final int leagueOne = 0;
	private static final int leagueTwo = 1;
	private static final int leagueThree = 2;
	
	private static final int negativeNumber = -7;
	private static final int largerThanNumLeagues = 8;
	private static final int equalToNumLeagues = 3;
	
	@Before
	public void Setup() throws TeamException{
		soccerComp = new SoccerCompetition("Div1", numLeaguesInSoccerComp1, numTeams);
		soccerComp2 = new SoccerCompetition("Div2", numLeaguesInSoccerComp2, numTeams);
		Team1 = new SoccerTeam("Team1", "ONE");
		Team2 = new SoccerTeam("Team2", "TWO");
		Team3 = new SoccerTeam("Team3", "THREE");
		Team4 = new SoccerTeam("Team4", "FOUR");
		Team5 = new SoccerTeam("Team5", "FIVE");
		Team6 = new SoccerTeam("Team6", "SIX");
		Team7 = new SoccerTeam("Team7", "SEVEN");
		Team8 = new SoccerTeam("Team8", "EIGHT");
		Team9 = new SoccerTeam("Team9", "NINE");
	}
	
	private void registerTeamsToSoccerComp2() throws LeagueException, CompetitionException{
		soccerComp2.getLeague(leagueTwo).registerTeam(Team1);
		soccerComp2.getLeague(leagueTwo).registerTeam(Team2);
		soccerComp2.getLeague(leagueTwo).registerTeam(Team3);
		soccerComp2.getLeague(leagueOne).registerTeam(Team4);
		soccerComp2.getLeague(leagueOne).registerTeam(Team5);
		soccerComp2.getLeague(leagueOne).registerTeam(Team6);
		soccerComp2.getLeague(leagueThree).registerTeam(Team7);
		soccerComp2.getLeague(leagueThree).registerTeam(Team8);
		soccerComp2.getLeague(leagueThree).registerTeam(Team9);
	}
	
	private void playGamesInSoccerComp2() throws LeagueException, CompetitionException{
		soccerComp2.startSeason();
		soccerComp2.getLeague(leagueOne).playMatch("Team4", fourGoals, "Team6", twoGoals);
		soccerComp2.getLeague(leagueTwo).playMatch("Team1", oneGoal, "Team2", fiveGoals);
		soccerComp2.getLeague(leagueThree).playMatch("Team8", threeGoals, "Team9", twoGoals);
	}
	
	@Test
	public void testCreatingASoccerCompetitionName() throws LeagueException, CompetitionException{
		SoccerCompetition soccerComp = new SoccerCompetition("League1", numLeaguesInSoccerComp1, numTeams);
		assertEquals(numTeams, soccerComp.getLeague(0).getRequiredNumTeams());
	}
	
	@Test(expected = CompetitionException.class)
	public void testGetLeagueWithNegativeLeagueNumber() throws CompetitionException{
		soccerComp.getLeague(negativeNumber);
	}
	
	@Test(expected = CompetitionException.class)
	public void testGetLeagueWithEqualLeagueNumberToNumberOfLeaguesInComp() throws CompetitionException{
		soccerComp2.getLeague(equalToNumLeagues);
	}
	
	@Test(expected = CompetitionException.class)
	public void testGetLeagueWithLargerLeagueNumberThanNumberOfLeaguesInComp() throws CompetitionException{
		soccerComp.getLeague(largerThanNumLeagues);
	}
	
	@Test
	public void testGetLeague() throws CompetitionException, LeagueException{
		assertEquals(soccerComp.getLeague(leagueOne).getRegisteredNumTeams(), numTeamsInLeagueIsZero);
		soccerComp.getLeague(leagueOne).registerTeam(Team1);
		assertEquals(soccerComp.getLeague(leagueOne).getRegisteredNumTeams(), numTeamsInLeagueIsZero + 1);
	}
	
	@Test
	public void testStartSeason() throws CompetitionException, LeagueException{
		soccerComp.getLeague(leagueOne).registerTeam(Team1);
		soccerComp.getLeague(leagueOne).registerTeam(Team2);
		soccerComp.getLeague(leagueOne).registerTeam(Team3);
		assertTrue(soccerComp.getLeague(leagueOne).isOffSeason());
		soccerComp.startSeason();
		assertFalse(soccerComp.getLeague(leagueOne).isOffSeason());
	}
	
	@Test
	public void testEndSeasonWithOneLeague() throws CompetitionException, LeagueException{
		soccerComp.getLeague(leagueOne).registerTeam(Team1);
		soccerComp.getLeague(leagueOne).registerTeam(Team2);
		soccerComp.getLeague(leagueOne).registerTeam(Team3);
		soccerComp.startSeason();
		soccerComp.endSeason();
		assertTrue(soccerComp.getLeague(leagueOne).isOffSeason());
	}
	
	@Test
	public void testEndSeasonWithMoreThanOneLeague() throws LeagueException, CompetitionException{
		registerTeamsToSoccerComp2();
		soccerComp2.startSeason();
		soccerComp2.endSeason();
		assertTrue(soccerComp2.getLeague(leagueThree).isOffSeason());
		assertTrue(soccerComp2.getLeague(leagueTwo).isOffSeason());
		assertTrue(soccerComp2.getLeague(leagueOne).isOffSeason());
	}
	
	@Test 
	public void testEndSeasonStandings() throws LeagueException, CompetitionException{
		registerTeamsToSoccerComp2();
		playGamesInSoccerComp2();
		SoccerTeam beforeEnding = soccerComp2.getLeague(leagueOne).getBottomTeam();
		soccerComp2.endSeason();
		SoccerTeam afterEnding = soccerComp2.getLeague(leagueOne).getBottomTeam();
		assertNotEquals(beforeEnding, afterEnding);
	}
	
	@Test(expected = LeagueException.class)
	public void testEndSeasonsThatLeagueDoesNotContainTheBottomTeamAfterEnd() throws LeagueException, CompetitionException{
		registerTeamsToSoccerComp2();
		playGamesInSoccerComp2();
		soccerComp2.endSeason();
		soccerComp2.getLeague(leagueOne).getTeamByOfficalName("Team6");
	}
	
}



























