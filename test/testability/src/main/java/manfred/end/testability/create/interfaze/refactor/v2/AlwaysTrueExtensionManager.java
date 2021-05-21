package manfred.end.testability.create.interfaze.refactor.v2;

public class AlwaysTrueExtensionManager implements ExtensionManager {

    @Override
    public boolean isValid(String fileName) {
        return true;
    }
}
