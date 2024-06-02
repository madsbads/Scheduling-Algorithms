import java.util.*;

public class FCFS implements Algorithm
{
    private List<Task_Orig> queue;

    public FCFS(List<Task_Orig> queue) {
        this.queue = queue;
    }

    @Override
    public void schedule() {
        while (!queue.isEmpty()) {
            Task_Orig task = pickNextTask();
            CPU.run(task, task.getBurst());
            queue.remove(task);
        }
    }

    @Override
    public Task_Orig pickNextTask() {
        return queue.get(0); // FCFS picks the first task in the list
    }
}
