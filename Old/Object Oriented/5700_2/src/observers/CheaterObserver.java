package observers;

import java.util.Observable;
import java.util.Observer;

import race.Race;
import race.RaceGroup;
import race.Racer;

public class CheaterObserver implements Observer {
	private Race race;
	
	public CheaterObserver(Race race){
		this.race = race;
	}
	
	@Override //THIS BE UGLY AF - ON MY TO DO LIST TO FIX
	public void update(Observable o, Object arg) {
		race = (Race)o;
		for(RaceGroup group : race.getGroups()){
			for(Racer racer : group.getRacers()){
				int numOfPassedCheckpoint = racer.getPassedCheckpoints().size();
				for(RaceGroup otherGroup : race.getGroups()){
					if(racer.getGroup() != otherGroup){
						for(Racer otherRacer : otherGroup.getRacers()){
							for(int i = 0; i < numOfPassedCheckpoint; i++)
								if((Integer.parseInt(racer.getPassedCheckpoints().get(i).getTime()) 
										- Integer.parseInt(otherRacer.getPassedCheckpoints().get(i).getTime())) < 3000)
									if(i < numOfPassedCheckpoint - 1)
										if((Integer.parseInt(racer.getPassedCheckpoints().get(i+1).getTime()) 
												- Integer.parseInt(otherRacer.getPassedCheckpoints().get(i+1).getTime())) < 3000){
											if(!race.getCheaters().contains(racer))
												race.addCheater(racer);
											if(!race.getCheaters().contains(otherRacer))
												race.addCheater(otherRacer);
										}	
						}
					}
				}
				for(int i = 0; i < numOfPassedCheckpoint; i++){
					if(racer.getPassedCheckpoints().get(i).getSensor().getId() != 0)//Checks if a racer skipped a checkpoint
						race.addCheater(racer);
				}
			}
		}
		
	}

}
