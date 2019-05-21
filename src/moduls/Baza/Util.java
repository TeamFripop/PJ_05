package moduls.Baza;

import java.nio.ByteBuffer;
import java.util.UUID;

public class Util {
    public static byte[] generateUuid()
    {
        return uuidToBinary(UUID.randomUUID());
    }
    public static byte[] uuidToBinary(UUID uuid)
    {
        byte[] bytes = new byte[16];
        ByteBuffer.wrap(bytes).putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits());
        return bytes;
    }
    public static UUID binaryToUuid(byte[] data)
    {
        ByteBuffer bytes = ByteBuffer.wrap(data);
        Long start = bytes.getLong();
        Long end = bytes.getLong();
        return new UUID(start, end);
    }
}
