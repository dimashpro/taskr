package com.taskr.engine;

import com.taskr.domain.Task;
import com.taskr.service.TaskService;
import com.taskr.domain.TaskPriority;
import com.taskr.repository.TaskRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TaskrEngine {
    public static final int VIEW_TODAY_TASKS = 1;
    public static final int VIEW_ALL_TASKS = 2;
    public static final int ADD_TASK = 3;
    public static final int DELETE_TASK = 5;
    public static final int EXIT = -1;
    private static final int UPDATE_TASK = 4;
    private final TaskService taskService = new TaskService(new TaskRepository());

    private final Scanner scanner;

    public TaskrEngine() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean mainMenuRunning = true;
        do {
            printDoubleLineBoxedText("Taskr - Home");
            printSingleLineBoxedText("" +
                    "[" + VIEW_TODAY_TASKS + "] View today`s tasks\n" +
                    "[" + VIEW_ALL_TASKS + "] View all tasks\n" +
                    "[" + ADD_TASK + "] Add task\n" +
                    "[" + UPDATE_TASK + "] Update task\n" +
                    "[" + DELETE_TASK + "] Delete task\n\n" +
                    "[" + EXIT + "] Exit");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case VIEW_TODAY_TASKS:
                    enterViewTodayTasksMenu();
                    break;
                case VIEW_ALL_TASKS:
                    enterViewAllTasksMenu();
                    break;
                case ADD_TASK:
                    enterAddTaskMenu();
                    break;
                case UPDATE_TASK:
                    enterUpdateTaskMenu();
                    break;
                case DELETE_TASK:
                    enterDeleteTaskMenu();
                    break;
                case EXIT:
                    printSingleLineBoxedText("Bye, bye!");
                    mainMenuRunning = false;
                    break;
                default:
                    System.out.println("  Wrong option... [" + selectedOption + "]");
                    break;
            }

        } while (mainMenuRunning);
    }

    private void enterUpdateTaskMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Taskr - Update task");
            printTopCorneredSingleLine(160);
            System.out.println("  Pick the task you want to update : \n");
            List<Task> allTasks = taskService.getAll();
            for (int i = 0; i < allTasks.size(); i++) {
                System.out.println("  [" + i + "] " + allTasks.get(i));
            }
            System.out.println("\n  [-1] Back");
            printBottomCorneredSingleLine(160);
            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
            else if (innerSelectedOption >= 0 && innerSelectedOption < allTasks.size()) {
                scanner.nextLine();
                printTopCorneredSingleLine(160);
                System.out.println("  Please complete the following form, leave empty if no change is required :");

                System.out.print("   - Title : ");
                String title = scanner.nextLine();

                System.out.print("   - Description : ");
                String description = scanner.nextLine();

                System.out.print("   - Priority (HIGH/MEDIUM/LOW) : ");
                String stringPriority = scanner.nextLine();
                TaskPriority taskPriority = null;
                if (!stringPriority.trim().isEmpty())
                    taskPriority = TaskPriority.valueOf(stringPriority);

                System.out.print("   - Start date (yyyy-MM-dd) : ");
                String stringStartDate = scanner.nextLine();
                LocalDate startDate = null;
                if (!stringStartDate.trim().isEmpty())
                    startDate = LocalDate.parse(stringStartDate);

                System.out.print("   - End date (yyyy-MM-dd) : ");
                String stringEndDate = scanner.nextLine();
                LocalDate endDate = null;
                if (!stringEndDate.trim().isEmpty())
                    endDate = LocalDate.parse(stringEndDate);

                System.out.print("   - Tags (a, a1, a2 ... n): ");
                String stringTags = scanner.nextLine();
                List<String> tags = null;
                if (!stringTags.trim().isEmpty())
                    tags = Arrays.asList(stringTags.split(", "));

                Task task = new Task(title, description, tags, taskPriority, startDate, endDate);
                task.setId(allTasks.get(innerSelectedOption).getId());

                taskService.save(task);
                System.out.println("  Successfully updated : ");
                System.out.println(task);
                printBottomCorneredSingleLine(160);
            } else {
                System.out.println("  Wrong option... [" + innerSelectedOption + "]");
            }

        } while (innerMenuRunning);
    }

    private void enterViewTodayTasksMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Taskr - View today`s tasks");
        //    String todayTasks = taskService.getForToday().stream().map(t -> "\n" + t.toString()).reduce("", String::concat);
            String todayTasks = taskService.getForToday().toString();

            printSingleLineBoxedText("Here are your tasks for :" + LocalDate.now() + todayTasks + "\n\n[-1] Back");
            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
        } while (innerMenuRunning);
    }

    private void enterViewAllTasksMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Taskr - View all tasks");
           // String allTasks = taskService.getAll().stream().map(t -> "\n" + t.toString()).reduce("", String::concat);
            String allTasks = taskService.getAll().toString();

            printSingleLineBoxedText("Here are your tasks :" + allTasks + "\n\n[-1] Back");
            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
        } while (innerMenuRunning);
    }

    private void enterDeleteTaskMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Taskr - Delete task");
            printTopCorneredSingleLine(160);
            System.out.println("  Pick the task you want to delete : \n");
            List<Task> allTasks = taskService.getAll();
            for (int i = 0; i < allTasks.size(); i++) {
                System.out.println("  [" + i + "] " + allTasks.get(i));
            }
            System.out.println("\n  [-1] Back");
            printBottomCorneredSingleLine(160);

            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
            else if (innerSelectedOption >= 0 && innerSelectedOption < allTasks.size()) {
                Task task = allTasks.get(innerSelectedOption);
                taskService.delete(task);
                printSingleLineBoxedText("  Task [" + innerSelectedOption + "] successfully deleted.");
            } else {
                System.out.println("  Wrong option... [" + innerSelectedOption + "]");
            }

        } while (innerMenuRunning);
    }

    private void enterAddTaskMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Taskr - Add task");
            printSingleLineBoxedText("[1] Complete the form\n\n[-1] Back");

            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
            else if (innerSelectedOption == 1) {
                scanner.nextLine();
                printTopCorneredSingleLine(160);
                System.out.println("  Please complete the following form, leave empty for null :");

                System.out.print("   - Title : ");
                String title = scanner.nextLine();

                System.out.print("   - Description : ");
                String description = scanner.nextLine();

                System.out.print("   - Priority (HIGH/MEDIUM/LOW) : ");
                String stringPriority = scanner.nextLine();
                TaskPriority taskPriority = null;
                if (!stringPriority.trim().isEmpty())
                    taskPriority = TaskPriority.valueOf(stringPriority);

                System.out.print("   - Start date (yyyy-MM-dd) : ");
                String stringStartDate = scanner.nextLine();
                LocalDate startDate = null;
                if (!stringStartDate.trim().isEmpty())
                    startDate = LocalDate.parse(stringStartDate);

                System.out.print("   - End date (yyyy-MM-dd) : ");
                String stringEndDate = scanner.nextLine();
                LocalDate endDate = null;
                if (!stringEndDate.trim().isEmpty())
                    endDate = LocalDate.parse(stringEndDate);

                System.out.print("   - Tags (a, a1, a2 ... n): ");
                String stringTags = scanner.nextLine();
                List<String> tags = null;
                if (!stringTags.trim().isEmpty())
                    tags = Arrays.asList(stringTags.split(", "));

                Task task = new Task(title, description, tags, taskPriority, startDate, endDate);
                taskService.save(task);
                System.out.println("  Successfully saved : ");
                System.out.println(task);
                printBottomCorneredSingleLine(160);
            } else {
                System.out.println("  Wrong option... [" + innerSelectedOption + "]");
            }

        } while (innerMenuRunning);
    }

    private void printSingleLineBoxedText(String text) {
        if (text.contains("\n")) {
            String[] split = text.split("\n");
            String[] sorted = split.clone();
            Arrays.sort(sorted, Comparator.comparingInt(String::length).reversed());

            printTopCorneredSingleLine(sorted[0].length());
            for (int i = 0; i < split.length; i++) {
                System.out.println(" " + split[i]);
            }
            printBottomCorneredSingleLine(sorted[0].length());

        } else {
            printTopCorneredSingleLine(text.length());
            System.out.println(" " + text);
            printBottomCorneredSingleLine(text.length());
        }
    }

    private void printBottomCorneredSingleLine(int length) {
        System.out.print("\u2516");
        for (int i = 0; i < length + 1; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u251A");
    }

    private void printTopCorneredSingleLine(int length) {
        System.out.print("\u250E");
        for (int i = 0; i < length + 1; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2512");
    }

    private void printDoubleLineBoxedText(String text) {
        if (text.contains("\n")) {
            String[] split = text.split("\n");
            String[] sorted = split.clone();
            Arrays.sort(sorted, Comparator.comparingInt(String::length).reversed());

            printTopDoubleCorneredLine(sorted[0].length());
            for (int i = 0; i < split.length; i++) {
                System.out.println(" " + split[i]);
            }
            printBottomDoubleCorneredLine(sorted[0].length());

        } else {
            printTopDoubleCorneredLine(text.length());
            System.out.println(" " + text + " ");
            printBottomDoubleCorneredLine(text.length());
        }
    }

    private void printBottomDoubleCorneredLine(int length) {
        System.out.print("\u255A");
        for (int i = 0; i < length; i++) {
            System.out.print("\u2550");
        }
        System.out.println("\u255D");
    }

    private void printTopDoubleCorneredLine(int length) {
        System.out.print("\u2554");
        for (int i = 0; i < length; i++) {
            System.out.print("\u2550");
        }
        System.out.println("\u2557");
    }
    public static void main(String[] args) {
        new TaskrEngine().run();
    }
}

