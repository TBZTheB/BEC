package fileTest;

public class WordSearch {
    public static double calculateTicketPrice(String ticket, int numberDay){
        double GE = 1200000;
        double GB = 1800000;
        double GF = 2500000;
        double ticketPrice = 0;
        if(numberDay <= 0 || numberDay > 30 ){
            return -1;
        }
        switch (ticket) {
            case "Economy":
                ticketPrice = GE;
                break;
            case "Business":
                ticketPrice = GB;
                break;
            case "First class":
                ticketPrice = GF;
                break;
            default:
                return -1;
        }
        if(numberDay <=7){
            ticketPrice *= 1.5;
        } else if(numberDay > 14){
            ticketPrice *= 0.8;
        }
        return ticketPrice;
    }
    public static void main(String[] args) {
        System.out.println(calculateTicketPrice("Economy", 100));
        System.out.println(calculateTicketPrice("Economy", 5));
        System.out.println(calculateTicketPrice("Business", 5));
        System.out.println(calculateTicketPrice("First class", 5));
        System.out.println(calculateTicketPrice("abc", 5));
        System.out.println(calculateTicketPrice("Economy", 10));
        System.out.println(calculateTicketPrice("Economy", 25));
    }
}
