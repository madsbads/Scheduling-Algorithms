import java.util.*;

public class SJF implements Algorithm {
    private List<Task_Orig> queue;

    public SJF(List<Task_Orig> queue) {
        this.queue = queue;
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
            CPU.run(task, task.getBurst());
            currentTime += task.getBurst();
            task.setCompletionTime(currentTime);
            completedTasks.add(task);
            queue.remove(task);
        }

        calculateAndPrintMetrics(completedTasks);
    }

    @Override
    public Task_Orig pickNextTask() {
        return queue.stream().min(Comparator.comparingInt(Task_Orig::getBurst)).orElse(null);
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
