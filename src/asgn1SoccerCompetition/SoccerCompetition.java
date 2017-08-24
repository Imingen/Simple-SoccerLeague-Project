/**
 * 
 */
package asgn1SoccerCompetition;

import java.util.ArrayList;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;

/**
 * A class to model a soccer competition. The competition contains one or more number of leagues, 
 * each of which contain a number of teams. Over the course a season matches are played between
 * teams in each league. At the end of the season a premier (top ranked team) and wooden spooner 
 * (bottom ranked team) are declared for each league. If there are multiple leagues then relegation 
 * and promotions occur between the leagues.
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerCompetition implements SportsCompetition{
	//Specifies the name of the league
	private String name;
	//Specifies the number of leagues in this competition
	private int numLeagues;
	//Specifies the number of teams in each league
	private int numTeams;
	//Holds all the leagues in a competition
	private ArrayList<SoccerLeague> leagues;
	
	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues and number of teams in each league
	 * 
	 * @param name The name of the competition.
	 * @param numLeagues The number of leagues in the competition.
	 * @param numTeams The number of teams in each league.
	 */
	public SoccerCompetition(String name, int numLeagues, int numTeams){
		this.name = name;
		this.numLeagues = numLeagues;
		this.numTeams = numTeams;
		leagues = new ArrayList<>();
	
		for(int team = 0; team < numLeagues; team++){
			leagues.add(new SoccerLeague(numTeams));
		}
	}
	
	/**
	 * Retrieves a league with a specific number (indexed from 0). Returns an exception if the 
	 * league number is invalid.
	 * 
	 * @param leagueNum The number of the league to return.
	 * @return A league specified by leagueNum.
	 * @throws CompetitionException if the specified league number is less than 0.
	 *  or equal to or greater than the number of leagues in the competition.
	 */
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException{
		if(leagueNum < 0 || leagueNum >= numLeagues){
			throw new CompetitionException();
		}
		else{
			return leagues.get(leagueNum);
		}
	}
	

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	public void startSeason() {
		for(SoccerLeague league : leagues){
			try {
				league.startNewSeason();
			} catch (LeagueException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/** 
	 * Ends the season of each of the leagues in the competition. 
	 * If there is more than one league then it handles promotion
	 * and relegation between the leagues.  
	 * 
	 */
	public void endSeason()  {
		//if there is more than one league we have to 
		//handle relegation and promotion between the leagues
		if(numLeagues > 1){
			SoccerTeam tempWinI; 
			SoccerTeam tempLooseNext;
			//first we go trough all the leagues in this competition and ends the season
			//for each league
			for(SoccerLeague league : leagues){
				try {
					league.endSeason();
				} catch (LeagueException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			//We start at the end of the leagues arraylist and switch the higest scoring team
			//in the current index of the arraylist with the lowest scoring team in the index+1
			// in the array list, by first getting topteam and bottom team
			//then removing each team and finally register them in their new leagues.
			//The reason for starting at the end is so we dont encounter any problems when we move
			//the teams to another league. If we start at the top we will move the team with the 
			//least points down and this will be put at the last spot in this league and then be the 
			//bottom team in this league also. But if we start at the top we move the most winning team UP, 
			//and this team is at the bottom on this array, and we make sure we dont sort here 
			//so this teams stays at the bottom. 
			for(int teamIndexInLeague = leagues.size() - 1; teamIndexInLeague > 0 ; teamIndexInLeague--){
				try {
						tempLooseNext = leagues.get(teamIndexInLeague-1).getBottomTeam();
						tempWinI = leagues.get(teamIndexInLeague).getTopTeam();
						leagues.get(teamIndexInLeague-1).removeTeam(tempLooseNext);
						leagues.get(teamIndexInLeague).removeTeam(tempWinI);
						leagues.get(teamIndexInLeague-1).registerTeam(tempWinI);
						leagues.get(teamIndexInLeague).registerTeam(tempLooseNext);
				} catch (LeagueException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}	
		}
		//if there is only one league all we have to do is call the 
		//endseason() method for that league
		else{
			for(SoccerLeague league : leagues){
				try {
					league.endSeason();
				} catch (LeagueException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	/** 
	 * For each league displays the competition standings.
	 */
	public void displayCompetitionStandings(){
		System.out.println("+++++" + this.name + "+++++");
		
		// TO DO (optional)
		// HINT The heading for each league is
		for(int i = 0; i < leagues.size(); i++){
		System.out.println("---- League" + (i +1) + " ----");
		  System.out.println("Official Name" +  '\t' +  "Nick Name" + '\t' + "Form" + '\t' +  "Played" + '\t' + "Won" + '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t' + "Points");
		  leagues.get(i).displayLeagueTable();
		}
	}

}
