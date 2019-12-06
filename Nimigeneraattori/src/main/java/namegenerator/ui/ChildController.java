package namegenerator.ui;

/**
 * Child controller.
 */
public abstract class ChildController {
    protected Controller parent;

    private boolean hasError = false;

    /**
     * Stores a reference to the main controller.
     *
     * @param controller main controller
     */
    public void setParent(Controller controller) {
        this.parent = controller;
    }

    /**
     * Renders the UI elements controlled by the child controller.
     */
    public abstract void render();

    /**
     * Checks the child controller's error status.
     *
     * @return true if errors exist
     */
    public boolean hasError() {
        return hasError;
    }

    /**
     * Sets the error status.
     *
     * @param hasError error status
     */
    protected void setError(boolean hasError) {
        this.hasError = hasError;

        this.parent.renderChildren();
    }
}