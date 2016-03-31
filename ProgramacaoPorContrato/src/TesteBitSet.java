import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by 12111151 on 3/10/16.
 */
public class TesteBitSet {
    public static void main (String[] args){
        BitSet bitSet = new BitSet();

        bitSet.set(14);
//        bitSet.set();
        
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i+1)) {
            System.out.println("index = " + i);
            System.out.println(bitSet.get(i));
        }

//        System.out.println(bitSet.nextSetBit(2));
    }

}
