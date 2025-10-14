package zord.callmezord.zordscreatures.entity.ai;

import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import zord.callmezord.zordscreatures.entity.GenderedAnimal;

public class AiGenderedBreeding<T extends Animal & GenderedAnimal> extends BreedGoal {


    public AiGenderedBreeding(Animal animal, double speedModifier) {
        super(animal, speedModifier);
    }


    @Override
    public boolean canUse() {

        if(!super.canUse()) return false;

        //GENDER CHECKER, ONLY WORKS IF THEY'RE DIFFERENT GENERDERS!
        if (this.animal instanceof GenderedAnimal MobA && partner instanceof GenderedAnimal MobB) {
            return MobA.isMale() != MobB.isMale();
        }
        return false;
    }
}
