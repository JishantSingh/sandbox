import java.util.Scanner;

/**
 * psudo code
 *
 * monthly_principal = take input from user
 * annual_interest_rate = take input from user
 * monthly_interest_rate = annual_interest_rate/12
 *
 * time_in_months = take_input_from user
 *
 * while(time_in_months > 0) {
 * 	amount = amount + monthly_principal
 * 	amount = amount(1 + monthly_interest_rate)
 * 	time_in_months = time_in_months - 1
 * }
 */
public class SavingsAccount {
    double monthlyPrincipal;
    double annualRate;
    double monthlyRate;

    /**
     * parameterized constructor
     * @param monthlyPrincipal: monthly added installment
     * @param annualRate: annual rate of interest
     */
    SavingsAccount(double monthlyPrincipal, double annualRate) {
        this.monthlyPrincipal = monthlyPrincipal;
        this.annualRate = annualRate / 100;
        this.monthlyRate = this.annualRate / 12;
    }

    /**
     *
     * @param months: number of months to calculate amount after
     * @return
     */
    public double calculateAmount(int months) {
        double amount = 0;
        while (months-- > 0) {
            //added monthly installment
            amount = amount + this.monthlyPrincipal;
            //calculates final amount for month
            amount = amount * (1 + this.monthlyRate);
        }
        return amount;
    }

    /**
     * driver
     * @param args: command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //taking inputs
        System.out.println("Enter Principal Amount");
        double principal = sc.nextDouble();
        System.out.println("Enter Annual Interest Rate in %, eg 5 for 5%");
        double annualRate = sc.nextDouble();
        System.out.println("Enter number of months");
        int months = sc.nextInt();
        SavingsAccount sa = new SavingsAccount(principal, annualRate);
        System.out.println("Amount after " + months + " months is " + sa.calculateAmount(months));
    }

}
