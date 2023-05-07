package gum.tbhmod.main.entity;

import gum.tbhmod.main.entity.goals.JumpiesGoal;
import gum.tbhmod.main.init.ItemRegistry;
import gum.tbhmod.main.init.SoundRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class TbhEntity extends PathAwareEntity {
    public static entitySettings settings = new entitySettings(
            "tbh_creature",
            TbhEntity::new,
            SpawnGroup.CREATURE,
            BiomeTags.IS_FOREST,
            0.8f, 0.8f,
            5, 1, 5
    );

    protected TbhEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new TemptGoal(this, 1.1, Ingredient.ofItems(ItemRegistry.COLA), false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, TbhEntity.class, 6.0F));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(7, new JumpiesGoal(this));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.isBreedingItem(itemStack)) {
            if(this.onGround) {
                this.jump();
            }
            if (!this.world.isClient) {
                this.eat(player, itemStack);
                world.playSound(null, this.getBlockPos(), SoundRegistry.YIPPEE, SoundCategory.NEUTRAL, 1f,  0.9f+(random.nextFloat()*0.2f));
                world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 1f, 0.95f+(random.nextFloat()*0.1f));
                return ActionResult.SUCCESS;
            }else{
                return ActionResult.CONSUME;
            }
        }
        return super.interactMob(player, hand);
    }

    protected SoundEvent getAmbientSound() {
        return SoundRegistry.YIPPEE;
    }
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.TBH_HURT;
    }
    protected SoundEvent getDeathSound() {
        return SoundRegistry.TBH_DIES;
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ItemRegistry.COLA);
    }

    protected void eat(PlayerEntity player, ItemStack stack) {
        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if(stack.isOf(ItemRegistry.COLA)){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*20, 5), player);
        }
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.7F;
    }

    public static DefaultAttributeContainer.Builder createTbhAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    public float getPathfindingFavor (BlockPos pos, WorldView world) {
        return 10;
    }

    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }
}
