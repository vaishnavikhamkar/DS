import java.io.*;

class Bully {
    int cood, ch, crash;
    int[] prc;

    public void election(int n) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nThe coordinator has crashed!");
        int flag = 1;
        while (flag == 1) {
            crash = 0;
            for (int i1 = 0; i1 < n; i1++)
                if (prc[i1] == 0)
                    crash++;
            if (crash == n) {
                System.out.println("\nAll processes are crashed");
                break;
            } else {
                System.out.print("\nEnter the initiator: ");
                int init = Integer.parseInt(br.readLine());
                if ((init < 1) || (init > n) || (prc[init - 1] == 0)) {
                    System.out.println("\nInvalid initiator");
                    continue;
                }
                for (int i1 = init - 1; i1 < n; i1++) {
                    System.out.println("\nProcess " + (i1 + 1) + " called for election");
                    for (int i2 = init - 1; i2 < n; i2++) {
                        if (prc[i2] == 0) {
                            System.out.println("Process " + (i2 + 1) + " is dead");
                        } else
                            System.out.println("Process " + (i2 + 1) + " is in");
                    }
                }
                for (int i1 = n - 1; i1 >= 0; i1--) {
                    if (prc[i1] == 1) {
                        cood = (i1 + 1);
                        System.out.println("\nNew coordinator is " + (cood));
                        flag = 0;
                        break;
                    }
                }
            }
        }
    }

    public void Bully() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the number of processes: ");
        int n = Integer.parseInt(br.readLine());
        prc = new int[n];
        crash = 0;
        for (int i = 0; i < n; i++)
            prc[i] = 1;
        cood = n;
        do {
            System.out.println("\n1.Crash A Process\n2.Recover A Process\n3.Display New Coordinator\n4.Exit");
            System.out.print("Enter a choice: ");
            ch = Integer.parseInt(br.readLine());
            switch (ch) {
                case 1:
                    System.out.print("\nEnter a process to crash: ");
                    int cp = Integer.parseInt(br.readLine());
                    if ((cp > n) || (cp < 1)) {
                        System.out.println("Invalid process! Enter a valid process.");
                    } else if ((prc[cp - 1] == 1) && (cood != cp)) {
                        prc[cp - 1] = 0;
                        System.out.println("\nProcess " + cp + " has been crashed");
                    } else if ((prc[cp - 1] == 1) && (cood == cp)) {
                        prc[cp - 1] = 0;
                        election(n);
                    } else
                        System.out.println("\nProcess " + cp + " is already crashed");
                    break;
                case 2:
                    System.out.println("\nCrashed processes are:");
                    for (int i = 0; i < n; i++) {
                        if (prc[i] == 0)
                            System.out.println(i + 1);
                        crash++;
                    }
                    System.out.print("\nEnter the process you want to recover: ");
                    int rp = Integer.parseInt(br.readLine());
                    if ((rp < 1) || (rp > n))
                        System.out.println("\nInvalid process. Enter a valid ID.");
                    else if ((prc[rp - 1] == 0) && (rp > cood)) {
                        prc[rp - 1] = 1;
                        System.out.println("\nProcess " + rp + " has been recovered");
                        cood = rp;
                        System.out.println("\nProcess " + rp + " is the new coordinator");
                    } else if (crash == n) {
                        prc[rp - 1] = 1;
                        cood = rp;
                        System.out.println("\nProcess " + rp + " is the new coordinator");
                        crash--;
                    } else if ((prc[rp - 1] == 0) && (rp < cood)) {
                        prc[rp - 1] = 1;
                        System.out.println("\nProcess " + rp + " has been recovered");
                    } else
                        System.out.println("\nProcess " + rp + " is not a crashed process");
                    break;
                case 3:
                    System.out.println("\nCurrent coordinator is " + cood);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid entry!");
            }
        } while (ch != 4);
    }

    public static void main(String[] args) throws IOException {
        Bully ob = new Bully();
        ob.Bully();
    }
}