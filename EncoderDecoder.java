
import java.util.HashMap;
import java.security.SecureRandom;

public class EncoderDecoder {
    private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    private HashMap<Character, Integer> referenceMap; // A:0, B:1, C:2, D:3 ... /:43

    public EncoderDecoder()
	{
        referenceMap = new HashMap<Character, Integer>();
        for (int i = 0; i < REFERENCE_TABLE.length(); i++)
		{
            referenceMap.put(REFERENCE_TABLE.charAt(i), i);
        }
    }
	
	public String encode(String plainText)
	{
		char nullChar = '\u0000';
		String result = encode(plainText, nullChar);
		return result;
	}
	
    public String encode(String plainText, char offsetChar)
	{
		if (offsetChar == '\u0000')
		{
			SecureRandom secureRandom = new SecureRandom();
			int randomInt = secureRandom.nextInt(REFERENCE_TABLE.length() + 1);
			offsetChar = REFERENCE_TABLE.charAt(randomInt);
		}
		
		
        StringBuilder encodedText = new StringBuilder();
        int offset = referenceMap.get(offsetChar);

        // add the offset character as the first character of the encoded message
        encodedText.append(offsetChar);

        // iterate through the plain text and encode each character
        for (int i = 0; i < plainText.length(); i++)
		{
            char c = plainText.charAt(i);
            int index = referenceMap.getOrDefault(c, -1);

            // if the character is not in the reference table, add it to the encoded message as is
            if (index == -1)
			{
                encodedText.append(c);
            }
			else 
			{
                // apply the negative offset to the index to get the encoded character
                int encodedIndex = (index - offset + REFERENCE_TABLE.length()) % REFERENCE_TABLE.length();
                encodedText.append(REFERENCE_TABLE.charAt(encodedIndex));
            }
        }

        return encodedText.toString();
    }

    public String decode(String encodedText)
	{
        char offsetChar = encodedText.charAt(0);
        int offset = referenceMap.get(offsetChar);
        StringBuilder plainText = new StringBuilder();

        // iterate through the encoded message and decode each character
        for (int i = 1; i < encodedText.length(); i++)
		{
            char c = encodedText.charAt(i);
            int index = referenceMap.getOrDefault(c, -1);

            // if the character is not in the reference table, add it to the plain text as is
            if (index == -1)
			{
                plainText.append(c);
            }
			else
			{
                // apply the offset to the index to get the original character
                int originalIndex = (index + offset) % REFERENCE_TABLE.length();
                plainText.append(REFERENCE_TABLE.charAt(originalIndex));
            }
        }
        return plainText.toString();
    }
	
	public static void main(String args[])
	{
		EncoderDecoder ed = new EncoderDecoder();
		
		String encoded = ed.encode("HELLO WORLD", 'B');
		System.out.println(encoded);
		
		String decoded = ed.decode(encoded);
		System.out.println(decoded);
	}
}
