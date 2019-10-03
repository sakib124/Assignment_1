import java.io.*;
import java.net.*;

public class EchoClient {


    private double weight, height, BMI;
    
    Socket echo;
    DataInputStream is;
    DataOutputStream os;

    public EchoClient(double weight, double height) {

        this.weight = weight;
        this.height = height;
    }

    public double getBMI() {
        return BMI;
    }


    public void startClient() throws IOException {
        echo = new Socket("localhost", 3500);
        is = new DataInputStream(echo.getInputStream());
        os = new DataOutputStream(echo.getOutputStream());
        os.writeDouble(weight);
        os.flush();
        os.writeDouble(height);
        os.flush();

        BMI = is.readDouble();

        System.out.println("This client's BMI is: " + BMI);

    }
}