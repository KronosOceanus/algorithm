import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 传入中缀表达式的字符串形式
 * 转换成后缀表达式
 * 并计算
 */
public class InSuffix {

    private String expression;
    private String suffix;
    private double result;
    //转换使用的栈
    private MyStack<String> stack;
    //计算使用的栈
    private MyStack<Double> numStack;
    //用来匹配字符串
    private Matcher m;
    public InSuffix(String ex){
        this.expression = ex;
        m = Pattern.compile("(\\d+.\\d+)|(\\d+)").matcher(expression);
        stack = new MyStack<>();
        suffix = transform();
        numStack = new MyStack<>();
        result = calculate();
    }

    //中缀转后缀，得到字符串
    private String transform(){
        m = m.reset(expression);
        StringBuffer result = new StringBuffer();
        for (int i=0;i<expression.length();     ){
            switch(expression.charAt(i)){
                case '+':{
                    while (!stack.isEmpty()
                            && !stack.top().equals("(")){
                        result.append(stack.pop()).append(" ");
                    }
                    stack.push("+");
                    i++;
                    break;
                }
                case '-':{
                    while (!stack.isEmpty()
                            && !stack.top().equals("(")){
                        result.append(stack.pop()).append(" ");
                    }
                    stack.push("-");
                    i++;
                    break;
                }
                case '*':{
                    while (!stack.isEmpty() && !stack.top().equals("+")
                            && !stack.top().equals("-")
                            && !stack.top().equals("(")){
                        result.append(stack.pop()).append(" ");
                    }
                    stack.push("*");
                    i++;
                    break;
                }
                case '/':{
                    while (!stack.isEmpty() && !stack.top().equals("+")
                            && !stack.top().equals("-")
                            && !stack.top().equals("(")){
                        result.append(stack.pop()).append(" ");
                    }
                    stack.push("/");
                    i++;
                    break;
                }
                case '(':{
                    stack.push("(");
                    i++;
                    break;
                }
                case ')':{
                    while (!stack.isEmpty() && !stack.top().equals("(")){
                        result.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                    i++;
                    break;
                }
                default:{
                    if (m.find()){
                        result.append(m.group()).append(" ");
                        i = m.end();
                    }
                    break;
                }
            }
        }
        while(! stack.isEmpty()){
            result.append(stack.pop()).append(" ");
        }
        return result.toString();
    }
    //后缀计算，得到结果
    private double calculate(){
        double middle = 0D;
        m = m.reset(suffix);
        for (int i=0;i<suffix.length();    ){
            switch (suffix.charAt(i)){
                case '+':{
                    middle = numStack.pop() + numStack.pop();
                    numStack.push(middle);
                    i++;
                    break;
                }
                case '-':{
                    middle = numStack.pop() - numStack.pop();
                    numStack.push(middle);
                    i++;
                    break;
                }
                case '*':{
                    middle = numStack.pop() * numStack.pop();
                    numStack.push(middle);
                    i++;
                    break;
                }
                case '/':{
                    middle = numStack.pop() / numStack.pop();
                    numStack.push(middle);
                    i++;
                    break;
                }
                case ' ':i++; break;
                default:{
                    if (m.find()){
                        numStack.push(Double.parseDouble(m.group()));
                        i = m.end();
                    }
                    break;
                }
            }
        }
        return numStack.pop();
    }



    private String getExpression(){
        return this.expression;
    }
    private void setExpression(String ex){
        this.expression = ex;
        stack.clear();
        suffix = transform();
        numStack.clear();
        result = calculate();
    }

    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }
}
