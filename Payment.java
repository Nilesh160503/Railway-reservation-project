package railwayreservation.system;
import railwayreservation.interfaces.payment;
import java.util.Scanner;
import notInUse.threading;

public class Payment implements payment {
    @Override
    public boolean payment(int amount) {
        System.out.println("For the payment of ₹"+amount);
        System.out.println("please enter the Card Number:");
        Scanner sc=new Scanner(System.in);
        int accountNo= sc.nextInt();
        System.out.println("Enter the OTP sent to registered mobile number:");
        int otp= sc.nextInt();

        System.out.println("verifying ...");
        threading.sleep(1000);
        System.out.println("Processing  ....\nplease wait.");
        threading.sleep(3000);

        System.out.println("\nPayment Successful!!!");
        threading.sleep(1000);

        return true;
    }
}
