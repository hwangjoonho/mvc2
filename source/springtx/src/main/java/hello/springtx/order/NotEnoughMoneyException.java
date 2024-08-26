package hello.springtx.order;

public class NotEnoughMoneyException extends Exception {
//    롤백할 경우 고객이 주문한 주문정보 다 사라지므로 체크 예외 발생 -> 대기
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
