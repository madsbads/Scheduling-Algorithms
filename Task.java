import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private int taskId;
    private int priority;
    private int burstTime;
    private int remainingTime;
    private int waitingTime;
    private int turnaroundTime;
    private int responseTime;

    public Task(int priority, int burstTime) {
        this.taskId = idGenerator.getAndIncrement();
        this.priority = priority;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.responseTime = -1;  // Initialize to -1 to denote not yet started
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
        this.remainingTime = burstTime; // Update remaining time accordingly
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "Task(ID=" + taskId + ", Priority=" + priority + ", Burst=" + burstTime + ")";
    }
}
