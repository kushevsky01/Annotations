package ShellPackage;

public interface Command {
    String getName();
    void exec();

    String getHelp();
}
