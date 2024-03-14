package pepse.world;

/**
 * The JumpObserver interface defines the contract for classes that observe the jumping behavior of an avatar.
 */
public interface JumpObserver {
    /**
     * This method is called when the avatar jumps.
     * 
     * @param deltaTime The time elapsed since the last jump event.
     */
    void onAvatarJump();
}
/**
 * The JumpSubject interface defines the contract for the subject (avatar) that other classes can observe.
 */
interface JumpSubject {
     /**
     * Registers an observer to receive notifications about avatar jumps.
     * 
     * @param observer The observer to be registered.
     */
    void registerObserver(JumpObserver observer);
    /**
     * Removes an observer from the list of registered observers.
     * 
     * @param observer The observer to be removed.
     */
    void removeObserver(JumpObserver observer);
    /**
     * Notifies all registered observers when the avatar jumps.
     */
    void notifyObservers();
}
