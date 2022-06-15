package ShellPackage;

@ShellCommand(name = "exite")
public class ExiteCommand implements Command {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void exec() {
        System.exit(0);
    }

    @Override
    public String getHelp() {
        return "exit - end of cmd";
    }
}
