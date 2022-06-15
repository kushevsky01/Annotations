package ShellPackage;

@ShellCommand(name = "dir")
public class DirCommand implements Command {

    @Override
    public String getName() {
        return "dir";
    }

    @Override
    public void exec() {
        System.out.println("Working directory: "+ System.getProperty("user.dir"));
    }

    @Override
    public String getHelp() {
        return "dir - getting the current directory";
    }
}
