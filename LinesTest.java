import student.TestCase;

/**
 * tests line parser class
 * 
 * @author Bryan O'Donoghue (bodonoghue)
 * @version 07.14.2020
 */
public class LinesTest extends TestCase {

    /**
     * tests line parser against string input, as well as invocation
     * of driver
     */
    public void testParser() {

        Lines liner = new Lines();
        // invoke driver
        String[] arg = new String[1];
        arg[0] = "inputFile.txt";
        BigNumArithmetic.main(arg);
        arg[0] = "dsanlia";
        // test against sample lines
        // 0 - return normally
        // 1 - no expressions
        // 2 - unbalanced amount of symbols and operands
        assertEquals(1, liner.parseLine("117"));
        assertEquals(1, liner.parseLine("hello"));
        assertEquals(2, liner.parseLine("1 + 7"));
        assertEquals(2, liner.parseLine("+ 7 +"));
        assertEquals(1, liner.parseLine("1111111"));
        assertEquals(1, liner.parseLine("  + + + + + + + ^ ^ ^ * *"));
        assertEquals(2, liner.parseLine("123 412 + ^ * + + +"));
        assertEquals(2, liner.parseLine("123 412 4314 512  5531 +"));
        assertEquals(2, liner.parseLine("" + " 352324012 + 03 ^          "
            + "555557778 *"));
        assertEquals(0, liner.parseLine("99999999 990001 * 01119111 55565   "
            + " 33333 + * +  88888888              + "));
        assertEquals(2, liner.parseLine(
            " 5555555 333333 5454353 999999 666666 01 ^ * * +"));

        assertEquals(2, liner.parseLine(" 3432 3333 9999 + * ^ * * 6666 +"));
    }

}
