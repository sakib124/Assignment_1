import java.net.*;
import java.io.*;

public class MTEchoServer {

    public static void main(String[] args) {

        //        if (args.length != 1) {
        //            System.err.println("Usage: java EchoServer <port number>");
        //            System.exit(1);
        //        }
        //         
        //        int portNumber = Integer.parseInt(args[0]);
        MTEchoServer es = new MTEchoServer();
        es.run(3500);
    }

    public void run(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket client = serverSocket.accept();
                Connection cc = new Connection(client);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}

class Connection extends Thread {
    Socket client;
    PrintWriter out;
    BufferedReader in ;
    DataInputStream r;
    DataOutputStream w;
    public Connection(Socket s) { // constructor
        client = s;


        try {
            //           out = new PrintWriter(client.getOutputStream(), true);                   
            //           in = new BufferedReader(
            //           new InputStreamReader(client.getInputStream()));
            r = new DataInputStream(s.getInputStream());
            w = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException ex) {
                System.out.println("Error while getting socket streams.." + ex);
            }
            return;
        }
        this.start(); // Thread starts here...this start() will call run()
    }

    public double bmiIndex(double weight, double height) {

        double BMI = weight / Math.pow(height, 2);
        double roundedBMI = Math.round(BMI * 10.0) / 10.0;

        return roundedBMI;
    }

    public void run() {
        try {
            double weight, height, bmiIndex, prefWeight;

            weight = r.readDouble();
            System.out.println("Client's weight is: " + weight + " kg");

            height = r.readDouble();
            System.out.println("Client's height is: " + height + " m");


            bmiIndex = bmiIndex(weight, height);

            w.writeDouble((double) bmiIndex);
            w.flush();

            System.out.println("The BMI Index is: " + (double) bmiIndex);

            if (bmiIndex < 18.5) {
                System.out.println("Client is underweight.");
                prefWeight = 18.5 * Math.pow(height, 2) - weight;
                double roundedprefWeight = Math.round(prefWeight * 10.0) / 10.0;
                System.out.println("They will need to gain " + roundedprefWeight + " kg to reach the normal weight.");
            } else if (bmiIndex < 25) {
                System.out.println("Client is at a normal weight.");
            } else if (bmiIndex < 30) {
                System.out.println("Client is overweight");
                prefWeight = weight - 24.9 * Math.pow(height, 2);
                double roundedprefWeight = Math.round(prefWeight * 10.0) / 10.0;
                System.out.println("They will need to lose " + roundedprefWeight + " kg to reach the normal weight.");
            } else {
                System.out.println("Client is obese");
                prefWeight = weight - 24.9 * Math.pow(height, 2);
                double roundedprefWeight = Math.round(prefWeight * 10.0) / 10.0;
                System.out.println("They will need to lose " + roundedprefWeight + " kg to reach the normal weight.");
            }

            System.out.println("--------------------------------------------------------------------------");

            client.close();
        } catch (IOException e) {
            System.out.println("Exception caught...");
            System.out.println(e.getMessage());
        }
    }
}