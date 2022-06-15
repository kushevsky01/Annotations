package ShellPackage;

@ShellCommand(name = "help")
public class HelpCommand implements Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void exec() {
        for (Command com: ShellPackage.Shell.list){
            System.out.println(com.getHelp());
        }
    }

    @Override
    public String getHelp() {
        return null;
    }
}
