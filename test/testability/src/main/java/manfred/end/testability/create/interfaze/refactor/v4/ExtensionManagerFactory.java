package manfred.end.testability.create.interfaze.refactor.v4;

public class ExtensionManagerFactory {

    public static final ExtensionManagerFactory INSTANCE = new ExtensionManagerFactory();

    private ExtensionManagerFactory() {
    }

    private ExtensionManager customExtensionManager;

    public void setCustomExtensionManager(ExtensionManager customExtensionManager) {
        this.customExtensionManager = customExtensionManager;
    }

    public ExtensionManager create() {
        if (customExtensionManager != null) {
            return customExtensionManager;
        }
        return new FileExtensionManager();
    }
}
