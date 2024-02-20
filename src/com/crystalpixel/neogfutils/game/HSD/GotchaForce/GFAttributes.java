package com.crystalpixel.neogfutils.game.HSD.GotchaForce;

import com.crystalpixel.neogfutils.utils.accesor.Accessor;

public class GFAttributes extends Accessor {

    public GFAttributes() {
        super.trimmedSize = 0x1B0;
        super.init();
    }

    public short getSafetyResistance() {
        return _s.getShort(0x0);
    }

    public void setSafetyResistance(short value) {
        _s.setShort(0x0, value);
    }

    public short getStaggerResistance() {
        return _s.getShort(0x2);
    }

    public void setStaggerResistance(short value) {
        _s.setShort(0x2, value);
    }

    public short getBoostGaugeLimit() {
        return _s.getShort(0x4);
    }

    public void setBoostGaugeLimit(short value) {
        _s.setShort(0x4, value);
    }

    public short getBoostGaugeFillDelay() {
        return _s.getShort(0x6);
    }

    public void setBoostGaugeFillDelay(short value) {
        _s.setShort(0x6, value);
    }

    public short getBoostGaugeFillRate() {
        return _s.getShort(0x8);
    }

    public void setBoostGaugeFillRate(short value) {
        _s.setShort(0x8, value);
    }

    public short getBoostGaugeConsumptionJump() {
        return _s.getShort(0xA);
    }

    public void setBoostGaugeConsumptionJump(short value) {
        _s.setShort(0xA, value);
    }

    public short getBoostGaugeConsumptionHover() {
        return _s.getShort(0xC);
    }

    public void setBoostGaugeConsumptionHover(short value) {
        _s.setShort(0xC, value);
    }

    public short getBoostGaugeConsumptionAirDash() {
        return _s.getShort(0xE);
    }

    public void setBoostGaugeConsumptionAirDash(short value) {
        _s.setShort(0xE, value);
    }

    public short getBoostGaugeConsumptionGlide() {
        return _s.getShort(0x10);
    }

    public void setBoostGaugeConsumptionGlide(short value) {
        _s.setShort(0x10, value);
    }

    public short getUnknown12() {
        return _s.getShort(0x12);
    }

    public void setUnknown12(short value) {
        _s.setShort(0x12, value);
    }

    public short getUnknown14() {
        return _s.getShort(0x14);
    }

    public void setUnknown14(short value) {
        _s.setShort(0x14, value);
    }

    public short getUnknown16() {
        return _s.getShort(0x16);
    }

    public void setUnknown16(short value) {
        _s.setShort(0x16, value);
    }

    public float getStartWalkSpeedFront() {
        return _s.getFloat(0x18);
    }

    public void setStartWalkSpeedFront(float value) {
        _s.setFloat(0x18, value);
    }

    public float getStartWalkSpeedFrontAngle() {
        return _s.getFloat(0x1C);
    }

    public void setStartWalkSpeedFrontAngle(float value) {
        _s.setFloat(0x1C, value);
    }

    public float getStartWalkSpeedSideAngleFront() {
        return _s.getFloat(0x20);
    }

    public void setStartWalkSpeedSideAngleFront(float value) {
        _s.setFloat(0x20, value);
    }

    public float getStartWalkSpeedSideAngleBack() {
        return _s.getFloat(0x24);
    }

    public void setStartWalkSpeedSideAngleBack(float value) {
        _s.setFloat(0x24, value);
    }

    public float getStartWalkSpeedBack() {
        return _s.getFloat(0x28);
    }

    public void setStartWalkSpeedBack(float value) {
        _s.setFloat(0x28, value);
    }

    public float getWalkSpeed() {
        return _s.getFloat(0x2C);
    }

    public void setWalkSpeed(float value) {
        _s.setFloat(0x2C, value);
    }

    public float getWalkRotation() {
        return _s.getFloat(0x30);
    }

