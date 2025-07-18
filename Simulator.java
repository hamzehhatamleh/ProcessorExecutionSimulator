
import java.util.*;
import java.io.*;



import java.util.*;
import java.io.*;
public class Simulator {
    int numberProcessors;
    int numberClockCycles;
    String path; // path to text file that contains tasks infos
    Queue<Task> taskQueue; // Queue to add the tasks to
    List<Processor> processors; // our processors
    private static int taskCounter=1;


    Simulator(int numberProcessors , int numberClockCycles , String path) throws FileNotFoundException {
        this.numberProcessors = numberProcessors;
        this.numberClockCycles = numberClockCycles;
        this.path = path;
        this.taskQueue = new LinkedList<>();
        this.processors = new ArrayList<>();

        for (int i = 0; i < numberProcessors; i++) {
            processors.add(new Processor(numberClockCycles,i+1)); // Initialize processors
        }
    }


    // simulate the full process
    public void simulate() throws IOException {
        Scheduler scheduler = new Scheduler(this);
        for (int cycle = 0; cycle < numberClockCycles; cycle++) {
            System.out.println("\n--- Cycle: " + (cycle + 1) + " ---");
            HandleTextFile(cycle);
            sortTasksInQueue();

            System.out.print("Tasks in Queue: ");
            if (taskQueue.isEmpty()) {
                System.out.println("None");
            } else {
                taskQueue.forEach(task -> System.out.print(task.getTaskNumber() + " "));
                System.out.println();
            }
            scheduler.scheduleTasks(cycle);

        }
    }



    public void addTaskToTheQueue(Task T){
        taskQueue.add(T);
    }



    public Task removeTaskFromTheQueue() {
        if (!taskQueue.isEmpty()) {
            return taskQueue.poll(); // Removes and returns the first task in the queue
        }
        return null; // If the queue is empty, return null
    }


    public void HandleTextFile(int clockCycle) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(path));
        String line;
        while ((line = bf.readLine()) != null) {


            // Assuming the file has values separated by spaces or commas
            String[] parts = line.split(" ");

            if (parts.length >= 3) { // Ensure at least 3 elements exist
                int creationTime = Integer.parseInt(parts[0]);

                if(creationTime == clockCycle+1){

                    int executionTime = Integer.parseInt(parts[1]);
                    int priority = Integer.parseInt(parts[2]);
                    Task t = new Task(creationTime, executionTime, priority, taskCounter);
                    addTaskToTheQueue(t);
                    taskCounter++;
                }
            }
        }
    }

    // this function will sort the queue to dequeue easily
    public void sortTasksInQueue() {
        List<Task> taskList = new ArrayList<>(taskQueue); // Convert queue to list

        taskList.sort(Comparator
                .comparingInt(Task::getPriority)           // 1. Sort by priority (1 before 0)
                .thenComparingInt(Task::getExecutionTime)// 2. Then sort by execution time (longer first)
                .reversed()
        );

        // Clear the queue and add sorted tasks back
        taskQueue.clear();
        taskQueue.addAll(taskList);
    }





    public int getNumberProcessors() {
        return numberProcessors;
    }
}

