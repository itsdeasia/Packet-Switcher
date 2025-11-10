/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

class Packet {
    int id;
    int size; // bytes
    int delay; // milliseconds

    public Packet(int id, int size, int delay) {
        this.id = id;
        this.size = size;
        this.delay = delay;
    }
}

class Router {
    private int bufferSize;
    private Queue<Packet> buffer = new LinkedList<>();

    public Router(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    // Receive packet into buffer (if space available)
    public void receivePacket(Packet p) {
        if (buffer.size() < bufferSize) {
            buffer.add(p);
            System.out.println("Packet " + p.id + " added to buffer (" + p.size + " bytes)");
        } else {
            System.out.println("Packet " + p.id + " dropped! (Buffer overflow)");
        }
    }

    // Forward all packets in the buffer
    public void forwardPackets() {
        System.out.println("\nForwarding packets through the router...");
        Random rand = new Random();

        while (!buffer.isEmpty()) {
            Packet p = buffer.poll();
            try {
                Thread.sleep(p.delay * 100); // simulate network delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Packet " + p.id + " transmitted successfully (Delay: " + p.delay + " ms)");
        }
        System.out.println("\n All packets have been processed.");
    }
}

public class PacketSwitchingSimulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to the Packet Switching Simulator!");
        System.out.println("This simulation demonstrates how network packets move through a router.\n");

        System.out.print("Enter the number of packets to send: ");
        int numPackets = input.nextInt();

        System.out.print("Enter the router buffer size: ");
        int bufferSize = input.nextInt();

        Router router = new Router(bufferSize);

        System.out.println("\n🚀 Creating packets and sending to router...");
        for (int i = 1; i <= numPackets; i++) {
            int size = rand.nextInt(151) + 50; // 50–200 bytes
            int delay = rand.nextInt(5) + 1;   // 1–5 ms delay
            Packet p = new Packet(i, size, delay);
            router.receivePacket(p);
        }

        router.forwardPackets();
        System.out.println("\n Simulation complete. Thank you for exploring packet switching!");
        input.close();
    }
}