    public void setWalkRotation(float value) {
        _s.setFloat(0x30, value);
    }

    public float getHoverRotation() {
        return _s.getFloat(0x34);
    }

    public void setHoverRotation(float value) {
        _s.setFloat(0x34, value);
    }

    public float getGlideRotation() {
        return _s.getFloat(0x38);
    }

    public void setGlideRotation(float value) {
        _s.setFloat(0x38, value);
    }

    public byte getGroundJumpShortDuration() {
        return _s.get(0x3C);
    }

    public void setGroundJumpShortDuration(byte value) {
        _s.set(0x3C, value);
    }

    public byte getGroundJumpLongDuration() {
        return _s.get(0x3D);
    }

    public void setGroundJumpLongDuration(byte value) {
        _s.set(0x3D, value);
    }

    public byte getGroundJumpCooldown() {
        return _s.get(0x3E);
    }

    public void setGroundJumpCooldown(byte value) {
        _s.set(0x3E, value);
    }

    public byte getAirJumpDuration() {
        return _s.get(0x3F);
    }

    public void setAirJumpDuration(byte value) {
        _s.set(0x3F, value);
    }

    public byte getAirJumpCooldown() {
        return _s.get(0x40);
    }

    public void setAirJumpCooldown(byte value) {
        _s.set(0x40, value);
    }

    public byte getMovementFlames() {
        return _s.get(0x41);
    }

    public void setMovementFlames(byte value) {
        _s.set(0x41, value);
    }

    public short getLiftAngle() {
        return _s.getShort(0x42);
    }

    public void setLiftAngle(short value) {
        _s.setShort(0x42, value);
    }

    public float getLiftAcceleration() {
        return _s.getFloat(0x44);
    }

    public void setLiftAcceleration(float value) {
        _s.setFloat(0x44, value);
    }

    public float getGroundJumpVerticalSpeed() {
        return _s.getFloat(0x48);
    }

    public void setGroundJumpVerticalSpeed(float value) {
        _s.setFloat(0x48, value);
    }

    public float getAirJumpVerticalSpeed() {
        return _s.getFloat(0x4C);
    }

    public void setAirJumpVerticalSpeed(float value) {
        _s.setFloat(0x4C, value);
    }

    public float getGroundJumpHorizontalSpeed() {
        return _s.getFloat(0x50);
    }

    public void setGroundJumpHorizontalSpeed(float value) {
        _s.setFloat(0x50, value);
    }

    public float getAirIdleHorizontalSpeed() {
        return _s.getFloat(0x54);
    }

    public void setAirIdleHorizontalSpeed(float value) {
        _s.setFloat(0x54, value);
    }

    public float getAirDashHorizontalSpeed() {
        return _s.getFloat(0x58);
    }

    public void setAirDashHorizontalSpeed(float value) {
        _s.setFloat(0x58, value);
    }

    public float getAirDashHorizontalAcceleration() {
        return _s.getFloat(0x5C);
    }

    public void setAirDashHorizontalAcceleration(float value) {
        _s.setFloat(0x5C, value);
    }

    public float getAirDashVerticalSpeed() {
        return _s.getFloat(0x60);
    }

    public void setAirDashVerticalSpeed(float value) {
        _s.setFloat(0x60, value);
    }

    public float getAirDashDuration() {
        return _s.getFloat(0x64);
    }

    public void setAirDashDuration(float value) {
        _s.setFloat(0x64, value);
    }

    public float getLiftVerticalAccelerationOffset() {
        return _s.getFloat(0x68);
    }

    public void setLiftVerticalAccelerationOffset(float value) {
        _s.setFloat(0x68, value);
    }

    public float getAirIdleVerticalAcceleration() {
        return _s.getFloat(0x6C);
    }

    public void setAirIdleVerticalAcceleration(float value) {
        _s.setFloat(0x6C, value);
    }

    public float getAirDashVerticalAcceleration() {
        return _s.getFloat(0x70);
    }

