package manfred.end.testability.create.interfaze.refactor.v2;

public class LogAnalyzer {

    private ExtensionManager extensionManager;

    public LogAnalyzer(ExtensionManager extensionManager) {
        this.extensionManager = extensionManager;
    }

    public boolean isValidLogFileName(String fileName) {
        return extensionManager.isValid(fileName);
    }
}
