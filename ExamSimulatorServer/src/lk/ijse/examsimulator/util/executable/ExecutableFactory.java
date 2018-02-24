package lk.ijse.examsimulator.util.executable;

import lk.ijse.examsimulator.util.executable.custom.Executable;
import lk.ijse.examsimulator.util.executable.custom.impl.ExecutableImpl;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class ExecutableFactory {
    private static ExecutableFactory factory;
    private Executable executable;

    private ExecutableFactory(){
        executable=new ExecutableImpl();
    }

    public static ExecutableFactory getInstance(){
        if(factory==null) factory=new ExecutableFactory();
        return factory;
    }

    public Executable getExecutable(){
        return executable;
    }
}