    public void setAirDashVerticalAcceleration(float value) {
        _s.setFloat(0x70, value);
    }

    public float getLiftSpeedHorizontalMax() {
        return _s.getFloat(0x74);
    }

    public void setLiftSpeedHorizontalMax(float value) {
        _s.setFloat(0x74, value);
    }

    public float getLiftSpeedVerticalMax() {
        return _s.getFloat(0x78);
    }

    public void setLiftSpeedVerticalMax(float value) {
        _s.setFloat(0x78, value);
    }

    public float getAirIdleFallSpeedMax() {
        return _s.getFloat(0x7C);
    }

    public void setAirIdleFallSpeedMax(float value) {
        _s.setFloat(0x7C, value);
    }

    public float getHomingRangeShotEnemy() {
        return _s.getFloat(0x80);
    }

    public void setHomingRangeShotEnemy(float value) {
        _s.setFloat(0x80, value);
    }

    public float getHomingRangeShotAlly() {
        return _s.getFloat(0x84);
    }

    public void setHomingRangeShotAlly(float value) {
        _s.setFloat(0x84, value);
    }

    public float getHomingRangeMeleeEnemy() {
        return _s.getFloat(0x88);
    }

    public void setHomingRangeMeleeEnemy(float value) {
        _s.setFloat(0x88, value);
    }

    public float getHomingRangeMeleeAlly() {
        return _s.getFloat(0x8C);
    }

    public void setHomingRangeMeleeAlly(float value) {
        _s.setFloat(0x8C, value);
    }

    public float getHomingRangeSpecialEnemy() {
        return _s.getFloat(0x90);
    }

    public void setHomingRangeSpecialEnemy(float value) {
        _s.setFloat(0x90, value);
    }

    public float getHomingRangeSpecialAlly() {
        return _s.getFloat(0x94);
    }

    public void setHomingRangeSpecialAlly(float value) {
        _s.setFloat(0x94, value);
    }

    public float getPushingStrength() {
        return _s.getFloat(0x98);
    }

    public void setPushingStrength(float value) {
        _s.setFloat(0x98, value);
    }

    public byte getDamageReductionHandicap() {
        return _s.get(0x9C);
    }

    public void setDamageReductionHandicap(byte value) {
        _s.set(0x9C, value);
    }

    public byte getDamageReductionRageBorg() {
        return _s.get(0x9D);
    }

    public void setDamageReductionRageBorg(byte value) {
        _s.set(0x9D, value);
    }

    public byte getDamageReductionRageForce() {
        return _s.get(0x9E);
    }

    public void setDamageReductionRageForce(byte value) {
        _s.set(0x9E, value);
    }

    public byte getJumpCount() {
        return _s.get(0x9F);
    }

    public void setJumpCount(byte value) {
        _s.set(0x9F, value);
    }

    public byte getUnknownA0() {
        return _s.get(0xA0);
    }

    public void setUnknownA0(byte value) {
        _s.set(0xA0, value);
    }

    public byte getSizeLimitBig() {
        return _s.get(0xA1);
    }

    public void setSizeLimitBig(byte value) {
        _s.set(0xA1, value);
    }

    public byte getSizeLimitSmall() {
        return _s.get(0xA2);
    }

    public void setSizeLimitSmall(byte value) {
        _s.set(0xA2, value);
    }

    public byte getSizeScaleType() {
        return _s.get(0xA3);
    }

    public void setSizeScaleType(byte value) {
        _s.set(0xA3, value);
    }

    public short getUnknownA4() {
        return _s.getShort(0xA4);
    }

    public void setUnknownA4(short value) {
        _s.setShort(0xA4, value);
    }

    public short getUnknownA6() {
        return _s.getShort(0xA6);
    }

    public void setUnknownA6(short value) {
        _s.setShort(0xA6, value);
    }

    public short getUnknownAC() {
        return _s.getShort(0xAC);
    }

    public void setUnknownAC(short value) {
        _s.setShort(0xAC, value);
    }

