package manfred.end.testability.create.interfaze.refactor.v1;

public class AlwaysTrueExtensionManager implements ExtensionManager {

    @Override
    public boolean isValid(String fileName) {
        return true;
    }
}
