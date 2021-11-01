
/**
 * lines is the parser that handles what to do for each line
 * 
 * @author Bryan O'Donoghue (bodonoghue)
 * @version 07.14.2020
 */
public class Lines {
    // holder is the stack to save read operands
    private Stack holder;
    // operations object handles symbols and math ops
    private Operations operator;


    /**
     * stack and operator used for multiple lines
     */
    public Lines() {
        // holder is the stack to save read operands
        holder = new Stack();
        // operations object handles symbols and math ops

        operator = new Operations();
    }


    /**
     * parse line parses the line
     * 
     * @param s
     *            string passed from scanner in main
     * @return int for problem checking
     */
    public int parseLine(String s) {
        // list holds new operand placed on stack
        LinkedList list;
        // holds popped number from stack representing right operand
        LinkedList rightOp;
        // holds popped number from stack representing
        // right operand
        LinkedList leftOp;
        // checks if the operation can be performed based on
        // available operands
        boolean badOp = false;
        if (s.matches("[^0-9+*^]+")) {
            return 1; // invalid character check
        }
        int opCount = 0;
        int numCount = 0;
        String[] strLine = s.split(" +");

        if (s.length() == 0) { // string non empty
            return 1; // invalid line passed
        }
        if (Character.isWhitespace(s.charAt(0))) {
            strLine[0] = "whitespace";
        }
        for (int i = 0; i < strLine.length; i++) {
            switch (strLine[i]) { // Read the next term
                case "+":
                    // addition symbol
                    opCount++;
                    if (holder.length() < 2) {
                        badOp = true;
                    }
                    else {
                        rightOp = (LinkedList)holder.pop();
                        leftOp = (LinkedList)holder.pop();
                        holder.push(operator.add(leftOp, rightOp));
                    }
                    System.out.print("+ ");
                    break;
                case "*":
                    // multiplication symbol
                    opCount++;

                    if (holder.length() < 2) {
                        badOp = true;
                    }
                    else {
                        rightOp = (LinkedList)holder.pop();
                        leftOp = (LinkedList)holder.pop();
                        holder.push(operator.multiply(leftOp, rightOp));

                    }
                    System.out.print("* ");
                    break;
                case "^":
                    // exponent symbol
                    opCount++;

                    if (holder.length() < 2) {
                        badOp = true;
                    }
                    else {
                        rightOp = (LinkedList)holder.pop();
                        leftOp = (LinkedList)holder.pop();
                        holder.push(operator.exponent(leftOp, rightOp));
                    }
                    System.out.print("^ ");
                    break;
                case "whitespace":
                    break;
                default: // digit
                    numCount++;
                    list = new LinkedList(strLine[i]);
                    System.out.print(list.toString() + " ");
                    holder.push(list);
                    break;
            }
        }
        System.out.print("= ");
        if (numCount == 0 || opCount == 0) {
            System.out.println("");
            holder.clear();
            return 1; // no ops or no numbers
        }
        if (opCount == (numCount - 1) && !badOp) {
            System.out.println(holder.topValue().toString());
            holder.clear();
        }
        else {
            System.out.println("");
            holder.clear();
            return 2; // expressions > ops
        }
        return 0;
    }

}
