package net.jedonahoe.hpc.command;

import java.util.List;

import net.jedonahoe.hpc.Job;

public class SqueueCommand implements Command {

    private int exitValue = 0;

    public String getOutput() {
        return "output";
    }

    public int getExitValue() {
        return this.exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public void setJobs(List<Job> pending, List<Job> running, List<Job> completing) {
        
    }



        


    
    
}
