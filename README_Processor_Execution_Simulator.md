# Processor Execution Simulator

## Overview

This simulator models the execution of tasks on a fixed number of synchronized processors over a defined number of clock cycles.

## Features

- Fixed number of processors with unique IDs (P1, P2, ...)
- Tasks with creation time, execution time, and priority (high or low)
- Task queue with priority-based scheduling
- Scheduling rules:
  - High-priority tasks assigned first
  - Ties broken by longest execution time, then random choice
- Non-preemptive task execution
- Immediate assignment if processor available at task creation
- Cycle-by-cycle console report showing task creation, execution, completion, and processor status

## Input

- Number of processors
- Total clock cycles
- Path to a text file describing tasks (creation time, execution time, priority)

## Output

- Readable console report per cycle showing key events and processor states
