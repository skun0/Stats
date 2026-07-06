package stats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class Event implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

    	if (e.getView().getTitle() == null) return;

        e.setCancelled(true);
        e.setResult(org.bukkit.event.Event.Result.DENY);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {

    	if (e.getView().getTitle() == null) return;

        e.setCancelled(true);
    }
}