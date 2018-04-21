import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class KingCrabRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 4), new Bug());
        world.add(new Location(4, 4), new KingCrab());
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(4, 3), new Flower());
        world.add(new Location(4,5 ), new Rock());
        /*
        world.add(new Location(4, 5), new Flower());
        world.add(new Location(2, 2), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(3, 8), new Flower());
        world.add(new Location(6, 5), new Bug());
        world.add(new Location(5, 3), new Bug());
        world.add(new Location(4, 5), new KingCrab());
        world.add(new Location(6, 1), new KingCrab());
        world.add(new Location(7, 4), new KingCrab());
        */
        world.show();
    }
}