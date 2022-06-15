package ShellPackage;

import java.time.LocalDate;

@ShellCommand(name = "data")
public class DateCommand implements Command {
    @Override
    public String getName() {
        return "data";
    }

    @Override
    public void exec() {
        LocalDate date =  LocalDate.now();
        System.out.println(date);
    }

    @Override
    public String getHelp() {
        return "data - date nowadays";
    }
}
