package lk.ijse.examsimulator.util.comparator.impl;

import lk.ijse.examsimulator.util.comparator.CodeComparator;

/**
 * Created by oshan on 25-Dec-17.
 *
 * @author oshan
 */
public class CodeComparatorImpl implements CodeComparator {
    @Override
    public boolean compare(String correctAnswer, String givenAnswer) {
        return correctAnswer.equals(givenAnswer);
    }

    @Override
    public boolean compareIgnoreCase(String correctAnswer, String givenAnswer) {
        return correctAnswer.equalsIgnoreCase(givenAnswer);
    }
}