    public short getUnknownAE() {
        return _s.getShort(0xAE);
    }

    public void setUnknownAE(short value) {
        _s.setShort(0xAE, value);
    }

    public byte getUnknownB6() {
        return _s.get(0xB6);
    }

    public void setUnknownB6(byte value) {
        _s.set(0xB6, value);
    }

    public byte getUnknownB7() {
        return _s.get(0xB7);
    }

    public void setUnknownB7(byte value) {
        _s.set(0xB7, value);
    }

    public float getCameraYMelee() {
        return _s.getFloat(0xB8);
    }

    public void setCameraYMelee(float value) {
        _s.setFloat(0xB8, value);
    }

    public float getCameraYShot() {
        return _s.getFloat(0xBC);
    }

    public void setCameraYShot(float value) {
        _s.setFloat(0xBC, value);
    }

    public float getCameraZMeleeMin() {
        return _s.getFloat(0xC0);
    }

    public void setCameraZMeleeMin(float value) {
        _s.setFloat(0xC0, value);
    }

    public float getCameraZMeleeMax() {
        return _s.getFloat(0xC4);
    }

    public void setCameraZMeleeMax(float value) {
        _s.setFloat(0xC4, value);
    }

    public float getCameraZShotMin() {
        return _s.getFloat(0xC8);
    }

    public void setCameraZShotMin(float value) {
        _s.setFloat(0xC8, value);
    }

    public float getCameraZShotMax() {
        return _s.getFloat(0xCC);
    }

    public void setCameraZShotMax(float value) {
        _s.setFloat(0xCC, value);
    }

    public float getCameraYMeleeRotateFar() {
        return _s.getFloat(0xD0);
    }

    public void setCameraYMeleeRotateFar(float value) {
        _s.setFloat(0xD0, value);
    }

    public float getCameraYMeleeRotateClose() {
        return _s.getFloat(0xD4);
    }

    public void setCameraYMeleeRotateClose(float value) {
        _s.setFloat(0xD4, value);
    }

    public float getCameraYShotRotateFar() {
        return _s.getFloat(0xD8);
    }

    public void setCameraYShotRotateFar(float value) {
        _s.setFloat(0xD8, value);
    }

    public float getCameraYShotRotateClose() {
        return _s.getFloat(0xDC);
    }

    public void setCameraYShotRotateClose(float value) {
        _s.setFloat(0xDC, value);
    }

    public float getCameraYMeleeGlideRotateFar() {
        return _s.getFloat(0xE0);
    }

    public void setCameraYMeleeGlideRotateFar(float value) {
        _s.setFloat(0xE0, value);
    }

    public float getCameraYMeleeGlideRotateClose() {
        return _s.getFloat(0xE4);
    }

    public void setCameraYMeleeGlideRotateClose(float value) {
        _s.setFloat(0xE4, value);
    }

    public float getCameraYShotGlideRotateFar() {
        return _s.getFloat(0xE8);
    }

    public void setCameraYShotGlideRotateFar(float value) {
        _s.setFloat(0xE8, value);
    }

    public float getCameraYShotGlideRotateClose() {
        return _s.getFloat(0xEC);
    }

    public void setCameraYShotGlideRotateClose(float value) {
        _s.setFloat(0xEC, value);
    }

    public float getFrontStepSpeedStart() {
        return _s.getFloat(0xF0);
    }

    public void setFrontStepSpeedStart(float value) {
        _s.setFloat(0xF0, value);
    }

    public float getFrontStepAcceleration() {
        return _s.getFloat(0xF4);
    }

    public void setFrontStepAcceleration(float value) {
        _s.setFloat(0xF4, value);
    }

    public float getFrontStepSpeedMin() {
        return _s.getFloat(0xF8);
    }

    public void setFrontStepSpeedMin(float value) {
        _s.setFloat(0xF8, value);
    }

