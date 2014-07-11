package it.github.mats391.randomcoords;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PlayerCoords implements Listener
{

	public static void addNewPlayer( final Player player ) {
		final int offsetX = MINIMUM_OFFSET + rand.nextInt( -2 * MINIMUM_OFFSET );
		final int offsetZ = MINIMUM_OFFSET + rand.nextInt( -2 * MINIMUM_OFFSET );

		playerCoords.put( player.getUniqueId(), new int[] { roundToClosest16( player.getLocation().getBlockX() + offsetX ),
				roundToClosest16( player.getLocation().getBlockZ() + offsetZ ) } );
	}

	public static void clean( final Player player ) {
		playerCoords.remove( player.getUniqueId() );
	}

	public static int getChunkX( final Player player ) {
		if ( !playerCoords.containsKey( player.getUniqueId() ) )
			addNewPlayer( player );

		return playerCoords.get( player.getUniqueId() )[0] / 16;
	}

	public static int getChunkZ( final Player player ) {
		if ( !playerCoords.containsKey( player.getUniqueId() ) )
			addNewPlayer( player );

		return playerCoords.get( player.getUniqueId() )[1] / 16;
	}

	public static int getX( final Player player ) {
		if ( !playerCoords.containsKey( player.getUniqueId() ) )
			addNewPlayer( player );

		return playerCoords.get( player.getUniqueId() )[0];
	}

	public static int getZ( final Player player ) {
		if ( !playerCoords.containsKey( player.getUniqueId() ) )
			addNewPlayer( player );

		return playerCoords.get( player.getUniqueId() )[1];
	}

	private static int roundToClosest16( final double d ) {
		return (int) ( d / 16 ) * 16;
	}

	private static ConcurrentHashMap<UUID, int[]>	playerCoords	= new ConcurrentHashMap<UUID, int[]>();

	private final static Random						rand			= new Random();

	private static final int						MINIMUM_OFFSET	= -7500;

}
