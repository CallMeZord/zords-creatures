package zord.callmezord.zordscreatures.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import zord.callmezord.zordscreatures.entity.entities.Iguana;

import java.util.EnumSet;
import java.util.Optional;

public class AiBasking extends Goal {

    //VALUES
    private final PathfinderMob mob;
    private final double speedModifier;
    private final int baseDelayTicks;
    private final int randomExtraDelay;
    private final int baskDurationTicks;
    private final int searchRadius;

    private int delayTimer;
    private int baskTimer;
    private BlockPos targetPos;


    //SPECIFIER
    public AiBasking(PathfinderMob mob, double speedModifier, int baseDelayTicks, int randomExtraDelay, int baskDurationTicks, int searchRadius) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.baseDelayTicks = baseDelayTicks;
        this.randomExtraDelay = randomExtraDelay;
        this.baskDurationTicks = baskDurationTicks;
        this.searchRadius = searchRadius;
        delayTimer = baseDelayTicks;

        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }


    //USABILITY CHECKS
    @Override
    public boolean canUse() {
        Level level = mob.level();

        //DELAY
        if (--delayTimer > 0) return false;
        //DELAY SETTER
        delayTimer = baseDelayTicks + mob.getRandom().nextInt(randomExtraDelay);

        if (level.isRaining()) return false;
        if (mob.getTarget() != null) return false;

        Optional<BlockPos> sunPos = findSunnySpot();
        if (sunPos.isPresent()) {

            targetPos = sunPos.get();
            return true;
        }
        return false;
    }


    @Override
    public boolean canContinueToUse() {
        Level level = mob.level();
        if (baskTimer <= 0) return false;
        if (!level.isBrightOutside() || level.isRaining()) return false;
        return level.canSeeSky(mob.blockPosition());
    }


    //STARTS
    @Override
    public void start() {
        if (targetPos != null) {
            mob.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speedModifier);
        }
        baskTimer = baskDurationTicks;
        //MOB USERS
        if (mob instanceof Iguana iguana) iguana.setBasking(true);
    }

    @Override
    public void stop() {
        targetPos = null;
        mob.getNavigation().stop();
        delayTimer = baseDelayTicks;
        //MOB LOSERS
        if (mob instanceof Iguana iguana) iguana.setBasking(false);
    }
    //AND STOPS ^


    //EACH TICK
    @Override
    public void tick() {

        if (targetPos != null && mob.blockPosition().closerThan(targetPos, 1.5D)) {

            mob.getNavigation().stop();
            mob.getLookControl().setLookAt(targetPos.getX(), targetPos.getY(), targetPos.getZ());
        }
        baskTimer--;
    }



    private Optional<BlockPos> findSunnySpot() {
        Level level = mob.level();
        BlockPos basePos = mob.blockPosition();
        BlockPos prefPos = null;
        int top = Integer.MIN_VALUE;

        for (int dx = -searchRadius; dx <= searchRadius; dx++) {
            for (int dz = -searchRadius; dz <= searchRadius; dz++) {

                BlockPos pos = basePos.offset(dx, 0, dz);
                BlockPos ground = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, pos);

                if (level.canSeeSky(ground) && level.getBlockState(ground.below()).isSolid()) {

                    if (ground.getY() > top) {

                        if (mob.getNavigation().isStableDestination(ground)) {

                            top = ground.getY();
                            prefPos = ground;

                        }

                    }

                }

            }

        }
        return Optional.ofNullable(prefPos);
    }

}
