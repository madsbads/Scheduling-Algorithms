import java.util.*;

public class Scheduler {
    private List<Task> tasks;
    private int timeQuantum;

    public Scheduler(List<Task> tasks, int timeQuantum) {
        this.tasks = new ArrayList<>(tasks);
        this.timeQuantum = timeQuantum;
    }

    public List<Task> fcfs() {
        // First-Come, First-Served (FCFS) Scheduling
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(Task::getTaskId));
        return scheduleTasks(sortedTasks);
    }

    public List<Task> sjf() {
        // Shortest-Job-First (SJF) Scheduling
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(Task::getBurstTime));
        return scheduleTasks(sortedTasks);
    }

    public List<Task> priorityScheduling() {
        // Priority Scheduling
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(Task::getPriority).reversed());
        return scheduleTasks(sortedTasks);
    }

    public List<Task> roundRobin() {
        // Round-Robin Scheduling
        Queue<Task> taskQueue = new LinkedList<>(tasks);
        List<Task> result = new ArrayList<>();
        int time = 0;
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            if (task.getResponseTime() == -1) {
                task.setResponseTime(time);
            }
            if (task.getRemainingTime() > timeQuantum) {
                task.setRemainingTime(task.getRemainingTime() - timeQuantum);
                time += timeQuantum;
                taskQueue.add(task);
            } else {
                time += task.getRemainingTime();
                task.setTurnaroundTime(time);
                task.setRemainingTime(0);
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> priorityWithRoundRobin() {
        // Priority with Round-Robin Scheduling
        List<Task> sortedByPriority = new ArrayList<>(tasks);
        sortedByPriority.sort(Comparator.comparingInt(Task::getPriority).reversed());
        List<Task> result = new ArrayList<>();
        int time = 0;

        while (!sortedByPriority.isEmpty()) {
            int currentPriority = sortedByPriority.get(0).getPriority();
            Queue<Task> taskQueue = new LinkedList<>();
            for (Iterator<Task> it = sortedByPriority.iterator(); it.hasNext(); ) {
                Task task = it.next();
                if (task.getPriority() == currentPriority) {
                    taskQueue.add(task);
                    it.remove();
                }
            }
            while (!taskQueue.isEmpty()) {
                Task task = taskQueue.poll();
                if (task.getResponseTime() == -1) {
                    task.setResponseTime(time);
                }
                if (task.getRemainingTime() > timeQuantum) {
                    task.setRemainingTime(task.getRemainingTime() - timeQuantum);
                    time += timeQuantum;
                    taskQueue.add(task);
                } else {
                    time += task.getRemainingTime();
                    task.setTurnaroundTime(time);
                    task.setRemainingTime(0);
                    result.add(task);
                }
            }
        }
        return result;
    }

    private List<Task> scheduleTasks(List<Task> sortedTasks) {
        int time = 0;
        for (Task task : sortedTasks) {
            if (task.getResponseTime() == -1) {
                task.setResponseTime(time);
            }
            task.setWaitingTime(time);
            time += task.getBurstTime();
            task.setTurnaroundTime(time);
        }
        return sortedTasks;
    }

    public void displayResults(List<Task> tasks) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalResponseTime = 0;

        System.out.println("Task ID\tWaiting Time\tTurnaround Time\tResponse Time");
        for (Task task : tasks) {
            System.out.println(task.getTaskId() + "\t" + task.getWaitingTime() + "\t\t" + task.getTurnaroundTime() + "\t\t" + task.getResponseTime());
            totalWaitingTime += task.getWaitingTime();
            totalTurnaroundTime += task.getTurnaroundTime();
            totalResponseTime += task.getResponseTime();
        }

        int numTasks = tasks.size();
        System.out.println("Average Waiting Time: " + totalWaitingTime / numTasks);
        System.out.println("Average Turnaround Time: " + totalTurnaroundTime / numTasks);
        System.out.println("Average Response Time: " + totalResponseTime / numTasks);
    }

    public static void main(String[] args) {
        List<Task> tasks = Arrays.asList(
            new Task(2, 20),
            new Task(1, 10),
            new Task(4, 5),
            new Task(3, 15)
        );

        Scheduler scheduler = new Scheduler(tasks, 10);

        System.out.println("FCFS Scheduling:");
        List<Task> fcfsTasks = scheduler.fcfs();
        scheduler.displayResults(fcfsTasks);

        System.out.println("\nSJF Scheduling:");
        List<Task> sjfTasks = scheduler.sjf();
        scheduler.displayResults(sjfTasks);

        System.out.println("\nPriority Scheduling:");
        List<Task> priorityTasks = scheduler.priorityScheduling();
        scheduler.displayResults(priorityTasks);

        System.out.println("\nRound-Robin Scheduling:");
        List<Task> rrTasks = scheduler.roundRobin();
        scheduler.displayResults(rrTasks);

        System.out.println("\nPriority with Round-Robin Scheduling:");
        List<Task> priorityRrTasks = scheduler.priorityWithRoundRobin();
        scheduler.displayResults(priorityRrTasks);
    }
}
