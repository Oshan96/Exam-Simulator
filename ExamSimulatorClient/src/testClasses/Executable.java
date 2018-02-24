package testClasses;

/**
 * Created by oshan on 23-Dec-17.
 * Current version does not support to get user inputs..
 * @author oshan
 */
public interface Executable {
    /**
     * <p>
     *     Generates the .class file as to the given source code.
     *     The user provided class (from the source code) will be a static
     *     inner class called <b>Demo</b> which is inside the compiled class <b>(Run)</b>
     * </p>
     * @param source Source code
     * @param mainClass name of the main class. (<i>null</i> to wrap around <i>Run</i> class)
     * @return returns whether the complication is success or fail
     */
    boolean compile(String source, String mainClass) throws Exception;

    /**
     * <p>
     *     Executes the generated class file <i>(Run.class)</i> for the previously compiled source file <i>(Run.java)</i>.
     *     This method shall <b>only</b> be invoked after the <i>compile</i> method is called.
     *     Otherwise <i>compileAndExecute(String)</i> method or <i>getOutputFromSource(String)</i> method should be called.
     * </p>
     * @throws Exception if called without/before <i>compile(String)</i> method being called.
     */
    void execute()throws Exception;

//    void execute(String mainClass) throws Exception;

    /**
     * <p>
     *     Performs compilation and execution tasks for the provided source code.
     * </p>
     * @param source source code
     * @return true if compilation and execution is success else, false
     */
    boolean compileAndExecute(String source, String mainClass)throws Exception;

    /**
     * <p>
     *     Compile, execute and then read the output for the given source code
     * </p>
     *
     * @param source source code
     * @return String (the output produced by the source code or empty string if any errors occurred)
     * @throws Exception
     */
    String getOutputFromSource(String source, String mainClass)throws Exception;
}
