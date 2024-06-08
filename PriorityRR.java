import java.util.*;

public class PriorityRR implements Algorithm {
    private List<Task_Orig> queue;
    private int timeSlice;

    public PriorityRR(List<Task_Orig> queue, int timeSlice) {
        this.queue = queue;
        this.timeSlice = timeSlice;
    }

    @Override
    public void schedule() {
        int currentTime = 0;
        List<Task_Orig> completedTasks = new ArrayList<>();

        while (!queue.isEmpty()) {
            Task_Orig task = pickNextTask();
            if (task.getStartTime() == -1) {
                task.setStartTime(currentTime);
            }

            int runTime = Math.min(timeSlice, task.getBurst());
            CPU.run(task, runTime);
            currentTime += runTime;
            task.setBurst(task.getBurst() - runTime);

            if (task.getBurst() > 0) {
                // Task is not finished, re-add to the queue with updated priority
                queue.add(task);
            } else {
                // Task is finished
                task.setCompletionTime(currentTime);
                completedTasks.add(task);
                queue.remove(task); // Remove completed task from the queue
            }
        }

        // Calculate and print the metrics after scheduling
        calculateAndPrintMetrics(completedTasks);
    }

    @Override
    public Task_Orig pickNextTask() {
        // Priority-based task selection
        Task_Orig highestPriorityTask = null;
        for (Task_Orig task : queue) {
            if (highestPriorityTask == null || task.getPriority() < highestPriorityTask.getPriority()) {
                highestPriorityTask = task;
            }
        }
        return highestPriorityTask;
    }

    @Override
    public void calculateAndPrintMetrics(List<Task_Orig> completedTasks) {
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;

        for (Task_Orig task : completedTasks) {
            int turnaroundTime = task.getCompletionTime() - task.getArrivalTime();
            int waitingTime = turnaroundTime - task.getBurst();
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
