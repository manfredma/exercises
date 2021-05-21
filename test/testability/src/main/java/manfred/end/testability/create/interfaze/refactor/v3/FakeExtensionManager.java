package manfred.end.testability.create.interfaze.refactor.v3;

public class FakeExtensionManager implements ExtensionManager {

    private RuntimeException exception;

    private boolean isValid;

    @Override
    public boolean isValid(String fileName) {
        if (exception != null) {
            throw exception;
        }
        return isValid;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
