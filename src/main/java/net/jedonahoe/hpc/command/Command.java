package net.jedonahoe.hpc.command;

import java.util.List;

import net.jedonahoe.hpc.Job;

public interface Command {

    public String getOutput();

    public int getExitValue();

    public void setExitValue(int exitValue);

    public void setJobs(List<Job> pending, List<Job> running, List<Job> completing);
}