    public float getFrontStepExtension() {
        return _s.getFloat(0xFC);
    }

    public void setFrontStepExtension(float value) {
        _s.setFloat(0xFC, value);
    }

    public float getSideStepSpeedStart() {
        return _s.getFloat(0x100);
    }

    public void setSideStepSpeedStart(float value) {
        _s.setFloat(0x100, value);
    }

    public float getSideStepAcceleration() {
        return _s.getFloat(0x104);
    }

    public void setSideStepAcceleration(float value) {
        _s.setFloat(0x104, value);
    }

    public float getSideStepSpeedMin() {
        return _s.getFloat(0x108);
    }

    public void setSideStepSpeedMin(float value) {
        _s.setFloat(0x108, value);
    }

    public float getSideStepExtension() {
        return _s.getFloat(0x10C);
    }

    public void setSideStepExtension(float value) {
        _s.setFloat(0x10C, value);
    }

    public float getBackStepSpeedStart() {
        return _s.getFloat(0x110);
    }

    public void setBackStepSpeedStart(float value) {
        _s.setFloat(0x110, value);
    }

    public float getBackStepAcceleration() {
        return _s.getFloat(0x114);
    }

    public void setBackStepAcceleration(float value) {
        _s.setFloat(0x114, value);
    }

    public float getBackStepSpeedMin() {
        return _s.getFloat(0x118);
    }

    public void setBackStepSpeedMin(float value) {
        _s.setFloat(0x118, value);
    }

    public float getBackStepExtension() {
        return _s.getFloat(0x11C);
    }

    public void setBackStepExtension(float value) {
        _s.setFloat(0x11C, value);
    }

    public float getTargetRangeInner() {
        return _s.getFloat(0x120);
    }

    public void setTargetRangeInner(float value) {
        _s.setFloat(0x120, value);
    }

    public float getTargetRangeOuter() {
        return _s.getFloat(0x124);
    }

    public void setTargetRangeOuter(float value) {
        _s.setFloat(0x124, value);
    }

    public byte getUnknown128() {
        return _s.get(0x128);
    }

    public void setUnknown128(byte value) {
        _s.set(0x128, value);
    }

    public byte getUnknown129() {
        return _s.get(0x129);
    }
    
    public void setUnknown129(byte value) {
        _s.set(0x129, value);
    }
    
    public byte getUnknown12A() {
        return _s.get(0x12A);
    }
    
    public void setUnknown12A(byte value) {
        _s.set(0x12A, value);
    }
    
    public byte getUnknown12B() {
        return _s.get(0x12B);
    }
    
    public void setUnknown12B(byte value) {
        _s.set(0x12B, value);
    }
    
    public float getInnerRangeSideStepRotation() {
        return _s.getFloat(0x12C);
    }
    
    public void setInnerRangeSideStepRotation(float value) {
        _s.setFloat(0x12C, value);
    }
    
    public float getOuterRangeSideStepRotation() {
        return _s.getFloat(0x130);
    }
    
    public void setOuterRangeSideStepRotation(float value) {
        _s.setFloat(0x130, value);
    }
    
    public float getHomingRangeShotGround() {
        return _s.getFloat(0x134);
    }
    
    public void setHomingRangeShotGround(float value) {
        _s.setFloat(0x134, value);
    }
    
    public float getHomingRangeShotAir() {
        return _s.getFloat(0x138);
    }
    
    public void setHomingRangeShotAir(float value) {
        _s.setFloat(0x138, value);
    }
    
    public float getHomingRangeShotGlide() {
        return _s.getFloat(0x13C);
    }
    
    public void setHomingRangeShotGlide(float value) {
        _s.setFloat(0x13C, value);
    }
    
    public float getHomingRangeMeleeGround() {
        return _s.getFloat(0x140);
    }
    
    public void setHomingRangeMeleeGround(float value) {
        _s.setFloat(0x140, value);
    }
    
    public float getHomingRangeMeleeAir() {
        return _s.getFloat(0x144);
    }
    
