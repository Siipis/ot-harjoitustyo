package namegenerator.ui;

public abstract class ChildController {
    protected Controller parent;

    private boolean hasError = false;

    public void setParent(Controller controller) {
        this.parent = controller;
    }

    public Controller getParent() {
        return parent;
    }

    public abstract void render();

    public boolean hasError() {
        return hasError;
    }

    protected void setError(boolean hasError) {
        this.hasError = hasError;

        this.parent.renderChildren();
    }
}