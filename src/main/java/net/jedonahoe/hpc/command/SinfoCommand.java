package net.jedonahoe.hpc.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.jedonahoe.hpc.Job;

public class SinfoCommand implements Command {

    private int totalNodes;
    private int idle;
    private int allocated = 0;
    private int down = 0;
    private int exitValue = 0;

    public String getOutput() {
        String sinfoOutput = "PARTITION AVAIL TIMELIMIT NODES STATE  NODELIST\n" + 
                             "batch     up     infinite      " + idle + " idle   adev[xx-xx]\n";
        if (this.allocated > 0)
            sinfoOutput += "batch     up     infinite      " + allocated + " alloc  adev[xx-xx]\n";
        if (this.down > 0)
            sinfoOutput += "batch     up     infinite      " + down + " down   adev[xx-xx]\n";
        return sinfoOutput;
    }

    public void setCluster(int totalNodes) {
        this.totalNodes = totalNodes;
        this.idle = totalNodes;
    }

    public int getExitValue() {
        return this.exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public void setJobs(List<Job> pending, List<Job> running, List<Job> completing) {
        List<Job> active = Stream.concat(running.stream(), completing.stream())
            .collect(Collectors.toList());
        this.allocated = 0;
        for (Job job: active) {
            this.allocated += job.getNodes();
        }
        this.idle = this.totalNodes - this.allocated;
    }


}
