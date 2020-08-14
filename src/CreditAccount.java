import java.util.Scanner;

/**
 * pseudo code
 * <p>
 * accountNumber = take input from user
 * monthStartBalance = take input from user
 * currentMonthCharges = take input from user
 * currentMonthCredits = take input from user
 * creditLimit = take input from user
 * <p>
 * newBalance = monthStartBalance + currentMonthCharges - currentMonthCredits
 * <p>
 * if(newBalance > creditLimit) credit limit exceeded
 */
public class CreditAccount {
    int accountNumber;
    int monthStartBalance;
    int currentMonthCharges;
    int currentMonthCredits;
    int creditLimit;

    /**
     * parameterized constructor
     * @param accountNumber: account number of customer
     * @param monthStartBalance: Balance at the beginning of the month
     * @param currentMonthCharges: Total of all items charged by this customer this month
     * @param currentMonthCredits: Total of all credits applied to this customer’s account this month
     * @param creditLimit: Allowed credit limit
     */
    public CreditAccount(int accountNumber, int monthStartBalance, int currentMonthCharges,
                         int currentMonthCredits, int creditLimit) {
        this.accountNumber = accountNumber;
        this.monthStartBalance = monthStartBalance;
        this.currentMonthCharges = currentMonthCharges;
        this.currentMonthCredits = currentMonthCredits;
        this.creditLimit = creditLimit;
    }

    /**
     * runs credit check with logic defined inside
     */
    public void runCreditCheck() {
        int newBalance = this.monthStartBalance + this.currentMonthCharges - this.currentMonthCredits;
        if (newBalance > this.creditLimit) {
            System.out.println("Credit Limit Exceeded");
        }
    }

    /**
     * driver
     * @param args: command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number");
        int accNo = sc.nextInt();
        System.out.println("Enter Balance at the beginning of the month");
        int openBal = sc.nextInt();
        System.out.println("Enter Total of all items charged by this customer this month");
        int monthChg = sc.nextInt();
        System.out.println("Enter Total of all credits applied to this customer’s account this month");
        int monthCdt = sc.nextInt();
        System.out.println("Enter Allowed credit limit");
        int creditLimit = sc.nextInt();
        CreditAccount ca = new CreditAccount(accNo, openBal, monthChg, monthCdt, creditLimit);
        ca.runCreditCheck();

    }
}