    public void setHomingRangeMeleeAir(float value) {
        _s.setFloat(0x144, value);
    }
    
    public float getHomingRangeMeleeGlide() {
        return _s.getFloat(0x148);
    }
    
    public void setHomingRangeMeleeGlide(float value) {
        _s.setFloat(0x148, value);
    }
    
    public float getHomingRangeSpecialGround() {
        return _s.getFloat(0x14C);
    }
    
    public void setHomingRangeSpecialGround(float value) {
        _s.setFloat(0x14C, value);
    }
    
    public float getHomingRangeSpecialAir() {
        return _s.getFloat(0x150);
    }
    
    public void setHomingRangeSpecialAir(float value) {
        _s.setFloat(0x150, value);
    }
    
    public float getHomingRangeSpecialGlide() {
        return _s.getFloat(0x154);
    }
    
    public void setHomingRangeSpecialGlide(float value) {
        _s.setFloat(0x154, value);
    }
    
    public byte getUnknown158() {
        return _s.get(0x158);
    }
    
    public void setUnknown158(byte value) {
        _s.set(0x158, value);
    }
    
    public byte getUnknown159() {
        return _s.get(0x159);
    }
    
    public void setUnknown159(byte value) {
        _s.set(0x159, value);
    }
    
    public byte getUnknown15A() {
        return _s.get(0x15A);
    }
    
    public void setUnknown15A(byte value) {
        _s.set(0x15A, value);
    }
    
    public byte getUnknown15B() {
        return _s.get(0x15B);
    }
    
    public void setUnknown15B(byte value) {
        _s.set(0x15B, value);
    }
    
    public short getLookRotateLeft1() {
        return _s.getShort(0x15C);
    }
    
    public void setLookRotateLeft1(short value) {
        _s.setShort(0x15C, value);
    }
    
    public short getLookRotateRightDivisor() {
        return _s.getShort(0x15E);
    }
    
    public void setLookRotateRightDivisor(short value) {
        _s.setShort(0x15E, value);
    }
    
    public short getLookRotateUp2() {
        return _s.getShort(0x160);
    }
    
    public void setLookRotateUp2(short value) {
        _s.setShort(0x160, value);
    }
    
    public short getLookRotateDown2() {
        return _s.getShort(0x162);
    }
    
    public void setLookRotateDown2(short value) {
        _s.setShort(0x162, value);
    }
    
    public short getLookRotateLeft2() {
        return _s.getShort(0x164);
    }
    
    public void setLookRotateLeft2(short value) {
        _s.setShort(0x164, value);
    }
    
    public short getLookRotateRightMultiplier() {
        return _s.getShort(0x166);
    }
    
    public void setLookRotateRightMultiplier(short value) {
        _s.setShort(0x166, value);
    }
    
    public byte getUnknown168() {
        return _s.get(0x168);
    }
    
    public void setUnknown168(byte value) {
        _s.set(0x168, value);
    }
    
    public byte getUnknown169() {
        return _s.get(0x169);
    }
    
    public byte getUnknown16A() {
        return _s.get(0x16A);
    }
    
    public void setUnknown16A(byte value) {
        _s.set(0x16A, value);
    }

    public byte getUnknown16B() {
        return _s.get(0x16B);
    }
    
    public void setUnknown16B(byte value) {
        _s.set(0x16B, value);
    }
    
    public byte getUnknown16C() {
        return _s.get(0x16C);
    }
    
    public void setUnknown16C(byte value) {
        _s.set(0x16C, value);
    }
    
    public byte getUnknown16D() {
        return _s.get(0x16D);
    }
    
    public void setUnknown16D(byte value) {
        _s.set(0x16D, value);
    }
    
    public byte getUnknown16E() {
        return _s.get(0x16E);
    }
    
    public void setUnknown16E(byte value) {
        _s.set(0x16E, value);
    }
    
    public byte getUnknown16F() {
        return _s.get(0x16F);
    }
    
