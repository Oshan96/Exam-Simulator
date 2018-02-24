package lk.ijse.examsimulator.util.comparator;

/**
 * Created by oshan on 25-Dec-17.
 *
 * @author oshan
 */
public interface CodeComparator {
    boolean compare(String correctAnswer, String givenAnswer);
    boolean compareIgnoreCase(String correctAnswer, String givenAnswer);
}
