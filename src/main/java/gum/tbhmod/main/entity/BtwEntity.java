package gum.tbhmod.main.entity;

import gum.tbhmod.main.entity.goals.JumpiesGoal;
import gum.tbhmod.main.init.AdvancementRegistry;
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
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BtwEntity extends TameableEntity {

    //TODO make the creatures food
    //TODO give funny little actions

    public static entitySettings settings = new entitySettings(
            "btw_creature",
            BtwEntity::new,
            SpawnGroup.CREATURE,
            BiomeTags.IS_FOREST,
            0.8f, 0.8f,
            5, 3, 8
    );

    protected BtwEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }


    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25F));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1F));
        this.goalSelector.add(3, new SitGoal(this));
        this.goalSelector.add(4, new TemptGoal(this, 0.9F, Ingredient.ofItems(ItemRegistry.COLA), false));
        this.goalSelector.add(7, new FollowOwnerGoal(this, 1.25F, 5.0F, 6.0F, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(9, new LookAtEntityGoal(this, BtwEntity.class, 8.0F));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.world.isClient) {
            boolean bl = this.isOwner(player) || this.isTamed() || itemStack.isOf(ItemRegistry.COLA) && !this.isTamed();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        }else{
            if (this.eat(player, itemStack)) {
                if (!this.isTamed()) {
                    if (this.random.nextInt(2) == 0) {
                        AdvancementRegistry.TAMED_BTW.trigger(Objects.requireNonNull(getServer()).getPlayerManager().getPlayer(player.getUuid()));

                        this.setOwner(player);
                        this.navigation.stop();
                        this.setSitting(true);
                        this.jumping = false;
                        this.world.sendEntityStatus(this, (byte)7);
                    }else{
                        this.world.sendEntityStatus(this, (byte)6);
                    }
                }

                return ActionResult.SUCCESS;
            }else{
                ActionResult actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted() && this.isOwner(player)) {
                    this.setSitting(!this.isSitting());
                    this.jumping = false;
                    this.navigation.stop();
                    return ActionResult.SUCCESS;
                }
            }
            return super.interactMob(player, hand);
        }
    }

    private void spawnParticles() {
        ParticleEffect particleEffect = ParticleTypes.HEART;
        for(int i = 0; i < 7; ++i) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.world.addParticle(particleEffect, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), d, e, f);
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundRegistry.BTW_AMBIENT;
    }
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.BTW_HURT;
    }
    protected SoundEvent getDeathSound() {
        return SoundRegistry.BTW_DIES;
    }

    protected boolean eat(PlayerEntity player, ItemStack stack) {
        if(stack.isOf(ItemRegistry.COLA) && this.onGround){
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            this.heal(4);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 20, 5), player);


            world.playSound(null, this.getBlockPos(), SoundRegistry.YAHOO, SoundCategory.NEUTRAL, 1f, 0.9f + (random.nextFloat() * 0.2f));

            world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 1f, 0.95f+(random.nextFloat()*0.1f));
            return true;
        }
        return false;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.7F;
    }

    public static DefaultAttributeContainer.Builder createBtwAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }

    @Override
    public float getPathfindingFavor (BlockPos pos, WorldView world) {
        return 10;
    }

    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }

    @Override
    public boolean cannotBeSilenced() {
        return super.cannotBeSilenced();
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
