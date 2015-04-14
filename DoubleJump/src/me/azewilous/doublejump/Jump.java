package me.azewilous.doublejump;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/*
 * @author Azewilous
 */

public class Jump implements Listener {

DoubleJump plugin;
	public Jump(DoubleJump doubleJump){
		this.plugin = doubleJump;
	}

	ArrayList<Player> jumpers = new ArrayList<Player>();
	
	@EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event){
    	Player player = event.getPlayer();
    	World world = player.getWorld();
    	if(player.hasPermission("dj.launch") || player.isOp()){
    			if (player.getGameMode() == GameMode.CREATIVE)
    				return;
    				if(player.isFlying() == true)
    					return;
    			event.setCancelled(true);
    			player.setAllowFlight(false);
    			player.setFlying(false);
    					if(plugin.djtoggle.contains(player)){
    			player.setVelocity(player.getLocation().getDirection()
    					.multiply(plugin.getConfig().getDouble("DoubleJump.Distance"))
    					.setY(plugin.getConfig().getInt("DoubleJump.Velocity")));
    				player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 3.0F, 6.0F);
    				world.playEffect(player.getLocation().add(0, 1, 0), Effect.EXPLOSION_HUGE, 1);
    					} 
    	}
    	jumpers.add(player);
 }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("dj.launched") || player.isOp()){
    			if((player.getGameMode()!=GameMode.CREATIVE)
    					&&(player.getLocation().subtract(0, 1, 0).getBlock().getType()!= Material.AIR)
    					&&(!player.isFlying())){
    				player.setAllowFlight(true);
    			}
    	}
    }
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
            if (event.getEntity() instanceof Player) {
                    Player player = (Player) event.getEntity();
                    if (event.getCause() == DamageCause.FALL) {
                            event.setDamage(0.0);
                            jumpers.remove(player);
                            if(!(jumpers.contains(player))){
                            	event.setCancelled(false);
                            }
                    }
            }
    }
	
}
