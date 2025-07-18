import java.util.Arrays;
public class Processor{
    int numberClockCycles;
    boolean[] available;
    int processorId;

    Processor(int numberClockCycles, int processorId) {
        this.numberClockCycles = numberClockCycles;
        this.processorId = processorId;
        available = new boolean[numberClockCycles];
        for (int i = 0; i < numberClockCycles; i++) {
            available[i] = true;
        }
    }

    public boolean isAvailable(int clockCycle) {
        return available[clockCycle];
    }

    // Ececute Task t on the mentioned clock cycle
    public void executeTask(Task t, int clockCycle) {
        if (available[clockCycle]) {
            System.out.println("  ✅ Executing Task " + t.getTaskNumber() + " (Exec Time: " + t.getExecutionTime() + ") on cycle: " + (clockCycle + 1) + " [Processor " + processorId + "]");
            for (int i = clockCycle; i < clockCycle + t.getExecutionTime(); i++) {
                if (i < numberClockCycles) {
                    available[i] = false;
                } else {
                    break;
                }
            }
        } else {
            System.out.println("  ❌ Task " + t.getTaskNumber() + " can't execute on cycle: " + (clockCycle + 1) + " (Processor busy)");
        }
    }



}

