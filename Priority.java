import java.util.*;

public class Priority implements Algorithm
{
    private List<Task_Orig> queue;

    public Priority(List<Task_Orig> queue) {
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
        return queue.stream().min(Comparator.comparingInt(Task_Orig::getPriority)).orElse(null);
    }
}
