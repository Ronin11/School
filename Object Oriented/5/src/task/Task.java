package task;

import java.util.ArrayList;

import engineer.Engineer;

public class Task {
	private ArrayList<Task> subtasks = new ArrayList<Task>();
	private Task prevTask;
	private ArrayList<Engineer> assignedEngineers = new ArrayList<Engineer>();
	private String label;
	private String description;
	private float originalEstimatedHours, revisedEstimatedHours, percentComplete, 
	estimatedRemainingHours, estimatedRemainingWorkdays;
	private int id;
	
	//This should insure that each id is unique.
	private static int newId = 0;
	
	
	public Task(){
		id = newId++;
	}
	
	public boolean isLeaf(){return subtasks.isEmpty();}
	public ArrayList<Task> getSubtasks(){return subtasks;}
	public void addSubtask(Task t){subtasks.add(t);}
	
	public Task getPrevTask(){return prevTask;}
	public void setPrevTask(Task t){prevTask = t;}

	public ArrayList<Engineer> getAssignedEngineers(){return assignedEngineers;}
	public boolean addEngineerToTask(Engineer e){return assignedEngineers.add(e);}
	public boolean removeEngineerToTask(Engineer e){return assignedEngineers.remove(e);}
	public boolean addTeamToTask(ArrayList<Engineer> team){return assignedEngineers.addAll(team);}
	
	public String getLabel(){return label;}
	public void setLabel(String s){label = s;}
	
	public String getDescription(){return description;}
	public void setDescription(String s){description = s;}
	
	public float getOriginalEstimatedHours(){
		if(this.isLeaf())
			return originalEstimatedHours;
		else{
			float sum = 0;
			for(Task t : subtasks)
				sum += t.getOriginalEstimatedHours();
			return sum;
		}
	}
	public void setOriginalEstimatedHours(float f){originalEstimatedHours = f;}
	
	public float getRevisedEstimatedHours(){
		if(this.isLeaf())
			return revisedEstimatedHours;
		else{
			float sum = 0;
			for(Task t : subtasks)
				sum += t.getRevisedEstimatedHours();
			return sum;
		}
	}
	public void setRevisedEstimatedHours(float f){revisedEstimatedHours = f;}
	
	public float getPercentComplete(){
		return percentComplete;
	}
	public void setPercentComplete(float f){percentComplete = f;}
	
	public float getEstimatedRemainingHours(){
		if(this.isLeaf())
			return estimatedRemainingHours;
		else{
			float sum = 0;
			for(Task t : subtasks)
				sum += t.getEstimatedRemainingHours();
			return sum;
		}
	}
	public void setEstimatedRemainingHours(float f){estimatedRemainingHours = f;}
	
	public float getEstimatedRemainingWorkdays(){
		if(this.isLeaf())
			return estimatedRemainingWorkdays;
		else{
			float sum = 0;
			for(Task t : subtasks)
				sum += t.getEstimatedRemainingHours();
			return sum;
		}
	}
	public void setEstimatedREmainingWorkdays(float f){estimatedRemainingWorkdays = f;}
	
	public int getId(){return id;}
}
