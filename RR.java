import java.util.*;

public class RR implements Algorithm {
    private List<Task_Orig> queue;
    private int timeSlice;

    public RR(List<Task_Orig> queue, int timeSlice) {
        this.queue = queue;
        this.timeSlice = timeSlice;
    }

    @Override
    public void schedule() {
        int currentTime = 0;
        List<Task_Orig> completedTasks = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            Task_Orig task = queue.remove(0); // Get the first task in the queue
            if (task.getStartTime() == -1) {
                task.setStartTime(currentTime); // Set start time if not already set
            }

            int runTime = Math.min(timeSlice, task.getBurst());
            CPU.run(task, runTime);
            currentTime += runTime;
            task.setBurst(task.getBurst() - runTime);

            if (task.getBurst() > 0) {
                // Task is not finished, re-add to the queue
                queue.add(task);
            } else {
                // Task is finished
                task.setCompletionTime(currentTime);
                completedTasks.add(task);
            }
        }

        calculateAndPrintMetrics(completedTasks);
    }

    @Override
    public Task_Orig pickNextTask() {
        return queue.get(0); // RR simply picks the next task in the queue
    }

    @Override
    public void calculateAndPrintMetrics(List<Task_Orig> completedTasks) {
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;

        for (Task_Orig task : completedTasks) {
            int turnaroundTime = task.getCompletionTime() - task.getArrivalTime();
            int waitingTime = turnaroundTime - (task.getBurst() + (task.getCompletionTime() - task.getStartTime()));
            int responseTime = task.getStartTime() - task.getArrivalTime();

            totalTurnaroundTime += turnaroundTime;
            totalWaitingTime += waitingTime;
            totalResponseTime += responseTime;

            System.out.println("Task " + task.getName() + " - Turnaround Time: " + turnaroundTime +
                               ", Waiting Time: " + waitingTime + ", Response Time: " + responseTime);
        }

        int numTasks = completedTasks.size();
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / numTasks);
        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / numTasks);
        System.out.println("Average Response Time: " + (double) totalResponseTime / numTasks);
    }
}
