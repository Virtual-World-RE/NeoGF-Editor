package com.crystalpixel.neogfutils.game.Graphics.GX;

import org.lwjgl.opengl.GL15;

import com.crystalpixel.neogfutils.game.Graphics.GX.Enums.GXBlendFactor;
import com.crystalpixel.neogfutils.game.Graphics.GX.Enums.GXBlendMode;
import com.crystalpixel.neogfutils.game.Graphics.GX.Enums.GXCompareType;
import com.crystalpixel.neogfutils.game.Graphics.GX.Enums.GXPrimitiveType;
import com.crystalpixel.neogfutils.game.Graphics.GX.Enums.GXTexFilter;
import com.crystalpixel.neogfutils.game.Graphics.GX.Enums.GXWrapMode;
import com.crystalpixel.neogfutils.utils.math.vector.Vector2f;
import com.crystalpixel.neogfutils.utils.math.vector.Vector3f;
import com.crystalpixel.neogfutils.utils.math.vector.Vector4f;

public class GXTranslator {

    public static Vector4f toVector4(GXColor4 v) {
        return new Vector4f(v.R, v.G, v.B, v.A);
    }

    public static Vector4f toVector4(GXVector4 v) {
        return new Vector4f(v.X, v.Y, v.Z, v.W);
    }

    public static Vector2f toVector2(GXVector2 v) {
        return new Vector2f(v.X, v.Y);
    }

    public static Vector3f toVector3(GXVector3 v) {
        return new Vector3f(v.X, v.Y, v.Z);
    }

    public static GXColor4 fromColor4(Vector4f v) {
        return new GXColor4(v.x, v.y, v.z, v.w);
    }

    public static GXVector4 fromVector4(Vector4f v) {
        return new GXVector4(v.x, v.y, v.z, v.w);
    }

    public static GXVector2 fromVector2(Vector2f v) {
        return new GXVector2(v.x, v.y);
    }

    public static GXVector3 fromVector3(Vector3f v) {
        return new GXVector3(v.x, v.y, v.z);
    }

    public static int toMagFilter(GXTexFilter filter) {
        switch (filter) {
            case GX_NEAR:
                return GL15.GL_NEAREST;
            case GX_LINEAR:
                return GL15.GL_LINEAR;
            default:
                return GL15.GL_NEAREST;
        }
    }

    public static int toPrimitiveType(GXPrimitiveType pt) {
        switch (pt) {
            case Quads:
                return GL15.GL_QUADS;
            case Triangles:
                return GL15.GL_TRIANGLES;
            case TriangleStrip:
                return GL15.GL_TRIANGLE_STRIP;
            case TriangleFan:
                return GL15.GL_TRIANGLE_FAN;
            case Lines:
                return GL15.GL_LINES;
            case LineStrip:
                return GL15.GL_LINE_STRIP;
            case Points:
                return GL15.GL_POINTS;
            default:
                return GL15.GL_TRIANGLES;
        }
    }

    public static int toWrapMode(GXWrapMode wm) {
        switch (wm) {
            case CLAMP:
                return GL15.GL_CLAMP_TO_EDGE;
            case MIRROR:
                return GL15.GL_MIRRORED_REPEAT;
            case REPEAT:
                return GL15.GL_REPEAT;
        }
        return GL15.GL_REPEAT;
    }

    public static int toAlphaFunction(GXCompareType type) {
        switch (type) {
            case Always:
                return GL15.GL_ALWAYS;
            case Equal:
                return GL15.GL_EQUAL;
            case GEqual:
                return GL15.GL_GEQUAL;
            case Greater:
                return GL15.GL_GREATER;
            case LEqual:
                return GL15.GL_LEQUAL;
            case Less:
                return GL15.GL_LESS;
            case NEqual:
                return GL15.GL_NOTEQUAL;
            case Never:
                return GL15.GL_NEVER;
            default:
                return GL15.GL_NEVER;
        }
    }

    public static int toBlendMode(GXBlendMode mode) {
        switch (mode) {
            case GX_BLEND:
                return GL15.GL_FUNC_ADD;
            case GX_LOGIC:
                return GL15.GL_FUNC_SUBTRACT;
            case GX_SUBTRACT:
                return GL15.GL_FUNC_SUBTRACT;
            default:
                return GL15.GL_FUNC_ADD;
        }
    }

    public static int toBlendingFactor(GXBlendFactor factor) {
        switch (factor) {
            case GX_BL_ONE:
                return GL15.GL_ONE;
            case GX_BL_SRCALPHA:
                return GL15.GL_SRC_ALPHA;
            case GX_BL_SRCCLR:
                return GL15.GL_SRC_COLOR;
            case GX_BL_ZERO:
                return GL15.GL_ZERO;
            case GX_BL_INVSRCCLR:
                return GL15.GL_ONE_MINUS_SRC_COLOR;
            case GX_BL_INVSRCALPHA:
                return GL15.GL_ONE_MINUS_SRC_ALPHA;
            case GX_BL_INVDSTALPHA:
                return GL15.GL_ONE_MINUS_DST_ALPHA;
            default:
                return GL15.GL_SRC_ALPHA;
        }
    }

    public static int toDepthFunction(GXCompareType type) {
        switch (type) {
            case Always:
                return GL15.GL_ALWAYS;
            case Equal:
                return GL15.GL_EQUAL;
            case GEqual:
                return GL15.GL_GEQUAL;
            case Greater:
                return GL15.GL_GREATER;
            case LEqual:
                return GL15.GL_LEQUAL;
            case Less:
                return GL15.GL_LESS;
            case NEqual:
                return GL15.GL_NOTEQUAL;
            case Never:
                return GL15.GL_NEVER;
        }
        return GL15.GL_NEVER;
    }

    public static int toBlendEquationMode(GXBlendMode mode) {
        switch (mode) {
            case GX_BLEND:
                return GL15.GL_FUNC_ADD;
            case GX_SUBTRACT:
                return GL15.GL_FUNC_SUBTRACT;
            case GX_LOGIC:
                return GL15.GL_FUNC_REVERSE_SUBTRACT;
            case GX_NONE:
                return GL15.GL_NONE;
        }
        return GL15.GL_FUNC_ADD;
    }
}
