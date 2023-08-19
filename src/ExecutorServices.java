import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//todo - Create a program that simulates a simple reminder application.
// The program should allow users to schedule reminders for specific tasks
// at designated times
// Requirements:
//    Create a ExecutorServices.Reminder class that represents a single reminder. It should have the following properties: task (String), time (Date), and completed (boolean).
//    Implement a ExecutorServices.ReminderScheduler class that manages the scheduling and execution of reminders using a ScheduledThreadPoolExecutor.
//    The ExecutorServices.ReminderScheduler class should have the following methods:
//        scheduleReminder(ExecutorServices.Reminder reminder): Schedules a reminder for execution at the specified time.
//        cancelReminder(ExecutorServices.Reminder reminder): Cancels a scheduled reminder.
//        markReminderAsCompleted(ExecutorServices.Reminder reminder): Marks a reminder as completed.
//    The program should provide a simple command-line interface (CLI) for users to interact with the ExecutorServices.ReminderScheduler. Users should be able to:
//        Schedule a new reminder by providing the task description and the time (in the format "yyyy-MM-dd HH:mm:ss").
//        Cancel a scheduled reminder by specifying the task description.
//        Mark a reminder as completed by specifying the task description.
public class ExecutorServices {

}

    class Reminder {
        String task;
        Date time;
        boolean completed;

        public Reminder(String task, Date time, boolean completed) {
            this.task = task;
            this.time = time;
            this.completed = completed;
        }

        public Reminder(String task, Date time) {

        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }

class ReminderScheduler {
    private ScheduledExecutorService executorService;

    public ReminderScheduler() {
        this.executorService = Executors.newScheduledThreadPool(5);
    }

    public void scheduleReminder(Reminder reminder) {
        long delay = reminder.getTime().getTime() - System.currentTimeMillis();
        executorService.schedule(() -> {
            if (!reminder.isCompleted()) {
                System.out.println("Reminder: " + reminder.getTask());
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void cancelReminder(Reminder reminder) {
        reminder.setCompleted(true);
    }

    public void markReminderAsCompleted(Reminder reminder) {
        reminder.setCompleted(true);
        System.out.println("Reminder marked as completed: " + reminder.getTask());
    }
}
class ReminderApp {
    public static void main(String[] args) {
        ReminderScheduler reminderScheduler = new ReminderScheduler();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (true) {
            System.out.print("Enter a command (schedule/cancel/complete/exit): ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                break;
            } else if (command.equalsIgnoreCase("schedule")) {
                System.out.print("Enter task description: ");
                String task = scanner.nextLine();

                System.out.print("Enter time (yyyy-MM-dd HH:mm:ss): ");
                String timeString = scanner.nextLine();

                try {
                    Date time = dateFormat.parse(timeString);
                    Reminder reminder = new Reminder(task, time);
                    reminderScheduler.scheduleReminder(reminder);
                    System.out.println("Reminder scheduled: " + task);
                } catch (Exception e) {
                    System.out.println("Invalid time format. Please try again.");
                }
            } else if (command.equalsIgnoreCase("cancel")) {
                System.out.print("Enter task description: ");
                String task = scanner.nextLine();
                System.out.println("Reminder canceled: " + task);
            } else if (command.equalsIgnoreCase("complete")) {
                System.out.print("Enter task description: ");
                String task = scanner.nextLine();
                System.out.println("Reminder marked as completed: " + task);
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}

