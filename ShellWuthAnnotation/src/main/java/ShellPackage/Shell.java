package ShellPackage;

import ShellPackage.Command;
import ShellPackage.DirCommand;
import ShellPackage.ShellCommand;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Shell {
    public static List<Command> list;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

         list = new ArrayList<>();
        Set<Class> classes = findAllClassesUsingClassLoader(args[0]);
        for (Class cls : classes) {
            getCommandsArray(cls, list);
        }


        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("(user)>>");
            String cmd = in.nextLine();


            boolean correct = false;
            for (Command command : list) {
                correct = false;
                if (command.getName().contentEquals(cmd)) {
                    command.exec();
                    correct = true;
                    break;
                }

            }
            if (!correct) {
                System.out.println(cmd + "-  не является внутренней или внешней командой");
                ;
            }
        }
    }

    static void getCommandsArray(Class<?> serv, List<Command> list) throws InstantiationException, IllegalAccessException {
        if (serv.isAnnotationPresent(ShellCommand.class)) {
            ShellCommand ann = serv.getAnnotation(ShellCommand.class);

            list.add((Command) serv.newInstance());
            System.out.println(list);
        }
    }


    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}