    public void setUnknown16F(byte value) {
        _s.set(0x16F, value);
    }
    
    public byte getUnknown170() {
        return _s.get(0x170);
    }
    
    public void setUnknown170(byte value) {
        _s.set(0x170, value);
    }
    
    public byte getUnknown171() {
        return _s.get(0x171);
    }
    
    public void setUnknown171(byte value) {
        _s.set(0x171, value);
    }
    
    public byte getUnknown172() {
        return _s.get(0x172);
    }
    
    public void setUnknown172(byte value) {
        _s.set(0x172, value);
    }
    
    public byte getUnknown173() {
        return _s.get(0x173);
    }
    
    public void setUnknown173(byte value) {
        _s.set(0x173, value);
    }
    
    public byte getUnknown174() {
        return _s.get(0x174);
    }
    
    public void setUnknown174(byte value) {
        _s.set(0x174, value);
    }
    
    public byte getUnknown175() {
        return _s.get(0x175);
    }
    
    public void setUnknown175(byte value) {
        _s.set(0x175, value);
    }
    
    public byte getUnknown176() {
        return _s.get(0x176);
    }
    
    public void setUnknown176(byte value) {
        _s.set(0x176, value);
    }
    
    public byte getUnknown177() {
        return _s.get(0x177);
    }
    
    public void setUnknown177(byte value) {
        _s.set(0x177, value);
    }
    
    public byte getUnknown178() {
        return _s.get(0x178);
    }
    
    public void setUnknown178(byte value) {
        _s.set(0x178, value);
    }
    
    public byte getUnknown179() {
        return _s.get(0x179);
    }
    
    public void setUnknown179(byte value) {
        _s.set(0x179, value);
    }
    
    public byte getUnknown17A() {
        return _s.get(0x17A);
    }
    
    public void setUnknown17A(byte value) {
        _s.set(0x17A, value);
    }
    
    public byte getUnknown17B() {
        return _s.get(0x17B);
    }
    
    public void setUnknown17B(byte value) {
        _s.set(0x17B, value);
    }
    
    public byte getUnknown17C() {
        return _s.get(0x17C);
    }
    
    public void setUnknown17C(byte value) {
        _s.set(0x17C, value);
    }
    
    public byte getUnknown17D() {
        return _s.get(0x17D);
    }
    
    public void setUnknown17D(byte value) {
        _s.set(0x17D, value);
    }
    
    public byte getUnknown17E() {
        return _s.get(0x17E);
    }
    
    public void setUnknown17E(byte value) {
        _s.set(0x17E, value);
    }
    
    public byte getUnknown17F() {
        return _s.get(0x17F);
    }
    
    public void setUnknown17F(byte value) {
        _s.set(0x17F, value);
    }
    
    public byte getUnknown180() {
        return _s.get(0x180);
    }
    
    public void setUnknown180(byte value) {
        _s.set(0x180, value);
    }
    
    public byte getUnknown181() {
        return _s.get(0x181);
    }
    
    public void setUnknown181(byte value) {
        _s.set(0x181, value);
    }
    
    public byte getUnknown182() {
        return _s.get(0x182);
    }
    
    public void setUnknown182(byte value) {
        _s.set(0x182, value);
    }
    
    public byte getVoiceType() {
        return _s.get(0x183);
    }
    
    public void setVoiceType(byte value) {
        _s.set(0x183, value);
    }
    
    public float getChargeGaugeShot() {
        return _s.getFloat(0x184);
    }
    
    public void setChargeGaugeShot(float value) {
        _s.setFloat(0x184, value);
    }
    
    public float getChargeGaugeMelee() {
        return _s.getFloat(0x188);
    }
    
    public void setChargeGaugeMelee(float value) {
        _s.setFloat(0x188, value);
    }
    
    public float getChargeGaugeSpecial() {
        return _s.getFloat(0x18C);
    }
    
