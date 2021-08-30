package net.jedonahoe.hpc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import net.jedonahoe.hpc.command.SinfoCommand;
import net.jedonahoe.hpc.command.SqueueCommand;

public class HPC implements Runnable {

    private int totalNodes;
    private List<Job> runningJobs = new ArrayList<Job>();
    private List<Job> pendingJobs = new ArrayList<Job>();
    private List<Job> completingJobs = new ArrayList<Job>();
    private Thread worker;
    private int interval = 1000;

    private final AtomicBoolean running = new AtomicBoolean(false);

    private double utilization = 0;

    private SqueueCommand squeue = new SqueueCommand();
    private SinfoCommand sinfo = new SinfoCommand();

    private boolean eventSimulated;
    
    public HPC(int totalNodes) {
        this.totalNodes = totalNodes;
        this.sinfo.setCluster(this.totalNodes);
    }

    public void start() {
        this.worker = new Thread(this);
        this.worker.start();
    }

    public void stop() {
        this.running.set(false);
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
        this.eventSimulated = true;
    }

    public void setSqueueFail() {
        this.squeue.setExitValue(127);
    }

    public void setSinfoFail() {
        this.squeue.setExitValue(127);
    }

    public void setHPCFail() {
        this.squeue.setExitValue(255);
        this.sinfo.setExitValue(255);
    }

    public void setJobs() {
        if (eventSimulated) {
            runningJobs.clear();
            double allocatedNodes = Math.floor((this.utilization / 100) * this.totalNodes);
            System.out.println("Supposed to be allocated " + (this.utilization / 100));
            System.out.println("allocated Nodes " + allocatedNodes + "\n");
            for (int i=0; i<allocatedNodes; i++) {
                Job job = new Job("user", 1, 1000);
                runningJobs.add(job);
            }
            this.squeue.setJobs(pendingJobs, runningJobs, completingJobs);
            this.sinfo.setJobs(pendingJobs, runningJobs, completingJobs);
            this.eventSimulated = false;
        }
    }

    public String getSqueue() {
        return this.squeue.getOutput();
    }

    public String getSinfo() {
        return this.sinfo.getOutput();
    }

    public void run() {
        running.set(true);
        while(running.get()) {
            try {
                this.setJobs();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }










    
}
