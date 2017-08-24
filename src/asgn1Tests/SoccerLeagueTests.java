package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	
	SoccerLeague league;
	SoccerTeam ST;
	SoccerTeam Team1;
	SoccerTeam Team2;
	SoccerTeam Team3;
	SoccerTeam Team4;
	SoccerTeam Team5;
	
	private static final int numberOfTeams = 5;
	private static final int noRegisteredTeams = 0;
	
	private static final int zeroGoals = 0;
	private static final int oneGoal = 1;
	private static final int twoGoals = 2;
	private static final int threeGoals = 3;
	private static final int fourGoals = 4;
	private static final int fiveGoals = 5;
	
	private static final int pointsForOneWin = 3;
	private static final int pointsForOneLoss = 0;
	
	private static final String STName = "Team";
	private static final String STNickName = "T";
	private static final String teamOneName = "Team1";
	private static final String teamOneNickName = "T1";
	private static final String teamTwoName = "Team2";
	private static final String teamTwoNickName = "T2";
	private static final String teamThreeName = "Team3";
	private static final String teamThreeNickName = "T3";
	private static final String teamFourName = "Team4";
	private static final String teamFourNickName = "T4";
	private static final String teamFiveName = "Team5";
	private static final String teamFiveNickName = "T5";
	
	private static final String randomTeamName = "Sculi";
	private static final String randomTeamNickName = "Buli";
	
	
	private static final int largeAmountOfGoalsGoals = 19;
		
	@Before
	public void Setup() throws TeamException{
		league = new SoccerLeague(numberOfTeams);
		ST = new SoccerTeam(STName, STNickName);
	}
		
	/**
	 * A method for making teams and register them to the league
	 * This method can be called in tests for faster workflow instead of writing the 
	 * same lines in most of the tests
	 * @throws TeamException
	 * @throws LeagueException
	 */
	private void makeTeams() throws TeamException, LeagueException{
		Team1 = new SoccerTeam(teamOneName, teamOneNickName);
		Team2 = new SoccerTeam(teamTwoName, teamTwoNickName);
		Team3 = new SoccerTeam(teamThreeName, teamThreeNickName);
		Team4 = new SoccerTeam(teamFourName, teamFourNickName);
		Team5 = new SoccerTeam(teamFiveName, teamFiveNickName);	
		league.registerTeam(Team1);
		league.registerTeam(Team2);
		league.registerTeam(Team3);
		league.registerTeam(Team4);
		league.registerTeam(Team5);
	}
		
	/**
	 * A method that plays some mathces between the registered teams in league
	 * The standings after this method is played is:</br>
	 * Team1:	4 points	4  goal difference  </br>
	 * Team5:	3 points	16 goal difference </br>
	 * Team3: 	3 points 	0  goal difference </br>
	 * Team2: 	1 points 	0  goal difference </br>
	 * Team4:   0 points	-1 goal difference </br>
	 * @throws TeamException
	 * @throws LeagueException
	 */
	private void playSomeMatches() throws TeamException, LeagueException{
		league.startNewSeason();
		Team1.playMatch(fourGoals, zeroGoals);
		Team3.playMatch(zeroGoals, fourGoals);
		Team3.playMatch(fiveGoals, oneGoal);
		Team4.playMatch(oneGoal, twoGoals);
		Team5.playMatch(largeAmountOfGoalsGoals, threeGoals);
		Team1.playMatch(oneGoal, oneGoal);
		Team2.playMatch(oneGoal, oneGoal);
		}
		
	@Test
	public void testCreatingALeague(){
		SoccerLeague league = new SoccerLeague(numberOfTeams);
		assertEquals(league.getRequiredNumTeams(), numberOfTeams);
	}
		
	@Test(expected = LeagueException.class)
	public void testTwoTeamsWithSameName() throws LeagueException, TeamException{
		SoccerTeam ST2 = new SoccerTeam("Team", "T");
		league.registerTeam(ST);
		league.registerTeam(ST2);
		
	}
		
	@Test(expected = LeagueException.class)
	public void testRegisterTeamWhenSeasonHasStarted() throws LeagueException{
		league.startNewSeason();
		league.registerTeam(ST);
	}	
	
	@Test(expected = LeagueException.class)
	public void testRegisterTeamWhenLeagueIsFull() throws TeamException, LeagueException{
		makeTeams();
		league.registerTeam(ST);
	}
	
	@Test
	public void testRegisterTeam() throws TeamException, LeagueException{
		SoccerTeam newTeam = new SoccerTeam(randomTeamName, randomTeamNickName);
		league.registerTeam(newTeam);
		assertEquals(league.getTeamByOfficalName(randomTeamName), newTeam);
	}
	
	@Test
	public void testSortTeamsTopTeam() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		SoccerTeam beforeSortTopTeam = league.getTopTeam();
		league.sortTeams();
		SoccerTeam afterSortTopTeam = league.getTopTeam();
		assertEquals(beforeSortTopTeam, afterSortTopTeam);
	}
	
	@Test
	public void testSortTeamsBottomTeamsNotEqual() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		SoccerTeam beforeSort = league.getBottomTeam();
		league.sortTeams();
		SoccerTeam afterSort = league.getBottomTeam();
		assertNotEquals(beforeSort, afterSort);
	}
	
	@Test
	public void testIfLeagueContainsTeam() throws TeamException, LeagueException{
		makeTeams();
		assertTrue(league.containsTeam(teamOneName));
	}
	
	@Test
	public void testIfLeagueDoNotContainsTeam() throws TeamException, LeagueException{
		makeTeams();
		assertFalse(league.containsTeam(randomTeamName));
	}
		
	@Test
	public void testIfLeagueContainsTeamAfterRemovingIt() throws LeagueException, TeamException{
		makeTeams();
		league.removeTeam(Team2);
		assertFalse(league.containsTeam(teamTwoName));
	}
		
	@Test
	public void testGetBottomTeamAfterPlay() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		league.sortTeams();
		assertEquals(league.getBottomTeam(), Team4);
	}
		
	@Test
	public void testGetBottomTeamWithoutPlaying() throws TeamException, LeagueException{
		makeTeams();
		assertEquals(league.getBottomTeam(), Team5);
	}
	
	@Test(expected = LeagueException.class)
	public void testGetBottomTeamWithLessThanRequiredTeamsInLeague() throws LeagueException{
		league.registerTeam(ST);
		league.getBottomTeam();
	}
		
	@Test(expected = LeagueException.class)
	public void testGetBottomTeamWithZeroTeamsInLeague() throws LeagueException{
		league.getBottomTeam();
	}

	
	@Test
	public void testGetBottomTeamWithEqualPointsButDifferentInGoals() throws TeamException, LeagueException{
		makeTeams();
		Team5.playMatch(twoGoals, oneGoal);
		Team4.playMatch(fiveGoals, zeroGoals);
		Team3.playMatch(fiveGoals, fourGoals);
		Team1.playMatch(zeroGoals, fiveGoals);
		league.sortTeams();
		assertEquals(league.getBottomTeam(), Team1);
	}
		
	@Test
	public void testGetBottomTeamWithEqualPointsAndEqualGoalDifference() throws TeamException, LeagueException{
		makeTeams();
		Team5.playMatch(twoGoals, oneGoal);
		Team4.playMatch(fiveGoals, zeroGoals);
		Team3.playMatch(fiveGoals, fourGoals);
		Team1.playMatch(zeroGoals, fiveGoals);
		Team2.playMatch(zeroGoals, fiveGoals);
		league.sortTeams();
		assertEquals(league.getBottomTeam(), Team2);
	}
	
	@Test
	public void testGetBottomTeamAfterShakingUpTheRatings() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		league.sortTeams();
		SoccerTeam beforePlay = league.getTopTeam();	
		for(int i = 0; i < 10; i ++){
			Team4.playMatch(i, zeroGoals);
		}
		league.sortTeams();
		assertNotEquals(beforePlay, league.getTopTeam());
		}
	
	@Test
	public void testGetTopTeamAfterPlayedMatches() throws TeamException, LeagueException{
		makeTeams(); 
		playSomeMatches();
		league.sortTeams();
		assertEquals(league.getTopTeam(), Team1);
	}
		
	@Test
	public void testGetTopTeamWithoutPlaying() throws TeamException, LeagueException{
		makeTeams();
		assertEquals(league.getTopTeam(), Team1);
		}
	
	@Test
	public void testGetTopTeamAfterShakingUpTheRatings() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		league.sortTeams();
		SoccerTeam beforePlay = league.getTopTeam();	
		for(int i = 0; i < 10; i ++){
			Team3.playMatch(i, zeroGoals);
		}
		league.sortTeams();
		assertNotEquals(beforePlay, league.getTopTeam());
		}
	
	@Test
	public void testGetTopTeamWithEqualPointsButDifferentInGoals() throws TeamException, LeagueException{
		makeTeams();
		Team1.playMatch(twoGoals, oneGoal);
		Team4.playMatch(fiveGoals, zeroGoals);
		league.sortTeams();
		assertNotEquals(league.getTopTeam(), Team1);
	}
		
	@Test
	public void testGetTopTeamWithEqualPointsAndEqualGoalDifference() throws TeamException, LeagueException{
		makeTeams();
		league.startNewSeason();
		Team1.playMatch(twoGoals, oneGoal);
		Team2.playMatch(twoGoals, oneGoal);
		league.sortTeams();
		assertEquals(league.getTopTeam(), Team1);
	}
		
	@Test(expected = LeagueException.class)
	public void testGetTopTeamWithLessThanRquiredTeamsInLeague() throws LeagueException{
		league.registerTeam(ST);
		league.getTopTeam();
	}
		
	@Test(expected = LeagueException.class)
	public void testGetTopTeamWithZeroTeamsInLeague() throws LeagueException{
		league.getTopTeam();
	}
		
	@Test
	public void testPlayMatchGivesCorrectGoalsForHomeTeam() throws TeamException, LeagueException{
		makeTeams();
		league.startNewSeason();
		league.playMatch(teamOneName, twoGoals, teamTwoName, fiveGoals);	
		assertEquals(Team1.getCompetitionPoints(), pointsForOneLoss);
	}
	
	@Test
	public void testPlayMatchGivesCorrectGoalsForAwayTeam() throws LeagueException, TeamException{
		makeTeams();
		league.startNewSeason();
		league.playMatch(teamOneName, twoGoals, teamTwoName, fiveGoals);	
		assertEquals(Team2.getCompetitionPoints(), pointsForOneWin);
	}
	
	@Test
	public void testPlayMatchGivesCorrectPointsInDraw() throws TeamException, LeagueException{
		makeTeams();
		league.startNewSeason();
		league.playMatch(teamOneName, twoGoals, teamTwoName, twoGoals);
		assertEquals(Team2.getCompetitionPoints(), Team1.getCompetitionPoints());
	}
	
	@Test(expected = LeagueException.class)
	public void testPlayMatchWheSeasonNotStarted() throws TeamException, LeagueException{
		makeTeams();
		league.playMatch(teamOneName, threeGoals, teamTwoName, largeAmountOfGoalsGoals);
	}
		
	@Test(expected = LeagueException.class)
	public void testPlayMatchWithSameName() throws LeagueException, TeamException{
		makeTeams();
		league.startNewSeason();
		league.playMatch(teamOneName, threeGoals, teamOneName, twoGoals);
	}
	
	@Test(expected = LeagueException.class)
	public void testNoTeamWithGivenNameInLeague() throws LeagueException, TeamException{
		makeTeams();
		league.getTeamByOfficalName(randomTeamName);
	}
		
	@Test
	public void testTeamWithGivenNameInLeague() throws LeagueException, TeamException{
		makeTeams();
		assertEquals(league.getTeamByOfficalName(teamOneName), Team1);
	}
		
	@Test(expected = LeagueException.class)
	public void testGetTheTeamAfterItHasBeenRemoved() throws TeamException, LeagueException{
		makeTeams();
		league.removeTeam(Team2);
		league.getTeamByOfficalName(teamTwoName);
	}
		
	@Test (expected = LeagueException.class)
	public void testGetTeamNameWithNoTeamsInleague() throws LeagueException{
		league.getTeamByOfficalName(teamOneName);
	}
		
	@Test
	public void testIsOffSeasonWithOffSeasonEqualTrue(){
		assertTrue(league.isOffSeason()); 
	}
		
	@Test
	public void testIsOffSeasonAfterSeasonHasStartedAndThenAfterEndedSeasonAgain() throws LeagueException, TeamException{
		makeTeams();
		league.startNewSeason();
		assertFalse(league.isOffSeason());
	}
	
	@Test
	public void testIsOffSeasonAfterSeasonHasEnded() throws TeamException, LeagueException{
		makeTeams();
		league.startNewSeason();
		league.endSeason();
		assertTrue(league.isOffSeason());
	}
		
	public void testEndSeasonWithNoSeasonStarted() throws LeagueException{
		league.endSeason();
	}
		
	@Test
	public void testStartNewSeason() throws TeamException, LeagueException{
		makeTeams();
		league.startNewSeason();
		assertFalse(league.isOffSeason());
	}
		
	@Test(expected = LeagueException.class)
	public void testStartNewSeasonWithAllreadyStarteSeason() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		league.startNewSeason();
	}
		
	@Test(expected = LeagueException.class)
	public void testStartNewSeasonWithLessTeamsThanRequired() throws LeagueException{
		league.registerTeam(ST);
		league.startNewSeason();
	}
			
	@Test
	public void testIfStartNewSeasonProperlyResetStats() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		int beforeNewSeason = league.getTopTeam().getCompetitionPoints();
		league.endSeason();
		league.startNewSeason();
		int afterNewSeason = league.getTopTeam().getCompetitionPoints();
		assertNotEquals(beforeNewSeason, afterNewSeason);
	}
		
	@Test
	public void testGetRequiredTeams(){
		assertEquals(league.getRequiredNumTeams(), numberOfTeams);
	}
		
	@Test
	public void testGetRegisteredNumOfTeams() throws TeamException, LeagueException{
		makeTeams();
		assertEquals(league.getRegisteredNumTeams(), numberOfTeams);
	}
			
	@Test
	public void testGetRegisteredNumOfTeamsWithZeroTeams(){
		assertEquals(league.getRegisteredNumTeams(), noRegisteredTeams);
	}
		
	@Test
	public void testRemoveTeam() throws TeamException, LeagueException{
		makeTeams();
		assertEquals(league.getRegisteredNumTeams(), numberOfTeams);
		league.removeTeam(Team1);
		assertEquals(league.getRegisteredNumTeams(), numberOfTeams - 1);
	}
			
	@Test(expected = LeagueException.class)
	public void testRemoveTeamWithSeasonStarted() throws TeamException, LeagueException{
		makeTeams();
		playSomeMatches();
		league.removeTeam(Team3);
	}
		
	@Test(expected = LeagueException.class)
	public void testRemoveTeamOnATeamThatIsNotInTheLeague() throws TeamException, LeagueException{
		makeTeams();
		SoccerTeam newTeam = new SoccerTeam("Sculi", "Buli");
		league.removeTeam(newTeam);
	}
}
































