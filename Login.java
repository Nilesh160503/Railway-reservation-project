package railwayreservation.main;

import java.util.Objects;
import java.util.Scanner;

final public class Login {
    static public boolean login(){
        String id="SUIIT";
        String Password="Suiit@111";
        System.out.println("Enter the user id:");
        Scanner scanner =new Scanner(System.in);
        String enteredId=scanner.next();
        System.out.println("Enter the Password:");
        String enteredPassword=scanner.next();
        if(Objects.equals(enteredId, id) || Objects.equals(enteredPassword, Password)){
            return true;
        }else {
            return false;

        }
    }
}
