package manfred.end.testability.create.interfaze.refactor.v3;

public class AlwaysTrueExtensionManager implements ExtensionManager {

    @Override
    public boolean isValid(String fileName) {
        return true;
    }
}
