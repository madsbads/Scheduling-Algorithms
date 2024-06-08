# Scheduling Algorithms
1. First-come, first-served (FCFS), which schedules tasks in the order in which they request the CPU.
2. Shortest-job-first (SJF), which schedules tasks in order of the length of the tasks’ next CPU burst.
3. Priority scheduling, which schedules tasks based on priority.
4. Round-robin scheduling, where each task is run for a time quantum (or for the remainder of its CPU burst).
5. Priority with round-robin, which schedules tasks in order of priority and uses round-robin scheduling for tasks with equal priority.

Compiled on macOS using Java SE Runtime Environment (build 22.0.1+8-16) 

[`Atomic Integer Documentation`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)

To run the program, use command line: `java Driver <<algorithm> <schedule> [timeslice if RR]`
