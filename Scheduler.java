import java.util.ArrayList;
import java.util.List;

class Scheduler {
    private Simulator sm; // The simulator that holds processors and tasks

    // Constructor initializing the simulator
    Scheduler(Simulator sm) {
        this.sm = sm;
    }

    // Schedules tasks for processors at a given clock cycle
    // Schedules tasks for processors at a given clock cycle
    public void scheduleTasks(int clockCycle) {
        // Create and start threads for all processors
        Thread[] threads = createThreads(clockCycle);

        // Wait for all threads to finish executing
        waitForThreads(threads);
    }

    // Creates threads for each processor and starts them
    private Thread[] createThreads(int clockCycle) {
        Thread[] threads = new Thread[sm.processors.size()];

        for (int i = 0; i < sm.processors.size(); i++) {
            final Processor processor = sm.processors.get(i);
            threads[i] = new Thread(() -> executeTaskForProcessor(processor, clockCycle));  //
            threads[i].start();
        }
        return threads;
    }

    // Executes the task for a given processor at a specified clock cycle
    private void executeTaskForProcessor(Processor processor, int clockCycle) {
        if (processor.isAvailable(clockCycle)) {
            Task task = deque(); // Get a task from the queue
            if (task != null) {
                processor.executeTask(task, clockCycle); // Execute the task
            }
        }
    }

    // Waits for all threads to finish execution
    private void waitForThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join(); // Ensure all threads complete before proceeding
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interrupted exception
            }
        }
    }

    // Synchronized method to remove a task from the queue
    public synchronized Task deque() {
        return sm.removeTaskFromTheQueue(); // Returns the next task to execute
    }
}
