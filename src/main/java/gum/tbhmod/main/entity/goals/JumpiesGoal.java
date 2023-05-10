package gum.tbhmod.main.entity.goals;

import java.util.EnumSet;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class JumpiesGoal extends Goal {
    private final MobEntity mob;
    private final World world;
    private int timer;

    public JumpiesGoal(MobEntity mob) {
        this.mob = mob;
        this.world = mob.world;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    public boolean canStart() {
        return (this.mob.getRandom().nextInt(75) == 0);
    }

    public void start() {
        this.timer = this.getTickCount(40);
        this.world.sendEntityStatus(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.timer = 0;
    }

    public boolean shouldContinue() {
        return this.timer > 0;
    }

    public void tick() {
        this.timer = Math.max(0, this.timer - 1);
        JumpControl jumpControl = mob.getJumpControl();
        jumpControl.setActive();
    }
}
