package net.jedonahoe.hpc;

public class Job {

    private String user;
    private int nodes;
    private long duration;
    private long startTime;

    public Job(String user, int nodes, long duration) {
        this.user = user;
        this.nodes = nodes;
        this.duration = duration;
    }

    public String getUser() {
        return this.user;
    }

    public int getNodes() {
        return this.nodes;
    }

    
    
}
