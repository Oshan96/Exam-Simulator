package lk.ijse.examsimulator.util.executable.custom.impl;

import lk.ijse.examsimulator.util.executable.custom.Executable;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class will compile,execute,compile/execute provided source code dynamically.
 * Current version does not support for user inputs.
 * Created by oshan on 22-Dec-17.
 * @author oshan
 */

public class ExecutableImpl implements Executable{

    public ExecutableImpl(){

    }

    /**
     * <p>
     *     This method is called through the compile(StringBuilder source) method
     *     At a later stage. This method can also be declared as <b>public</b> as
     *     well.
     *     The .java file will be generated as to the given source code.
     *     (As an inner class of a class called <b>Run</b>)
     * </p>
     * @param source source code
     * @return the java file created as for the provided source code
     *
     */
    private synchronized File createFile(StringBuilder source,String mainClass){

        StringBuilder sourceCode=new StringBuilder("public class Run{\n");
        sourceCode.append("public Run(){}\n");
        sourceCode.append("public void execute(){new Run.Demo();}\n");
        sourceCode.append("public static void main(String args[]){Run.Demo.main(args);}\n");
        sourceCode.append("public static class Demo{\n");
        sourceCode.append("public Demo(){main(new String[0]);}\n");
        sourceCode.append(source);
        sourceCode.append("\n}\n}");

        if(mainClass==null){
            try (FileWriter fileWriter = new FileWriter("Run.java"); BufferedWriter writer = new BufferedWriter(fileWriter)) {
                writer.write(sourceCode.toString());
                return new File("Run.java");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try (FileWriter fileWriter = new FileWriter(mainClass+".java"); BufferedWriter writer = new BufferedWriter(fileWriter)) {
                writer.write(source.toString());
                return new File(mainClass+".java");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

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
    @Override
    public synchronized boolean compile(String source,String mainClass) throws Exception{
        File file=createFile(new StringBuilder(source),mainClass);
        if(file==null){
            return false;
        }
        JavaCompiler compiler= ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics=new DiagnosticCollector<>();
        StandardJavaFileManager manager=compiler.getStandardFileManager(diagnostics,null,null);

        Iterable<? extends JavaFileObject> compilationUnit= manager.getJavaFileObjectsFromFiles(Collections.singletonList(file));

        JavaCompiler.CompilationTask task= compiler.getTask(
               null,
                manager,
                diagnostics,
                null,
                null,
                compilationUnit
        );
        boolean bool=task.call(); // returns false if compile errors occur, else return true for success compilation
        if(!bool){
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                System.out.format("Error on line %d in %s%n",
                        diagnostic.getLineNumber(),
                        diagnostic.getSource().toUri());
            }

        }
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * <p>
     *     Executes the generated class file <i>(Run.class)</i> for the previously compiled source file <i>(Run.java)</i>.
     *     This method shall <b>only</b> be invoked after the <i>compile</i> method is called.
     *     Otherwise <i>compileAndExecute(String)</i> method or <i>getOutputFromSource(String)</i> method should be called.
     * </p>
     * @throws Exception if called without/before <i>compile(String)</i> method being called
     */
    @Override
    public void execute() throws Exception{
        try {
            URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{new File("./").toURI().toURL()});
            Class<?> obj=urlClassLoader.loadClass("Run");
            Object ob=obj.newInstance();
            Method method=obj.getMethod("execute");
            method.invoke(ob);
        } catch (ClassNotFoundException e){
            throw e;
        } catch (IllegalAccessException | InstantiationException | MalformedURLException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }



    /**
     * <p>
     *     Performs compilation and execution tasks for the provided source code.
     * </p>
     * @param source source code
     * @return true if compilation and execution is success else, false
     */
    @Override
    public synchronized boolean compileAndExecute(String source,String mainClass)throws Exception{
        boolean bool = compile(source,mainClass);
        if(!bool)return false;
        execute();
        return true;
    }

    /**
     * <p>
     *     Compile, execute and then read the output for the given source code
     * </p>
     *
     * @param source source code
     * @return String (the output produced by the source code or empty string if any errors occurred)
     */
    @Override
    public synchronized String getOutputFromSource(String source,String mainClass) throws Exception {
        String output="";
        if(!compile(source,mainClass)){
            return "";
        }

        ProcessBuilder pro= new ProcessBuilder("java", mainClass);
        Process process =pro.start();
        process.waitFor();

        BufferedInputStream pis=new BufferedInputStream(process.getInputStream());
        Scanner si=new Scanner(pis);

        while(si.hasNextLine()) {
            output+=si.nextLine()+"\n";
        }

        //To check the errors occurred while running the program.
        BufferedInputStream bis=new BufferedInputStream(process.getErrorStream());
        Scanner s=new Scanner(bis);
        if(s.hasNextLine()) return "";
//        System.out.println(process.exitValue());

        process.destroy();

        return output;
    }


}

    /*
        //Format of the student given source code is :

        ///////////////////////////////////
            public static void main(String args){
                //Do stuff
            }

            //More methods
        ///////////////////////////////////////////

        //////////////Final class should look like//////////////
        public class Run{

            public Run(){}

            public void execute(){
                new Run.Demo();
            }

            public static void main(String args[]){
                Run.Demo.main(args);
            }

            public static class Demo{
                public Demo(){
                    main(new String[0]);
                }
                public static void main(String args[]){
                    //Do stuff
                }

                //more methods
            }
        }

     */