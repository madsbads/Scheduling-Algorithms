/**
 * Driver.java
 * 
 * Demonstrates different scheduling algorithms.
 * 
 * Usage:
 *  
 *  java Driver <schedule> <algorithm>
 *
 * where 
 *  schedule is schedule of tasks
 *
 *  algorithm = [FCFS, SJF, PRI, RR, PRI-RR]
 */
  
 import java.io.*;
 import java.util.*;
 
 public class Driver
 {
     public static void main(String[] args) throws IOException {
         if (args.length < 2 || args.length > 3) {
             System.err.println("Usage: java Driver <algorithm> <schedule> [timeslice]");
             System.exit(0);
         }
 
         BufferedReader inFile = new BufferedReader(new FileReader(args[1]));
 
         String schedule;
 
         // create the queue of tasks
         List<Task_Orig> queue = new ArrayList<>();
 
         // read in the tasks and populate the ready queue        
         while ( (schedule = inFile.readLine()) != null) {
             String[] params = schedule.split(",\\s*");
             queue.add(new Task_Orig(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
         }
 
         inFile.close();
         
         Algorithm scheduler = null;
         String choice = args[0].toUpperCase();
 
         switch(choice) {
             case "FCFS":
                 scheduler = new FCFS(queue);
                 break;
             case "SJF":
                 scheduler = new SJF(queue);
                 break;
             case "PRI":
                 scheduler = new Priority(queue);
                 break;
             case "RR":
                 if (args.length != 3) {
                     System.err.println("Usage: java Driver RR <schedule> <timeslice>");
                     System.exit(0);
                 }
                 int rrTimeSlice = Integer.parseInt(args[2]);
                 scheduler = new RR(queue, rrTimeSlice);
                 break;
             case "PRI-RR":
                 if (args.length != 3) {
                     System.err.println("Usage: java Driver PRI-RR <schedule> <timeslice>");
                     System.exit(0);
                 }
                 int priRRTimeSlice = Integer.parseInt(args[2]);
                 scheduler = new PriorityRR(queue, priRRTimeSlice);
                 break;
             default:
                 System.err.println("Invalid algorithm");
                 System.exit(0);
         }
 
         // start the scheduler
         scheduler.schedule();
     }
 }
 