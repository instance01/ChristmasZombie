package com.comze_instancelabs.christmasmobs;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import net.minecraft.server.v1_7_R1.AttributeInstance;
import net.minecraft.server.v1_7_R1.EntityInsentient;
import net.minecraft.server.v1_7_R1.GenericAttributes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;


public class Main extends JavaPlugin implements Listener {

	Random r = new Random(10000);
	
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	ArrayList<Skeleton> skeletons = new ArrayList<Skeleton>();
	
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		
		CustomEntityType.registerEntities();
	}
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

    	if(cmd.getName().equalsIgnoreCase("spawnzombie")){
    		if(sender.hasPermission("christmasmobs.spawn")){
	    		if(sender instanceof Player){
	    			Player p = (Player) sender;
	    			
	    			Zombie z = (Zombie) p.getWorld().spawnEntity(p.getLocation(), CustomEntityType.ZOMBIE.getEntityType());
	    			
	    			ItemStack lhelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	    		    LeatherArmorMeta lam = (LeatherArmorMeta)lhelmet.getItemMeta();
	    		    lam.setColor(Color.fromRGB(255, 0, 0));
	    		    lhelmet.setItemMeta(lam);
	    		    
	    		    ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
	    		    LeatherArmorMeta lam1 = (LeatherArmorMeta)lboots.getItemMeta();
	    		    lam1.setColor(Color.fromRGB(255, 0, 0));
	    		    lboots.setItemMeta(lam1);
	    		    
	    		    ItemStack lchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	    		    LeatherArmorMeta lam2 = (LeatherArmorMeta)lchestplate.getItemMeta();
	    		    lam2.setColor(Color.fromRGB(255, 0, 0));
	    		    lchestplate.setItemMeta(lam2);
	    		    
	    		    ItemStack lleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
	    		    LeatherArmorMeta lam3 = (LeatherArmorMeta)lleggings.getItemMeta();
	    		    lam3.setColor(Color.fromRGB(255, 0, 0));
	    		    lleggings.setItemMeta(lam3);
	    		    
	    			z.getEquipment().setHelmet(lhelmet);
	    			z.getEquipment().setBoots(lboots);
	    			z.getEquipment().setChestplate(lchestplate);
	    			z.getEquipment().setLeggings(lleggings);
	    			
	    			if(args.length > 0){
	    				String name = args[0].replace("&", "§");
	    				z.setCustomName(name);
	    			}
	    			
	    			setZombieSpeed(z, 0.0D);
	    			zombies.add(z);
	    		}
	    		return true;	
    		}
    		
    	}else if(cmd.getName().equalsIgnoreCase("spawnskeleton")){
    		if(sender.hasPermission("christmasmobs.spawn")){
	    		if(sender instanceof Player){
	    			
	    			Player p = (Player) sender;
	    			
	    			Skeleton s = (Skeleton) p.getWorld().spawnEntity(p.getLocation(), CustomEntityType.SKELETON.getEntityType());
	    			
	    			ItemStack lhelmet = new ItemStack(Material.LEATHER_HELMET, 1);
	    		    LeatherArmorMeta lam = (LeatherArmorMeta)lhelmet.getItemMeta();
	    		    lam.setColor(Color.fromRGB(255, 0, 0));
	    		    lhelmet.setItemMeta(lam);
	    		    
	    		    ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
	    		    LeatherArmorMeta lam1 = (LeatherArmorMeta)lboots.getItemMeta();
	    		    lam1.setColor(Color.fromRGB(255, 0, 0));
	    		    lboots.setItemMeta(lam1);
	    		    
	    		    ItemStack lchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	    		    LeatherArmorMeta lam2 = (LeatherArmorMeta)lchestplate.getItemMeta();
	    		    lam2.setColor(Color.fromRGB(255, 0, 0));
	    		    lchestplate.setItemMeta(lam2);
	    		    
	    		    ItemStack lleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
	    		    LeatherArmorMeta lam3 = (LeatherArmorMeta)lleggings.getItemMeta();
	    		    lam3.setColor(Color.fromRGB(255, 0, 0));
	    		    lleggings.setItemMeta(lam3);
	    		    
	    			s.getEquipment().setHelmet(lhelmet);
	    			s.getEquipment().setBoots(lboots);
	    			s.getEquipment().setChestplate(lchestplate);
	    			s.getEquipment().setLeggings(lleggings);
	    			
	    			if(args.length > 0){
	    				String name = args[0].replace("&", "§");
	    				s.setCustomName(name);
	    			}
	    			
	    			setSkeletonSpeed(s, 0.0D);
	    			skeletons.add(s);
	    		}
	    		return true;		
    		}
    	}else if(cmd.getName().equalsIgnoreCase("removezombie")){
    		if(sender.hasPermission("christmasmobs.remove")){
	    		if(sender instanceof Player){
	    			Player p = (Player) sender;
	    			for(Entity e : p.getNearbyEntities(2, 2, 2)){
	    				if(e instanceof Zombie){
	    					e.remove();
	    				}
	    			}
	    		}
	    		return true;	
    		}
    	}else if(cmd.getName().equalsIgnoreCase("removeskeleton")){
    		if(sender.hasPermission("christmasmobs.remove")){
	    		if(sender instanceof Player){
	    			Player p = (Player) sender;
	    			for(Entity e : p.getNearbyEntities(2, 2, 2)){
	    				if(e instanceof Skeleton){
	    					e.remove();
	    				}
	    			}
	    		}
	    		return true;	
    		}
    	}
    	return false;
    }
    
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
     
        if(event.getRightClicked().getType() == EntityType.ZOMBIE){
            String PlayerName = p.getName();
         
            Potion potion = new Potion(PotionType.STRENGTH, 1, false);
            ItemStack potionstack = potion.toItemStack(1);
            
            ItemMeta itemMeta = potionstack.getItemMeta();
            itemMeta.setDisplayName("§6Glühwein");
            potionstack.setItemMeta(itemMeta);
            
            p.sendMessage(ChatColor.RED + "Merry Christmas! =)");
            p.getInventory().addItem(potionstack);
        }
    }
    
    private static final UUID movementSpeedUID = UUID.fromString("306a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
    
    /*@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();
 
        if (entity.getType() == EntityType.ZOMBIE){
            setZombieSpeed((Zombie) entity, 0.0D);
        }
    }*/
    
    public void setZombieSpeed(Zombie z,double speed){
        AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)z).getHandle()).getAttributeInstance(GenericAttributes.d);
        attributes.setValue(speed);
    }
    
    public void setSkeletonSpeed(Skeleton s,double speed){
        AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)s).getHandle()).getAttributeInstance(GenericAttributes.d);
        attributes.setValue(speed);
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
    	if(event.getEntity() instanceof Zombie){
    		if(zombies.contains((Zombie)event.getEntity())){
    			event.setCancelled(true);
    		}
    	}else if(event.getEntity() instanceof Skeleton){
    		if(skeletons.contains((Skeleton)event.getEntity())){
    			event.setCancelled(true);
    		}
    	}
    }
    
    public void saveAllZombies(){
    	for(Zombie z : zombies){
    		String num = Integer.toString(r.nextInt());
    		getConfig().set("entities.zombies.zombie" + num + ".x", z.getLocation().getBlockX());
    		getConfig().set("entities.zombies.zombie" + num + ".y", z.getLocation().getBlockY());
    		getConfig().set("entities.zombies.zombie" + num + ".z", z.getLocation().getBlockZ());
    		getConfig().set("entities.zombies.zombie" + num + ".world", z.getLocation().getWorld().getName());
    	}
    	this.saveConfig();
    }
    
    public void loadAllZombies(){
		for(String z : getConfig().getConfigurationSection("entities.zombies").getKeys(false)){
			Location zl = new Location(Bukkit.getWorld(getConfig().getString("entities.zombies." + z + ".world")), getConfig().getInt("entities.zombies." + z + ".x"), getConfig().getInt("entities.zombies." + z + ".y"), getConfig().getInt("entities.zombies." + z + ".z"));
			
			//TODO get them zombies and add to zombies-arraylist
		}
    }
	
    public void saveAllSkeletons(){
    	for(Zombie z : zombies){
    		String num = Integer.toString(r.nextInt());
    		getConfig().set("entities.skeletons.skeleton" + num + ".x", z.getLocation().getBlockX());
    		getConfig().set("entities.skeletons.skeleton" + num + ".y", z.getLocation().getBlockY());
    		getConfig().set("entities.skeletons.skeleton" + num + ".z", z.getLocation().getBlockZ());
    		getConfig().set("entities.skeletons.skeleton" + num + ".world", z.getLocation().getWorld().getName());
    	}
    	this.saveConfig();
    }
    
    public void loadAllSkeletons(){
		for(String s : getConfig().getConfigurationSection("entities.skeletons").getKeys(false)){
			Location sl = new Location(Bukkit.getWorld(getConfig().getString("entities.skeletons." + s + ".world")), getConfig().getInt("entities.skeletons." + s + ".x"), getConfig().getInt("entities.skeletons." + s + ".y"), getConfig().getInt("entities.skeletons." + s + ".z"));
			
			//TODO get them skeletons and add to skeletons-arraylist
		}
    }
}
