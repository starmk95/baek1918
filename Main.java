import java.util.*;

public class Main {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String inorder = sc.next();
        String[] each = inorder.split("");
        StringBuilder sb = new StringBuilder();
        for (String x : each) {
            if (x.equals(")")) { // 닫힌 괄호가 들어오면 stack에 열린 괄호가 나올 때까지 pop하고 식에 append 해줌
                while (!stack.peek().equals("(")) {
                    sb.append(stack.pop());
                }
                stack.pop(); // "(" stack에서 제거
            } else if (x.equals("+") || x.equals("-")) {
                // +,-연산자가 들어올 때
                // stack이 비거나 열린 괄호가 나오거나
                // stack에 있는 우선 순위가 같은 +, - 연산자들은 append 해준다. (stack에는 열린 괄호와 연산자만 들어감)
                if (stack.empty()) {
                    // stack이 비어있으면
                    stack.push(x);
                } else {
                    // stack이 비어있지 않으면
                    while (!stack.empty()) {
                        // 열린 괄호를 만나면 pop과 append 중지
                        if (stack.peek().equals("(")) break;
                        // 아니라면 stack이 빌때까지 pop하고 append
                        sb.append(stack.pop());
                    }
                    stack.push(x);
                }
            } else if (x.equals("*") || x.equals("/")) {
                if (stack.empty()) {
                    // stack이 비어있으면
                    stack.push(x);
                } else {
                    // stack이 비어있지 않으면
                    while (!stack.empty()) {
                        // 열린 괄호를 만나면 pop과 append 중지
                        if (stack.peek().equals("(")) break;
                        // *, /보다 우선 순위가 낮은 +, - 연산자를 만나면 pop과 append 중지
                        else if (stack.peek().equals("+") || stack.peek().equals("-")) break;
                        // 아니라면 pop하고 append
                        sb.append(stack.pop());
                    }
                    stack.push(x);
                }
            } else if (x.equals("(")) {
                // 열린 괄호가 들어와도 stack에 넣어준다.
                stack.push(x);
            } else {
                // 피연산자가 들어오면 바로 append
                sb.append(x);
            }
        }
        // 반복문을 다 돌고도 stack에 남아있는 연산자가 있다면
        // -> 열림 괄호 없이 들어온 연산자가 있었을 경우 처리
        while (!stack.empty()) sb.append(stack.pop());

        System.out.print(sb);
    }
}
