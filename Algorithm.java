import java.util.List;

/**
 * Interface representing a generic scheduling algorithm.
 *
 * @author Greg Gagne - March 2016
 */

public interface Algorithm
{
    /**
     * Invokes the scheduler
     */
    public abstract void schedule();

    /**
     * Selects the next task using the appropriate scheduling algorithm
     */
    public abstract Task_Orig pickNextTask();

    /**
     * Calculate metrics
     */  
    void calculateAndPrintMetrics(List<Task_Orig> completedTasks);
    
}
