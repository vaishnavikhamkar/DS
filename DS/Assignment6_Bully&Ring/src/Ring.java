import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {
        int temp, i, j;
        Rr[] proc = new Rr[10];
        // object initialisation
        for (i = 0; i < proc.length; i++)
            proc[i] = new Rr();
        // scanner used for getting input from console
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of process: ");
        int num = in.nextInt();
        // getting input from users
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.print("\nEnter the id of process: ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // sorting the processes from on the basis of id
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        for (i = 0; i < num; i++) {
            System.out.print("\n");
            System.out.print(" [" + i + "] " + " " + proc[i].id);
        }

        int init, ch, temp1, temp2;
        int[] arr = new int[10];
        proc[num - 1].state = "inactive";
        System.out.println("\n\nProcess " + proc[num - 1].id + " select as coordinator");

        while (true) {
            System.out.print("\n1.Election\n2.Quit\nEnter a choice: ");
            ch = in.nextInt();
            for (i = 0; i < num; i++) {
                proc[i].f = 0;
            }
            switch (ch) {
                case 1:
                    System.out.print("\nEnter the process number who initialized election: ");

                    init = in.nextInt();
                    temp2 = init;
                    temp1 = init + 1;
                    i = 0;
                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {

                            System.out.println("\nProcess " + proc[init].id + " send message to " + proc[temp1].id);

                            proc[temp1].f = 1;
                            init = temp1;
                            arr[i] = proc[temp1].id;
                            i++;
                        }

                        if (temp1 == num) {
                            temp1 = 0;
                        } else {
                            temp1++;
                        }
                    }
                    System.out.println("\nProcess " + proc[init].id + " send message to " + proc[temp1].id);

                    arr[i] = proc[temp1].id;
                    i++;
                    int max = -1;

                    // finding maximum for coordinator selection
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }
                    // coordinator is found then printing on console
                    System.out.println("\nProcess " + max + " select as coordinator");

                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    break;

                case 2:
                    System.out.println("\nProgram terminated\n");
                    return;

                default:
                    System.out.println("\nInvalid response \n");
            }
        }
    }
}

class Rr {

    public int index; // to store the index of process
    public int id; // to store id/name of process
    public int f;
    String state; // indicates whether active or inactive state of node
}
