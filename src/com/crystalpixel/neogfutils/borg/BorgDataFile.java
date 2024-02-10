package com.crystalpixel.neogfutils.borg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

public class BorgDataFile extends Application {

    short safetyResistance;
    short staggerResistance;
    short boostGaugeLimit;
    short boostGaugeFillDelay;
    short boostGaugeFillRate;
    short boostGaugeConsumptionJump;
    short boostGaugeConsumptionHover;
    short boostGaugeConsumptionAirDash;
    short boostGaugeConsumptionGlide;
    short unknown12;
    short unknown14;
    short unknown16;
    float startWalkSpeedFront;
    float startWalkSpeedFrontAngle;
    float startWalkSpeedSideAngleFront;
    float startWalkSpeedSideAngleBack;
    float startWalkSpeedBack;
    float walkSpeed;
    float walkRotation;
    float hoverRotation;
    float glideRotation;
    byte groundJumpShortDuration;
    byte groundJumpLongDuration;
    byte groundJumpCooldown;
    byte airJumpDuration;
    byte airJumpCooldown;
    byte movementFlames;
    short liftAngle;
    float liftAcceleration;
    float groundJumpVerticalSpeed;
    float airJumpVerticalSpeed;
    float groundJumpHorizontalSpeed;
    float airIdleHorizontalSpeed;
    float airDashHorizontalSpeed;
    float airDashHorizontalAcceleration;
    float airDashVerticalSpeed;
    float airDashDuration;
    float liftVerticalAccelerationOffset;
    float airIdleVerticalAcceleration;
    float airDashVerticalAcceleration;
    float liftSpeedHorizontalMax;
    float liftSpeedVerticalMax;
    float airIdleFallSpeedMax;
    float homingRangeShotEnemy;
    float homingRangeShotAlly;
    float homingRangeMeleeEnemy;
    float homingRangeMeleeAlly;
    float homingRangeSpecialEnemy;
    float homingRangeSpecialAlly;
    float pushingStrength;
    byte damageReductionHandicap;
    byte damageReductionRageBorg;
    byte damageReductionRageForce;
    byte jumpCount;
    byte unknownA0;
    byte sizeLimitBig;
    byte sizeLimitSmall;
    byte sizeScaleType;
    short unknownA4;
    short unknownA6;
    short unknownAC;
    short unknownAE;
    byte unknownB6;
    byte unknownB7;
    float cameraYMelee;
    float cameraYShot;
    float cameraZMeleeMin;
    float cameraZMeleeMax;
    float cameraZShotMin;
    float cameraZShotMax;
    float cameraYMeleeRotateFar;
    float cameraYMeleeRotateClose;
    float cameraYShotRotateFar;
    float cameraYShotRotateClose;
    float cameraYMeleeGlideRotateFar;
    float cameraYMeleeGlideRotateClose;
    float cameraYShotGlideRotateFar;
    float cameraYShotGlideRotateClose;
    float frontStepSpeedStart;
    float frontStepAcceleration;
    float frontStepSpeedMin;
    float frontStepExtension;
    float sideStepSpeedStart;
    float sideStepAcceleration;
    float sideStepSpeedMin;
    float sideStepExtension;
    float backStepSpeedStart;
    float backStepAcceleration;
    float backStepSpeedMin;
    float backStepExtension;
    float targetRangeInner;
    float targetRangeOuter;
    float unknown128;
    float unknown129;
    float unknown12A;
    float unknown12B;
    float innerRangeSideStepRotation;
    float outerRangeSideStepRotation;
    float homingRangeShotGround;
    float homingRangeShotAir;
    float homingRangeShotGlide;
    float homingRangeMeleeGround;
    float homingRangeMeleeAir;
    float homingRangeMeleeGlide;
    float homingRangeSpecialGround;
    float homingRangeSpecialAir;
    float homingRangeSpecialGlide;
    float unknown158;
    float unknown159;
    float unknown15A;
    float unknown15B;
    short lookRotateLeft1;
    short lookRotateRightDivisor;
    short lookRotateUp2;
    short lookRotateDown2;
    short lookRotateLeft2;
    short lookRotateRightMultiplier;
    byte unknown168;
    byte unknown169;
    byte unknown16A;
    byte unknown16B;
    byte unknown16C;
    byte unknown16D;
    byte unknown16E;
    byte unknown16F;
    byte unknown170;
    byte unknown171;
    byte unknown172;
    byte unknown173;
    byte unknown174;
    byte unknown175;
    byte unknown176;
    byte unknown177;
    byte unknown178;
    byte unknown179;
    byte unknown17A;
    byte unknown17B;
    byte unknown17C;
    byte unknown17D;
    byte unknown17E;
    byte unknown17F;
    byte unknown180;
    byte unknown181;
    byte unknown182;
    byte voiceType;
    float chargeGaugeShot;
    float chargeGaugeMelee;
    float chargeGaugeSpecial;
    float victoryCameraY;
    float victoryCameraZMax;
    float victoryCameraZMin;
    byte unknown19C;
    byte unknown19D;
    byte unknown19E;
    byte unknown19F;
    byte specType;
    byte specIcon;
    byte specIconShieldOverride;
    byte specJumpType;
    byte specDefense;
    byte specShot;
    byte specAttack;
    byte specSpeed;
    byte specIconAttack1;
    byte specIconAttack2;
    byte specIconAttack3;
    byte specIconAttack4;
    byte specIconAttack5;
    byte specTribe;

