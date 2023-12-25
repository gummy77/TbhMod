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
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class TbhEntity extends TameableEntity {
    private static final Ingredient TAMING_INGREDIENT;
    private static final entitySettings settings = new entitySettings(
            "tbh_creature",
            SpawnGroup.CREATURE,
            BiomeTags.IS_FOREST,
            0.8f, 0.8f,
            3, 1, 4
    );

    public TbhEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }

    public static entitySettings getSettings() {
        return settings;
    }

    protected void initGoals() {
        TemptGoal temptGoal = new TemptGoal(this, 1.1, TAMING_INGREDIENT, false);
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, temptGoal);
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, TbhEntity.class, 6.0F));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.3, 2.0F, 2.0F, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(9, new JumpiesGoal(this));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.world.isClient) {
            boolean bl = this.isOwner(player) || this.isTamed() || TAMING_INGREDIENT.test(itemStack) && !this.isTamed();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            if (this.eat(player, itemStack)) {
                if (!this.isTamed()) {
                    if (this.random.nextInt(2) == 0) {
                        AdvancementRegistry.TAMED_TBH.trigger(Objects.requireNonNull(getServer()).getPlayerManager().getPlayer(player.getUuid()));

                        this.setOwner(player);
                        this.navigation.stop();
                        this.setSitting(true);
                        this.jumping = false;
                        this.world.sendEntityStatus(this, (byte) 7);
                    } else {
                        this.world.sendEntityStatus(this, (byte) 6);
                    }
                }

                return ActionResult.success(this.world.isClient);
            } else {
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

    protected SoundEvent getAmbientSound() {
        return SoundRegistry.TBH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.TBH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundRegistry.TBH_DIES;
    }

    protected boolean eat(PlayerEntity player, ItemStack stack) {
        if (TAMING_INGREDIENT.test(stack) && this.onGround) {
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            this.jump();
            this.heal(4);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 20, 5), player);

            if (this.random.nextInt(3) == 0) {
                world.playSound(null, this.getBlockPos(), SoundRegistry.COLA, SoundCategory.NEUTRAL, 1f, 0.9f + (random.nextFloat() * 0.2f));
            } else {
                world.playSound(null, this.getBlockPos(), SoundRegistry.YIPPEE, SoundCategory.NEUTRAL, 1f, 0.9f + (random.nextFloat() * 0.2f));
            }
            if(stack.isOf(Items.SAND)){
                world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_SAND_BREAK, SoundCategory.NEUTRAL, 1f, 0.95f + (random.nextFloat() * 0.1f));
            } else {
                world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 1f, 0.95f + (random.nextFloat() * 0.1f));
            }
            return true;
        }
        return false;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.7F;
    }

    public static DefaultAttributeContainer.Builder createTbhAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return 10;
    }

    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    static {
        TAMING_INGREDIENT = Ingredient.ofItems(ItemRegistry.COLA, ItemRegistry.COLA_BOTTLE, Items.SAND);
    }
}
