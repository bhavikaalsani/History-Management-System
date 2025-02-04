import java.util.*;

class Tab {
    String url;
    Tab next;
    Tab prev;

    public Tab(String url) {
        this.url = url;
        this.next = null;
        this.prev = null;
    }
}

public class historymanagment {
    private static Tab currentTab;
    private static Stack<Tab> backStack = new Stack<>();
    private static Stack<Tab> forwardStack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Welcome to Browser History Manager ---");
            System.out.println("1. Open new tab");
            System.out.println("2. Go to previous tab");
            System.out.println("3. Go to next tab");
            System.out.println("4. Show current tab");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    createTab(sc);
                    break;
                case 2:
                    prevTab();
                    break;
                case 3:
                    nextTab();
                    break;
                case 4:
                    showCurrentTab();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    public static void createTab(Scanner sc) {
        System.out.print("Enter website URL: ");
        String url = sc.nextLine();

        Tab newTab = new Tab(url);
        if (currentTab != null) {
            backStack.push(currentTab);
            forwardStack.clear();
        }
        currentTab = newTab;

        System.out.println("New tab opened: " + currentTab.url);
    }

    public static void prevTab() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentTab);
            currentTab = backStack.pop();
            System.out.println("Switched to previous tab: " + currentTab.url);
        } else {
            System.out.println("No previous tab.");
        }
    }

    public static void nextTab() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentTab);
            currentTab = forwardStack.pop();
            System.out.println("Switched to next tab: " + currentTab.url);
        } else {
            System.out.println("No next tab.");
        }
    }

    public static void showCurrentTab() {
        if (currentTab != null) {
            System.out.println("Current tab: " + currentTab.url);
        } else {
            System.out.println("No tabs open.");
        }
    }
}
