package com.crystalpixel.neogfutils.utils.text;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.crystalpixel.neogfutils.utils.Utils;

public class TextUtils {

    private static final int STORY_TEXT_START_ADDRESS = 0x8F33C0;
    private static final Map<Integer, String> storyTextMap = new HashMap<>();

    public static String getStoryText(int index) throws IOException {
        if (!storyTextMap.containsKey(index)) {
            storyTextMap.put(index, readText(getTextAddress(index)));
        }
        return storyTextMap.get(index);
    }

    private static int getTextAddress(int index) throws IOException {
        try (RandomAccessFile raf = Utils.getRaf()) {
            raf.seek(STORY_TEXT_START_ADDRESS + index * 4);
            byte[] magic = new byte[4];
            raf.readFully(magic);
            return ByteBuffer.wrap(magic).getInt();
        }
    }

    private static String readText(int startAddress) throws IOException {
        try (RandomAccessFile raf = Utils.getRaf()) {
            raf.seek(6 + startAddress & 0x00FFFFFF);
            StringBuilder text = new StringBuilder();
            while (true) {
                byte[] magic = new byte[2];
                raf.readFully(magic);
                int halfword = ByteBuffer.wrap(magic).getShort() & 0xFFFF;
                if (halfword == 0x1001) break;
                if (halfword == 0x1000) {
                    text.append('\n');
                    continue;
                }
                int type = halfword & 0xF000;
                if (type == 0x8000) {
                    // apply colour
                    int effect = halfword & 0x7FFF;
                    switch (effect) {
                        case 0: text.append("[Player]"); break;
                        case 2: raf.readFully(magic); break;
                        case 1:
                        case 3: break;
                    }
                } else if (type == 0xF000) {
                    if (halfword == 0xFFFD) {}
                    text.append(' ');
                } else {
                    text.append(getStoryTextChar(halfword & 0x7FFF));
                }
            }
            return text.toString().trim();
        }
    }

    private static char getStoryTextChar(int index) {
        char[] textChars = new char[] {
                '`', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', ',', '“',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', ':', ';', '’', '-',
                '!', '?', '‘', '%', '&', '*', '(', ')', '/', '@',
                '#', '+', '=', '÷', '•', '_', '<', '>', '×', 'Á',
                'É', 'Í', 'Ó', 'Ú', 'á', 'é', 'í', 'ó', 'ú', 'À',
                'È', 'Ì', 'Ò', 'Ù', 'à', 'è', 'ì', 'ò', 'ù', 'Â',
                'Ê', 'Î', 'Ô', 'Û', 'â', 'ê', 'î', 'ô', 'û', 'Ä',
                'Ë', 'Ï', 'Ö', 'Ü', 'ä', 'ë', 'ï', 'ö', 'ü', 'Ñ',
                'Ç', 'Æ', 'ß', 'ñ', 'ç', 'æ', '¿', '¡', 'Œ', 'œ',
                '”'
        };
        return index < textChars.length ? textChars[index] : ' ';
    }

    private static char getBorgTextChar(int index) {
        char[] textChars = new char[] {
                '`', 'G', 'R', 'e', 'd', 'i', 's', 'y', 'o', 'u',
                'r', 'p', 'a', 't', 'n', '.', 'H', 'f', 'g', 'h',
                'w', 'l', 'c', 'E', 'm', '’', 'v', '!', 'b', ',',
                'F', 'C', 'A', 'S', 'K', 'k', 'j', 'T', 'U', 'M',
                'J', 'I', 'N', 'B', 'V', '-', '0', 'x', 'L', 'P',
                'D', ';', 'Z', 'W', 'Y', '&', 'q', 'z', '“', 'X',
                'Q', '(', '+', ')', '6', '3', '?', '4', '/', '0',
                '1', '2', '5', '7', '8', '9', ':', '‘', '%', '*',
                '@', '#', '=', '÷', '•', '_', '<', '>', '×', 'Á',
                'É', 'Í', 'Ó', 'Ú', 'á', 'é', 'í', 'ó', 'ú', 'À',
                'È', 'Ì', 'Ò', 'Ù', 'à', 'è', 'ì', 'ò', 'ù', 'Â',
                'Ê', 'Î', 'Ô', 'Û', 'â', 'ê', 'î', 'ô', 'û', 'Ä',
                'Ë', 'Ï', 'Ö', 'Ü', 'ä', 'ë', 'ï', 'ö', 'ü', 'Ñ',
                'Ç', 'Æ', 'ß', 'ñ', 'ç', 'æ', '¿', '¡', 'Œ', 'œ',
        };
        return textChars.length > index ? textChars[index] : ' ';
    }
}