    public void setChargeGaugeSpecial(float value) {
        _s.setFloat(0x18C, value);
    }
    
    public float getVictoryCameraY() {
        return _s.getFloat(0x190);
    }
    
    public void setVictoryCameraY(float value) {
        _s.setFloat(0x190, value);
    }
    
    public float getVictoryCameraZMax() {
        return _s.getFloat(0x194);
    }
    
    public void setVictoryCameraZMax(float value) {
        _s.setFloat(0x194, value);
    }
    
    public float getVictoryCameraZMin() {
        return _s.getFloat(0x198);
    }
    
    public void setVictoryCameraZMin(float value) {
        _s.setFloat(0x198, value);
    }
    
    public byte getUnknown19C() {
        return _s.get(0x19C);
    }
    
    public void setUnknown19C(byte value) {
        _s.set(0x19C, value);
    }
    
    public byte getUnknown19D() {
        return _s.get(0x19D);
    }
    
    public void setUnknown19D(byte value) {
        _s.set(0x19D, value);
    }
    
    public byte getUnknown19E() {
        return _s.get(0x19E);
    }
    
    public void setUnknown19E(byte value) {
        _s.set(0x19E, value);
    }
    
    public byte getUnknown19F() {
        return _s.get(0x19F);
    }
    
    public void setUnknown19F(byte value) {
        _s.set(0x19F, value);
    }
    
    public byte getSpecType() {
        return _s.get(0x1A0);
    }
    
    public void setSpecType(byte value) {
        _s.set(0x1A0, value);
    }
    
    public byte getSpecIcon() {
        return _s.get(0x1A1);
    }
    
    public void setSpecIcon(byte value) {
        _s.set(0x1A1, value);
    }
    
    public byte getSpecIconShieldOverride() {
        return _s.get(0x1A2);
    }
    
    public void setSpecIconShieldOverride(byte value) {
        _s.set(0x1A2, value);
    }
    
    public byte getSpecJumpType() {
        return _s.get(0x1A3);
    }
    
    public void setSpecJumpType(byte value) {
        _s.set(0x1A3, value);
    }
    
    public byte getSpecDefense() {
        return _s.get(0x1A4);
    }
    
    public void setSpecDefense(byte value) {
        _s.set(0x1A4, value);
    }
    
    public byte getSpecShot() {
        return _s.get(0x1A5);
    }
    
    public void setSpecShot(byte value) {
        _s.set(0x1A5, value);
    }
    
    public byte getSpecAttack() {
        return _s.get(0x1A6);
    }
    
    public void setSpecAttack(byte value) {
        _s.set(0x1A6, value);
    }
    
    public byte getSpecSpeed() {
        return _s.get(0x1A7);
    }
    
    public void setSpecSpeed(byte value) {
        _s.set(0x1A7, value);
    }
    
    public byte getSpecIconAttack1() {
        return _s.get(0x1A8);
    }
    
    public void setSpecIconAttack1(byte value) {
        _s.set(0x1A8, value);
    }
    
    public byte getSpecIconAttack2() {
        return _s.get(0x1A9);
    }
    
    public void setSpecIconAttack2(byte value) {
        _s.set(0x1A9, value);
    }
    
    public byte getSpecIconAttack3() {
        return _s.get(0x1AA);
    }
    
    public void setSpecIconAttack3(byte value) {
        _s.set(0x1AA, value);
    }
    
    public byte getSpecIconAttack4() {
        return _s.get(0x1AB);
    }
    
    public void setSpecIconAttack4(byte value) {
        _s.set(0x1AB, value);
    }
    
    public byte getSpecIconAttack5() {
        return _s.get(0x1AC);
    }
    
    public void setSpecIconAttack5(byte value) {
        _s.set(0x1AC, value);
    }
    
    public byte getSpecTribe() {
        return _s.get(0x1AD);
    }
    
    public void setSpecTribe(byte value) {
        _s.set(0x1AD, value);
    }    
}
