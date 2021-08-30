package net.jedonahoe.hpc;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class HPCSimulator 
{
    public static void main( String[] args )
    {
        HPC hpc = new HPC(16);
        hpc.start();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            printSimulatorMenu();
            int menuItem = Integer.parseInt(userInput.nextLine());

            if (menuItem == 1) {
                hpc.setUtilization(100);
                System.out.println("\nSet system utilization to 100%\n");
            }
            if (menuItem == 2) {
                hpc.setUtilization(50);
                System.out.println("\nSet system utilization to 50%\n");
            }
            if (menuItem == 3) {
                hpc.setUtilization(5);
                System.out.println("\nSet system utilization to 5%\n");
            }

            if (menuItem == 4) {
                System.out.println("\nsinfo:");
                System.out.println(hpc.getSinfo() + "\n");
            }
                
            if (menuItem == 5) {
                System.out.println("\nsqueue:");
                System.out.println(hpc.getSqueue() + "\n");
            }

            if (menuItem == 6) {
                hpc.stop();
                System.out.println("\nBye Bye");
                break;
            }
        }
        userInput.close();
    }

    public static void printSimulatorMenu() {
        System.out.println("HPC Simulator Menu:\n");
        System.out.println("[1] HPC Uilization OK");
        System.out.println("[2] HPC Utilization WARNING");
        System.out.println("[3] HPC Utilization CRITICAL");
        System.out.println("[4] Sinfo");
        System.out.println("[5] Squeue");
        System.out.println("[6] Exit\n");
    }
}