    public void ReadBorgDataFile() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(
                new File("D:/Bordel/GotchaForce/FULL_AFS_FILE_DUMP/ExtractedPzz/pl0000/000C_pl0000data.bin")));
        this.safetyResistance = dis.readShort();
        this.staggerResistance = dis.readShort();
        this.boostGaugeLimit = dis.readShort();
        this.boostGaugeFillDelay = dis.readShort();
        this.boostGaugeFillRate = dis.readShort();
        this.boostGaugeConsumptionJump = dis.readShort();
        this.boostGaugeConsumptionHover = dis.readShort();
        this.boostGaugeConsumptionAirDash = dis.readShort();
        this.boostGaugeConsumptionGlide = dis.readShort();
        this.unknown12 = dis.readShort();
        this.unknown14 = dis.readShort();
        this.unknown16 = dis.readShort();
        this.startWalkSpeedFront = dis.readFloat();
        this.startWalkSpeedFrontAngle = dis.readFloat();
        this.startWalkSpeedSideAngleFront = dis.readFloat();
        this.startWalkSpeedSideAngleBack = dis.readFloat();
        this.startWalkSpeedBack = dis.readFloat();
        this.walkSpeed = dis.readFloat();
        this.walkRotation = dis.readFloat();
        this.hoverRotation = dis.readFloat();
        this.glideRotation = dis.readFloat();
        this.groundJumpShortDuration = dis.readByte();
        this.groundJumpLongDuration = dis.readByte();
        this.groundJumpCooldown = dis.readByte();
        this.airJumpDuration = dis.readByte();
        this.airJumpCooldown = dis.readByte();
        this.movementFlames = dis.readByte();
        this.liftAngle = dis.readShort();
        this.liftAcceleration = dis.readFloat();
        this.groundJumpVerticalSpeed = dis.readFloat();
        this.airJumpVerticalSpeed = dis.readFloat();
        this.groundJumpHorizontalSpeed = dis.readFloat();
        this.airIdleHorizontalSpeed = dis.readFloat();
        this.airDashHorizontalSpeed = dis.readFloat();
        this.airDashHorizontalAcceleration = dis.readFloat();
        this.airDashVerticalSpeed = dis.readFloat();
        this.airDashDuration = dis.readFloat();
        this.liftVerticalAccelerationOffset = dis.readFloat();
        this.airIdleVerticalAcceleration = dis.readFloat();
        this.airDashVerticalAcceleration = dis.readFloat();
        this.liftSpeedHorizontalMax = dis.readFloat();
        this.liftSpeedVerticalMax = dis.readFloat();
        this.airIdleFallSpeedMax = dis.readFloat();
        this.homingRangeShotEnemy = dis.readFloat();
        this.homingRangeShotAlly = dis.readFloat();
        this.homingRangeMeleeEnemy = dis.readFloat();
        this.homingRangeMeleeAlly = dis.readFloat();
        this.homingRangeSpecialEnemy = dis.readFloat();
        this.homingRangeSpecialAlly = dis.readFloat();
        this.pushingStrength = dis.readFloat();
        this.damageReductionHandicap = dis.readByte();
        this.damageReductionRageBorg = dis.readByte();
        this.damageReductionRageForce = dis.readByte();
        this.jumpCount = dis.readByte();
        this.unknownA0 = dis.readByte();
        this.sizeLimitBig = dis.readByte();
        this.sizeLimitSmall = dis.readByte();
        this.sizeScaleType = dis.readByte();
        dis.readByte(); // Skip field58_0xa4
        dis.readByte(); // Skip field59_0xa5
        dis.readByte(); // Skip field60_0xa6
        dis.readByte(); // Skip field61_0xa7
        this.unknownA4 = dis.readShort();
        this.unknownA6 = dis.readShort();
        this.unknownAC = dis.readShort();
        this.unknownAE = dis.readShort();
        dis.readByte(); // Skip field66_0xb0
        dis.readByte(); // Skip field67_0xb1
        dis.readByte(); // Skip field68_0xb2
        dis.readByte(); // Skip field69_0xb3
        dis.readByte(); // Skip field70_0xb4
        dis.readByte(); // Skip field71_0xb5
        this.unknownB6 = dis.readByte();
        dis.readByte(); // Skip field73_0xb7
        this.cameraYMelee = dis.readFloat();
        this.cameraYShot = dis.readFloat();
        this.cameraZMeleeMin = dis.readFloat();
        this.cameraZMeleeMax = dis.readFloat();
        this.cameraZShotMin = dis.readFloat();
        this.cameraZShotMax = dis.readFloat();
        this.cameraYMeleeRotateFar = dis.readFloat();
        this.cameraYMeleeRotateClose = dis.readFloat();
        this.cameraYShotRotateFar = dis.readFloat();
        this.cameraYShotRotateClose = dis.readFloat();
        this.cameraYMeleeGlideRotateFar = dis.readFloat();
        this.cameraYMeleeGlideRotateClose = dis.readFloat();
        this.cameraYShotGlideRotateFar = dis.readFloat();
        this.cameraYShotGlideRotateClose = dis.readFloat();
        this.frontStepSpeedStart = dis.readFloat();
        this.frontStepAcceleration = dis.readFloat();
        this.frontStepSpeedMin = dis.readFloat();
        this.frontStepExtension = dis.readFloat();
        this.sideStepSpeedStart = dis.readFloat();
        this.sideStepAcceleration = dis.readFloat();
        this.sideStepSpeedMin = dis.readFloat();
        this.sideStepExtension = dis.readFloat();
        this.backStepSpeedStart = dis.readFloat();
        this.backStepAcceleration = dis.readFloat();
        this.backStepSpeedMin = dis.readFloat();
        this.backStepExtension = dis.readFloat();
        this.targetRangeInner = dis.readFloat();
        this.targetRangeOuter = dis.readFloat();
        dis.readByte(); // Skip field102_0x128
        dis.readByte(); // Skip field103_0x129
        dis.readByte(); // Skip field104_0x12a
        dis.readByte(); // Skip field105_0x12b
        this.innerRangeSideStepRotation = dis.readFloat();
        this.outerRangeSideStepRotation = dis.readFloat();
        this.homingRangeShotGround = dis.readFloat();
        this.homingRangeShotAir = dis.readFloat();
        this.homingRangeShotGlide = dis.readFloat();
        this.homingRangeMeleeGround = dis.readFloat();
        this.homingRangeMeleeAir = dis.readFloat();
        this.homingRangeMeleeGlide = dis.readFloat();
        this.homingRangeSpecialGround = dis.readFloat();
        this.homingRangeSpecialAir = dis.readFloat();
        this.homingRangeSpecialGlide = dis.readFloat();
        dis.readByte(); // Skip field117_0x158
        dis.readByte(); // Skip field118_0x159
        dis.readByte(); // Skip field119_0x15a
        dis.readByte(); // Skip field120_0x15b
        this.lookRotateLeft1 = dis.readShort();
        this.lookRotateRightDivisor = dis.readShort();
        this.lookRotateUp2 = dis.readShort();
        this.lookRotateDown2 = dis.readShort();
        this.lookRotateLeft2 = dis.readShort();
        this.lookRotateRightMultiplier = dis.readShort();
        dis.readByte(); // Skip field127_0x168
        dis.readByte(); // Skip field128_0x169
        dis.readByte(); // Skip field129_0x16a
        dis.readByte(); // Skip field130_0x16b
        dis.readByte(); // Skip field131_0x16c
        dis.readByte(); // Skip field132_0x16d
        dis.readByte(); // Skip field133_0x16e
        dis.readByte(); // Skip field134_0x16f
        dis.readByte(); // Skip field135_0x170
        dis.readByte(); // Skip field136_0x171
        dis.readByte(); // Skip field137_0x172
        dis.readByte(); // Skip field138_0x173
        dis.readByte(); // Skip field139_0x174
        dis.readByte(); // Skip field140_0x175
        dis.readByte(); // Skip field141_0x176
        dis.readByte(); // Skip field142_0x177
        dis.readByte(); // Skip field143_0x178
        dis.readByte(); // Skip field144_0x179
        dis.readByte(); // Skip field145_0x17a
        dis.readByte(); // Skip field146_0x17b
        dis.readByte(); // Skip field147_0x17c
        dis.readByte(); // Skip field148_0x17d
        dis.readByte(); // Skip field149_0x17e
        dis.readByte(); // Skip field150_0x17f
        dis.readByte(); // Skip field151_0x180
        dis.readByte(); // Skip field152_0x181
        this.unknown182 = dis.readByte();
        this.voiceType = dis.readByte();
        this.chargeGaugeShot = dis.readFloat();
        this.chargeGaugeMelee = dis.readFloat();
        this.chargeGaugeSpecial = dis.readFloat();
        this.victoryCameraY = dis.readFloat();
        this.victoryCameraZMax = dis.readFloat();
        this.victoryCameraZMin = dis.readFloat();
        dis.readByte(); // Skip field161_0x19c
        dis.readByte(); // Skip field162_0x19d
        dis.readByte(); // Skip field163_0x19e
        dis.readByte(); // Skip field164_0x19f
        this.specType = dis.readByte();
        this.specIcon = dis.readByte();
        this.specIconShieldOverride = dis.readByte();
        this.specJumpType = dis.readByte();
        this.specDefense = dis.readByte();
        this.specShot = dis.readByte();
        this.specAttack = dis.readByte();
        this.specSpeed = dis.readByte();
        this.specIconAttack1 = dis.readByte();
        this.specIconAttack2 = dis.readByte();
        this.specIconAttack3 = dis.readByte();
        this.specIconAttack4 = dis.readByte();
        this.specIconAttack5 = dis.readByte();
        this.specTribe = dis.readByte();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lire le fichier de données Borg et obtenir le DataInputStream
        ReadBorgDataFile();

        // Créer une VBox pour afficher les valeurs lues
        VBox vbox = new VBox();

        // Ajouter les valeurs lues dans des étiquettes (Labels) à la VBox
        vbox.getChildren().addAll(
                new Label("Safety Resistance: " + safetyResistance),
                new Label("Stagger Resistance: " + staggerResistance),
                new Label("Boost Gauge Limit: " + boostGaugeLimit),
                new Label("Boost Gauge Fill Delay: " + boostGaugeFillDelay),
                new Label("Boost Gauge Fill Rate: " + boostGaugeFillRate),
                new Label("Boost Gauge Consumption Jump: " + boostGaugeConsumptionJump),
                new Label("Boost Gauge Consumption Hover: " + boostGaugeConsumptionHover),
                new Label("Boost Gauge Consumption Air Dash: " + boostGaugeConsumptionAirDash),
                new Label("Boost Gauge Consumption Glide: " + boostGaugeConsumptionGlide),
                new Label("Unknown 12: " + unknown12),
                new Label("Unknown 14: " + unknown14),
                new Label("Unknown 16: " + unknown16),
                new Label("Start Walk Speed Front: " + startWalkSpeedFront),
                new Label("Start Walk Speed Front Angle: " + startWalkSpeedFrontAngle),
                new Label("Start Walk Speed Side Angle Front: " + startWalkSpeedSideAngleFront),
                new Label("Start Walk Speed Side Angle Back: " + startWalkSpeedSideAngleBack),
                new Label("Start Walk Speed Back: " + startWalkSpeedBack),
                new Label("Walk Speed: " + walkSpeed),
                new Label("Walk Rotation: " + walkRotation),
                new Label("Hover Rotation: " + hoverRotation),
                new Label("Glide Rotation: " + glideRotation),
                new Label("Ground Jump Short Duration: " + groundJumpShortDuration),
                new Label("Ground Jump Long Duration: " + groundJumpLongDuration),
                new Label("Ground Jump Cooldown: " + groundJumpCooldown),
                new Label("Air Jump Duration: " + airJumpDuration),
                new Label("Air Jump Cooldown: " + airJumpCooldown),
                new Label("Movement Flames: " + movementFlames),
                new Label("Lift Angle: " + liftAngle),
                new Label("Lift Acceleration: " + liftAcceleration),
                new Label("Ground Jump Vertical Speed: " + groundJumpVerticalSpeed),
                new Label("Air Jump Vertical Speed: " + airJumpVerticalSpeed),
                new Label("Ground Jump Horizontal Speed: " + groundJumpHorizontalSpeed),
                new Label("Air Idle Horizontal Speed: " + airIdleHorizontalSpeed),
                new Label("Air Dash Horizontal Speed: " + airDashHorizontalSpeed),
                new Label("Air Dash Horizontal Acceleration: " + airDashHorizontalAcceleration),
                new Label("Air Dash Vertical Speed: " + airDashVerticalSpeed),
                new Label("Air Dash Duration: " + airDashDuration),
                new Label("Lift Vertical Acceleration Offset: " + liftVerticalAccelerationOffset),
                new Label("Air Idle Vertical Acceleration: " + airIdleVerticalAcceleration),
                new Label("Air Dash Vertical Acceleration: " + airDashVerticalAcceleration),
                new Label("Lift Speed Horizontal Max: " + liftSpeedHorizontalMax),
                new Label("Lift Speed Vertical Max: " + liftSpeedVerticalMax),
                new Label("Air Idle Fall Speed Max: " + airIdleFallSpeedMax),
                new Label("Homing Range Shot Enemy: " + homingRangeShotEnemy),
                new Label("Homing Range Shot Ally: " + homingRangeShotAlly),
                new Label("Homing Range Melee Enemy: " + homingRangeMeleeEnemy),
                new Label("Homing Range Melee Ally: " + homingRangeMeleeAlly),
                new Label("Homing Range Special Enemy: " + homingRangeSpecialEnemy),
                new Label("Homing Range Special Ally: " + homingRangeSpecialAlly),
                new Label("Pushing Strength: " + pushingStrength),
                new Label("Damage Reduction Handicap: " + damageReductionHandicap),
                new Label("Damage Reduction Rage Borg: " + damageReductionRageBorg),
                new Label("Damage Reduction Rage Force: " + damageReductionRageForce),
                new Label("Jump Count: " + jumpCount),
                new Label("Unknown A0: " + unknownA0),
                new Label("Size Limit Big: " + sizeLimitBig),
                new Label("Size Limit Small: " + sizeLimitSmall),
                new Label("Size Scale Type: " + sizeScaleType),
                new Label("Unknown A4: " + unknownA4),
                new Label("Unknown A6: " + unknownA6),
                new Label("Unknown AC: " + unknownAC),
                new Label("Unknown AE: " + unknownAE),
                new Label("Unknown B6: " + unknownB6),
                new Label("Unknown B7: " + unknownB7),
                new Label("Camera Y Melee: " + cameraYMelee),
                new Label("Camera Y Shot: " + cameraYShot),
                new Label("Camera Z Melee Min: " + cameraZMeleeMin),
                new Label("Camera Z Melee Max: " + cameraZMeleeMax),
                new Label("Camera Z Shot Min: " + cameraZShotMin),
                new Label("Camera Z Shot Max: " + cameraZShotMax),
                new Label("Camera Y Melee Rotate Far: " + cameraYMeleeRotateFar),
                new Label("Camera Y Melee Rotate Close: " + cameraYMeleeRotateClose),
                new Label("Camera Y Shot Rotate Far: " + cameraYShotRotateFar),
                new Label("Camera Y Shot Rotate Close: " + cameraYShotRotateClose),
                new Label("Camera Y Melee Glide Rotate Far: " + cameraYMeleeGlideRotateFar),
                new Label("Camera Y Melee Glide Rotate Close: " + cameraYMeleeGlideRotateClose),
                new Label("Camera Y Shot Glide Rotate Far: " + cameraYShotGlideRotateFar),
                new Label("Camera Y Shot Glide Rotate Close: " + cameraYShotGlideRotateClose),
                new Label("Front Step Speed Start: " + frontStepSpeedStart),
                new Label("Front Step Acceleration: " + frontStepAcceleration),
                new Label("Front Step Speed Min: " + frontStepSpeedMin),
                new Label("Front Step Extension: " + frontStepExtension),
                new Label("Side Step Speed Start: " + sideStepSpeedStart),
                new Label("Side Step Acceleration: " + sideStepAcceleration),
                new Label("Side Step Speed Min: " + sideStepSpeedMin),
                new Label("Side Step Extension: " + sideStepExtension),
                new Label("Back Step Speed Start: " + backStepSpeedStart),
                new Label("Back Step Acceleration: " + backStepAcceleration),
                new Label("Back Step Speed Min: " + backStepSpeedMin),
                new Label("Back Step Extension: " + backStepExtension),
                new Label("Target Range Inner: " + targetRangeInner),
                new Label("Target Range Outer: " + targetRangeOuter),
                new Label("Inner Range Side Step Rotation: " + innerRangeSideStepRotation),
                new Label("Outer Range Side Step Rotation: " + outerRangeSideStepRotation),
                new Label("Homing Range Shot Ground: " + homingRangeShotGround),
                new Label("Homing Range Shot Air: " + homingRangeShotAir),
                new Label("Homing Range Shot Glide: " + homingRangeShotGlide),
                new Label("Homing Range Melee Ground: " + homingRangeMeleeGround),
                new Label("Homing Range Melee Air: " + homingRangeMeleeAir),
                new Label("Homing Range Melee Glide: " + homingRangeMeleeGlide),
                new Label("Homing Range Special Ground: " + homingRangeSpecialGround),
                new Label("Homing Range Special Air: " + homingRangeSpecialAir),
                new Label("Homing Range Special Glide: " + homingRangeSpecialGlide),
                new Label("Unknown 158: " + unknown158),
                new Label("Unknown 159: " + unknown159),
                new Label("Unknown 15A: " + unknown15A),
                new Label("Unknown 15B: " + unknown15B),
                new Label("Look Rotate Left 1: " + lookRotateLeft1),
                new Label("Look Rotate Right Divisor: " + lookRotateRightDivisor),
                new Label("Look Rotate Up 2: " + lookRotateUp2),
                new Label("Look Rotate Down 2: " + lookRotateDown2),
                new Label("Look Rotate Left 2: " + lookRotateLeft2),
                new Label("Look Rotate Right Multiplier: " + lookRotateRightMultiplier),
                new Label("Unknown 168: " + unknown168),
                new Label("Unknown 169: " + unknown169),
                new Label("Unknown 16A: " + unknown16A),
                new Label("Unknown 16B: " + unknown16B),
                new Label("Unknown 16C: " + unknown16C),
                new Label("Unknown 16D: " + unknown16D),
                new Label("Unknown 16E: " + unknown16E),
                new Label("Unknown 16F: " + unknown16F),
                new Label("Unknown 170: " + unknown170),
                new Label("Unknown 171: " + unknown171),
                new Label("Unknown 172: " + unknown172),
                new Label("Unknown 173: " + unknown173),
                new Label("Unknown 174: " + unknown174),
                new Label("Unknown 175: " + unknown175),
                new Label("Unknown 176: " + unknown176),
                new Label("Unknown 177: " + unknown177),
                new Label("Unknown 178: " + unknown178),
                new Label("Unknown 179: " + unknown179),
                new Label("Unknown 17A: " + unknown17A),
                new Label("Unknown 17B: " + unknown17B),
                new Label("Unknown 17C: " + unknown17C),
                new Label("Unknown 17D: " + unknown17D),
                new Label("Unknown 17E: " + unknown17E),
                new Label("Unknown 17F: " + unknown17F),
                new Label("Unknown 180: " + unknown180),
                new Label("Unknown 181: " + unknown181),
                new Label("Unknown 182: " + unknown182),
                new Label("Voice Type: " + voiceType),
                new Label("Charge Gauge Shot: " + chargeGaugeShot),
                new Label("Charge Gauge Melee: " + chargeGaugeMelee),
                new Label("Charge Gauge Special: " + chargeGaugeSpecial),
                new Label("Victory Camera Y: " + victoryCameraY),
                new Label("Victory Camera Z Max: " + victoryCameraZMax),
                new Label("Victory Camera Z Min: " + victoryCameraZMin),
                new Label("Unknown 19C: " + unknown19C),
                new Label("Unknown 19D: " + unknown19D),
                new Label("Unknown 19E: " + unknown19E),
                new Label("Unknown 19F: " + unknown19F),
                new Label("Spec Type: " + specType),
                new Label("Spec Icon: " + specIcon),
                new Label("Spec Icon Shield Override: " + specIconShieldOverride),
                new Label("Spec Jump Type: " + specJumpType),
                new Label("Spec Defense: " + specDefense),
                new Label("Spec Shot: " + specShot),
                new Label("Spec Attack: " + specAttack),
                new Label("Spec Speed: " + specSpeed),
                new Label("Spec Icon Attack 1: " + specIconAttack1),
                new Label("Spec Icon Attack 2: " + specIconAttack2),
                new Label("Spec Icon Attack 3: " + specIconAttack3),
                new Label("Spec Icon Attack 4: " + specIconAttack4),
                new Label("Spec Icon Attack 5: " + specIconAttack5),
                new Label("Spec Tribe: " + specTribe));
        // Créer un ScrollPane et ajouter la VBox à son contenu
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane à la scène

        // Créer une scène avec le ScrollPane et afficher la scène dans la fenêtre
        Scene scene = new Scene(scrollPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Borg Data");
        primaryStage.show();
    }
}
