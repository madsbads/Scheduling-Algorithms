import java.util.*;

public class PriorityRR implements Algorithm
{
    private List<Task_Orig> queue;
    private int timeSlice;

    public PriorityRR(List<Task_Orig> queue, int timeSlice) {
        this.queue = queue;
        this.timeSlice = timeSlice;
    }

    @Override
    public void schedule() {
        while (!queue.isEmpty()) {
            Task_Orig task = pickNextTask();
            int burst = task.getBurst();
            if (burst > timeSlice) {
                CPU.run(task, timeSlice);
                task.setBurst(burst - timeSlice);
                queue.add(task); // Re-add the task to the end of the queue
            } else {
                CPU.run(task, burst);
                queue.remove(task);
            }
        }
    }

    @Override
    public Task_Orig pickNextTask() {
        Task_Orig highestPriorityTask = queue.stream().min(Comparator.comparingInt(Task_Orig::getPriority)).orElse(null);
        queue.remove(highestPriorityTask);
        return highestPriorityTask;
    }
}
