package cz.muni.tron.events;


public interface EventListener {
    
    public void keyPressed(KeyPressedEvent event);
    
    public void mouseClicked(MouseClickedEvent event);
    
    public void gameTick();
    
}
