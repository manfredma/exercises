package manfred.end.testability.create.interfaze.refactor.v1;

public class LogAnalyzer {
    public boolean isValidLogFileName(String fileName) {
        ExtensionManager fileExtensionManager = new FileExtensionManager();
        return fileExtensionManager.isValid(fileName);
    }
}
