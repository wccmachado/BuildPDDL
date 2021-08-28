import ufc.Navigator.Navigator;
import ufc.file.ReadFile;

public class Main {


    public static void main(String[] args) {
        ReadFile read = new ReadFile("/home/wccmachado/IdeaProjects/BuildPDDL/domain/rovers/p20.pddl");
        read.run();
        Navigator navigator = new Navigator(read);
        navigator.createActionNonDeterministicNavigator();
        navigator.printAction();
    }
}
