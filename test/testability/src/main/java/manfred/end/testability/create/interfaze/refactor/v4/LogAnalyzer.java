package manfred.end.testability.create.interfaze.refactor.v4;

public class LogAnalyzer {

    private ExtensionManager extensionManager;

    public LogAnalyzer(ExtensionManager extensionManager) {
        this.extensionManager = ExtensionManagerFactory.INSTANCE.create();
    }

    public boolean isValidLogFileName(String fileName) {
        return extensionManager.isValid(fileName);
    }
}
