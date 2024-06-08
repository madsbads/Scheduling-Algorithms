/**
 * Task to be scheduled by the scheduling alogrithm.
 *
 * Each task is represented by
 *
 *  String name - a task name, not necessarily unique
 *
 *  int tid - unique task identifier
 *
 *  int priority - the relative priority of a task where a higher number indicates
 *  higher relative priority.
 *
 *  int burst - the CPU burst of this this task
 * 
 *  Edited to include var and setter/getters for metrics
 */

 import java.util.concurrent.atomic.AtomicInteger;

 public class Task_Orig {
     private String name;
     private int tid;
     private int priority;
     private int burst;
 
     // New fields for metrics calculation
     private int startTime = -1; // -1 indicates not started yet
     private int completionTime;
     private int arrivalTime; 
 
     private static AtomicInteger tidAllocator = new AtomicInteger();
 
     public Task_Orig(String name, int priority, int burst) {
         this.name = name;
         this.priority = priority;
         this.burst = burst;
         this.tid = tidAllocator.getAndIncrement();
         this.arrivalTime = 0; // Setting arrival time as 0 for all tasks for simplicity
     }
 
     // Getters and setters for the new fields
     public int getStartTime() {
         return startTime;
     }
 
     public void setStartTime(int startTime) {
         this.startTime = startTime;
     }
 
     public int getCompletionTime() {
         return completionTime;
     }
 
     public void setCompletionTime(int completionTime) {
         this.completionTime = completionTime;
     }
 
     public int getArrivalTime() {
         return arrivalTime;
     }
 
     // Other getters and setters
     public String getName() {
         return name;
     }
 
     public int getTid() {
         return tid;
     }
 
     public int getPriority() {
         return priority;
     }
 
     public int getBurst() {
         return burst;
     }
 
     public void setBurst(int burst) {
         this.burst = burst;
     }
 
     @Override
     public boolean equals(Object other) {
         if (other == this)
             return true;
 
         if (!(other instanceof Task_Orig))
             return false;
 
         Task_Orig rhs = (Task_Orig)other;
         return this.tid == rhs.tid;
     }
 
     @Override
     public String toString() {
         return "\n" + "Name: " + name + ", Tid: " + tid + ", Priority: " + priority + ", Burst: " + burst;
     }
 }
 